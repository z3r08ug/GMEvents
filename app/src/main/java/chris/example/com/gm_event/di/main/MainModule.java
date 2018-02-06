package chris.example.com.gm_event.di.main;

import chris.example.com.gm_event.data.remote.RemoteDataSource;
import chris.example.com.gm_event.view.main.MainPresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by chris on 2/6/2018.
 */
@Module
public class MainModule
{
    @Provides
    MainPresenter providerMainPresenter(RemoteDataSource remoteDataSource)
    {
        return new MainPresenter(remoteDataSource);
    }
}
