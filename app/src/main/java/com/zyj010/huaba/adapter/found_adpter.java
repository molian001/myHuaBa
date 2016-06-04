package com.zyj010.huaba.adapter;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zyj010.huaba.R;
import com.zyj010.huaba.model.Found;

import java.util.List;

/**
 * Created by zyj010 on 2016/5/3 0003.
 */
public class found_adpter extends BaseAdapter{
    private Context ct;
    private List<Found> founds;

   public found_adpter(Context ct,List<Found> found){
        this.ct=ct;
        this.founds=found;
    }

    @Override
    public int getCount() {
        return founds.size();
    }

    @Override
    public Object getItem(int position) {
        return founds.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder=null;
            Found found=null;
        if(convertView==null){
            convertView= LayoutInflater.from(ct).inflate(R.layout.item_found,null);
            viewHolder=new ViewHolder();
            viewHolder.lable= (TextView) convertView.findViewById(R.id.found_lable);
            viewHolder.lable2= (TextView) convertView.findViewById(R.id.found_lable2);
            viewHolder.time= (TextView) convertView.findViewById(R.id.found_time);
            viewHolder.background= (RelativeLayout) convertView.findViewById(R.id.found_background);
            convertView.setTag(viewHolder);
            found=founds.get(position);



        }
        else {viewHolder= (ViewHolder) convertView.getTag();
        }

        viewHolder.lable.setText(found.getLable());
        viewHolder.lable2.setText(found.getLable2());
        viewHolder.time.setText(found.getTime());
        BitmapDrawable bd= new BitmapDrawable(found.getFound_background());
        viewHolder.background.setBackground(bd);

        return convertView;
    }
    static  class  ViewHolder{
        TextView lable;
        TextView lable2;
        TextView time;
        RelativeLayout background;
    }
}
