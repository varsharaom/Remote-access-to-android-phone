package com.example.vvra;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewNumberToBlock extends Activity {
EditText newNum;
Button add;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_new_number_to_block);
		newNum=(EditText)findViewById(R.id.enterNumber);
		add=(Button)findViewById(R.id.okButton);
	    add.setOnClickListener(new View.OnClickListener() {	
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					//String Cname=name.getText().toString();
					String CNewno=newNum.getText().toString();
					if(CNewno.equals(""))
					{
						Toast.makeText(getApplicationContext(), "Enter contact number", Toast.LENGTH_LONG).show();
					}
					else
					{
						String contactinfo="Unknown:"+CNewno+"`";
						try{
							FileOutputStream fos = openFileOutput("BlockedNumbers.txt",MODE_APPEND);        
							OutputStreamWriter oos=new OutputStreamWriter(fos);
							oos.append(contactinfo);
							oos.flush();
							oos.close(); 					
						}
						catch(Exception e){}
						Intent BlockPage=new Intent(getApplicationContext(),Block.class);
						startActivity(BlockPage);
						finish();
					}
				}
			});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_new_number_to_block, menu);
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
