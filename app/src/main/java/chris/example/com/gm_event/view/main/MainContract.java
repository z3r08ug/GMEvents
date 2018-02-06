package chris.example.com.gm_event.view.main;


import chris.example.com.gm_event.model.EventsResponse;
import chris.example.com.gm_event.util.BasePresenter;
import chris.example.com.gm_event.util.BaseView;

/**
 * Created by chris on 2/6/2018.
 */

public interface MainContract
{
    interface  View extends BaseView
    {
        void setEvents(EventsResponse eventsResponse);
        void showProgress(String message);
    }
    
    interface Presenter extends BasePresenter<View>
    {
        void getEvents(String token, String latitude, String longitude);
    }
}
