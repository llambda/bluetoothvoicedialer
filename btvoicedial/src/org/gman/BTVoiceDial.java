package org.gman;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;
import android.widget.Toast;
import android.bluetooth.BluetoothAdapter;

public class BTVoiceDial extends AppWidgetProvider {
	public static String ACTION_WIDGET_RECEIVER = "ActionReceiverWidget";
	public static String ACTION_WIDGET_CONFIGURE = "ConfigureWidget";
	public static String TAG = "BTVoiceDial";
	private SharedPreferences mPrefs;
	
	@Override
	public void onReceive(final Context context, Intent rec_intent) {
		mPrefs = context.getSharedPreferences(BTPrefs.PREFS_NAME, Context.MODE_PRIVATE);
		boolean auto_bluetooth = mPrefs.getBoolean("auto_bluetooth", true);
		//boolean dumb_mode = mPrefs.getBoolean("dumb_mode", false);
		boolean regular_dialer = mPrefs.getBoolean("regular_dialer", false);
		boolean also_launch  = mPrefs.getBoolean("also_launch", false);
		
		int i = 0;
		try {
			i = Integer.parseInt(mPrefs.getString("bluetooth_delay", "17"));
		} catch (NumberFormatException e) {
			;
		}		
		if (i <0)
			i = 0;
		final int delay = i * 1000;
		
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
				R.layout.main);
//		if (rec_intent.getAction().equals(android.bluetooth.BluetoothAdapter.ACTION_STATE_CHANGED)) {
//			//do stuff
//		}
		if (rec_intent.getAction().equals(ACTION_WIDGET_RECEIVER)) {
			/*if (dumb_mode) {
				startDialer(context);
				return;
			}*/
			
			final BluetoothAdapter bta = BluetoothAdapter.getDefaultAdapter();
			//if (bta.getState() == BluetoothAdapter.ERROR !bta.isEnabled()) {
			if (bta == null) {
				if (regular_dialer) {
					startRegularDialer(context);
				} else {
					startDialer(context);
					//Toast.makeText(context, "Bluetooth is not available", Toast.LENGTH_LONG).show();
				}
			} else if (bta.getState() == BluetoothAdapter.STATE_OFF || bta.getState() == BluetoothAdapter.STATE_TURNING_OFF) {				//remoteViews.setString(R.id.button1, "setText", "enabling bluetooth...");
				// final Button button = (Button) findViewById(R.id.button1);
				if (auto_bluetooth) {
					Toast t = Toast.makeText(context, R.string.bt_turning_on, Toast.LENGTH_LONG);
					t.show();
					bta.enable();
					if (also_launch) {
						new Thread(new Runnable() {
							public void run() {
								try {
									Thread.sleep(delay);
								} catch (InterruptedException e) {
								// 	TODO Auto-generated catch block
									e.printStackTrace();
								}
								startDialer(context);
							}
						}).start();
					} else if (regular_dialer) {
						startRegularDialer(context);
					}
				} else {
					if (regular_dialer) {
						startRegularDialer(context);
					} else {
						startDialer(context);
					}
				}
			} else if (bta.getState() == BluetoothAdapter.STATE_TURNING_ON) {
				startDialer(context);
				/*if (regular_dialer) {
					startRegularDialer(context);
				}*/
			} /*else if (bta.getBondedDevices().isEmpty()) {
				Toast.makeText(context, "Pair with a device", Toast.LENGTH_SHORT).show();
				startSettings(context);
			}*/ else {
				startDialer(context);
				/*Set<BluetoothDevice> set = bta.getBondedDevices();
				boolean connected = false;
				for (BluetoothDevice b : set) {
					Toast.makeText(context, "Checking: " + b.getName(), Toast.LENGTH_SHORT).show();
					connected = true;
					break;
				}
				if (connected) {
					startDialer(context);
				} else {
					startSettings(context);
				}*/
					
			}
		}
		super.onReceive(context, rec_intent);
	}
	
	public static void startSettings(Context context) {
		Intent intent = new Intent();
		intent.setAction(android.provider.Settings.ACTION_BLUETOOTH_SETTINGS);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}
	
	public static void startDialer(Context context) {
		Intent intent = new Intent();
		
		intent.setAction(Intent.ACTION_VOICE_COMMAND);
		intent.addFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK);
		try {
			context.startActivity(intent);
		} catch (android.content.ActivityNotFoundException e) {
			Toast.makeText(context,
					R.string.no_application, Toast.LENGTH_LONG).show();
		}
			
	}
	
	public static void startRegularDialer(Context context) {
		Intent intent = new Intent();
		intent.setComponent(new ComponentName(
				"com.android.voicedialer",
				"com.android.voicedialer.VoiceDialerActivity"));
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		try {
			context.startActivity(intent);
		} catch (android.content.ActivityNotFoundException e) {
			Toast.makeText(context, R.string.no_regular_voicedialer, Toast.LENGTH_LONG).show();
		}
	}
	

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
				R.layout.main);
		Intent active = new Intent(context, BTVoiceDial.class);
		active.setAction(ACTION_WIDGET_RECEIVER);
		//active.putExtra("msg", "Message for Button 1");
		PendingIntent actionPendingIntent = PendingIntent.getBroadcast(context,
				0, active, 0);
		remoteViews.setOnClickPendingIntent(R.id.button1, actionPendingIntent);
		appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
	}
}