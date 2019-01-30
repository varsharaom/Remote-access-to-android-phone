package com.example.vvra;


import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewNumber extends Activity {
Button save;
EditText name,no;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_new_number);
		name=(EditText)findViewById(R.id.addName);
		no=(EditText)findViewById(R.id.AddNumb);
	    save=(Button)findViewById(R.id.save);
	    save.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String Cname=name.getText().toString();
				String Cno=no.getText().toString();
				if(Cname.equals("")||Cno.equals(""))
				{
					Toast.makeText(getApplicationContext(), "Enter contact details", Toast.LENGTH_LONG).show();
				}
				else
				{
					String continfo=Cname+":"+Cno+"`";
					try{
						FileOutputStream fos = openFileOutput("newNo.txt",MODE_APPEND);        
						OutputStreamWriter oos=new OutputStreamWriter(fos);
						oos.append(continfo);
						oos.flush();
						oos.close(); 					
					}
					catch(Exception e){}
					Intent addno=new Intent(getApplicationContext(),AddPeople.class);
					startActivity(addno);
					finish();
				}
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_new_number, menu);
		return true;
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		Intent addNoNext=new Intent(getApplicationContext(),AddPeople.class);
		startActivity(addNoNext);        
		finish();
	}
}