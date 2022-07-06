package at.technikum.tour_planner.dal.map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RouteAPI {

    @GET("directions/v2/route")
    Call<Route> queryRoute(@Query("key") String key, @Query("from") String from, @Query("to") String to);

    @GET("staticmap/v5/map?size=220,300@2x")
    Call<ResponseBody> queryMap(@Query("key") String key, @Query("start") String from, @Query("end") String to, @Query("sessionId") String sessionId, @Query("boundingBox") String boundingBox);


}
