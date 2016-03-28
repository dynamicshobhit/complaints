package com.example.shobhit.complaint;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
///CustomAdapterPosts(this,post_titles,post_body,post_votes,post_resolved,post_postedby))
/**
 * Created by shobhit on 28-03-2016.
 */
public class CustomAdapterPosts extends BaseAdapter {
    ArrayList<String> titles;
    ArrayList<String> body;
    ArrayList<String> votes;
    ArrayList<String> resolved;
    ArrayList<String> posted_by;

    Context context;
    Posts posts1;
    ArrayList<String> names;
    private static LayoutInflater inflater=null;
    public CustomAdapterPosts(Posts posts, ArrayList<String> post_titles, ArrayList<String> post_body, ArrayList<String> post_votes, ArrayList<String> post_resolved,ArrayList<String> post_postedby) {
        // TODO Auto-generated constructor stub
        titles=post_titles;
        body=post_body;
        votes=post_votes;
        resolved=post_resolved;
        posted_by=post_postedby;
        context=posts;
        posts1=posts;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return titles.size();
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
        TextView tv3;
        TextView tv4;
        TextView tv5;
    }
    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        final View rowView;
        rowView = inflater.inflate(R.layout.post_row, null);
        holder.tv1=(TextView) rowView.findViewById(R.id.posttitle);
        holder.tv2=(TextView) rowView.findViewById(R.id.postbody);
        holder.tv3=(TextView) rowView.findViewById(R.id.postvotes);
        holder.tv4=(TextView) rowView.findViewById(R.id.post_resolved_status);
        holder.tv5=(TextView) rowView.findViewById(R.id.name);
        holder.tv1.setText(titles.get(position));
        holder.tv2.setText(body.get(position));
        holder.tv3.setText(votes.get(position));
        holder.tv4.setText(resolved.get(position));
        holder.tv5.setText(posted_by.get(position));
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                //courselist1.getcoursedetails(rowView);
                Toast.makeText(context, "You Clicked " + titles.get(position), Toast.LENGTH_SHORT).show();
            }
        });
        return rowView;
    }


}
