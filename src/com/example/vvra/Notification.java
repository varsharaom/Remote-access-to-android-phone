package com.example.vvra;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Notification extends Activity {
	TextView MyText;
	Cursor c6;
	SQLiteDatabase sqdb;
	Button b;
	//Button delete;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification);
		MyText = (TextView) findViewById(R.id.Alltext);
		b=(Button)findViewById(R.id.button1);
	//	delete=(Button)findViewById(R.id.check);
		sqdb = openOrCreateDatabase("hello", MODE_PRIVATE, null);
		sqdb.execSQL("create table if not exists inf(number varchar(15),latitude varchar(15),longitude varchar(15),name varchar(20),sentence varchar(150));");
		try{
		
		//c6 = sqdb.rawQuery("select number,latitude,longitude,name from info11", null);
			c6 = sqdb.rawQuery("select sentence from inf", null);
		// Toast.makeText(getApplicationContext(), "aftr", Toast.LENGTH_LONG)
		// .show();
		// c.moveToFirst();
		StringBuilder s = new StringBuilder();
		// c5.moveToLast();
		while (c6.moveToNext()) {
			s.append(c6.getString(0)).append("\n\n");
		}

	//	Toast.makeText(getApplicationContext(), s.substring(0, s.length()),
		//		Toast.LENGTH_LONG).show();

		MyText.setText(s.substring(0, s.length()));
		}
		catch(Exception e){
			MyText.setText("No Notifications");
		}
		
	/*	delete.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				sqdb.execSQL("delete from info11;");
				Toast.makeText(getApplicationContext(), "deleted",
						Toast.LENGTH_LONG).show();
				MyText.setText("");
			}
		});*/
		b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String str="Notification about your friends who have changed " +
						"their sim card along with the location of their phone.\n" +
						"Sequence : New phone number,latitude ,longitude,owner's name.\n" +
						"To find the location,use GET LOCATION option in the Menu and enter the coordinates manually.";
				Intent infoscreen=new Intent(getApplicationContext(),Information.class);
				infoscreen.putExtra("datas", str);
				startActivity(infoscreen);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.notification, menu);
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
}
