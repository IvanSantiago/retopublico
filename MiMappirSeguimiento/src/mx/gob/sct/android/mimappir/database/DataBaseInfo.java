package mx.gob.sct.android.mimappir.database;

import android.provider.BaseColumns;

/**
 * 
 * Data base information, table names and table columns
 * 
 * */

public interface DataBaseInfo extends BaseColumns {
	
	public static final String OPERATOR_NAME = "OperatorName";

	public static final String TABLE_TELEPHONIC_QUALITY = "TelephonicQualityData";

	public static final String TABLE_CALL_QUALITY = "CallQualityData";	
	
	public static final String VOICE_SIGNAL_INTENSITY = "VoiceSignalIntensity";
	
	public static final String DATA_SIGNAL_TYPE = "DataSignalType";
	
	public static final String LATITUDE_USER = "LatitudeUser";
	
	public static final String LONGITUDE_USER = "LongitudeUser";
	
	public static final String ID_CELL = "IdCell";
	
	public static final String LATITUDE_CELL = "LatitudeCell";
	
	public static final String LONGITUDE_CELL = "LongitudeCell";
	
	public static final String CELL_PHONE_MODEL = "CellPhoneModel";
	
	public static final String CELL_PHONE_MANUFACTURER = "CellPhoneManufacturer";
	
	public static final String TIMESTAMP = "Timestamp";
	
	public static final String SUCCESSFULL_CALL= "SuccessfullCall";	
	
}
