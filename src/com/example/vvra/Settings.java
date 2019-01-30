package com.example.vvra;



/*
 * Copyright 2013 Csaba Szugyiczki
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.FileInputStream;
import java.io.InputStreamReader;

import com.example.vvra.CarouselAdapter.OnItemClickListener;
import com.example.vvra.CircleLayout.OnCenterClickListener;
import com.example.vvra.CircleLayout.OnItemSelectedListener;
import com.example.vvra.CircleLayout.OnRotationFinishedListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.TextView;
import android.widget.Toast;



public class Settings extends Activity implements OnItemSelectedListener,
		OnItemClickListener, OnRotationFinishedListener, OnCenterClickListener, com.example.vvra.CircleLayout.OnItemClickListener {
	TextView selectedTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);

		CircleLayout circleMenu = (CircleLayout) findViewById(R.id.main_circle_layout);
		circleMenu.setOnItemSelectedListener(this);
		circleMenu.setOnItemClickListener(this);
		circleMenu.setOnRotationFinishedListener(this);
		circleMenu.setOnCenterClickListener(this);

		selectedTextView = (TextView) findViewById(R.id.main_selected_textView);
		selectedTextView.setText(((CircleImageView) circleMenu
				.getSelectedItem()).getName());
	}

	@Override
	public void onItemSelected(View view, String name) {
		selectedTextView.setText(name);

		switch (view.getId()) {
			case R.id.changePassword:
				// Handle facebook selection
				break;
			case R.id.timer:
				// Handle google selection
				break;
			case R.id.setTheme:
				// Handle linkedin selection
				break;
			case R.id.clear:
				// Handle myspace selection
				break;
			case R.id.block:
				// Handle twitter selection
				break;
			case R.id.forgotPassword:
				// Handle wordpress selection
				break;
		}
	}

	public void onItemClick(View view, String name) {
	/*	Toast.makeText(getApplicationContext(),
				getResources().getString(R.string.start_app) + " " + name,
				Toast.LENGTH_SHORT).show();*/
		String s="";
		try{
			FileInputStream fIn = openFileInput("flag.txt");
		InputStreamReader isr=new InputStreamReader(fIn);
		char[] inputbuffer=new char[100];			
		int charread;
	//	Toast.makeText(getApplicationContext(),"helll",Toast.LENGTH_LONG).show();
	//s="";
		while((charread=isr.read(inputbuffer))>0)
		  {
			String readstring=String.copyValueOf(inputbuffer,0,charread);
			s+=readstring;
			inputbuffer=new char[100];
		  }					
		}
		catch(Exception e){}
		switch (view.getId()) {
			case R.id.block:
				// Handle facebook click
				if(s.equals("1"))
				{
					//Toast.makeText(getApplicationContext(), "Already registered!", Toast.LENGTH_LONG).show();
				Intent BlockContacts=new Intent(getApplicationContext(),Block.class);
				startActivity(BlockContacts);
				}
				else{
					Toast.makeText(getApplicationContext(), "Not registered yet!", Toast.LENGTH_LONG).show();
				}
				break;
			case R.id.changePassword:
				// Handle google click
				
				if(s.equals("1"))
				{
					//Toast.makeText(getApplicationContext(), "Already registered!", Toast.LENGTH_LONG).show();
				Intent changePassword=new Intent(getApplicationContext(),ChangePassword.class);
				startActivity(changePassword);
				}
				else{
					Toast.makeText(getApplicationContext(), "Not registered yet!", Toast.LENGTH_LONG).show();
				}
				break;
			case R.id.setTheme:
				// Handle linkedin click
				
				//Intent getplace=new Intent(getApplicationContext(),GetCurrentLocation.class);
				//startActivity(getplace);
				Intent setTheme=new Intent(getApplicationContext(),SetTheme.class);
				startActivity(setTheme);
				break;
			case R.id.clear:
				// Handle myspace click
				//Cursor c6;
				
				
				
				AlertDialog.Builder	builder = new AlertDialog.Builder(this);
				builder.setTitle("Clear?");		
				builder.setMessage("Are you sure you want to clear notifications?");
				builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {			
				@Override		
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();        
					//finish(); 
					
					
					SQLiteDatabase sqdb;
					//Intent checknotification=new Intent(getApplicationContext(),Notification.class);
					//startActivity(checknotification);
					sqdb = openOrCreateDatabase("hello", MODE_PRIVATE, null);
					sqdb.execSQL("create table if not exists inf(number varchar(15),latitude varchar(15),longitude varchar(15),name varchar(20));");
					sqdb.execSQL("delete from inf;");
					Toast.makeText(getApplicationContext(), "cleared",
							Toast.LENGTH_LONG).show();
					
					
				
		       }});		
				builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();		
				}
				});
				AlertDialog alert = builder.create();
				alert.show();
			
				
				
				
				
				
				//MyText.setText("");
				
				
				
				break;
			case R.id.timer:
				// Handle twitter click
				if(s.equals("1"))
				{
					//Toast.makeText(getApplicationContext(), "Already registered!", Toast.LENGTH_LONG).show();
				Intent timerclass=new Intent(getApplicationContext(),SetTimer.class);
				startActivity(timerclass);
				}
				else{
					Toast.makeText(getApplicationContext(), "Not registered yet!", Toast.LENGTH_LONG).show();
				}
				break;
			case R.id.forgotPassword:
				// Handle wordpress click
				if(s.equals("1")){
				Intent forgotPassword=new Intent(getApplicationContext(),ForgotPassword.class);
				startActivity(forgotPassword);}
				else
				{
					Toast.makeText(getApplicationContext(), "Not registered yet!", Toast.LENGTH_LONG).show();	
				}
				break;
		}
	}

	public void onCenterClick() {
	//	Toast.makeText(getApplicationContext(), R.string.center_click,
		//		Toast.LENGTH_SHORT).show();
	}

	public void onRotationFinished(View view, String name) {
		Animation animation = new RotateAnimation(0, 360, view.getWidth() / 2,
				view.getHeight() / 2);
		animation.setDuration(250);
		view.startAnimation(animation);
	}

	@Override
	public void onItemClick(CarouselAdapter<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onBackPressed()
	{
					Intent l2=new Intent(getApplicationContext(),CarouselMain.class);
					startActivity(l2);
					finish();
	}

}
