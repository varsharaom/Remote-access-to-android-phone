package com.example.vvra;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends Activity implements OnClickListener {
EditText pass1,pass2,name1,secQues,secAns;
Button b;
int f=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		b=(Button)findViewById(R.id.button1);
	//	b2=(Button)findViewById(R.id.button2);
		name1=(EditText)findViewById(R.id.editText1);
		pass1=(EditText)findViewById(R.id.password1);
		pass2=(EditText)findViewById(R.id.editText3);
		secQues=(EditText)findViewById(R.id.editText4);
		secAns=(EditText)findViewById(R.id.editText5);
		b.setOnClickListener(this);
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
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
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		case R.id.button1:
		try{
			String passwd1=pass1.getText().toString();
			String passwd2=pass2.getText().toString();
			String secQ=secQues.getText().toString();
			String secA=secAns.getText().toString();
			String name=name1.getText().toString();
			if(passwd1.equals("")||passwd2.equals(""))
			{
				Toast.makeText(getApplicationContext(),"Enter a password",Toast.LENGTH_SHORT).show();
			}
			else if(secQ.equals("")||secA.equals(""))
			{
				Toast.makeText(getApplicationContext(),"Enter a Security question and answer",Toast.LENGTH_SHORT).show();
			}		
			else if(name.equals("")){
				Toast.makeText(getApplicationContext(),"Enter your name",Toast.LENGTH_LONG).show();
			}
			else if(passwd1.equals(passwd2))
			{
			/* pasword matching.. future enable... */
			 	Pattern p = Pattern.compile("^(?=.*[0-9]+.*)(?=.*[a-zA-Z]+.*)[0-9a-zA-Z]{8,}$");
				Matcher m = p.matcher(passwd1);
				boolean val=m.matches();
				val=true;// delete this line later.. val is true only if pw match
				if(!val)
				{
					Dialog match1=new Dialog(this);
					match1.setTitle("Invalid Password");
					TextView tv1=new TextView(this);
					tv1.setText("Password must contain at least one letter, at least one number, and be longer than eight charaters.");
					pass1.setText("");
					pass2.setText("");	
					match1.setContentView(tv1);
					match1.show();
				}		
				else
				{
					Dialog match=new Dialog(this);
					match.setTitle("SUCCESS!");
					TextView tv=new TextView(this);
					tv.setText("    Password Saved");
					f=1;
					match.setContentView(tv);
					match.show();
					try{
			 	FileOutputStream fout=openFileOutput("textfile.txt", 0);
				OutputStreamWriter osw=new OutputStreamWriter(fout);
				osw.write(passwd1);
				osw.flush();
				osw.close();
				FileOutputStream fout3=openFileOutput("SecQues.txt", 0);
				OutputStreamWriter osw3=new OutputStreamWriter(fout3);
				osw3.write(secQ);
				osw3.flush();
				osw3.close();				
				FileOutputStream fout4=openFileOutput("SecAns.txt", 0);
				OutputStreamWriter osw4=new OutputStreamWriter(fout4);
				osw4.write(secA);
				osw4.flush();
				osw4.close();				
				String flag="1";
				FileOutputStream fout2=openFileOutput("flag.txt", 0);
				OutputStreamWriter osw2=new OutputStreamWriter(fout2);
				osw2.write(flag);
				osw2.flush();
				osw2.close();		
				FileOutputStream fout22=openFileOutput("Name.txt", 0);
				OutputStreamWriter osw22=new OutputStreamWriter(fout22);
				osw22.write(name);
				osw22.flush();
				osw22.close();
				TelephonyManager tManager = (TelephonyManager)getApplicationContext().getSystemService(getApplicationContext().TELEPHONY_SERVICE);
		        String simId=tManager.getSimSerialNumber();
		        String subscriberid=tManager.getSubscriberId();
		        FileOutputStream fout5=openFileOutput("simId.txt", 0);
				OutputStreamWriter osw5=new OutputStreamWriter(fout5);
				osw5.write(simId);
				//Toast.makeText(getApplicationContext(), simId, Toast.LENGTH_LONG).show();
				osw5.flush();
				osw5.close();
				FileOutputStream fout6=openFileOutput("subsId.txt", 0);
				OutputStreamWriter osw6=new OutputStreamWriter(fout6);
				osw6.write(subscriberid);
				//Toast.makeText(getApplicationContext(), subscriberid, Toast.LENGTH_LONG).show();
				osw6.flush();
				osw6.close();
				Intent loginnext=new Intent(getApplicationContext(),MsgReadActivity.class);
				startActivity(loginnext);				
			}
			catch(Exception e){}
		}
		}
		else
		{
			Dialog match=new Dialog(this);
			match.setTitle("Warning!!!");	
			TextView tv=new TextView(this);
			tv.setText("        Passwords Do Not Match");
			match.setContentView(tv);
			match.show();
		}
		}
		catch(Exception e){}
		break;
		
		
			
	}}
}
