package com.example.vvra;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



//import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FrgtNewPassword extends Activity {
	EditText newP,confP;
	Button ok;
	String newPass="",confPass="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_frgt_new_password);
		newP=(EditText)findViewById(R.id.fgNewPwd);
		confP=(EditText)findViewById(R.id.FgConfirmPwd);
		ok=(Button)findViewById(R.id.FgOK);
		ok.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				newPass=newP.getText().toString();
				confPass=confP.getText().toString();
				if(newPass.equals("")||confPass.equals(""))
				{
					Toast.makeText(getApplicationContext(), "Enter a password", Toast.LENGTH_LONG).show();
				}
				else if(newPass.equals(confPass))
				{
					Pattern p = Pattern.compile("^(?=.*[0-9]+.*)(?=.*[a-zA-Z]+.*)[0-9a-zA-Z]{8,}$");
					Matcher m = p.matcher(newPass);
					boolean val=m.matches();
					if(!val)
					{
					    String s="Password must contain at least one letter, at least one number, and be longer than eight charaters.";
						Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
						newP.setText("");
						confP.setText("");							
					}		
					else
					{											
					try{
						FileOutputStream fout=openFileOutput("textfile.txt", 0);
						OutputStreamWriter osw=new OutputStreamWriter(fout);
						osw.write(newPass);
						osw.flush();
						osw.close();
						Toast.makeText(getApplicationContext(), "Password succesfully changed!", Toast.LENGTH_LONG).show();
						Intent backToSettingsPage=new Intent(getApplicationContext(),Settings.class);
						startActivity(backToSettingsPage);
						finish();
					}
					catch(Exception e){}
					}
				}
				else
				{
					Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.frgt_new_password, menu);
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
