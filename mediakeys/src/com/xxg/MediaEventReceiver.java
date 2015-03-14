package com.xxg;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
		//Toast.makeText(context, intent.toString(), Toast.LENGTH_SHORT).show();
		//Bundle extras = intent.getExtras();
		//Toast.makeText(context, extras.toString(), Toast.LENGTH_LONG).show();
		//Toast.makeText(context, extras.getString(Intent.EXTRA_KEY_EVENT), Toast.LENGTH_LONG).show();
	}

}
