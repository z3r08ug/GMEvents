package chris.example.com.gm_event.view.details;

import javax.inject.Inject;

import chris.example.com.gm_event.data.remote.RemoteDataSource;


/**
 * Created by chris on 1/15/2018.
 */

public class DetailsPresenter implements DetailsContract.Presenter
{
    RemoteDataSource remoteDataSource;
    DetailsContract.View view;
    public static final String TAG = DetailsPresenter.class.getSimpleName() + "_TAG";
    
    @Inject
    public DetailsPresenter(RemoteDataSource remoteDataSource)
    {
        this.remoteDataSource = remoteDataSource;
    }
    
    public DetailsPresenter()
    {
    
    }
    
    @Override
    public void attachView(DetailsContract.View view)
    {
        this.view = view;
    }
    
    @Override
    public void detachView()
    {
        this.view = null;
    }
}
