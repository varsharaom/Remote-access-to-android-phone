package com.example.vvra;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Locale;




import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class GetCurrentLocation extends Activity implements OnClickListener {
EditText lat,lng;
Button findLocation,getinfo;
String lat1,lng1;
//private LocationManager locationMangaer=null;
//private LocationListener locationListener=null;	
double lat11,lng11;
//private Button btnGetLocation = null;
//private EditText editLocation = null;	
//private ProgressBar pb =null;

//private static final String TAG = "Debug";
private Boolean flag = false;
String s7="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_current_location);
		lat=(EditText)findViewById(R.id.latitude);
		lng=(EditText)findViewById(R.id.longitude);
		findLocation=(Button)findViewById(R.id.findLoc);
		getinfo=(Button)findViewById(R.id.button1);
		findLocation.setOnClickListener(this);
		getinfo.setOnClickListener(this);
		Cursor c5;
		SQLiteDatabase sqdb;
		sqdb = openOrCreateDatabase("hello", MODE_PRIVATE, null);
		sqdb.execSQL("create table if not exists inf(number varchar(15),latitude varchar(15),longitude varchar(15),sentence varchar(150));");
		c5 = sqdb.rawQuery(
				"select latitude,longitude from inf", null);
		//Toast.makeText(getApplicationContext(), "aftr",
			//	Toast.LENGTH_LONG).show();
		// c.moveToFirst();
		StringBuilder s = new StringBuilder();
		//c5.moveToLast();
		//while (c5.moveToNext()) 
		if(c5.moveToLast()){
			s.append(c5.getString(0)).append(",").append(c5.getString(1));
					//.append(",").append(c5.getString(2))
					//.append("\n");

		
		//Toast.makeText(getApplicationContext(),
			//	s.substring(0, s.length()), Toast.LENGTH_LONG).show();
		lat.setText(c5.getString(0));
		lng.setText(c5.getString(1));
		}
	else
	{
		lat.setText("0.0");
		lng.setText("0.0");
	}
		}
		
		
	//	locationMangaer = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		//File file=new File("Coordinates.txt");
		//Toast.makeText(getApplicationContext(), "file exists abovvve", Toast.LENGTH_LONG).show();
	/*	File file = getApplicationContext().getFileStreamPath("Coordinates.txt");
	    if(file == null || !file.exists()) {
	       // return false;
	    	
	    	lat.setText("0.0");
			lng.setText("0.0");
	    }
	   // return true;
		
		
		
	//	Toast.makeText(getApplicationContext(), "file exists abovvve", Toast.LENGTH_LONG).show();
		else  {
		try {
			Toast.makeText(getApplicationContext(), "file exists", Toast.LENGTH_LONG).show();
			FileInputStream getcor = 
					openFileInput("Coordinates.txt");
			InputStreamReader isrs111 = new InputStreamReader(getcor);
			char[] buffer121 = new char[100];
			int charread121;
			while ((charread121 = isrs111.read(buffer121)) > 0) {
				String readstring = String.copyValueOf(buffer121, 0,
						charread121);
				s7 += readstring;
				buffer121 = new char[100];
			}

		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "exxxxxxx", Toast.LENGTH_LONG).show();}
		Toast.makeText(getApplicationContext(), "s7"+s7, Toast.LENGTH_LONG).show();
		 lat1=s7.substring(0,11);
		 lng1=s7.substring(11);
		// lat11=Double.parseDouble(lat1);
		// lng11=Double.parseDouble(lng1);
		Toast.makeText(getApplicationContext(), "lat1 "+lat1+"\nlng1 "+lng1, Toast.LENGTH_LONG).show();
		lat.setText(lat1);
		lng.setText(lng1);
		
		
		
		
		}
		/*else
		{
			lat.setText("0.0");
			lng.setText("0.0");
		}
	
		*/
	//}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.get_current_location, menu);
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
		switch(arg0.getId()){
		case R.id.button1:
			//Toast.makeText(getApplicationContext(),"click",Toast.LENGTH_LONG).show();
			String str="Enable the mobile data to get the name of the city corresponding to the entered coordinates.";
			Intent infoscreen=new Intent(getApplicationContext(),Information.class);
			infoscreen.putExtra("datas", str);
			startActivity(infoscreen);
			break;
		case R.id.findLoc:
		// TODO Auto-generated method stub
	/*	flag = displayGpsStatus();
		if (flag) {
			
			//Log.v(TAG, "onClick");		
			
		//	editLocation.setText("Please!! move your device to see the changes in coordinates."+"\nWait..");
			
		//	pb.setVisibility(View.VISIBLE);
			locationListener = new MyLocationListener();

			locationMangaer.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10,
	                locationListener);
			
			} else {
			//alertbox("Gps Status!!", "Your GPS is: OFF");
		}*/
		 String cityName=null;      		      
		    Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());      		     
		    List<Address>  addresses;  
		    
		    String x1=lat.getText().toString();
		    String x2=lng.getText().toString();
		    if(x1.equals("")||x2.equals("")){
		    	
		    }
		    else{
		    lat11=Double.parseDouble(x1);
		    lng11=Double.parseDouble(x2);
		    try {

		        Geocoder geo = new Geocoder(getApplicationContext(), Locale.getDefault());
		        List<Address> addresses1 = geo.getFromLocation(lat11, lng11, 1);
		        if (addresses1.isEmpty()) {
		           // addres.setText("Waiting for Location");
		        	//Toast.makeText(getApplicationContext(), "waiitng", Toast.LENGTH_LONG).show();
		        }
		        else {
		            if (addresses1.size() > 0) {
		              //  addres.setText(addresses.get(0).getFeatureName() + ", " + addresses.get(0).getLocality() +", " + addresses.get(0).getAdminArea() + ", " + addresses.get(0).getCountryName());
		                Toast.makeText(getApplicationContext(), "Address:- " + addresses1.get(0).getFeatureName() +"\n"+ addresses1.get(0).getAdminArea() + "\n"+addresses1.get(0).getLocality(), Toast.LENGTH_LONG).show();
		            }
		        }
		    }
		    catch (Exception e) {
		    	Toast.makeText(getApplicationContext(), "Network error!Try again later", Toast.LENGTH_LONG).show();
		        e.printStackTrace(); // getFromLocation() may sometimes fail
		    }
		    break;
		
	}}}
	

	/*----------Method to Check GPS is enable or disable ------------- */
