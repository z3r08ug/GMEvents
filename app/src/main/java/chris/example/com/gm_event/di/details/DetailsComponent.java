package chris.example.com.gm_event.di.details;


import chris.example.com.gm_event.view.details.DetailsActivity;
import dagger.Subcomponent;

/**
 * Created by chris on 2/6/2018.
 */
@Subcomponent(modules = DetailsModule.class)
public interface DetailsComponent
{
    void inject(DetailsActivity detailsActivity);
}
