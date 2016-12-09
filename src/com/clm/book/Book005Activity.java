package com.clm.book;


import java.util.Timer;
import java.util.TimerTask;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;

public class Book005Activity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_main);
        
        //新建数据库
	    SQLiteDatabase db = this.openOrCreateDatabase("clm.db", MODE_PRIVATE, null);
	       try {
				db.execSQL(" create table if not exists lianxiren (_id int, name text primary key, tel text,image_id text) ");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		     //读取联系人 
	       Cursor cur=getContentResolver().query(Phone.CONTENT_URI, null,null ,null,null);
		   startManagingCursor(cur);
		 while(cur.moveToNext())
			{
				int nameIndex = cur.getColumnIndex(Phone.DISPLAY_NAME);
				String name = cur.getString(nameIndex);	
				int telIndex = cur.getColumnIndex(Phone.NUMBER);
				String tel = cur.getString(telIndex);
			    int photo1= R.drawable.icon;
			    String photo=Integer.toString(photo1);
			 try {
					db.execSQL("insert into lianxiren(name,tel,image_id) values('" + name+ "','" + tel+ "','"+photo+"')");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		  cur.close();   
	       
        final Intent it=new Intent(this,Book005Menu.class);
        Timer time=new Timer();
        TimerTask tt=new TimerTask(){

			@Override
			public void run() {
				startActivity(it);
				Book005Activity.this.finish();
			}
		};
        time.schedule(tt, 1000*3);
    }
    
    
}