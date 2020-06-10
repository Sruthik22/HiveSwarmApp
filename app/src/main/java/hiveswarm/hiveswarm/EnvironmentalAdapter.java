package hiveswarm.hiveswarm;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class EnvironmentalAdapter extends BaseAdapter {

    // Declare Variables
    Context context;
    LayoutInflater inflater;
    private List<EnvironmentalListItem> recordList;
    private ArrayList<EnvironmentalListItem> arraylist;

    public EnvironmentalAdapter(Context context, List<EnvironmentalListItem> recordList) {
        this.context = context;
        this.recordList = recordList;
        inflater = LayoutInflater.from(context);
        this.arraylist = new ArrayList<EnvironmentalListItem>();
        this.arraylist.addAll(recordList);
    }

    public class ViewHolder {
        TextView Hive_ID;
    }

    @Override
    public int getCount() {
        return recordList.size();
    }

    @Override
    public Object getItem(int position) {
        return recordList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.environmentaladapter_item, null);
            // Locate the TextViews in listview_item.xml
            holder.Hive_ID = (TextView) view.findViewById(R.id.ip_address);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.Hive_ID.setText(recordList.get(position).getIp_address());

        // Listen for ListView Item Click
        view.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Send single item click data to HiveInformation Class
                Intent intent = new Intent(context, EnvironmentalInformation.class);
                // Pass all data rank
                // Pass all data country
                intent.putExtra("pi_ip",
                        (recordList.get(position).getIp_address()));
                intent.putExtra("List Item", position);
                context.startActivity(intent);
            }
        });
        return view;
    }

}
