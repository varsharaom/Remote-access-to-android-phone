package com.example.vvra;

//import android.support.v7.app.ActionBarActivity;


import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class Helping extends Activity {
TextView one,two,three;
ViewFlipper page;
	
    Animation animFlipInForeward;
    Animation animFlipOutForeward;
    Animation animFlipInBackward;
    Animation animFlipOutBackward;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helping);
        
        page = (ViewFlipper)findViewById(R.id.flipper);
        
        animFlipInForeward = AnimationUtils.loadAnimation(this, R.anim.flipin);
        animFlipOutForeward = AnimationUtils.loadAnimation(this, R.anim.flipout);
        animFlipInBackward = AnimationUtils.loadAnimation(this, R.anim.flipin_reverse);
        animFlipOutBackward = AnimationUtils.loadAnimation(this, R.anim.flipout_reverse);
        one=(TextView)findViewById(R.id.tv1);
        two=(TextView)findViewById(R.id.tv2);
        three=(TextView)findViewById(R.id.tv3);
        one.setText("\nRegister with this app by entering a secure password and also " +
        		"enter a security question of your choice." +
        		"\n\nYou can now access your phone from any other phone!\n ");
        two.setText("\nJust send your password as a text message to your " +
        		"phone.\n\nYou will get a list of options as follows:\n1a-Inbox " +
        		"SMS\n1b-Sent Items\n1c-Drafts\n2c-Call Log\n3s-To start call " +
        		"divert to the number from which you send your password" +
        		"\n3e-To stop call divert\n4-To get contact number\n5-To turn off silent mode\n6-To get location of the phone ");
        three.setText("\nTo get a list of today's inbox messages, " +
        		"just send 1a to your phone as a text message.\n"+
		       " \n   To get a contact number, just use this format :" +
		       "\n 4-Type your contact name or the first few starting letters here "+
		       "\n   Suppose you have a contact named John," +
		       " you can send one of the following options\n\n4-j\n4-jo\n4-joh\n4-john\n\n");
       // three.setText("hellloo");
        
    }
    
    private void SwipeRight(){
    	page.setInAnimation(animFlipInBackward);
		page.setOutAnimation(animFlipOutBackward);
		page.showPrevious();
    }
    
    private void SwipeLeft(){
    	page.setInAnimation(animFlipInForeward);
		page.setOutAnimation(animFlipOutForeward);
		page.showNext();
    }
    
    @Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
    	return gestureDetector.onTouchEvent(event);
	}

	SimpleOnGestureListener simpleOnGestureListener 
    = new SimpleOnGestureListener(){

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {

			float sensitvity = 50;
			if((e1.getX() - e2.getX()) > sensitvity){
				SwipeLeft();
			}else if((e2.getX() - e1.getX()) > sensitvity){
				SwipeRight();
			}
			
			return true;
		}
    	
    };
    
    GestureDetector gestureDetector
	= new GestureDetector(simpleOnGestureListener);
}