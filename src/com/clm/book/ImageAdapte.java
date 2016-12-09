package com.clm.book;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageAdapte extends BaseAdapter {

	private Context context;
	private Integer[] image_id=
		{   R.drawable.a,R.drawable.b,R.drawable.c,
			R.drawable.d,R.drawable.e,R.drawable.f,
			R.drawable.g,R.drawable.h,R.drawable.i,
			R.drawable.j,R.drawable.k,R.drawable.l,
			R.drawable.m,R.drawable.n,R.drawable.o,
			R.drawable.p,R.drawable.q,R.drawable.r,
			R.drawable.s,R.drawable.t,R.drawable.u,
			R.drawable.v,R.drawable.w,R.drawable.x,
			R.drawable.y,R.drawable.z,R.drawable.zz,
		};
	public ImageAdapte (Context c)
	{
		context=c;
	}
	public int getCount() {

      	return image_id.length;
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return image_id[position];
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		
		ImageView image=new ImageView(context);
		image.setImageResource(image_id[position]);
		return image;
	}

}
