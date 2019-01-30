package com.example.vvra;


import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class SetTheme extends Activity {
ImageButton im1,im2,im3;
RelativeLayout rela;
Button b;
//LinearLayout line;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_theme);
		im1=(ImageButton)findViewById(R.id.imageButton1);
		im2=(ImageButton)findViewById(R.id.imageButton2);
		im3=(ImageButton)findViewById(R.id.imageButton3);
		
		//line=(LinearLayout)findViewById(R.id.lin1);
		b=(Button)findViewById(R.id.button1);
		b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String str="Change the theme of the menu screen by choosing among the different themes.";
				Intent infoscreen=new Intent(getApplicationContext(),Information.class);
				infoscreen.putExtra("datas", str);
				startActivity(infoscreen);
				
			}
		});
		im1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try{
				FileOutputStream fout6 = openFileOutput("Background.txt", 0);
				OutputStreamWriter osw6 = new OutputStreamWriter(fout6);
				osw6.write("one");
				osw6.flush();
				osw6.close();
				//Toast.makeText(getApplicationContext(), "one", Toast.LENGTH_LONG).show();
				}
				catch(Exception e){
					
				}
				
				
				
			//	rela.setBackgroundResource(R.drawable.ic_launcher);
				//line.setBackgroundResource(R.drawable.ic_launcher);
				/*Intent goback=new Intent(getApplicationContext(),CarouselMain.class);
				goback.putExtra("data1", "1");
				startActivity(goback);*/
				
			}
		});
		
		im2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try{
					FileOutputStream fout6 = openFileOutput("Background.txt", 0);
					OutputStreamWriter osw6 = new OutputStreamWriter(fout6);
					osw6.write("two");
					osw6.flush();
					osw6.close();
					//Toast.makeText(getApplicationContext(), "two", Toast.LENGTH_LONG).show();
					}
					catch(Exception e){
						
					}
			}
		});
		im3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try{
					FileOutputStream fout6 = openFileOutput("Background.txt", 0);
					OutputStreamWriter osw6 = new OutputStreamWriter(fout6);
					osw6.write("three");
					osw6.flush();
					osw6.close();
					//Toast.makeText(getApplicationContext(), "default", Toast.LENGTH_LONG).show();
					}
					catch(Exception e){
						
					}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.set_theme, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	@Override
	public void onBackPressed()
	{
					Intent l3=new Intent(getApplicationContext(),Settings.class);
					startActivity(l3);
					finish();
	}
}
