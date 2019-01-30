package com.example.vvra;

import java.io.FileInputStream;
import java.io.InputStreamReader;



//import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
//import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ForgotPassword extends Activity {
	TextView t,retrieved;
	Button b;
	EditText e;
	String str,ans="",str1,str2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forgot_password);
		t=(TextView)findViewById(R.id.secQ);
		retrieved=(TextView)findViewById(R.id.textView3);
		b=(Button)findViewById(R.id.secOKK);
		e=(EditText)findViewById(R.id.SecEditText);
		try{
			FileInputStream fIn = openFileInput("SecQues.txt");
			InputStreamReader isr=new InputStreamReader(fIn);
			char[] inputbuffer=new char[100];			
			int charread;
			str="";
			while((charread=isr.read(inputbuffer))>0)
			{
				String readstring=String.copyValueOf(inputbuffer,0,charread);
				str+=readstring;
				inputbuffer=new char[100];
			}
		}
		catch(Exception e){
		}
		t.setText(str);
		b.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ans=e.getText().toString();
				try{
					FileInputStream fIn = openFileInput("SecAns.txt");
					InputStreamReader isr=new InputStreamReader(fIn);
					char[] inputbuffer=new char[100];			
					int charread;
					str1="";
					while((charread=isr.read(inputbuffer))>0)
					{
						String readstring=String.copyValueOf(inputbuffer,0,charread);
						str1+=readstring;
						inputbuffer=new char[100];
					}
				}
				catch(Exception e){
				}
				if(ans.equals(""))
				{
					Toast.makeText(getApplicationContext(), "Enter the answer", Toast.LENGTH_LONG).show();
				}
				else if(ans.equals(str1))
				{
					try{
						FileInputStream fInn = openFileInput("textfile.txt");
						InputStreamReader isrn=new InputStreamReader(fInn);
						char[] inputbuffer=new char[100];			
						int charreadn;
						str2="";
						while((charreadn=isrn.read(inputbuffer))>0)
						{
							String readstring=String.copyValueOf(inputbuffer,0,charreadn);
							str2+=readstring;
							inputbuffer=new char[100];
						}
					}
					catch(Exception e){
					}
					retrieved.setText(str2);
					//Intent forgotPwdNext=new Intent(getApplicationContext(),FrgtNewPassword.class);
					//startActivity(forgotPwdNext);
					//finish();
				}
				else
				{
					Toast.makeText(getApplicationContext(), "Wrong answer!", Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.forgot_password, menu);
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
