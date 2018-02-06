package chris.example.com.gm_event.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import chris.example.com.gm_event.R;
import chris.example.com.gm_event.model.Event;

/**
 * Created by chris on 2/6/2018.
 */
public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder>
{
    private final Context context;
    private List<Event> events;
    
    public EventsAdapter(List<Event> items, Context context)
    {
        this.events = items;
        this.context = context;
    }
    
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                              int viewType)
    {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }
    
    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        Event event = events.get(position);
        if (event != null)
        {
            
        }
        
    }
    
    @Override
    public int getItemCount()
    {
        if (events == null)
        {
            return 0;
        }
        return events.size();
    }
    
    public class ViewHolder extends RecyclerView.ViewHolder
    {
//        private final TextView tvTemp;
//        private final TextView tvTime;
//        private final ImageView ivCondition;
        public ViewHolder(View itemView)
        {
            super(itemView);
//            tvTemp = itemView.findViewById(id.tvTemp);
//            tvTime = itemView.findViewById(id.tvTime);
//            ivCondition = itemView.findViewById(id.ivHourlyCondition);
        }
    }
    
}