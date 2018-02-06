package chris.example.com.gm_event.view.details;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import javax.inject.Inject;

import chris.example.com.gm_event.GM_EventApplication;
import chris.example.com.gm_event.R;


public class DetailsActivity extends AppCompatActivity implements DetailsContract.View
{
    public static final String TAG = DetailsActivity.class.getSimpleName() + "_TAG";
    @Inject
    DetailsPresenter presenter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        GM_EventApplication.get(this).getDetailsComponent().inject(this);
        
        presenter.attachView(this);
    }
    
    @Override
    public void showError(String error)
    {
    
    }
    
    @Override
    protected void onStop()
    {
        super.onStop();
        GM_EventApplication.get(this).clearDetailsComponent();
    }
}
