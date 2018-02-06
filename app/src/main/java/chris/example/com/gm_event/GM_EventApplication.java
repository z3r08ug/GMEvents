package chris.example.com.gm_event;

import android.app.Application;
import android.content.Context;

import chris.example.com.gm_event.di.app.AppComponent;
import chris.example.com.gm_event.di.app.AppModule;
import chris.example.com.gm_event.di.app.DaggerAppComponent;
import chris.example.com.gm_event.di.main.MainComponent;
import chris.example.com.gm_event.di.main.MainModule;
import chris.example.com.gm_event.util.Constants;


/**
 * Created by chris on 1/15/2018.
 */

public class GM_EventApplication extends Application
{
    private AppComponent appComponent;
    private MainComponent mainComponent;
    
    @Override
    public void onCreate()
    {
        super.onCreate();
    
        AppModule appModule = new AppModule(Constants.BASE_URL);
        
        appComponent = DaggerAppComponent.builder()
                .appModule(appModule)
                .build();
    
    }
    
    public static GM_EventApplication get(Context context)
    {
        return (GM_EventApplication) context.getApplicationContext();
    }
    
    public MainComponent getMainComponent()
    {
        mainComponent = appComponent.add(new MainModule());
        return mainComponent;
    }
    
    public void clearMainComponent()
    {
        mainComponent = null;
    }
}
