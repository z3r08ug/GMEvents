package chris.example.com.gm_event.di.app;

import chris.example.com.gm_event.data.remote.RemoteDataSource;
import dagger.Module;
import dagger.Provides;

/**
 * Created by chris on 2/6/2018.
 */
@Module
public class AppModule
{
    String baseURL;
    
    public AppModule(String baseURL)
    {
        this.baseURL = baseURL;
    }
    
    @Provides
    RemoteDataSource providesRemoteDataSource()
    {
        return new RemoteDataSource(baseURL);
    }
}
