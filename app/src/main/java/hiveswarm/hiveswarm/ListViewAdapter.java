package hiveswarm.hiveswarm;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
    private FragmentActivity myContext;

    public ListViewAdapter(Context context, List<HiveDataListItem> hiveDataList) {
        this.context = context;
        myContext = (FragmentActivity) context;
        this.hiveDataList = hiveDataList;
        inflater = LayoutInflater.from(context);
        this.arraylist = new ArrayList<HiveDataListItem>();
        this.arraylist.addAll(hiveDataList);
    }

    public class ViewHolder {
        TextView Hive_ID;
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
            holder.Hive_ID = (TextView) view.findViewById(R.id.hive_Id);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.Hive_ID.setText(hiveDataList.get(position).getHive_Id());

        // Listen for ListView Item Click
        view.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Send single item click data to HiveInformation Class

                //Not going directly to the Base Adapter --> Go to an Activity that calls this Base Adapter
                //What if instead of going direct to HivesWithHiveID



                Intent intent = new Intent(context, HivesWithHiveID.class);
                // Pass all data rank
                // Pass all data country
                intent.putExtra("Hive_ID",
                        (hiveDataList.get(position).getHive_Id()));
                // Start HivesWithHiveId Class --> To see if there are multiple records with the same Hive Id
                context.startActivity(intent);
            }
        });
        return view;
    }

}
