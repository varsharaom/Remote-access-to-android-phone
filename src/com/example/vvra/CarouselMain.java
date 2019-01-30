package com.example.vvra;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import com.example.vvra.CarouselAdapter.OnItemClickListener;
import com.example.vvra.CarouselAdapter.OnItemSelectedListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
//import android.widget.TextView;
import android.widget.Toast;

public class CarouselMain extends Activity {
    /** Called when the activity is first created. */
	String s2="";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carousel_main);
        Carousel carousel = (Carousel)findViewById(R.id.carousel);
        File file = getApplicationContext().getFileStreamPath("Background.txt");
	    if(file == null || !file.exists()) {
	       // return false;
	    	
	    	//lat.setText("0.0");
			//lng.setText("0.0");
	    	}
	    else{
	    	try{
	    	
	    	
	    	FileInputStream fIns1 = openFileInput("Background.txt");
			InputStreamReader isrs1 = new InputStreamReader(fIns1);
			char[] buffer12 = new char[100];
			int charread12;
			while ((charread12 = isrs1.read(buffer12)) > 0) {
				String readstring = String.copyValueOf(buffer12, 0,
						charread12);
				s2 += readstring;
				buffer12 = new char[100];
			}}
	    	catch(Exception e){
	    		
	    	}
	    	if(s2.equals("two")){
	    		LinearLayout l1=(LinearLayout)findViewById(R.id.lin1);
				l1.setBackgroundResource(R.drawable.mainback1);
	    	}
	    	else
	    		if(s2.equals("one")){
		    		LinearLayout l1=(LinearLayout)findViewById(R.id.lin1);
					l1.setBackgroundResource(R.drawable.mainback2);
		    	}
	    		else
		    		if(s2.equals("three")){
			    		LinearLayout l1=(LinearLayout)findViewById(R.id.lin1);
						l1.setBackgroundResource(R.drawable.mainback3);
			    	}
	    		
	    }
        
       // Intent xx=getIntent();
	/*	String number=xx.getStringExtra("data1");
		
		if(number.equals("1")){
			LinearLayout l1=(LinearLayout)findViewById(R.id.lin1);
			l1.setBackgroundResource(R.drawable.ic_launcher);
		}*/
        carousel.setOnItemClickListener(new OnItemClickListener(){

			public void onItemClick(CarouselAdapter<?> parent, View view,
					int position, long id) {	
				String s="";
			/*	Toast.makeText(CarouselMain.this, 
						String.format("%s has been clicked", 
						((CarouselItem)parent.getChildAt(position)).getName()), 
						Toast.LENGTH_SHORT).show();	*/
				
				String name=((CarouselItem)parent.getChildAt(position)).getName();
				if(name.equals("REGISTER")){
					
					try{
						FileInputStream fIn = openFileInput("flag.txt");
					InputStreamReader isr=new InputStreamReader(fIn);
					char[] inputbuffer=new char[100];			
					int charread;
					//Toast.makeText(getApplicationContext(),"helll",Toast.LENGTH_LONG).show();
				//s="";
					while((charread=isr.read(inputbuffer))>0)
					  {
						String readstring=String.copyValueOf(inputbuffer,0,charread);
						s+=readstring;
						inputbuffer=new char[100];
					  }					
					}
					catch(Exception e){}
					if(s.equals("1"))
					{
						Toast.makeText(getApplicationContext(), "Already registered!", Toast.LENGTH_LONG).show();	
				//	Intent registerScreen=new Intent(getApplicationContext(),Login.class);
				//	startActivity(registerScreen);
						}
					else
					{
						Intent registerScreen=new Intent(getApplicationContext(),Register.class);
						startActivity(registerScreen);
						//Toast.makeText(getApplicationContext(), "Already registered!", Toast.LENGTH_LONG).show();	
					}
					
					//Toast.makeText(getApplicationContext(), "uuuuuuuu", Toast.LENGTH_LONG).show();
				}
				else if(name.equals("HELP")){
					//Toast.makeText(getApplicationContext(),"chala",Toast.LENGTH_SHORT).show();
					Intent helpscreen=new Intent(getApplicationContext(),Helping.class);
					startActivity(helpscreen);
				
				
			}
				else if(name.equals("SETTINGS")){
					Intent Settingscreen=new Intent(getApplicationContext(),Settings.class);
					startActivity(Settingscreen);
				
				
			}
				else if(name.equals("ADD PEOPLE")){
					Intent AddPeople=new Intent(getApplicationContext(),AddPeople.class);
					startActivity(AddPeople);
				
				
			}
				else if(name.equals("GET LOCATION")){
					Intent getplace=new Intent(getApplicationContext(),GetCurrentLocation.class);
					startActivity(getplace);
				
				
			}
				else if(name.equals("NOTIFICATIONS")){
					Intent getnoti=new Intent(getApplicationContext(),Notification.class);
					startActivity(getnoti);
				
				
			}
        	
        }});

        carousel.setOnItemSelectedListener(new OnItemSelectedListener(){

			public void onItemSelected(CarouselAdapter<?> parent, View view,
					int position, long id) {
				
		      /*  final TextView txt = (TextView)(findViewById(R.id.selected_item));
		        
				switch(position){
				case 0:
					txt.setText("The cat (Felis catus), also known as the domestic cat or housecat to distinguish it from other felids and felines, is a small, usually furry, domesticated, carnivorous mammal that is valued by humans for its companionship and for its ability to hunt vermin and household pests. Cats have been associated with humans for at least 9,500 years, and are currently the most popular pet in the world. Owing to their close association with humans, cats are now found almost everywhere in the world.");
					break;
				case 1:
					txt.setText("The hippopotamus (Hippopotamus amphibius), or hippo, from the ancient Greek for \"river horse\" (ἱπποπόταμος), is a large, mostly herbivorous mammal in sub-Saharan Africa, and one of only two extant species in the family Hippopotamidae (the other is the Pygmy Hippopotamus.) After the elephant, the hippopotamus is the third largest land mammal and the heaviest extant artiodactyl.");
					break;
				case 2:
					txt.setText("A monkey is a primate, either an Old World monkey or a New World monkey. There are about 260 known living species of monkey. Many are arboreal, although there are species that live primarily on the ground, such as baboons. Monkeys are generally considered to be intelligent. Unlike apes, monkeys usually have tails. Tailless monkeys may be called \"apes\", incorrectly according to modern usage; thus the tailless Barbary macaque is called the \"Barbary ape\".");
					break;
				case 3:
					txt.setText("A mouse (plural: mice) is a small mammal belonging to the order of rodents. The best known mouse species is the common house mouse (Mus musculus). It is also a popular pet. In some places, certain kinds of field mice are also common. This rodent is eaten by large birds such as hawks and eagles. They are known to invade homes for food and occasionally shelter.");
					break;
				case 4:
					txt.setText("The giant panda, or panda (Ailuropoda melanoleuca, literally meaning \"black and white cat-foot\") is a bear native to central-western and south western China.[4] It is easily recognized by its large, distinctive black patches around the eyes, over the ears, and across its round body. Though it belongs to the order Carnivora, the panda's diet is 99% bamboo.");
					break;
				case 5:
					txt.setText("Rabbits (or, colloquially, bunnies) are small mammals in the family Leporidae of the order Lagomorpha, found in several parts of the world. There are eight different genera in the family classified as rabbits, including the European rabbit (Oryctolagus cuniculus), cottontail rabbits (genus Sylvilagus; 13 species), and the Amami rabbit (Pentalagus furnessi, an endangered species on Amami Ōshima, Japan)");
					break;
				}
				*/
			}

			public void onNothingSelected(CarouselAdapter<?> parent) {
			}
        	
        }
        );
        
    }
    @Override
	public void onBackPressed() {
    	
    	 AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
         alertDialogBuilder.setTitle("Exit Application?");
         alertDialogBuilder
                 .setMessage("Click yes to exit!")
                 .setCancelable(false)
                 .setPositiveButton("Yes",
                         new DialogInterface.OnClickListener() {
                             public void onClick(DialogInterface dialog, int id) {
                                 moveTaskToBack(true);
                                 android.os.Process.killProcess(android.os.Process.myPid());
                                 System.exit(1);
                             }
                         })

                 .setNegativeButton("No", new DialogInterface.OnClickListener() {
                     public void onClick(DialogInterface dialog, int id) {

                         dialog.cancel();
                     }
                 });

         AlertDialog alertDialog = alertDialogBuilder.create();
         alertDialog.show();
		// TODO Auto-generated method stub	
	/*	AlertDialog.Builder	builder = new AlertDialog.Builder(this);
		builder.setTitle("EXIT");		
		builder.setMessage("Do you want to exit?");
		builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {			
		@Override		
		public void onClick(DialogInterface dialog, int which) {
			dialog.dismiss();        
			finish(); 
		/*	Intent intent = new Intent(getApplicationContext(), SplashScreen.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra("EXIT", true);
			startActivity(intent);*/
      /*  }});		
		builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();		
		}
		});
		AlertDialog alert = builder.create();
		alert.show();
	}*/
}
}
