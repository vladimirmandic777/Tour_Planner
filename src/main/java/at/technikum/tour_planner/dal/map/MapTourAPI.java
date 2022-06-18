package at.technikum.tour_planner.dal.map;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MapTourAPI {


    /*
    start und end muss verändert werden
     */
    @GET("start=New+York,NY&end=Washington,DC&size=600,400@2x")
    Call<MapTour> queryMap();

}
