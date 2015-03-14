package com.xxg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class mediakeys extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);		
		
        Button button  = (Button) findViewById(R.id.button1);
		
		button.setOnClickListener(
				new OnClickListener () {
					public void onClick(View v) {
						Intent i = new Intent(Intent.ACTION_MEDIA_BUTTON);
						i.putExtra(Intent.EXTRA_KEY_EVENT,  new KeyEvent(KeyEvent.ACTION_DOWN,
								KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE));
						sendBroadcast(i);
						
						try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						i = new Intent(Intent.ACTION_MEDIA_BUTTON);
						i.putExtra(Intent.EXTRA_KEY_EVENT,  new KeyEvent(KeyEvent.ACTION_UP,
								KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE)); 
						sendBroadcast(i);
					}
				});
		
		 button  = (Button) findViewById(R.id.button2);
		 
		 button.setOnClickListener(
					new OnClickListener () {
						public void onClick(View v) {
							Intent i = new Intent(Intent.ACTION_MEDIA_BUTTON);
							i.putExtra(Intent.EXTRA_KEY_EVENT,  new KeyEvent(KeyEvent.ACTION_DOWN,
									KeyEvent.KEYCODE_MEDIA_NEXT)); 
							sendBroadcast(i);
							
							i = new Intent(Intent.ACTION_MEDIA_BUTTON);
							i.putExtra(Intent.EXTRA_KEY_EVENT,  new KeyEvent(KeyEvent.ACTION_UP,
									KeyEvent.KEYCODE_MEDIA_NEXT)); 
							sendBroadcast(i);
						}
					});
		

		 button  = (Button) findViewById(R.id.button3);
		 
		 button.setOnClickListener(
					new OnClickListener () {
						public void onClick(View v) {
							Intent i = new Intent(Intent.ACTION_MEDIA_BUTTON);
							i.putExtra(Intent.EXTRA_KEY_EVENT,  new KeyEvent(KeyEvent.ACTION_DOWN,
									KeyEvent.KEYCODE_MEDIA_PREVIOUS)); 
							sendBroadcast(i);
							
							i = new Intent(Intent.ACTION_MEDIA_BUTTON);
							i.putExtra(Intent.EXTRA_KEY_EVENT,  new KeyEvent(KeyEvent.ACTION_UP,
									KeyEvent.KEYCODE_MEDIA_PREVIOUS)); 
							sendBroadcast(i);
						}
					});
		
    }
}