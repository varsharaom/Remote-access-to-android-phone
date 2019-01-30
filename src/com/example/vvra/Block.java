package com.example.vvra;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Block extends Activity {
Button addFromContacts,AddNewNo,in;
String ContInfo="",s3="",Pre,Post;
ListView listView22;
String[] arr;
int pos;
ArrayList<String> mylist = new ArrayList<String>();
ArrayAdapter<String> adapter ;
int i,j,len1,k=-1,count=-1;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_block);
		 addFromContacts = (Button) findViewById(R.id.addFromContacts);
		 AddNewNo = (Button) findViewById(R.id.addNewNumber);
		 in=(Button)findViewById(R.id.button1);
		 in.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String str="The contact numbers in the Blocked contacts list will not be able to access your phone.";
				Intent infoscreen=new Intent(getApplicationContext(),Information.class);
				infoscreen.putExtra("datas", str);
				startActivity(infoscreen);
			}
		});
		 listView22 = (ListView)findViewById(R.id.list22);
		 try{
			 FileInputStream fIn =openFileInput("BlockedNumbers.txt");
			InputStreamReader isr=new InputStreamReader(fIn);
			char[] inputbuffer=new char[100];			
			int charread;
			while((charread=isr.read(inputbuffer))>0)
			{
				String readstring=String.copyValueOf(inputbuffer,0,charread);
				s3+=readstring;
				inputbuffer=new char[100];
			}			
		 }
		 catch(Exception e){}
		 String listItem1="";
		 len1=s3.length();
		 
		 for(i=0;i<len1;i++)
		 {	 
			 if(s3.charAt(i)=='`')
			 {
				 	mylist.add(listItem1);
					arr = mylist.toArray(new String[mylist.size()]);
				 	adapter = new ArrayAdapter<String>(this, R.layout.list_text,R.id.list_content,arr );
				 	listView22.setAdapter(adapter);
				 	listItem1="";
			 }
			 else
			 {
				 listItem1+=s3.charAt(i);
			 }
		 }		 
		AddNewNo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//Toast.makeText(getApplicationContext(), "o hell", Toast.LENGTH_LONG).show();
				Intent addnoToBlock=new Intent(getApplicationContext(),AddNewNumberToBlock.class);
				startActivity(addnoToBlock);
				//finish();
			}
		});
		 addFromContacts.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_PICK);
				intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
				startActivityForResult(intent, 1);						
			}
		});
		listView22.setOnItemClickListener(new OnItemClickListener(){
			 public void onItemClick(AdapterView<?> arg0, View v, int position,long id) {
					// TODO Auto-generated method stub
					AlertDialog.Builder adb=new AlertDialog.Builder(Block.this);
			        adb.setTitle("Delete?");
			        adb.setMessage("Are you sure you want to delete ??");
			        final int positionToRemove = position;
			        j=position;
			        pos=j;
			        adb.setNegativeButton("Cancel", null);
			        adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
			            public void onClick(DialogInterface dialog, int which) {
			                mylist.remove(positionToRemove);
			            	arr = mylist.toArray(new String[mylist.size()]);
			            	adapter= new ArrayAdapter<String>(getApplicationContext(),  R.layout.list_text,R.id.list_content,arr);
			            	listView22.setAdapter(adapter);
			            	String s2="";
									 try{
										 FileInputStream fIn =openFileInput("BlockedNumbers.txt");
										InputStreamReader isr=new InputStreamReader(fIn);
										char[] inputbuffer=new char[100];			
										int charread;
										while((charread=isr.read(inputbuffer))>0)
										{
											String readstring=String.copyValueOf(inputbuffer,0,charread);
											s2+=readstring;
											inputbuffer=new char[100];
										}			
									 }
									 catch(Exception e){}	
									 int sl=s2.length();
									 int jj=0,c=0;
									 String pre="";
									 for(jj=0;jj<sl;jj++)
									 {
										 if(c!=pos)
										 {
											 pre+=s2.charAt(jj);
										 }
										 else
										 {
											 do
											 {
												 jj++;
											 }while(s2.charAt(jj)!='`');
										 }
										 if(s2.charAt(jj)=='`')
										 {
											 c++;
										 }
									 }
									 try{
									 		FileOutputStream fout6=openFileOutput("BlockedNumbers.txt", 0);
									 		OutputStreamWriter osw6=new OutputStreamWriter(fout6);
									 		osw6.write(pre);
											osw6.flush();
											osw6.close();
									 	}
									 	catch(Exception e){}							 
			            }});
			        adb.show();	
				}
		    });
	}	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (data != null) {
			Uri uri = data.getData();
			if (uri != null) {
				Cursor c = null;
				try {
					c = getContentResolver()
							.query(uri,
									new String[] {ContactsContract.CommonDataKinds.Phone.NUMBER,ContactsContract.CommonDataKinds.Phone.TYPE,ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME },null, null, null);
					if (c != null && c.moveToFirst()) {
						String number = c.getString(0);
						String name=c.getString(2);
						String str="";
						str=name+":"+number+"`";
						try{
								FileOutputStream fos = openFileOutput("BlockedNumbers.txt",MODE_APPEND);        
								OutputStreamWriter oos=new OutputStreamWriter(fos);
								oos.append(str);
						     	oos.flush();
						     	oos.close(); 					
							}
							catch(Exception e){}
							mylist.add(name+" : "+number);
							String[] arr = mylist.toArray(new String[mylist.size()]);
							adapter = new ArrayAdapter<String>(this, R.layout.list_text,R.id.list_content,arr );
							listView22.setAdapter(adapter);
						}
				} finally {
					if (c != null) {
						c.close();
					}
				}
			}
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.block, menu);
		return true;
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		Intent backToSettingsPage=new Intent(getApplicationContext(),Settings.class);
	startActivity(backToSettingsPage);        
		finish();
	}
}
