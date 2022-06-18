package at.technikum.tour_planner.dal.map;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


public class MapTourRepository implements MapTourRepositoryAPI {

    private final MapTourAPI mapTourAPI;

    public MapTourRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.mapquestapi.com/staticmap/v5/map?key=QDZwAjNR027Tm8E9iwx29cvLlYIQb1da/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        mapTourAPI = retrofit.create(MapTourAPI.class);
    }

    @Override
    public String queryMap() {
        return null;
    }
}
