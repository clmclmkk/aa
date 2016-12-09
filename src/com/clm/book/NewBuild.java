package com.clm.book;



import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
public class NewBuild extends Activity {
    ImageView im;
	Button head_photo;
	Button save;
	Button reset;
	EditText name;
	EditText tel;
	String image_id;
	String edit=null;
	String name11;
	String tel11;
	String photo11;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.c_newbuild);
		 head_photo=(Button) this.findViewById(R.id.button1);
		 reset=(Button) this.findViewById(R.id.button3);
	     im= (ImageView) this.findViewById(R.id.imageView1);
	     name=(EditText) this.findViewById(R.id.editText1);
         tel=(EditText) this.findViewById(R.id.editText2);
         save=(Button) this.findViewById(R.id.button2); 
	    
         name11=this.getIntent().getStringExtra("name");
         tel11=this.getIntent().getStringExtra("tel");
         image_id=this.getIntent().getStringExtra("photo");
         if(name11!=null)
         { name.setText(name11);
           if(tel11!=null )
           {	tel.setText(tel11);
           }
             
             Integer id=Integer.parseInt(image_id);
             im.setImageResource(id);
          
         }
         head_photo.setOnClickListener(new Button.OnClickListener(){
       
			public void onClick(View v) {
				Intent it1=new Intent();
				it1.setClass(NewBuild.this,HeadPhoto.class);
				//startActivity(it1);
				startActivityForResult(it1, 100);  
			}
	      });	
	    
	      save.setOnClickListener(new Button.OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				String name1 = name.getText().toString();
				String  tel1 = tel.getText().toString();
				
				SQLiteDatabase db = NewBuild.this.openOrCreateDatabase("clm.db", MODE_PRIVATE, null);
		 	    edit=NewBuild.this.getIntent().getStringExtra("edit");
				try {if(!"".equals(name1)&&name1!=""&&edit==null)
				     {if(image_id==null)
				        { int photo1= R.drawable.icon;
					     image_id=Integer.toString(photo1);
				    	 } 
					  db.execSQL("insert into lianxiren(name,tel,image_id) values('" + name1+ "','" + tel1+ "','"+image_id+"')");
					  Toast.makeText(NewBuild.this, "数据保存成功", Toast.LENGTH_SHORT).show();
					
					  Intent it = new Intent();
					  it.setClass(NewBuild.this, Book005Menu.class);
					  startActivity(it);
					  NewBuild.this.finish();
				     }
				     if(!"".equals(name1)&&name1!=""&&edit!=null)
				     {   edit=null;
				    	 db.execSQL("update lianxiren set name='"+name1+"',tel='"+tel1+"',image_id='"+image_id+"'where name='"+name11+"'");
				    	 	 
				    	Toast.makeText(NewBuild.this, "数据修改成功", Toast.LENGTH_SHORT).show();
				   		Intent it = new Intent();
							it.setClass(NewBuild.this, Book005Menu.class);
							startActivity(it);
							NewBuild.this.finish();
				     }		
					   if("".equals(name1))
					   {Toast.makeText(NewBuild.this, "数据保存失败", Toast.LENGTH_SHORT).show();}
				     
				} catch (SQLException e) {
					
					Toast.makeText(NewBuild.this, "数据保存失败", Toast.LENGTH_SHORT).show();
					e.printStackTrace();
				}
				db.close();		
			}
        });
        
	     reset.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				name.setText(null);
				tel.setText(null);
				im.setImageResource(R.drawable.unpicture);
			}}) ;
	      
	     
	}
	

	
	
	@Override  
    public void onActivityResult(int requestCode, int resultCode, Intent data)  
    {   if(20==resultCode)  
        {   
	    image_id=data.getStringExtra("image_id");
	    if(image_id!=null)
		{   Integer id=Integer.parseInt(image_id);
	    	im.setImageResource(id);
	    } 
	    }  
        super.onActivityResult(requestCode, resultCode, data);  
    } 
	  @Override  
	   public boolean onKeyDown(int keyCode, KeyEvent event)  
	    {  
	        if (keyCode == KeyEvent.KEYCODE_BACK )  
	        {  
	            Intent it2=new Intent();
	            it2.setClass(NewBuild.this,Book005Menu.class);
	            startActivity(it2);
	            NewBuild.this.finish();    
	        }  
	          
	       return false;  
	          
	    } 
}
