package chris.example.com.gm_event.di.details;

import chris.example.com.gm_event.data.remote.RemoteDataSource;
import chris.example.com.gm_event.view.details.DetailsPresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by chris on 2/6/2018.
 */
@Module
public class DetailsModule
{
    @Provides
    DetailsPresenter providerDetailsPresenter(RemoteDataSource remoteDataSource)
    {
        return new DetailsPresenter(remoteDataSource);
    }
}
