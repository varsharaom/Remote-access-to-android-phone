package com.example.vvra;



import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
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

public class ChangePassword extends Activity {
	EditText OldP,NewP,ConfirmP;
	Button ok;
	String oldPass,NewPass,ConfPass,s="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_password);
		OldP=(EditText)findViewById(R.id.editText1);
		NewP=(EditText)findViewById(R.id.editText2);
		ConfirmP=(EditText)findViewById(R.id.editText3);
		ok=(Button)findViewById(R.id.changeOK);
		ok.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				oldPass=OldP.getText().toString();
				NewPass=NewP.getText().toString();
				ConfPass=ConfirmP.getText().toString();
				try{
					FileInputStream fIn =openFileInput("textfile.txt");
					InputStreamReader isr=new InputStreamReader(fIn);
					char[] inputbuffer=new char[100];	
					s="";
					int charread;
					while((charread=isr.read(inputbuffer))>0)
					{
						String readstring=String.copyValueOf(inputbuffer,0,charread);
						s+=readstring;
						inputbuffer=new char[100];
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				if(oldPass.equals("")||NewPass.equals("")||ConfPass.equals(""))
				{
					Toast.makeText(getApplicationContext(), "Enter a password", Toast.LENGTH_LONG).show();					
				}
				else 
				{
					if(oldPass.equals(s))
				   {
					if(NewPass.equals(ConfPass))
					{
						Pattern p = Pattern.compile("^(?=.*[0-9]+.*)(?=.*[a-zA-Z]+.*)[0-9a-zA-Z]{8,}$");
						Matcher m = p.matcher(NewPass);
						boolean val=m.matches();
						val=true;
						if(!val)
						{
						    String s="Password must contain at least one letter, at least one number, and be longer than eight charaters.";
							Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
							NewP.setText("");
							ConfirmP.setText("");							
						}		
						else
						{
						try{
							FileOutputStream fout=openFileOutput("textfile.txt", 0);
							OutputStreamWriter osw=new OutputStreamWriter(fout);
							osw.write(NewPass);
							osw.flush();
							osw.close();
						}
						catch(Exception e){}
						Toast.makeText(getApplicationContext(), "Password Changed Successfully!", Toast.LENGTH_LONG).show();
						Intent changepwdNext=new Intent(getApplicationContext(),Settings.class);
						startActivity(changepwdNext);
						finish();
						}
					}
					else
					{
						Toast.makeText(getApplicationContext(), "New Passwords do not match", Toast.LENGTH_LONG).show();						
					}
				}
				else{
					Toast.makeText(getApplicationContext(), "Old password doesnt match", Toast.LENGTH_LONG).show();
				}
			}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.change_password, menu);
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
