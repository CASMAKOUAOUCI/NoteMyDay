package univ8.notemyday;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import univ8.notemyday.Activity.CriteriaDayActivity;
import univ8.notemyday.Activity.DetailHistoryActivity;
import univ8.notemyday.model.CriteriaDay;
import univ8.notemyday.model.Day;

/**
 * Created by abdelmadjidchaibi on 21/12/2016.
 */

public class CustomAdapter extends BaseAdapter {
    List<Object> result;
    Context context;
    int typeList;
    ListFragment fragment;

    private static LayoutInflater inflater=null;

    public CustomAdapter(FragmentActivity mainActivity, List<Object> prgmNameList, int type, ListFragment listFragment) {
        // TODO Auto-generated constructor stub
        typeList = type;
        result = prgmNameList;
        context = mainActivity;
        fragment =  listFragment;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.size();
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
        TextView tv;
        ImageView img;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.custom_adapter, null);
        holder.tv=(TextView) rowView.findViewById(R.id.textView1);
        holder.img=(ImageView) rowView.findViewById(R.id.imageView1);

        float rating;
        final String text;
        if (typeList == 0)
        {
            final Day day = (Day) result.get(position);
            rating = day.rating;
            text = day.toString();

        }else
        {
            final CriteriaDay cday = (CriteriaDay) result.get(position);
            rating = cday.getRating();
            text = cday.toString();
        }

        holder.tv.setText(text);

        int imageId;
        if (rating < 1) {
            imageId = R.drawable.smiley1;
        }else if (rating < 2) {
            imageId = R.drawable.smiley2;
        }else if (rating < 3) {
            imageId = R.drawable.smiley3;
        }else  if (rating < 4){
            imageId = R.drawable.smiley4;
        } else {
            imageId = R.drawable.smiley5;
        }
        holder.img.setImageResource(imageId);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (typeList == 1)
                {
                    Intent intent = new Intent(context, CriteriaDayActivity.class);
                    CriteriaDay criteriaDay = (CriteriaDay) result.get(position);
                    intent.putExtra("CriteriaDay", (Serializable) criteriaDay);
                    fragment.getActivity().startActivityForResult(intent,10001);
                }else if (typeList == 0)
                {
                    // display history details
                    Intent intent = new Intent(context, DetailHistoryActivity.class);
                    Day day = (Day) result.get(position);
                    intent.putExtra("Day", (Serializable) day);
                    fragment.getActivity().startActivity(intent);
                }
            }
        });
        return rowView;
    }

}