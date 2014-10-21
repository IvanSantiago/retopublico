package mx.gob.sct.android.mimappir.services;


import mx.gob.sct.android.mimappir.services.R;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.os.Bundle;
import android.view.View;

public class SplashDemo extends Activity {

	private PendingIntent mPendingIntent = null;

	private AlarmManager mAlarm=null;

	private String DEBUG_TAG = "service_screen";
	
	
		
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);
		
		startLocationService();
					
	}
	
	
	private void startLocationService() {
		
		ServiceLauncher mServiceLauncher = new ServiceLauncher();
		
		mServiceLauncher.launchService(this);
				
	}

	public void stopLocationService(View v) {
		mAlarm.cancel(mPendingIntent);
		finish();
	}

		
}
