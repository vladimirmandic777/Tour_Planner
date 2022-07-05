package at.technikum.tour_planner.dal.map;

import at.technikum.tour_planner.configuration.PropertyConfigurationReader;
import at.technikum.tour_planner.logger.ILoggerWrapper;
import at.technikum.tour_planner.logger.LoggerFactory;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;


public class MapRouteRepository implements MapRouteRepositoryAPI {

    //private MapTourAPI mapTourAPI;
    private static final ILoggerWrapper logger = LoggerFactory.getLogger(MapRouteRepository.class);
    private Route result;
    private InputStream resultMap;
    private RouteAPI routeTourAPI;
    private final String from;
    private final String to;
    private final String ApiKey;


    public MapRouteRepository(String from, String to) throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.mapquestapi.com/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        this.routeTourAPI = retrofit.create(RouteAPI.class);
        this.from = from;
        this.to = to;
        PropertyConfigurationReader prop = new PropertyConfigurationReader();

        this.ApiKey = prop.getAppConfiguration().getApiKey();
        //split in every method next code:
        //this.result = this.routeTourAPI.queryRoute(from, to).execute().body();



    }
    @Override
    public Map<String, Map<String, Double>> getBoundingBox() throws IOException {
        this.result = this.routeTourAPI.queryRoute(this.ApiKey, from, to).execute().body();
        return result.getRoute().getBoundingBox();
    }
    @Override
    public String getDistance() throws IOException {
        this.result = this.routeTourAPI.queryRoute(this.ApiKey, from, to).execute().body();
        return result.getRoute().getDistance();
    }
    @Override
    public String getTime() throws IOException {
        this.result = this.routeTourAPI.queryRoute(this.ApiKey, from, to).execute().body();
        return result.getRoute().getFormattedTime();
    }
    @Override
    public String getSessionId() throws IOException {
        this.result = this.routeTourAPI.queryRoute(this.ApiKey, from, to).execute().body();
        return result.getRoute().getSessionId();
    }

    @Override
    public InputStream getMap() {

        String bb = null;
        try {
            bb = this.getBoundingBox().get("ul").get("lat") + "," + this.getBoundingBox().get("ul").get("lng") + "," + this.getBoundingBox().get("lr").get("lat") + "," + this.getBoundingBox().get("lr").get("lng");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logger.info("BoundingBox parameter: " + bb);
        Response<ResponseBody> responseMap = null;//, this.getBoundingBox()
        try {
            responseMap = this.routeTourAPI.queryMap(this.ApiKey, from, to, this.getSessionId(), bb).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.resultMap = responseMap.body().byteStream();
        return this.resultMap;
    }



}
