package com.clm.book;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;

import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class Book005Menu extends Activity {
    
    ProgressDialog pg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	    this.setContentView(R.layout.b_showlianxiren);
	   ListView lv=(ListView)findViewById(R.id.listView1);
	 
	
	   SQLiteDatabase db = this.openOrCreateDatabase("clm.db", MODE_PRIVATE, null);
       try {
			db.execSQL(" create table if not exists lianxiren (_id int, name text primary key, tel text,image_id text) ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       //显示数据库   
 		Cursor  cursor = db.rawQuery("select * from lianxiren ", null);
		ListAdapter adpe=new SimpleCursorAdapter(this,
				R.layout.b_list,	//资源
				cursor,
				new String[]{"name","tel","image_id"},	//列名
				new int[]{android.R.id.text1,android.R.id.text2,R.id.imageView1}	//控件id
				);
	   lv.setAdapter(adpe);
	   lv.setOnItemClickListener(new OnItemClickListener(){
	        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	            ListView listView = (ListView)findViewById(R.id.listView1);
	            final Cursor cursor = (Cursor)listView.getItemAtPosition(position);
	            if(cursor.getString(1)!=null)
	            {	
				   Dialog dg=new AlertDialog.Builder(Book005Menu.this)
                  .setTitle("提示")
                  .setMessage("删除或编辑联系人")
                  .setPositiveButton("删除", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
				 SQLiteDatabase db = Book005Menu.this.openOrCreateDatabase("clm.db", MODE_PRIVATE, null);
						db.execSQL("delete from lianxiren where name='"+cursor.getString(1)+"'"); 
						
						 ListView lv=(ListView)findViewById(R.id.listView1);
						Cursor  cursor = db.rawQuery("select * from lianxiren ", null);
						ListAdapter adpe=new SimpleCursorAdapter(Book005Menu.this,
								R.layout.b_list,	//资源
								cursor,
								new String[]{"name","tel","image_id"},	//列名
								new int[]{android.R.id.text1,android.R.id.text2,R.id.imageView1}	//控件id
								);
					   lv.setAdapter(adpe);                                                                        
					}
			       })
                  .setNegativeButton("编辑",new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
				           Intent it=new Intent();  
				           
		                   it.putExtra("name", cursor.getString(1));
		                   it.putExtra("tel", cursor.getString(2)) ;
		                   String a=cursor.getString(3);
		             //     if(!a.equals("null"))
		             //      { 
		                    it.putExtra("photo",a);
		              //     }
		                 it.putExtra("edit","edit");
						   it.setClass(Book005Menu.this,NewBuild.class);
						   startActivity(it);
					       Book005Menu.this.finish();
					  }
				})
                  .create();
	              dg.show();
	        }
	        } 
	   } );
	}
	//菜单点击事件
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater MI=this.getMenuInflater();
		MI.inflate(R.menu.menu, menu);
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int item_id=item.getItemId();
		switch(item_id)
		{
		case R.id.newbuild:
			Intent it=new Intent();
			it.setClass(Book005Menu.this,NewBuild.class );
			startActivity(it);	
			Book005Menu.this.finish();
			break;
		case R.id.exit:
			Book005Menu.this.finish();
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
	 @Override  
	   public boolean onKeyDown(int keyCode, KeyEvent event)  
	    {  
	        if (keyCode == KeyEvent.KEYCODE_BACK )  
	        {  
	            Book005Menu.this.finish();
	      
	        }  
	          
	       return false;  
	          
	    } 
	
}
