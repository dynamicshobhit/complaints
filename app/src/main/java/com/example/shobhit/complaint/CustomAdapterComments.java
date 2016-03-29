package com.example.shobhit.complaint;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by shobhit on 29-03-2016.
 */
public class CustomAdapterComments extends BaseAdapter{

    ArrayList<String> description;
    Context context;
    Comments comment1;
    ArrayList<String> name;
    public static String id="";
    private static LayoutInflater inflater=null;
    public CustomAdapterComments(Comments comment, ArrayList<String> comments_descarray, ArrayList<String> comments_personarray) {
        // TODO Auto-generated constructor stub
        description=comments_descarray;
        context=comment;
        comment1=comment;
        name=comments_personarray;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return description.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tv1;
        TextView tv2;
    }
    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        final View rowView;
        rowView = inflater.inflate(R.layout.comment_row, null);
        holder.tv1=(TextView) rowView.findViewById(R.id.comment);
        holder.tv2=(TextView) rowView.findViewById(R.id.name);
        holder.tv1.setText(description.get(position));
        holder.tv2.setText(name.get(position));
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO start new activity for comments see customadapter 2
            }
        });
        return rowView;
    }

}
