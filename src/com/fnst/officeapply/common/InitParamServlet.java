package com.fnst.officeapply.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import org.apache.log4j.PropertyConfigurator;
import com.fnst.officeapply.dao.BaseDAO;
import com.fnst.officeapply.dbpool.DBPoolException;
import com.fnst.officeapply.dbpool.DBPoolManager;

public class InitParamServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public InitParamServlet() {
	}

	public void init() throws ServletException {
		//初始化参数
		initParams();
	}
	
	
	
	private void initParams() throws ServletException{
		
		String prefix = this.getServletContext().getRealPath("/");
		DBPoolManager.DB_File = prefix + this.getInitParameter("DB");

		String logConfigFile = this.getInitParameter("log4j_config");
		String logFolder = this.getInitParameter("log_folder");
		
		//设置系统vm变量
		String logdirStr = prefix + logFolder;
		System.setProperty("logdir", logdirStr);
		Properties prop = new Properties();
		FileInputStream fin = null;
		try {
			File logdir = new File(logdirStr);
			if(!logdir.exists()){
				logdir.mkdirs();
			}
			fin = new FileInputStream(prefix + logConfigFile);
			prop.load(fin);
			PropertyConfigurator.configure(prop);
			//初始化数据库连接池
			BaseDAO.manager = DBPoolManager.getInstance();
		} catch (FileNotFoundException e) {
			throw new ServletException("can not find the file:" + prefix + logConfigFile, e);
		} catch (IOException e) {
			throw new ServletException("can not load the file:" + prefix + logConfigFile, e);
		} catch (DBPoolException e) {
			throw new ServletException(e);
		}finally{
			if(fin != null){
				try {
					fin.close();
				} catch (IOException e) {
					throw new ServletException("stream fail to close.", e);
				}
			}
		}
	}

}
