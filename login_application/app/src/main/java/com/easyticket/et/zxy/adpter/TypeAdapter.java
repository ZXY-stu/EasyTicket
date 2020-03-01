package com.easyticket.et.zxy.adpter;

import java.util.ArrayList;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.easyticket.et.zxy.R;

public class TypeAdapter extends BaseAdapter {
	private ArrayList<Integer> types;
	private LayoutInflater inflater;
	private ArrayList<Integer> names;
	private int layoutId;
	public TypeAdapter(ArrayList<Integer> types,ArrayList<Integer> names,Context context,int layoutId){
		this.types=types;
		this.names = names;
		this.layoutId = layoutId;
		inflater=LayoutInflater.from(context);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return types.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return types.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Integer resId=(Integer) getItem(position);
		View view=inflater.inflate(layoutId, null);
		ImageView iv_type=(ImageView) view.findViewById(R.id.img_name1);
		TextView iv_text=(TextView) view.findViewById(R.id.tv_name1);
		iv_type.setImageResource(resId);
		iv_text.setText((Integer)names.get(position));
		return view;
	}

}
