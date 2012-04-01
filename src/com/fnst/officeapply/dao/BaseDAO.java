package com.fnst.officeapply.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.fnst.officeapply.common.AdminConfig;
import com.fnst.officeapply.dbpool.DBPoolException;
import com.fnst.officeapply.dbpool.DBPoolManager;
import com.fnst.officeapply.exception.OfficeException;

public abstract class BaseDAO implements SQLInfo{
	
	public static DBPoolManager manager;
	
	protected static AdminConfig adminConfig;
	
	protected static String DB_POOL_NAME;
	
	//为了性能考虑，没加同步，此处同步访问其实没有对值引发实质的变化.
	public BaseDAO() throws DBPoolException, OfficeException{
		if(manager == null){
			manager = DBPoolManager.getInstance();
		}
		if (adminConfig == null) {
			adminConfig = AdminConfig.getAdminConfig();
			DB_POOL_NAME = adminConfig.getValue(AdminConfig.DATABASE_POOL_NAME);
		}
	}
	
	protected String getSQL(int type, String sql1, String sql2){
		String sql;
		switch(type){
		case 0:
			sql = sql1;
			break;
		case 1:
			sql = sql2;
			break;
		default:
			sql = "";
			break;
		}
		return sql;
	}
	
	protected String getSQL(int type, String sql1, String sql2, String sql3){
		String sql;
		switch(type){
		case 0:
			sql = sql1;
			break;
		case 1:
			sql = sql2;
			break;
		case 2:
			sql = sql3;
			break;
		default:
			sql = "";
			break;
		}
		return sql;
	}
	
	protected void close(Statement[] states, ResultSet[] sets) throws OfficeException{
		if(states != null){
			for(Statement state : states){
				if(state != null){
					try {
						state.close();
					} catch (SQLException e) {
						throw new OfficeException("statement close fail:" + e);
					}
				}
			}
		}
		if(sets != null){
			for(ResultSet set : sets){
				if(set != null){
					try {
						set.close();
					} catch (SQLException e) {
						throw new OfficeException("statement close fail:" + e);
					}
				}
			}
		}
	}
	
}
