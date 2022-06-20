package at.technikum.tour_planner.dal.map;

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
    private final Route result;
    private final InputStream resultMap;
    private RouteAPI routeTourAPI;


    public MapRouteRepository(String from, String to) throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.mapquestapi.com/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        this.routeTourAPI = retrofit.create(RouteAPI.class);
        this.result = this.routeTourAPI.queryRoute(from, to).execute().body();

        Response<ResponseBody> responseMap = this.routeTourAPI.queryMap(from, to).execute();
        this.resultMap = responseMap.body().byteStream();

    }


    @Override
    public Map<String, Map<String, Double>> getBoundingBox() throws IOException {
        return result.getRoute().getBoundingBox();
    }
    @Override
    public String getDistance() throws IOException {
        return result.getRoute().getDistance();
    }
    @Override
    public String getTime() throws IOException {
        return result.getRoute().getFormattedTime();
    }
    @Override
    public String getSessionId() throws IOException {
        return result.getRoute().getSessionId();
    }

    @Override
    public InputStream getMap() {
        return this.resultMap;
    }



}
