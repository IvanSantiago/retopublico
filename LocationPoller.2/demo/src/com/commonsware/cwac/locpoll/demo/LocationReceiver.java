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

package com.commonsware.cwac.locpoll.demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.commonsware.cwac.locpoll.LocationPoller;

public class LocationReceiver extends BroadcastReceiver {
	private static String DEBUG_TAG = "QosCellServices";
	TelephonyManager        Tel;
	MyPhoneStateListener    MyListener;
	Context mContext;
	int type;

	Location loc;



	@Override
	public void onReceive(Context context, Intent intent) {
		mContext = context;

		File log=
				new File(Environment.getExternalStorageDirectory(),
						"LocationLog.txt");

		try {
			BufferedWriter out=
					new BufferedWriter(new FileWriter(log.getAbsolutePath(),
							log.exists()));

			out.write(new Date().toString());
			out.write(" : ");

			Bundle b=intent.getExtras();
			loc=(Location)b.get(LocationPoller.EXTRA_LOCATION);
			String msg;

			if (loc==null) {
				loc=(Location)b.get(LocationPoller.EXTRA_LASTKNOWN);

				if (loc==null) {
					msg=intent.getStringExtra(LocationPoller.EXTRA_ERROR);
				}
				else {
					msg="TIMEOUT, lastKnown="+loc.toString();
				}
			}
			else {
				msg=loc.toString();
				Log.d("Location Poller", msg);

				TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);      


				if ((tm.getNetworkType() == TelephonyManager.NETWORK_TYPE_HSDPA)) {
					Log.d("Type", "3g");// for 3g HSDPA networktype will be return as
					// per testing(real) in device with 3g enable data
					// and speed will also matters to decide 3g network type
					type = 2;
				} else if ((tm.getNetworkType() == TelephonyManager.NETWORK_TYPE_HSPAP)) {
					Log.d("Type", "4g"); // /No specification for the 4g but from wiki
					// i found(HSPAP used in 4g)
					// http://goo.gl/bhtVT
					type = 3;
				} else if ((tm.getNetworkType() == TelephonyManager.NETWORK_TYPE_GPRS)) {
					Log.d("Type", "GPRS");
					type = 1;
				} else if ((tm.getNetworkType() == TelephonyManager.NETWORK_TYPE_EDGE)) {
					Log.d("Type", "EDGE 2g");
					type = 0;
				}

				/* Update the listener, and start it */
				MyListener   = new MyPhoneStateListener();
				Tel       = ( TelephonyManager )mContext.getSystemService(Context.TELEPHONY_SERVICE);
				Tel.listen(MyListener ,PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
			}

			if (msg==null) {
				msg="Invalid broadcast received!";
			}

			out.write(msg);
			out.write("\n");
			out.close();
		}
		catch (IOException e) {
			Log.e(getClass().getName(), "Exception appending to log file", e);
		}
	}


	/* ������������������������������ */
	/* Start the PhoneState listener */
	/* ������������������������������ */
	private class MyPhoneStateListener extends PhoneStateListener
	{
		/* Get the Signal strength from the provider, each tiome there is an update */
		@Override
		public void onSignalStrengthsChanged(SignalStrength signalStrength)
		{
			super.onSignalStrengthsChanged(signalStrength);
			Log.d("Poller", "Go to Firstdroid!!! GSM Cinr = "+ String.valueOf(signalStrength.getGsmSignalStrength()));

			String url = "http://ttr.sct.gob.mx/qoscell/web/QOSCSENALGEO/REGISTRO/SENAL.action?dintensidad=1" +
					"&tipo="+String.valueOf(signalStrength.getGsmSignalStrength())+"&danchobanda=1&" +
					"dlatitud="+loc.getLatitude()+"&dlongitud=" +
					loc.getLongitude()+"&d_altitud="+loc.getAltitude()+"&dtipoconexiond="+type;

			downloadPage(url.trim());

			Log.d(DEBUG_TAG,url.trim());

			Tel.listen(MyListener, PhoneStateListener.LISTEN_NONE);


		}

	};/* End of private Class */
	@SuppressLint("NewApi")
	public static String downloadPage(String url){

		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		String page = "";

		BufferedReader in = null;
		try {       	
			HttpClient client = new DefaultHttpClient();	       	 	
			HttpGet request = new HttpGet(url);	            
			HttpParams params = request.getParams();	            
			HttpConnectionParams.setSoTimeout(params, 60000); // 1 minute timeout	            
			HttpResponse response = client.execute(request);

			//Predifined buffer size
			/*
			 * 
		            in = new BufferedReader(
		                    new InputStreamReader(
		                	response.getEntity().getContent()),8*2000);
			 * 
			 * */


			in = new BufferedReader(
					new InputStreamReader(
							response.getEntity().getContent()));

			StringBuffer sb = new StringBuffer("");            
			String line = "";
			//String NL = System.getProperty("line.separator");
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			in.close();
			page = sb.toString();             
			Log.v( DEBUG_TAG , "Pagina descargada --> "+ page);                        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					Log.v( DEBUG_TAG , "Error en la descarga de la pagina");    
					e.printStackTrace();
				}
			}
		}
		return page;
	}


}