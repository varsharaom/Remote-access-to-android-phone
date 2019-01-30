package com.example.vvra;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;

public class AddPeople extends Activity {
Button addContacts,NewNo,g;
String ContInfo="",s="",Pre,Post;
ListView listView;
String[] arr;
int pos;
ArrayList<String> mylist = new ArrayList<String>();
ArrayAdapter<String> adapter ;
int i,j,len,k=-1,count=-1;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_people);
		 addContacts = (Button) findViewById(R.id.addContact);
		 NewNo = (Button) findViewById(R.id.addNo);
		 g = (Button) findViewById(R.id.button1);
		 g.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String str="In case your phone is stolen,a message will be sent to the people in this list,along with the GPS coordinates" +
						" of your phone.Dont forget to enable the Location services in your phone.";
				Intent infoscreen=new Intent(getApplicationContext(),Information.class);
				infoscreen.putExtra("datas", str);
				startActivity(infoscreen);
			}
		});
		 listView = (ListView)findViewById(R.id.list);
		 try{
			 FileInputStream fIn =openFileInput("newNo.txt");
			InputStreamReader isr=new InputStreamReader(fIn);
			char[] inputbuffer=new char[100];			
			int charread;
			while((charread=isr.read(inputbuffer))>0)
			{
				String readstring=String.copyValueOf(inputbuffer,0,charread);
				s+=readstring;
				inputbuffer=new char[100];
			}			
		 }
		 catch(Exception e){}
		 String listItem="";
		 len=s.length();
		 
		 for(i=0;i<len;i++)
		 {	 
			 if(s.charAt(i)=='`')
			 {
				 	mylist.add(listItem);
					arr = mylist.toArray(new String[mylist.size()]);
				 	adapter = new ArrayAdapter<String>(this, R.layout.list_text,R.id.list_content,arr );
				 	listView.setAdapter(adapter);
				 	listItem="";
			 }
			 else
			 {
				 listItem+=s.charAt(i);
			 }
		 }		 
		NewNo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent addno=new Intent(getApplicationContext(),AddNewNumber.class);
				startActivity(addno);
				finish();
			}
		});
		 addContacts.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_PICK);
				intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
				startActivityForResult(intent, 1);						
			}
		});
		listView.setOnItemClickListener(new OnItemClickListener(){
			 public void onItemClick(AdapterView<?> arg0, View v, int position,long id) {
					// TODO Auto-generated method stub
					AlertDialog.Builder adb=new AlertDialog.Builder(AddPeople.this);
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
			            	listView.setAdapter(adapter);
			            	String s2="";
									 try{
										 FileInputStream fIn =openFileInput("newNo.txt");
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
									 		FileOutputStream fout6=openFileOutput("newNo.txt", 0);
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
								FileOutputStream fos = openFileOutput("newNo.txt",MODE_APPEND);        
								OutputStreamWriter oos=new OutputStreamWriter(fos);
								oos.append(str);
						     	oos.flush();
						     	oos.close(); 					
							}
							catch(Exception e){}
							mylist.add(name+" : "+number);
							String[] arr = mylist.toArray(new String[mylist.size()]);
							adapter = new ArrayAdapter<String>(this, R.layout.list_text,R.id.list_content,arr );
							listView.setAdapter(adapter);
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
		getMenuInflater().inflate(R.menu.add_people, menu);
		return true;
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		Intent titlenext=new Intent(getApplicationContext(),CarouselMain.class);
		startActivity(titlenext);        
		finish();
	}
}