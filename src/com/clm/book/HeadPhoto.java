package com.clm.book;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class HeadPhoto extends Activity {
      

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.d_headphoto);
		final ImageAdapte ia=new ImageAdapte(this);
		GridView gv=(GridView) this.findViewById(R.id.gridView1);
		gv.setAdapter(ia);
		
		gv.setOnItemClickListener(new OnItemClickListener(){

			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long id) {
		         
				 Intent it=new Intent();
		     	 long image_id=ia.getItemId(position);
		         String i=String.valueOf(image_id); 
		         it.putExtra("image_id", i);
		         setResult(20, it); 
		         HeadPhoto.this.finish();  
		     }
			});
	}

}
