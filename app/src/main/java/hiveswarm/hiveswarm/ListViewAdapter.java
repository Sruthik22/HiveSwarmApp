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

public class ListViewAdapter extends BaseAdapter {

    // Declare Variables
    Context context;
    LayoutInflater inflater;
    private List<HiveDataListItem> hiveDataList = null;
    private ArrayList<HiveDataListItem> arraylist;

    public ListViewAdapter(Context context,
                           List<HiveDataListItem> hiveDataList) {
        this.context = context;
        this.hiveDataList = hiveDataList;
        inflater = LayoutInflater.from(context);
        this.arraylist = new ArrayList<HiveDataListItem>();
        this.arraylist.addAll(hiveDataList);
    }

    public class ViewHolder {
        TextView Hive_ID;
        TextView Date;
    }

    @Override
    public int getCount() {
        return hiveDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return hiveDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.listview_item, null);
            // Locate the TextViews in listview_item.xml
            holder.Date = (TextView) view.findViewById(R.id.creation_date);
            holder.Hive_ID = (TextView) view.findViewById(R.id.hive_Id);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        String date = hiveDataList.get(position).getCreationDate();
        holder.Date.setText(date);
        holder.Hive_ID.setText(hiveDataList.get(position).getHive_Id());

        // Listen for ListView Item Click
        view.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(context, SingleItemView.class);
                // Pass all data rank
                intent.putExtra("ObjectId",
                        (hiveDataList.get(position).getObjectId()));
                intent.putExtra("CreationDate",
                        (hiveDataList.get(position).getCreationDate().toString()));
                // Pass all data country
                intent.putExtra("Hive_ID",
                        (hiveDataList.get(position).getHive_Id()));
                // Start SingleItemView Class
                context.startActivity(intent);
            }
        });
        return view;
    }

}
