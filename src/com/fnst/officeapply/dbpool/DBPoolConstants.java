package com.fnst.officeapply.dbpool;

public interface DBPoolConstants {
	
//	String DATABASE_NAME = "OfficeApply.db";
	
//	String DATABASE_DIRECTORY = "./resource/database/";
	
	String DB_POOL_PROP_FILE_NAME = "DBPool";
	
	String DB_POOL_LOG4J_LOGGER = "dblog";
	
	String CONNECTION_URL = ".url";
	
	String USERNAME = ".username";
	
	String PASSWORD = ".password";
	
	String MAX_CONNECTION = ".maxconn";
	
	String MIN_CONNECTION = ".minconn";
	
	String ONCE_CONNECTION = ".onceconn";
	
	String REQUEST_TIME_OUT = ".request.timeout";
	
	String MAX_FREE_TIME = ".maxfreetime";
	
	String DRIVER = ".driver";
	
	int MAX_CONNECTION_DEFAULT_VAL = 0;
	
	int MIN_CONNECTION_DEFAULT_VAL = 3;
	
	int ONCE_CONNECTION_DEFAULT_VAL = 3;
	
	int REQUEST_TIME_OUT_DEFAULT_VAL = 3000;
	
	int MAX_FREE_TIME_DEFAULT_VAL = 600000;
	
	long CONNECTION_LIFETIME_DEFAULT_VAL = 3600000*5;
	
}
