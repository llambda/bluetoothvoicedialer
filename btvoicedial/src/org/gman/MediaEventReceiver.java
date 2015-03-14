package org.gman;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

public class MediaEventReceiver extends BroadcastReceiver {

	private SharedPreferences mPrefs;
	
	public MediaEventReceiver() {
		super();
	}

	@Override
	public void onReceive(Context context, Intent intent) {		
    	mPrefs = context.getSharedPreferences(BTPrefs.PREFS_NAME, Context.MODE_PRIVATE);    	
		if (Intent.ACTION_MEDIA_BUTTON.equals(intent.getAction())) {
    		boolean media_keys_enabled = mPrefs.getBoolean("media_keys_enabled", false);
    		
    		if (media_keys_enabled) {
    			boolean media_play_key  = mPrefs.getBoolean("media_play_key", false);
        		boolean media_previous = mPrefs.getBoolean("media_previous", false);
        		boolean media_next = mPrefs.getBoolean("media_next", false);
    			
    			Bundle bundle = intent.getExtras();
            	KeyEvent ke = bundle.getParcelable(Intent.EXTRA_KEY_EVENT);
            	if (ke == null)
            		return;
            	int kc = ke.getKeyCode();
            	
            	if (   (kc == KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE && media_play_key)
            		|| (kc == KeyEvent.KEYCODE_MEDIA_PREVIOUS && media_previous) 
            		|| (kc == KeyEvent.KEYCODE_MEDIA_NEXT && media_next)) {
            		BTVoiceDial.startDialer(context);
            	}
            	
            	abortBroadcast();
    		}
        }
	}
}
