package com.example.vvra;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.provider.ContactsContract.PhoneLookup;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
//import android.util.Log;
import android.widget.Toast;

public class MsgReadActivity extends BroadcastReceiver {
	String passwd, s = "", str = "", message1, phoneNo1, SenderNo1,
			Contactname, Contact, inboxMsgs = "", CallLogMsgs = "";
	int flag = 0;
	String s2 = "", s22 = "varsha", s5 = "", s4 = "", s6;
	String contactMsg = "", newNum = "",coordinates;
	double latitude, longitude;
	int respond = 1;
	int targetmin, targethour;
	int hour11, hour1, h;
	Cursor c5;
	SQLiteDatabase sqdb;
	String myname="";
	String simIdFromFile = "",simIdOnBoot="",subscriberIdOnBoot="",x6,x7;
	// private LocationManager locationMangaer=null;
	// private LocationListener locationListener=null;
SmsManager smsnew1;
	private Boolean flag1 = false;
	// Context x;

	final SmsManager sms = SmsManager.getDefault();
	final SmsManager sms2 = SmsManager.getDefault();
	final SmsManager sms3 = SmsManager.getDefault();
	int i = 0;

	public void readContact(Context arg0, String cName) {
		int j;
		int f;
		int len = cName.length();
		ContentResolver cr = arg0.getContentResolver();
		Cursor cur = cr.query(
				ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null,
				null, null);
		while (cur.moveToNext()) {
			f = 1;
			String ContName = cur
					.getString(cur
							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
			String ContPhNo = cur
					.getString(cur
							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
			for (j = 0; j < len; j++) {
				if (Character.toLowerCase(cName.charAt(j)) != Character
						.toLowerCase(ContName.charAt(j))) {
					f = 0;
					break;
				}
			}
			if (f == 1) {
				contactMsg += ContName + ":" + ContPhNo + "\n";
			}
		}
		if (contactMsg.equals("")) {
			contactMsg = "Contact not found.";
		}
		//Toast.makeText(arg0, "heck", Toast.LENGTH_LONG).show();
		ArrayList<String> parts = sms2.divideMessage(contactMsg);
		sms2.sendMultipartTextMessage(phoneNo1, null, parts, null, null);
	}

	public String getContactName(Context context, String phoneNumber) {
		ContentResolver cr = context.getContentResolver();
		Uri uri = Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI,
				Uri.encode(phoneNumber));
		Cursor cursor = cr.query(uri,
				new String[] { PhoneLookup.DISPLAY_NAME }, null, null, null);
		if (cursor == null) {
			return null;
		}
		String contactName = null;
		if (cursor.moveToFirst()) {
			contactName = cursor.getString(cursor
					.getColumnIndex(PhoneLookup.DISPLAY_NAME));
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return contactName;
	}

	@Override
	public void onReceive(final Context arg0, Intent arg1) {
		// TODO Auto-generated method stub
		final Bundle bundle = arg1.getExtras();
		if (arg1.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
			
			
		/*	Thread timer=new Thread(){
				public void run(){
					try{
						sleep(7000);
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
					/*	TelephonyManager tManager = (TelephonyManager) arg0
								.getSystemService(Context.TELEPHONY_SERVICE);
						 simIdOnBoot = tManager.getSimSerialNumber();
						subscriberIdOnBoot = tManager.getSubscriberId();
							
						Toast.makeText(arg0,"\n"+simIdOnBoot+"\n"+subscriberIdOnBoot,Toast.LENGTH_LONG).show();
						/*Intent splashnext=new Intent(getApplicationContext(),CarouselMain.class);
					//	splashnext.putExtra("data1", "0");
						startActivity(splashnext);*/
				//	}
				//}
			//};
		//	timer.start();*/
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			TelephonyManager tManager = (TelephonyManager) arg0
					.getSystemService(Context.TELEPHONY_SERVICE);
			String simIdOnBoot = tManager.getSimSerialNumber();
			String subscriberIdOnBoot = tManager.getSubscriberId();
			String simIdFromFile = "";
			try {
				FileInputStream fIns = arg0.getApplicationContext()
						.openFileInput("simId.txt");
				InputStreamReader isrs = new InputStreamReader(fIns);
				char[] buffer1 = new char[100];
				int charread1;
				while ((charread1 = isrs.read(buffer1)) > 0) {
					String readstring = String.copyValueOf(buffer1, 0,
							charread1);
					simIdFromFile += readstring;
					buffer1 = new char[100];

				}
			//	Toast.makeText(arg0,"simIdfrom file is "
			//	+simIdFromFile+"\nsimIdOnboot is "+simIdOnBoot,Toast.LENGTH_LONG).show();
				if (!(simIdFromFile.equals(simIdOnBoot))) {// GPS FINDING LONGI
															// LATI START
															// locationMangaer =
															// (LocationManager)arg0.getSystemService(arg0.LOCATION_SERVICE);
															// locationListener
															// = new
															// MyLocationListener();

					// locationMangaer.requestLocationUpdates(LocationManager.GPS_PROVIDER,
					// 5000, 10,
					// locationListener);

					// locationListener = new MyLocationListener(arg0);

					// locationMangaer.requestLocationUpdates(LocationManager.GPS_PROVIDER,
					// 5000, 10,
					// locationListener);
					// Toast.makeText(arg0, "to hell with nitte"+s22,
					// Toast.LENGTH_LONG).show();
					// String ff= locationListener.getLoc();
					//Thread.sleep(8000);
					try {
						GPSTracker gps = new GPSTracker(arg0);

						// check if GPS enabled
					/*	if (gps.canGetLocation()) {

							latitude = gps.getLatitude();
							longitude = gps.getLongitude();

							// \n is for new line
							Toast.makeText(
									arg0,
									"Your Location is - \nLat: " + latitude
											+ "\nLong: " + longitude,
									Toast.LENGTH_LONG).show();
						} */
						//else {
							String provider = android.provider.Settings.Secure
									.getString(
											arg0.getContentResolver(),
											android.provider.Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
							if (!provider.contains("gps")) {
								// if gps is disabled
								final Intent poke = new Intent();
								poke.setClassName("com.android.settings",
										"com.android.settings.widget.SettingsAppWidgetProvider");
								poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
								poke.setData(Uri.parse("3"));
							//	Toast.makeText(arg0, "hwlllll",
								//		Toast.LENGTH_LONG).show();
								arg0.sendBroadcast(poke);
							//	latitude = gps.getLatitude();
							//	longitude = gps.getLongitude();
							}
							
							try{
							Thread.sleep(10000);}
							catch(Exception e){
								
							}
							
							
							if (gps.canGetLocation()) {

								latitude = gps.getLatitude();
								longitude = gps.getLongitude();

								// \n is for new line
								Toast.makeText(
										arg0,
										"Your Location is - \nLat: " + latitude
												+ "\nLong: " + longitude,
										Toast.LENGTH_LONG).show();}
							// GPS ENDS
								// can't get location
								// GPS or Network is not enabled
								// Ask user to enable GPS/network in settings
								// gps.showSettingsAlert();
						//}
						// File file = arg0.getFileStreamPath("newNo.txt");
						 //   if(file == null || !file.exists()) {
						
						
						FileInputStream fIns1 = arg0.getApplicationContext()
								.openFileInput("newNo.txt");
						InputStreamReader isrs1 = new InputStreamReader(fIns1);
						char[] buffer12 = new char[100];
						int charread12;
						while ((charread12 = isrs1.read(buffer12)) > 0) {
							String readstring = String.copyValueOf(buffer12, 0,
									charread12);
							s2 += readstring;
							buffer12 = new char[100];
						}
						
						   // }
					}// close of try block for gps code
					catch (Exception e) {
						
					}
				//	if(!s2.equals("")){
					//Toast.makeText(arg0, "not equal", Toast.LENGTH_LONG).show();
					int sl = s2.length();
					int jj = 0;
					String num = "";
					for (jj = 0; jj < sl; jj++) {
						if (s2.charAt(jj) == ':') {
							while (s2.charAt(jj) != '`') {
								num += s2.charAt(jj);
								jj++;
							}
						}
						if (s2.charAt(jj) == '`') {
							try {
myname="";
								//get the name from the file
								
								FileInputStream next1 = arg0.getApplicationContext()
										.openFileInput("Name.txt");
								InputStreamReader next11 = new InputStreamReader(next1);
								char[] fer1 = new char[100];
								int read1;
								while ((read1 = next11.read(buffer1)) > 0) {
									String readstring = String.copyValueOf(buffer1, 0,
											read1);
									myname += readstring;
									fer1 = new char[100];

								}
							
							//to check the integer part 0f the long n latitude and appends zero	
				//Toast.makeText(arg0, "jackson", Toast.LENGTH_LONG).show();				
								
					/*	int longg=(int)longitude;
						int latt=(int)latitude;
								
						if(latt==0){
							 x6="000.0000000";
						}
						if(latt<10&&latt>0){
							String x9=String.valueOf(latitude);
							 x6="00"+x9;
						}
						if(latt<100&&latt>9){
							String x9=String.valueOf(latitude);
							 x6="0"+x9;
						}
						if(longg==0){
							 x7="000.0000000";
						}
						if(longg<10&&longg>0){
							String x9=String.valueOf(longitude);
							 x7="00"+x9;
						}
						if(longg<100&&longg>9){
							String x9=String.valueOf(longitude);
							 x7="0"+x9;
						}
						*/
						
				//	Toast.makeText(arg0,"long "+longitude+"\nlat "+latitude, Toast.LENGTH_LONG).show();			
								
								
								
								
								
								SmsManager smsnew = SmsManager.getDefault();
								String theft = "New sim id: " + simIdOnBoot
										+ "\nNew subs id: "
										+ subscriberIdOnBoot + " latitude "
										+ latitude + " longitude " + longitude+" sender "+myname;
								ArrayList<String> parts = smsnew
										.divideMessage(theft);
								smsnew.sendMultipartTextMessage(num, null,
										parts, null, null);
							} catch (Exception e) {
								Toast.makeText(arg0, "Msg sending failed",
										Toast.LENGTH_LONG).show();
							}
							num = "";
						}
					}
					
					
			//	}
					
					
					
					
					FileOutputStream fout5 = arg0.getApplicationContext()
							.openFileOutput("simId.txt", 0);
					OutputStreamWriter osw5 = new OutputStreamWriter(fout5);
					osw5.write(simIdOnBoot);
					osw5.flush();
					osw5.close();
					FileOutputStream fout6 = arg0.getApplicationContext()
							.openFileOutput("subsId.txt", 0);
					OutputStreamWriter osw6 = new OutputStreamWriter(fout6);
					osw6.write(subscriberIdOnBoot);
					osw6.flush();
					osw6.close();
				}// close of comparing sim id and msging
			} catch (Exception e) {
			}
		}// close of action boot completed
		// \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

		try {
			if (bundle != null) {
				final Object[] pdusObj = (Object[]) bundle.get("pdus");
				for (int i = 0; i < pdusObj.length; i++) {
					SmsMessage currentMsg = SmsMessage
							.createFromPdu((byte[]) pdusObj[i]);
					phoneNo1 = currentMsg.getDisplayOriginatingAddress();
					SenderNo1 = phoneNo1;
					message1 = currentMsg.getDisplayMessageBody();
				}
			}
	//		Toast.makeText(arg0, phoneNo1, Toast.LENGTH_LONG).show();
			respond = 1;
		} catch (Exception e) {
		}
		// at the end of this we get the incoming number

		try {

			FileInputStream fIns11 = arg0.getApplicationContext()
					.openFileInput("BlockedNumbers.txt");
			InputStreamReader isrs11 = new InputStreamReader(fIns11);
			char[] buffer12 = new char[100];
			int charread12;
			while ((charread12 = isrs11.read(buffer12)) > 0) {
				String readstring = String.copyValueOf(buffer12, 0, charread12);
				s4 += readstring;
				buffer12 = new char[100];
			}

		} catch (Exception e) {

		}

		int sl = s4.length();
		int jj = 0;
		String num = "";
		for (jj = 0; jj < sl; jj++) {
			if (s4.charAt(jj) == ':') {
				jj++;
				while (s4.charAt(jj) != '`') {

					num += s4.charAt(jj);
					jj++;
				}
			}
			if (s4.charAt(jj) == '`') {
				try {// after the end of every contact
						// u shud compare the incoming number with the contact
						// extracted from the file
					String spaceRemovedNum = num.replaceAll("\\s+", "");
					int len = spaceRemovedNum.length();
				/*	Toast.makeText(
							arg0,
							"spaceRemovedNum " + spaceRemovedNum
									+ "\nlength of spaceremoved is " + len,
							Toast.LENGTH_LONG).show();*/
					newNum = "";
					if (len == 10) {
						newNum = "+91" + spaceRemovedNum;
					}

					else {

						newNum = spaceRemovedNum;
					}

				/*	Toast.makeText(arg0,
							"newnum for final comparison " + newNum,
							Toast.LENGTH_LONG).show();*/

					if (newNum.equals(phoneNo1)) {
						respond = 0;

					}
				/*	Toast.makeText(
							arg0,
							"respond " + respond + "\nnew num comparison is "
									+ newNum + "\nphone num is " + phoneNo1,
							Toast.LENGTH_LONG).show();*/
					// SmsManager smsnew = SmsManager.getDefault();
					// String
					// theft="New sim id: "+simIdOnBoot+"\nNew subs id: "+subscriberIdOnBoot+" latitude "+latitude+" longitude "+longitude;
					// ArrayList<String> parts = smsnew.divideMessage(theft);
					// smsnew.sendMultipartTextMessage(num, null, parts, null,
					// null);
				} catch (Exception e) {
					//Toast.makeText(arg0, "excptn", Toast.LENGTH_LONG).show();
				}
				num = "";
			}
		}

		// opening the password file extacting the password and
		// comparing the content of the incoming msg
		try {
			FileInputStream fIn = arg0.getApplicationContext().openFileInput(
					"textfile.txt");
			InputStreamReader isr = new InputStreamReader(fIn);
			char[] inputbuffer = new char[100];
			int charread;
			while ((charread = isr.read(inputbuffer)) > 0) {
				String readstring = String
						.copyValueOf(inputbuffer, 0, charread);
				s += readstring;
				inputbuffer = new char[100];
			}
			
			
			if((message1.length())>100){
			
			
			String comp=message1.substring(0, 12);
			//Toast.makeText(arg0, "comp is "+comp, Toast.LENGTH_LONG).show();
			if(comp.equals("New sim id: ")){
				//if the starting of the message is new Sim id then
			//	Toast.makeText(arg0, "inside comp", Toast.LENGTH_LONG).show();
				int leng1=message1.length();
				int k=71;
				//while(!(message1.charAt(k)))
				
			int dor=0;	
			int g=0;
			StringBuilder lat=new StringBuilder();
			StringBuilder lng=new StringBuilder();
			for(g=0;g<message1.length();g++){
				if(message1.charAt(g)=='l'){
					
					if(dor==1){
						g+=10;
						while(message1.charAt(g)!=' '){
							//Toast.makeText(arg0, "jjjjj", Toast.LENGTH_LONG).show();
							lng.append(message1.charAt(g));
							g++;
						}
						break;
					}
					if(dor==0){
						g+=9;
						while(message1.charAt(g)!=' '){
							lat.append(message1.charAt(g));
							g++;
						}
						dor=1;
					}
					
				}
			}
				
				int y;
				for(y=g;y<message1.length();y++){
					if(message1.charAt(y)=='r')
					{
						break;
					}
				}
				
				String xname=message1.substring(y+2);
				
				
				
				
				
				
			//	String lat=message1.substring(71,82);
				//String lng=message1.substring(72,82);
				//String lat=message1.substring(93,103);
			//	String lng=message1.substring(93,104);
				String ournum=phoneNo1;
				//String xname=message1.substring(111);
				String sent="Friend name:"+xname+",PhoneNo:"+ournum+",Longitude:"+lng+",Latitude:"+lat;
			//	Toast.makeText(arg0,"long "+lng+"\nlat "+lat+"\nleng "+leng1+"\nnum "+ournum+"\nname "+xname+"\nsent "+sent,Toast.LENGTH_LONG).show();
				
				
				try{
				sqdb = arg0.getApplicationContext().openOrCreateDatabase("hello", 0, null);
				sqdb.execSQL("create table if not exists inf(number varchar(15),latitude varchar(15),longitude varchar(15),name varchar(20),sentence varchar(150));");
				//Toast.makeText(arg0, "me", Toast.LENGTH_LONG).show();
				sqdb.execSQL("insert into inf values('"
						+ ournum + "','"
						+ lat + "','"
						+ lng + "','"
						+ xname + "','"
						+ sent + "');");
				//Toast.makeText(arg0, "entered",
				//		Toast.LENGTH_LONG).show();
				}
				catch(Exception e){
				//	Toast.makeText(arg0, "me excptn", Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}
				
				
				
				
				c5 = sqdb.rawQuery(
						"select number,latitude,longitude,name,sentence from inf", null);
			//	Toast.makeText(arg0, "aftr",
				//		Toast.LENGTH_LONG).show();
				// c.moveToFirst();
				StringBuilder s = new StringBuilder();
				while (c5.moveToNext()) {
					s.append(c5.getString(0)).append(",").append(c5.getString(1))
							.append(",").append(c5.getString(2)).append(",").append(c5.getString(3)).append(",").append(c5.getString(4)).append("\n");

				}
		/*		Toast.makeText(arg0,
						s.substring(0, s.length()), Toast.LENGTH_LONG).show();*/
				
				
				/*try {
					FileOutputStream fco = arg0.getApplicationContext()
							.openFileOutput("Coordinates.txt", 0);
					OutputStreamWriter oswco = new OutputStreamWriter(fco);
					 coordinates=lat+" "+lng;
					oswco.write(coordinates);
					oswco.flush();
					oswco.close();
				} catch (Exception e) {
					Toast.makeText(arg0, "exception creating file coordinates", Toast.LENGTH_LONG).show();
				}
				
				*/
				
				
				
				
			
				
				
				
			}
				
				
				
			}
			//Toast.makeText(arg0, coordinates, Toast.LENGTH_LONG).show();
			
			if (message1.equals(s) && respond == 1) {
				String options = "1a:Inbox SMS\n1b:Sent items\n1c: Drafts\n"
						+ "2c:Call Log\n3s:Start Call Divert\n3e:End Call Divert\n4-<ContactName>:Contact number\n5a-To turn off silent mode\n6a-To get location";
				try {
					SmsManager sms = SmsManager.getDefault();
					ArrayList<String> parts = sms.divideMessage(options);
					sms.sendMultipartTextMessage(phoneNo1, null, parts, null,
							null);
				} catch (Exception e) {
					Toast.makeText(arg0, "Msg sending failed",
							Toast.LENGTH_LONG).show();
				}
				Calendar c = Calendar.getInstance();
				int min1 = c.get(Calendar.MINUTE);
				int hour1 = c.get(Calendar.HOUR_OF_DAY);

				/*
				 * if(min1<50){ targetmin=min1+10; targethour=hour1; } else {
				 * 
				 * targethour=hour1+1; targetmin=(min1+10)%60; }
				 */
				/*
				 * if(hour1<=22){ targethour=hour1+1; }else{ targethour=1; }
				 */

				try {

					FileInputStream fIns111 = arg0.getApplicationContext()
							.openFileInput("Time.txt");
					InputStreamReader isrs111 = new InputStreamReader(fIns111);
					char[] buffer121 = new char[100];
					int charread121;
					while ((charread121 = isrs111.read(buffer121)) > 0) {
						String readstring = String.copyValueOf(buffer121, 0,
								charread121);
						s5 += readstring;
						buffer121 = new char[100];
					}

				} catch (Exception e) {
					//Toast.makeText(arg0, "exxxxxxx", Toast.LENGTH_LONG).show();
				}
				if (s5.equals("")) {
					h = 1;
				} else {
					h = Integer.parseInt(s5);
				}

				targethour = (hour1 + h) % 24;
			/*	Toast.makeText(
						arg0,
						"hour" + hour1 + "\nh " + h + "\ntargethour "
								+ targethour, Toast.LENGTH_LONG).show();*/
				String x = String.valueOf(targethour);

			//	Toast.makeText(arg0, x, Toast.LENGTH_LONG).show();

				try {
					FileOutputStream fout5663 = arg0.getApplicationContext()
							.openFileOutput("targettime.txt", 0);
					OutputStreamWriter osw5663 = new OutputStreamWriter(
							fout5663);
					osw5663.write(x);
					osw5663.flush();
					osw5663.close();
				} catch (Exception e) {
				//	Toast.makeText(arg0, "exceptionnn1111", Toast.LENGTH_LONG)
					//		.show();
				}

				try {
					FileOutputStream fout = arg0.getApplicationContext()
							.openFileOutput("phno.txt", 0);
					OutputStreamWriter osw = new OutputStreamWriter(fout);
					osw.write(phoneNo1);
					osw.flush();
					osw.close();
				} catch (Exception e) {
				}
			} else if (respond == 1) {
				String m = "4-";
				String m2 = message1.substring(0, 2);
				int l = message1.length();
				String contName = message1.substring(2, l);
				if (m.equals(m2)) {
					flag = 1;
				}
				if (message1.equals("1a") || message1.equals("1b")
						|| message1.equals("1c") || message1.equals("2c")
						|| message1.equals("3s") || message1.equals("3e")
						|| (flag == 1) || message1.equals("5a")||message1.equals("6a")) {
				//	Toast.makeText(arg0, message1, Toast.LENGTH_LONG).show();
					Calendar cal = Calendar.getInstance();
					SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
					String formattedDate = df.format(cal.getTime());
					try {
						FileInputStream phone = arg0.getApplicationContext()
								.openFileInput("phno.txt");
						InputStreamReader newisr = new InputStreamReader(phone);
						char[] inputbuffer1 = new char[100];
						int charread1;
						while ((charread1 = newisr.read(inputbuffer1)) > 0) {
							String readstring = String.copyValueOf(
									inputbuffer1, 0, charread1);
							str += readstring;
						//	Toast.makeText(arg0, "str is " + str,
							//		Toast.LENGTH_LONG).show();
							inputbuffer1 = new char[100];
						}
					/*	Toast.makeText(arg0,
								"str " + str + "\nphoneNo1 " + phoneNo1,
								Toast.LENGTH_LONG).show();*/
						if (str.equals(phoneNo1)) {

							try {
								s6 = "";

								FileInputStream p = arg0
										.getApplicationContext().openFileInput(
												"targettime.txt");
								InputStreamReader q = new InputStreamReader(p);
								char[] bu = new char[100];
								int read1211;
							//	Toast.makeText(arg0, "i m here",
								//		Toast.LENGTH_LONG).show();
								while ((read1211 = q.read(bu)) > 0) {
									String readstring = String.copyValueOf(bu,
											0, read1211);
									s6 += readstring;
								/*	Toast.makeText(arg0, "read is" + s6,
											Toast.LENGTH_LONG).show();*/
									bu = new char[100];
								}

							} catch (Exception e) {
								//Toast.makeText(arg0, "exception file not exst",
									//	Toast.LENGTH_LONG).show();
							}
						/*	Toast.makeText(arg0, "s6    is :" + s6,
									Toast.LENGTH_LONG).show();*/

							int th = Integer.parseInt(s6);

							Calendar c2 = Calendar.getInstance();
							// int min11 = c2.get(Calendar.MINUTE);

							hour11 = c2.get(Calendar.HOUR_OF_DAY);
						/*	Toast.makeText(arg0,
									"hour11 is " + hour11 + " th " + th,
									Toast.LENGTH_LONG).show();*/
							if (hour11 != th)

							{

								if (message1.equals("1a")
										|| message1.equals("1b")
										|| message1.equals("1c")) {
									String[] reqCols = new String[] { "_id",
											"address", "body", "date" };
									Cursor c = null;
									if (message1.equals("1a")) {
										Uri inboxURI = Uri
												.parse("content://sms/inbox");
										ContentResolver cr = arg0
												.getContentResolver();
										c = cr.query(inboxURI, reqCols, null,
												null, null);
									} else if (message1.equals("1b")) {
										Uri sentURI = Uri
												.parse("content://sms/sent");
										ContentResolver cr = arg0
												.getContentResolver();
										c = cr.query(sentURI, reqCols, null,
												null, null);
									} else if (message1.equals("1c")) {
										Uri draftURI = Uri
												.parse("content://sms/draft");
										ContentResolver cr = arg0
												.getContentResolver();
										c = cr.query(draftURI, reqCols, null,
												null, null);
									}
									if (c.moveToFirst()) {
										int ss = 0;
										int ss2 = c.getCount();
										while (ss != ss2) {
											String RetMsg = c
													.getString(c
															.getColumnIndexOrThrow("body"));
											String RetNo = c
													.getString(c
															.getColumnIndexOrThrow("address"));
											String Retdate = c
													.getString(c
															.getColumnIndexOrThrow("date"));
											Date actualdate = new Date(
													Long.valueOf(Retdate));
											String msgdate = (String) android.text.format.DateFormat
													.format("dd-MM-yyyy",
															actualdate);
											String time = (String) android.text.format.DateFormat
													.format("hh:mm:ss",
															actualdate);
											String name1 = getContactName(
													arg0,
													c.getString(c
															.getColumnIndexOrThrow("address")));
											c.moveToNext();
											ss++;
											if (formattedDate.equals(msgdate)) {
												inboxMsgs += name1 + ":"
														+ RetNo + "\n"
														+ msgdate + "  " + time
														+ "\n" + RetMsg
														+ "\n\n";
											}
										}
									}
									try {
										if (inboxMsgs.equals("")) {
											inboxMsgs = "No messages found today";
										}
										ArrayList<String> parts = sms2
												.divideMessage(inboxMsgs);
										sms2.sendMultipartTextMessage(phoneNo1,
												null, parts, null, null);
									} catch (Exception e) {
										Toast.makeText(arg0,
												"Msg sending failed",
												Toast.LENGTH_LONG).show();
									}
								} else if (message1.equals("2c")) {
									Cursor managedCursor = arg0
											.getContentResolver().query(
													CallLog.Calls.CONTENT_URI,
													null, null, null, null);
									int number = managedCursor
											.getColumnIndex(CallLog.Calls.NUMBER);
									int type = managedCursor
											.getColumnIndex(CallLog.Calls.TYPE);
									int date = managedCursor
											.getColumnIndex(CallLog.Calls.DATE);
									int count = managedCursor.getCount();
									i = 0;
									if (managedCursor.moveToLast()) {
										while (i < count) {
											String phNumber = managedCursor
													.getString(number);
											String callType = managedCursor
													.getString(type);
											String callDate = managedCursor
													.getString(date);
											Date callDayTime = new Date(
													Long.valueOf(callDate));
											String calldate = (String) android.text.format.DateFormat
													.format("dd-MM-yyyy",
															callDayTime);
											String Calltime = (String) android.text.format.DateFormat
													.format("hh:mm:ss",
															callDayTime);
											String name2 = getContactName(
													arg0,
													managedCursor
															.getString(managedCursor
																	.getColumnIndexOrThrow("number")));
											String dir = null;
											int dircode = Integer
													.parseInt(callType);
											switch (dircode) {
											case CallLog.Calls.OUTGOING_TYPE:
												dir = "OUTGOING";
												break;
											case CallLog.Calls.INCOMING_TYPE:
												dir = "INCOMING";
												break;
											case CallLog.Calls.MISSED_TYPE:
												dir = "MISSED";
												break;
											}
											if (formattedDate.equals(calldate)) {
												CallLogMsgs += "\n" + name2
														+ ":" + phNumber
														+ "\nType-" + dir
														+ "\n" + calldate + " "
														+ Calltime + "\n";
											}
											i++;
											managedCursor.moveToPrevious();
										}// end while
									}// end if
									managedCursor.close();
									try {
										if (CallLogMsgs.equals("")) {
											CallLogMsgs = "No calls are recieved or dialled today";
										}
										ArrayList<String> parts2 = sms3
												.divideMessage(CallLogMsgs);
										sms3.sendMultipartTextMessage(phoneNo1,
												null, parts2, null, null);
									} catch (Exception e) {
										Toast.makeText(arg0,
												"Msg sending failed",
												Toast.LENGTH_LONG).show();
									}
								}// end else if
								else if (message1.equals("3s")) {
									Intent msgReadActNext = new Intent();
									msgReadActNext.setClassName(
											"com.example.vvra",
											"com.example.vvra.CallDivert");
									msgReadActNext
											.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
									String no = "*21*" + phoneNo1 + "#";
									msgReadActNext.putExtra("data", no);
									Toast.makeText(arg0, "Call diverting",
											Toast.LENGTH_LONG).show();
									arg0.startActivity(msgReadActNext);
								} else if (message1.equals("3e")) {
									Intent msgReadActNext = new Intent();
									msgReadActNext.setClassName(
											"com.example.vvra",
											"com.example.vvra.CallDivert");
									msgReadActNext
											.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
									// String no="#21#";
									String no = "##21#";
									msgReadActNext.putExtra("data", no);
									arg0.startActivity(msgReadActNext);
								} else if (flag == 1) {
									readContact(arg0, contName);
								} else if (message1.equals("5a")) {
								//	Toast.makeText(arg0, message1 + "inside",
									//		Toast.LENGTH_LONG).show();
									final AudioManager mode = (AudioManager) arg0
											.getSystemService(arg0.AUDIO_SERVICE);
									mode.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
								}
								else if(message1.equals("6a")){
									//Toast.makeText(arg0, "6a", Toast.LENGTH_LONG).show();
									
									
									
						
									try {
										GPSTracker gps1 = new GPSTracker(arg0);

									
										//else {
											String provider = android.provider.Settings.Secure
													.getString(
															arg0.getContentResolver(),
															android.provider.Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
											if (!provider.contains("gps")) {
												// if gps is disabled
												final Intent poke = new Intent();
												poke.setClassName("com.android.settings",
														"com.android.settings.widget.SettingsAppWidgetProvider");
												poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
												poke.setData(Uri.parse("3"));
												//Toast.makeText(arg0, "hwlllll",
													//	Toast.LENGTH_LONG).show();
												arg0.sendBroadcast(poke);
											//	latitude = gps.getLatitude();
											//	longitude = gps.getLongitude();
											}
											
									/*		try{
											Thread.sleep(10000);}
											catch(Exception e){
												
											}*/
											
											
											if (gps1.canGetLocation()) {

												latitude = gps1.getLatitude();
												longitude = gps1.getLongitude();
												Thread.sleep(1000);
												
												latitude = gps1.getLatitude();
												longitude = gps1.getLongitude();
												Thread.sleep(1000);
												latitude = gps1.getLatitude();
												longitude = gps1.getLongitude();
												// \n is for new line
											/*	Toast.makeText(
														arg0,
														"Your Location is - \nLat: " + latitude
																+ "\nLong: " + longitude,
														Toast.LENGTH_LONG).show();*/
											try{	
												 smsnew1 = SmsManager.getDefault();
												String cc = "Latitude:"+latitude+" Longitude:"+longitude;
												ArrayList<String> parts = smsnew1
														.divideMessage(cc);
												smsnew1.sendMultipartTextMessage(phoneNo1, null,
														parts, null, null);
											} catch (Exception e) {
												Toast.makeText(arg0, "Msg sending failed",
														Toast.LENGTH_LONG).show();
											}
											
											
											
											}
											else{
												
											}
										
										
										
										
										   // }
									}// close of try block for gps code							
									
									catch(Exception e){
										
									}
									
									
									
									
									
									
									
									
									
									
									
									
									
									
								}
							}

							else {
								Toast.makeText(arg0, "Deleting file",
										Toast.LENGTH_LONG).show();
								File dir = arg0.getFilesDir();
								File file = new File(dir, "phno.txt");
								boolean deleted = file.delete();
								String exitMsg = "";
								if (deleted) {
									// exitMsg="Exit Successful.\nMake sure you have deleted password from sent items.\nTo access your phone again send the password.";
									exitMsg = "Time out!To access your phone again,resend the password.";
								} else {
									exitMsg = "Failed to exit..\nPlease try again by sending 0.";
								}
								ArrayList<String> parts = sms3
										.divideMessage(exitMsg);
								sms3.sendMultipartTextMessage(phoneNo1, null,
										parts, null, null);

							}

						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (message1.equals("EXIT")) {
					/*
					 * File dir=arg0.getFilesDir(); File file=new
					 * File(dir,"phno.txt"); boolean deleted =file.delete();
					 * String exitMsg=""; if(deleted) { exitMsg=
					 * "Exit Successful.\nMake sure you have deleted password from sent items.\nTo access your phone again send the password."
					 * ; } else {
					 * exitMsg="Failed to exit..\nPlease try again by sending 0."
					 * ; } ArrayList<String> parts =
					 * sms3.divideMessage(exitMsg);
					 * sms3.sendMultipartTextMessage(phoneNo1, null, parts,
					 * null, null);
					 */
				}

			}
		} catch (Exception e) {
		}
	}
	/*----------Listener class to get coordinates ------------- */
	/*
	 * private class MyLocationListener implements LocationListener { Context x;
	 * //Toast.makeText(x,"hellllooooo",Toast.LENGTH_LONG).show(); public void
	 * onLocationChanged(Location loc) {
	 * 
	 * //editLocation.setText(""); //pb.setVisibility(View.INVISIBLE);
	 * Toast.makeText(x,"Location changed : Lat: " + loc.getLatitude() +
	 * " Lng: " + loc.getLongitude(),Toast.LENGTH_SHORT).show(); String
	 * longitude = "Longitude: " +loc.getLongitude(); //Log.v(TAG, longitude);
	 * String latitude = "Latitude: " +loc.getLatitude(); //Log.v(TAG,
	 * latitude);
	 * 
	 * /*----------to get City-Name from coordinates -------------
	 */
	/*
	 * String cityName=null; Geocoder gcd = new Geocoder(x,
	 * Locale.getDefault()); List<Address> addresses; try { addresses =
	 * gcd.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1); if
	 * (addresses.size() > 0)
	 * System.out.println(addresses.get(0).getLocality());
	 * cityName=addresses.get(0).getLocality(); } catch (IOException e) {
	 * e.printStackTrace(); }
	 * 
	 * s22 = longitude+"\n"+latitude +"\n\nMy Currrent City is: "+cityName; //
	 * editLocation.setText(s); }
	 * 
	 * public void onProviderDisabled(String provider) { // TODO Auto-generated
	 * method stub }
	 * 
	 * 
	 * public void onProviderEnabled(String provider) { // TODO Auto-generated
	 * method stub }
	 * 
	 * 
	 * public void onStatusChanged(String provider, int status, Bundle extras) {
	 * // TODO Auto-generated method stub } }
	 */

}
