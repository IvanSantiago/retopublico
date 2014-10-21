package mx.gob.sct.android.mimappir.database;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPreferencesManager {

	public static final String IS_SERVICE_ACTIVE_PREFERENCE = "isServiceActive";


	public static final String SENSE_AMOUNT_PREFERENCE = "senseAmount";



	public static final String DATA_USED_SENSE_PREFERENCE = "dataUsedSense";


	public static final String QOSCELL_PREFERENCES = "quosCellPreferences";

	public static final String SEND_TYPE_PREFERENCE = "sendTypePreference";

	public static final String THREE_G_QUANTITY = "threeG";

	public static final String HSPA_QUANTITY = "hspa";

	public static final String EDGE_QUANTITY = "edge";




	private SharedPreferences mSharedPreferences;


	public SharedPreferencesManager(Context mContext) {		
		mSharedPreferences = mContext.getSharedPreferences(QOSCELL_PREFERENCES, Context.MODE_PRIVATE);

	}

	public void putIntPreference(String key, int value){
		Editor editor = mSharedPreferences.edit();
		editor.putInt(key, value);
		editor.commit();
	}

	public void putBooleanPreference(String key, int value){
		Editor editor = mSharedPreferences.edit();
		editor.putString(key, "value");
		editor.commit();
	}

	public int getIntValue(String key,int defaultValue) {
		return mSharedPreferences.getInt(key, defaultValue); // getting String
	}


	public boolean getBooleanValue(String key) {
		return mSharedPreferences.getBoolean(key, false);
	}

}
