/***
  Copyright (c) 2010 CommonsWare, LLC

  Licensed under the Apache License, Version 2.0 (the "License"); you may
  not use this file except in compliance with the License. You may obtain
  a copy of the License at
    http://www.apache.org/licenses/LICENSE-2.0
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 */

package mx.gob.sct.android.mimappir.services;

import mx.gob.sct.android.mimappir.database.SharedPreferencesManager;
import mx.gob.sct.android.mimappir.utils.DebugMode;
import mx.gob.sct.android.mimappir.utils.Monitor;
import mx.gob.sct.android.mimappir.utils.OnTaskCompleted;
import mx.gob.sct.android.mimappir.utils.WebUtil;
import mx.gob.sct.android.mimappir.services.R;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import mx.gob.sct.locationpoller.LocationPoller;

public class LocationReceiver extends BroadcastReceiver implements OnTaskCompleted {

	private static String DEBUG_TAG = "MiMappirServices";

	private final Boolean isDebugging = true;

	private int idOperdador;

	private Context mContext;

	int type;

	private NotificationManager mNotificationManager;

	NotificationCompat.Builder builder;

	// An ID used to post the notification.
	public static final int NOTIFICATION_ID = 1;

	/**Current users location*/
	Location userLocation;

	String operatorName;

	long cellLatitude;

	long cellLongitude;

	int cellId;



	int dataConnectionType,  operatorGsmSignalStrength;

	Monitor mMonitor;

	@Override
	public void onReceive(Context context, Intent intent) {
		mContext = context;

		Bundle b=intent.getExtras();

		userLocation = getLocationUpdate(b, intent);

		if(userLocation != null){
			DebugMode.logger(DEBUG_TAG, "Location found");

			//sendNotification("Location found");

			mMonitor = new Monitor(context,this);

			dataConnectionType = mMonitor.getDataConnectionType();				

			idOperdador = mMonitor.getPhoneCompanyNameId();

			cellId = mMonitor.getConnectedCid();

			mMonitor.startGsmListening();

		}else
		{ 
			DebugMode.logger(DEBUG_TAG, "No location found");
		}

	}

	/***
	 * Makes a call to the location service and gets current user's location
	 * */
	private Location getLocationUpdate(Bundle mBundle, Intent mIntent) {

		String message;

		Location location = (Location) mBundle.get(LocationPoller.EXTRA_LOCATION);				

		if (location == null) {
			location = (Location) mBundle.get(LocationPoller.EXTRA_LASTKNOWN);
			if (location == null) {
				message = mIntent.getStringExtra(LocationPoller.EXTRA_ERROR);
			}
			else {
				message = "TIMEOUT, lastKnownlocation = "+location.toString();
			}								
		}
		else {			
			return location;
		}
		if (message==null) 
			message = "Invalid broadcast received!";
		else 
			DebugMode.logger(DEBUG_TAG, message);
		return null;
	}


	private void sendNotification(String msg) {
		if(isDebugging){
			mNotificationManager = (NotificationManager)
					mContext.getSystemService(Context.NOTIFICATION_SERVICE);

			PendingIntent contentIntent = PendingIntent.getActivity(mContext, 0,
					new Intent(mContext, SplashDemo.class), 0);

			NotificationCompat.Builder mBuilder =
					new NotificationCompat.Builder(mContext)	
			.setSmallIcon(R.drawable.logo_solo)
			.setContentTitle("Servicio de geolocalización y seguimiento de usuario")
			.setStyle(new NotificationCompat.BigTextStyle()
			.bigText(msg))
			.setContentText(msg);

			mBuilder.setContentIntent(contentIntent);


			mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
		}
	}


	public void onTaskCompleted(int signalStrength) {

		operatorGsmSignalStrength = signalStrength;

		SharedPreferencesManager mSharedPreferencesManager = new SharedPreferencesManager(mContext);

		int quantity;

		switch (dataConnectionType) {
		case 2:
			quantity = mSharedPreferencesManager.getIntValue(SharedPreferencesManager.EDGE_QUANTITY, 0);

			mSharedPreferencesManager.putIntPreference(SharedPreferencesManager.EDGE_QUANTITY, quantity+1);
			break;
		case 1:
			quantity = mSharedPreferencesManager.getIntValue(SharedPreferencesManager.HSPA_QUANTITY, 0);

			mSharedPreferencesManager.putIntPreference(SharedPreferencesManager.HSPA_QUANTITY, quantity+1);

			break;
		case 0:
			quantity = mSharedPreferencesManager.getIntValue(SharedPreferencesManager.THREE_G_QUANTITY, 0);

			mSharedPreferencesManager.putIntPreference(SharedPreferencesManager.THREE_G_QUANTITY, quantity+1);

			break;
		default:
			break;
		}


		final String url = "http://10.0.2.2:7001/mimappir/web/QOSCSENALGEO/REGISTRO/SENAL.action?" +
				"dintensidad=" + operatorGsmSignalStrength +
				".0&tipo=0"+
				"&danchobanda=0&" +
				"dlatitud=" + userLocation.getLatitude() + 
				"&dlongitud=" + userLocation.getLongitude()+
				"&d_altitud="+ userLocation.getAltitude()
				+"&dtipoconexiond="+dataConnectionType+
				"&ioperador=" + idOperdador+
				"&idcell="+ mMonitor.getConnectedCid()+
				"&latitudecell="+ mMonitor.getConnectedCellLocationAreaCode()+
				"&longitudcell=1"+
				"&cellphonemodel=" + Build.MODEL +
				"&cellphonemanufacturer="+ Build.MANUFACTURER +
				"&tsregistro="+System.currentTimeMillis();

		WebUtil task = new WebUtil();
				
        task.execute(new String[] { url });
	}
	
}
