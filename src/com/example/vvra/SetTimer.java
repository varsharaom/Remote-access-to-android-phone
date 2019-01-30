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
import android.widget.EditText;

public class SetTimer extends Activity {
Button done;
EditText time1;
Button w;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_timer);
		done=(Button)findViewById(R.id.timeOK);
		time1=(EditText)findViewById(R.id.timeInHours);
		w=(Button)findViewById(R.id.button1);
		w.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String str="Specify the number of hours for which you can access your phone after sending the password.";
				Intent infoscreen=new Intent(getApplicationContext(),Information.class);
				infoscreen.putExtra("datas", str);
				startActivity(infoscreen);
			
			}
		});
		done.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			
				try{
				String tym=time1.getText().toString();
				
				FileOutputStream fout55=openFileOutput("Time.txt", 0);
				OutputStreamWriter osw55=new OutputStreamWriter(fout55);
				osw55.write(tym);
				osw55.flush();
				osw55.close();}
				catch(Exception e){
					
				}
				Intent backToSet=new Intent(getApplicationContext(),Settings.class);
				startActivity(backToSet);
				finish();
			}
		});
		
		
			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.set_timer, menu);
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
