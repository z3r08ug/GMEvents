package chris.example.com.gm_event.util;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import chris.example.com.gm_event.R;
import chris.example.com.gm_event.model.Event;
import chris.example.com.gm_event.view.details.DetailsActivity;

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
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
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
            holder.tvName.setText("Name: " + event.getName().getText());
            holder.tvStart.setText("Start Date: " + event.getStart().getLocal());
            holder.tvStatus.setText("Status: " + event.getStatus());
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
        private final TextView tvName;
        private final TextView tvStart;
        private final TextView tvStatus;
        public ViewHolder(View itemView)
        {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvListName);
            tvStart = itemView.findViewById(R.id.tvListStart);
            tvStatus = itemView.findViewById(R.id.tvListStatus);
            
            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent intent = new Intent(context, DetailsActivity.class);
                    intent.putExtra("event", events.get(getAdapterPosition()));
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
    
}