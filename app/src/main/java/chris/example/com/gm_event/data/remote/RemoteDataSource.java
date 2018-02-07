package chris.example.com.gm_event.data.remote;

import chris.example.com.gm_event.model.EventsResponse;
import chris.example.com.gm_event.util.Constants;
import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chris on 2/6/2018.
 */

public class RemoteDataSource
{
    private static String baseURL;
    
    public RemoteDataSource(String baseURL)
    {
        this.baseURL = baseURL;
    }
    
    public static Retrofit create()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                //add converter to parse the response
                .addConverterFactory(GsonConverterFactory.create())
                //add call adapter to convert the response to RxJava observable
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        
        return retrofit;
    }
    
    public static Observable<EventsResponse> getEvents(String token, String latitude, String longitude)
    {
        Retrofit retrofit = create();
        RemoteService remoteService = retrofit.create(RemoteService.class);
        return remoteService.getEvents(latitude, longitude, token);
    }
}
