package chris.example.com.gm_event.di.main;


import chris.example.com.gm_event.view.main.MainActivity;
import dagger.Subcomponent;

/**
 * Created by chris on 2/6/2018.
 */
@Subcomponent(modules = MainModule.class)
public interface MainComponent
{
    void inject(MainActivity mainActivity);
}
