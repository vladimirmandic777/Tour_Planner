package at.technikum.tour_planner.dal.map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RouteAPI {

        /*
        start und end muss verändert werden
        from=New+York,NY&to=Washington,DC
         */
        @GET("directions/v2/route?key=QDZwAjNR027Tm8E9iwx29cvLlYIQb1da")
        Call<Route> queryRoute(@Query("from") String from, @Query("to") String to);
        @GET("staticmap/v5/map?key=QDZwAjNR027Tm8E9iwx29cvLlYIQb1da")
        Call<ResponseBody> queryMap(@Query("start") String from, @Query("end") String to);
        //TODO: Add Bounding Box in parameters




}