/*	private Boolean displayGpsStatus() {
		ContentResolver contentResolver = getBaseContext().getContentResolver();
		boolean gpsStatus = Settings.Secure.isLocationProviderEnabled(
				contentResolver, LocationManager.GPS_PROVIDER);
		if (gpsStatus) {
			return true;

		} else {
			return false;
		}
	}*/

	/*----------Method to create an AlertBox ------------- */
/*	protected void alertbox(String title, String mymessage) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Your Device's GPS is Disable")
				.setCancelable(false)
				.setTitle("** Gps Status **")
				.setPositiveButton("Gps On",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// finish the current activity
								// AlertBoxAdvance.this.finish();
								Intent myIntent = new Intent(
										Settings.ACTION_SECURITY_SETTINGS);
								startActivity(myIntent);
								dialog.cancel();
							}
						})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// cancel the dialog box
								dialog.cancel();
							}
						});
		AlertDialog alert = builder.create();
		alert.show();
	}*/
	
	/*----------Listener class to get coordinates ------------- */
/*	private class MyLocationListener implements LocationListener {
        public void onLocationChanged(Location loc) {
          
            	//editLocation.setText("");
            	//pb.setVisibility(View.INVISIBLE);
                Toast.makeText(getBaseContext(),"Location changed : Lat: " + loc.getLatitude()
                                + " Lng: " + loc.getLongitude(),Toast.LENGTH_SHORT).show();
                String longitude = "Longitude: " +loc.getLongitude();  
    			//Log.v(TAG, longitude);
    		    String latitude = "Latitude: " +loc.getLatitude();
    		  //  Log.v(TAG, latitude);
    		    
    		    /*----------to get City-Name from coordinates ------------- */
    	/*	    String cityName=null;      		      
    		    Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());      		     
    		    List<Address>  addresses;  
    		    
    		    String x1=lat.getText().toString();
    		    String x2=lng.getText().toString();
    		   // lat11=Double.parseDouble("74.8800");
    		  //  lng11=Double.parseDouble("12.8700");
    		   // lat11=Double.parseDouble("12.9141420");
    		   // lng11=Double.parseDouble("74.8559570");
    		    lat11=Double.parseDouble(x1);
    		    lng11=Double.parseDouble(x2);
    		/*    try {  
    		   //  addresses = gcd.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);
    		    	addresses = gcd.getFromLocation(lat11, lng11, 1);
    		    	//addresses = gcd.getFromLocation(74.8800, 12.8700, 1);
    		     if (addresses.size() > 0)  
    		      System.out.println(addresses.get(0).getLocality());  
    		     cityName=addresses.get(0).getLocality();  
    		    } catch (IOException e) {    		      
    		     e.printStackTrace();  
    		    } 
    		    */
    		 /*   try {

    		        Geocoder geo = new Geocoder(getApplicationContext(), Locale.getDefault());
    		        List<Address> addresses1 = geo.getFromLocation(lat11, lng11, 1);
    		        if (addresses1.isEmpty()) {
    		           // addres.setText("Waiting for Location");
    		        	Toast.makeText(getApplicationContext(), "waiitng", Toast.LENGTH_LONG).show();
    		        }
    		        else {
    		            if (addresses1.size() > 0) {
    		              //  addres.setText(addresses.get(0).getFeatureName() + ", " + addresses.get(0).getLocality() +", " + addresses.get(0).getAdminArea() + ", " + addresses.get(0).getCountryName());
    		                Toast.makeText(getApplicationContext(), "Address:- " + addresses1.get(0).getFeatureName() +"\n"+ addresses1.get(0).getAdminArea() + "\n"+addresses1.get(0).getLocality(), Toast.LENGTH_LONG).show();
    		            }
    		        }
    		    }
    		    catch (Exception e) {
    		    	Toast.makeText(getApplicationContext(), "exception", Toast.LENGTH_LONG).show();
    		        e.printStackTrace(); // getFromLocation() may sometimes fail
    		    }
    		  //  String s = longitude+"\n"+latitude +"\n\nMy Currrent City is: "+cityName;
    		  //  Toast.makeText(getApplicationContext(), cityName, Toast.LENGTH_LONG).show();
     		   // editLocation.setText(s);
        }

        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub        	
        }

     
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub        	
        }

     
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // TODO Auto-generated method stub        	
        }
    }
	
	*/

}
