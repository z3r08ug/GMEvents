package chris.example.com.gm_event.di.app;

import chris.example.com.gm_event.di.details.DetailsComponent;
import chris.example.com.gm_event.di.details.DetailsModule;
import chris.example.com.gm_event.di.main.MainComponent;
import chris.example.com.gm_event.di.main.MainModule;
import dagger.Component;

/**
 * Created by chris on 2/6/2018.
 */

@Component(modules = AppModule.class)
public interface AppComponent
{
    MainComponent add(MainModule mainModule);
    DetailsComponent add(DetailsModule detailsModule);
}
