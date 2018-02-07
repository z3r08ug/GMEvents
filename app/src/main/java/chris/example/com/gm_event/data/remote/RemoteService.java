package chris.example.com.gm_event.data.remote;

import chris.example.com.gm_event.model.EventsResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by chris on 2/6/2018.
 */

public interface RemoteService
{
    @GET("events/search/")
    Observable<EventsResponse> getEvents(@Query("location.latitude") String latitude, @Query("location.longitude") String longitude, @Query("token") String token);
    
//    @GET("events/search/?location.within=5mi&location.latitude=33.892352&location.longitude=-84.473927&token=RJIAO2HDTRE37F4CDHOF")
//    Observable<EventsResponse> getEvents();
}
