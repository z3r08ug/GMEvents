package chris.example.com.gm_event.util;

/**
 * Created by chris on 2/6/2018.
 */

public interface BasePresenter <V extends BaseView>
{
    void attachView(V view);
    void detachView();
}
