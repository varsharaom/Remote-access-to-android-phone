package com.example.vvra;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.widget.Toast;
public class CallDivert extends Activity
{
	final SmsManager sms3=SmsManager.getDefault();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent msgReadActNext=getIntent();
		String number=msgReadActNext.getStringExtra("data");
		if(number.equals("##21#"))
		{
			//Intent intent=new Intent(Intent.ACTION_CALL);
			//intent.setData(Uri.parse("tel:##002#"));
			//callforward("#21#");
			//startActivity(intent);
			callforward("##21#");
		}
		else
		{
		/*	Intent intent=new Intent(Intent.ACTION_CALL);
			int c=number.length();
			String x=number.substring(3);
			String xx="*21*"+x+"#";
			Toast.makeText(getApplicationContext(), xx, Toast.LENGTH_LONG).show();
			intent.setData(Uri.parse("tel:"+xx));
			startActivity(intent);*/
			callforward(number);
		}
	}	
	 private void callforward(String callForwardString)
	    {
	        PhoneCallListener phoneListener = new PhoneCallListener();
	        TelephonyManager telephonyManager = (TelephonyManager)
	        this.getSystemService(Context.TELEPHONY_SERVICE);
	        telephonyManager.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE);        
	        Intent intentCallForward = new Intent(Intent.ACTION_CALL);
	        Uri mmiCode = Uri.fromParts("tel", callForwardString, "#");
	        intentCallForward.setData(mmiCode);
	        startActivity(intentCallForward);
	    }  
	 private class PhoneCallListener extends PhoneStateListener 
	 {
	        private boolean isPhoneCalling = false;         
	        @Override
	        public void onCallStateChanged(int state, String incomingNumber) 
	        {
	            if (TelephonyManager.CALL_STATE_RINGING == state){}	 
	            if (TelephonyManager.CALL_STATE_OFFHOOK == state) 
	            {
	                isPhoneCalling = true;
	            }	 
	            if (TelephonyManager.CALL_STATE_IDLE == state) 
	            {
	                if (isPhoneCalling)
	                {
	                	Intent i = getBaseContext().getPackageManager()
	                            .getLaunchIntentForPackage(getBaseContext().getPackageName());
	                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	                    startActivity(i);
	                    isPhoneCalling = false;
	                }
	            }
	        }
	 }
}
