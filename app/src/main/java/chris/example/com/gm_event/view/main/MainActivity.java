package chris.example.com.gm_event.view.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import chris.example.com.gm_event.GM_EventApplication;
import chris.example.com.gm_event.R;
import chris.example.com.gm_event.model.Event;
import chris.example.com.gm_event.model.EventsResponse;
import chris.example.com.gm_event.util.Constants;
import chris.example.com.gm_event.util.EventsAdapter;

public class MainActivity extends AppCompatActivity implements MainContract.View
{
    public static final String TAG = MainActivity.class.getSimpleName() + "_TAG";
    @Inject
    MainPresenter presenter;
    private List<Event> events;
    private String latitude, longitude;
    private RecyclerView recyclerView;
    private EventsAdapter eventsAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GM_EventApplication.get(this).getMainComponent().inject(this);
    
        bindViews();
        
        presenter.attachView(this);
        
        presenter.getEvents(Constants.OAUTH_TOKEN, latitude, longitude);
    }
    
    private void bindViews()
    {
        latitude = "33.892352";
        longitude = "-84.473927";
        events = new ArrayList<>();
        recyclerView = findViewById(R.id.rvEvents);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    
    @Override
    public void showError(String error)
    {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
    
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        presenter.detachView();
    }
    
    @Override
    public void setEvents(EventsResponse eventsResponse)
    {
        events = eventsResponse.getEvents();
        eventsAdapter = new EventsAdapter(events, this);
    }
    
    @Override
    public void showProgress(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    
    @Override
    protected void onStop()
    {
        super.onStop();
        GM_EventApplication.get(this).clearMainComponent();
    }
}
