package at.technikum.tour_planner.dal.map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.Map;

public interface RouteAPI {

        /*
        start und end muss verändert werden
        from=New+York,NY&to=Washington,DC
         */
        @GET("directions/v2/route?key=QDZwAjNR027Tm8E9iwx29cvLlYIQb1da")
        Call<Route> queryRoute(@Query("from") String from, @Query("to") String to);
        @GET("staticmap/v5/map?key=QDZwAjNR027Tm8E9iwx29cvLlYIQb1da&size=220,300@2x")
        Call<ResponseBody> queryMap(@Query("start") String from, @Query("end") String to, @Query("sessionId") String sessionId, @Query("boundingBox") String boundingBox);




}
