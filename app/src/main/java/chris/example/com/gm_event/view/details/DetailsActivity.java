package chris.example.com.gm_event.view.details;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import chris.example.com.gm_event.GM_EventApplication;
import chris.example.com.gm_event.R;
import chris.example.com.gm_event.model.Event;


public class DetailsActivity extends AppCompatActivity implements DetailsContract.View
{
    public static final String TAG = DetailsActivity.class.getSimpleName() + "_TAG";
    @Inject
    DetailsPresenter presenter;
    private TextView tvName, tvDescription, tvStart, tvEnd, tvCapacity, tvStatus, tvFree, tvUrl;
    private ImageView ivLogo;
    private Event event;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        GM_EventApplication.get(this).getDetailsComponent().inject(this);
        
        bindViews();
        presenter.attachView(this);
        displayInfo();
    }
    
    
    
    private void bindViews()
    {
        tvName = findViewById(R.id.tvName);
        tvDescription = findViewById(R.id.tvDescription);
        tvStart = findViewById(R.id.tvStart);
        tvEnd = findViewById(R.id.tvEnd);
        tvUrl = findViewById(R.id.tvUrl);
        tvCapacity = findViewById(R.id.tvCapacity);
        tvStatus = findViewById(R.id.tvStatus);
        tvFree = findViewById(R.id.tvFree);
        
        ivLogo = findViewById(R.id.ivLogo);
    }
    
    private void displayInfo()
    {
        event = (Event) getIntent().getSerializableExtra("event");
        if (event != null)
        {
            tvName.setText("Name: " + event.getName().getText());
            tvDescription.setText("Description: " + event.getDescription().getText());
            tvStart.setText("Start time: " + event.getStart().getLocal());
            tvEnd.setText("End Time: " + event.getEnd().getLocal());
            tvUrl.setText(event.getUrl());
            tvCapacity.setText("Capacity: " + event.getCapacity());
            tvStatus.setText("Status: " + event.getStatus());
            if (event.getIsFree())
                tvFree.setText("Free: Yes");
            else
                tvFree.setText("Free: No");
            
            Picasso.with(this).load(event.getLogo().getUrl()).into(ivLogo);
        }
    }
    
    @Override
    public void showError(String error)
    {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
    
    @Override
    protected void onStop()
    {
        super.onStop();
        GM_EventApplication.get(this).clearDetailsComponent();
    }
}
