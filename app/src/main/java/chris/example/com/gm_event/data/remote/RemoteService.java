package chris.example.com.gm_event.data.remote;

import chris.example.com.gm_event.model.EventsResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by chris on 2/6/2018.
 */

public interface RemoteService
{
    @GET("events/search/?token={token}&location.latitude={latitude}")
    Observable<EventsResponse> getEvents(@Path("token") String token, @Path("latitude") String latitude, @Path("longitude") String longitude);
}
