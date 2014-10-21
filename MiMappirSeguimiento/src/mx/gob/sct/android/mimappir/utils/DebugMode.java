package mx.gob.sct.android.mimappir.utils;

import android.os.Handler;
import android.util.Log;

public class DebugMode {	
	private static Handler mHandler;

	private static final boolean IS_DEBUGING = true; 
	
	/**
	 * 
	 * Sends the Log message in a handler to deliver it properly
	 * 
	 * **/
	public static void logger(final String tag, final String message){
		if(IS_DEBUGING){
			mHandler = new Handler();
					
			//Launched inside a handler in order to always deliver the message (inside services, etc.)
			mHandler.post(new Runnable() {				
				@Override
				public void run() {
					Log.d(tag, message);					
				}
			});		
		}	
	}
}
