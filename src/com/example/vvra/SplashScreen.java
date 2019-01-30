package com.example.vvra;



//import android.support.v7.app.ActionBarActivity;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class SplashScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
	/*	if (getIntent().getBooleanExtra("EXIT", false)) {
		    finish();
		}*/
		Thread timer=new Thread(){
			public void run(){
				try{
					sleep(3000);
				}
				catch(InterruptedException e)
				{
					e.printStackTrace();
				}
				finally{
					
				/*	FileOutputStream fout6 =openFileOutput("Background.txt", 0);
					OutputStreamWriter osw6 = new OutputStreamWriter(fout6);
					osw6.write(subscriberIdOnBoot);
					osw6.flush();
					osw6.close();*/
					
					
					Intent splashnext=new Intent(getApplicationContext(),CarouselMain.class);
				//	splashnext.putExtra("data1", "0");
					startActivity(splashnext);
				}
			}
		};
		timer.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash_screen, menu);
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
