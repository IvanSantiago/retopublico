package mx.gob.sct.android.mimappir.services;

import mx.gob.sct.android.mimappir.database.SharedPreferencesManager;
import mx.gob.sct.android.mimappir.utils.DebugMode;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.SystemClock;

import mx.gob.sct.locationpoller.LocationPoller;

public class ServiceLauncher {

	private Context context;

	private static final String TAG = "ServiceLauncher";

	public void launchService(Context context) {

		this.context = context;

		int period = 0;
		switch(getPeriod()){

		case 0:			
			period = 0;
			break;

		case 1:
			period = 1000 * 60 * 60 * 3 ;			
			break;

		case 2:			
			//period = 1000 * 60 * 60 * 5 ; //Cada 5 horas
			period = 1000 * 5 ; //cada 30 segundos
			break;

		case 3:
			period = 1000 * 60 * 15 ;
			break;
		}


		DebugMode.logger(TAG, "Period of " + period);



		Intent i = new Intent(context, LocationPoller.class);

		i.putExtra(LocationPoller.EXTRA_INTENT,
				new Intent(context, LocationReceiver.class));        

		i.putExtra(LocationPoller.EXTRA_PROVIDER,
				LocationManager.GPS_PROVIDER);

		PendingIntent mPendingIntent = null;

		mPendingIntent = PendingIntent.getBroadcast(context, 0, i, PendingIntent.FLAG_CANCEL_CURRENT);

		AlarmManager mAlarm = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

		mAlarm.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
				SystemClock.elapsedRealtime(),
				period,
				mPendingIntent);

		if(period != 0)
		{

			ComponentName receiver = new ComponentName(context, BootReceiver.class);
			PackageManager pm = context.getPackageManager();


			pm.setComponentEnabledSetting(receiver,
					PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
					PackageManager.DONT_KILL_APP);
		}
		else
			mAlarm.cancel(mPendingIntent);

	}

	private int getPeriod(){
		SharedPreferencesManager manager = new SharedPreferencesManager(context);

		return manager.getIntValue(SharedPreferencesManager.SENSE_AMOUNT_PREFERENCE, 2 );
	}

}
