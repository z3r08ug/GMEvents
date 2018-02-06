package chris.example.com.gm_event.view.main;

import java.util.List;

import javax.inject.Inject;

import chris.example.com.gm_event.data.remote.RemoteDataSource;
import chris.example.com.gm_event.model.Event;
import chris.example.com.gm_event.model.EventsResponse;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by chris on 1/15/2018.
 */

public class MainPresenter implements MainContract.Presenter
{
    RemoteDataSource remoteDataSource;
    MainContract.View view;
    public static final String TAG = MainPresenter.class.getSimpleName() + "_TAG";
    private EventsResponse events;
    
    @Inject
    public MainPresenter(RemoteDataSource remoteDataSource)
    {
        this.remoteDataSource = remoteDataSource;
    }
    
    public MainPresenter()
    {
    
    }
    
    @Override
    public void attachView(MainContract.View view)
    {
        this.view = view;
    }
    
    @Override
    public void detachView()
    {
        this.view = null;
    }
    
    @Override
    public void getEvents(String token, String latitude, String longitude)
    {
        RemoteDataSource.getEvents(token, latitude, longitude)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<EventsResponse>()
                {
                    @Override
                    public void onSubscribe(Disposable d)
                    {
                        view.showProgress("Downloading Event Data....");
                    }
    
                    @Override
                    public void onNext(EventsResponse eventsResponse)
                    {
                        events = eventsResponse;
                    }
    
                    @Override
                    public void onError(Throwable e)
                    {
                        view.showError(e.toString());
                    }
    
                    @Override
                    public void onComplete()
                    {
                        view.setEvents(events);
                    }
                });
    }
}
