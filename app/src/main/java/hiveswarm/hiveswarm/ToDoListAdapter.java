package hiveswarm.hiveswarm;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.parse.DeleteCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

public class ToDoListAdapter extends BaseAdapter {

    // Declare Variables
    Context context;
    LayoutInflater inflater;
    private List<ToDoListItem> recordList;
    private ArrayList<ToDoListItem> arraylist;
    private String Hive_ID;
    public static List<to_do_list> ob;


    public ToDoListAdapter(Context context, List<ToDoListItem> recordList, String Hive_ID) {
        this.context = context;
        this.recordList = recordList;
        inflater = LayoutInflater.from(context);
        this.arraylist = new ArrayList<>();
        this.arraylist.addAll(recordList);
        this.Hive_ID = Hive_ID;
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
            view = inflater.inflate(R.layout.todolistadapter_item, null);
            // Locate the TextViews in listview_item.xml
            holder.Hive_ID = (TextView) view.findViewById(R.id.ip_address);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.Hive_ID.setText(recordList.get(position).getHive_Id());

        // Listen for ListView Item Click
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                try {
                    //Making a query to the Database checking for the current username and Hive_ID
                    ParseQuery<to_do_list> query = ParseQuery.getQuery(to_do_list.class);
                    query.whereEqualTo("username", SignInActivity.sign_email);
                    query.whereEqualTo(GeneralObservationsActivity.hiveId, Hive_ID);
                    query.whereEqualTo("to_do", recordList.get(position).getHive_Id());
                    query.orderByAscending("createdAt");
                    ob = query.find();

                    ob.get(0).deleteInBackground(new DeleteCallback() {
                        public void done(ParseException e) {
                            // Handle success or failure here ...
                            //Tell the ToDoList class to execute again
                            recordList.remove(position);
                            ToDoList.lvItems.setAdapter(ToDoListAdapter.this);

                        }
                    });

                } catch (ParseException e) {
                    Log.e("Error", e.getMessage());
                    e.printStackTrace();
                }
                return false;
            }
        });
        return view;
    }

}
