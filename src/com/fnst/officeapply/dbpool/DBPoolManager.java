/**
 * @(#)DBPoolManager.java	2011/11/26
 * Copyright 2011 XuNeng All rights reserved.
 */
package com.fnst.officeapply.dbpool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;
import org.apache.log4j.Logger;
import com.fnst.officeapply.common.ComUtil;

/**
 * 管理类DBConnectionManager支持对一个或多个由属性文件定义的数据库连接 池的访问
 * @author XuNeng
 */
public class DBPoolManager implements DBPoolConstants{
	
    private static DBPoolManager instance;
    /**日志对象*/
    public static Logger logger;
    
	public static String DB_File;
    
	private Properties DBPoolPro = PropertiesLoader.getProperties(DB_POOL_PROP_FILE_NAME);
	
    private Map<String,DBConnectionPool> pools = new Hashtable<String,DBConnectionPool>();
    
    /**
     * 返回唯一实例.如果是第一次调用此方法,则创建实例
     * @return DBConnectionManager 唯一实例
     * @throws Exception 
     */
    public static synchronized DBPoolManager getInstance() throws DBPoolException {
        if (instance == null) {
            instance = new DBPoolManager();
        }
        return instance;
    }
    /**
     * 构造函数私有以防止其它对象创建本类实例
     * @throws Exception 
     */
    private DBPoolManager() throws DBPoolException {
        initManager();
    }
    /**
     * 将连接对象返回给由名字指定的连接池
     * @param name 在属性文件中定义的连接池名字
     * @param conn 连接对象
     */
    public void freeConnection(String name, Connection conn) {
    	DBConnectionPool pool = null;
		synchronized (this) {
			pool = pools.get(name);
		}
		if (pool != null) {
			pool.freeConnection(conn);
		} else {
			logger.info("this DBPOOL is not existed:" + name);
		}
		
    }
    
    /**
     * 将连接对象返回给由名字指定的连接池,并且关闭Statement和ResultSet
     * @param name
     * @param conn
     * @param state
     * @param rs
     * @throws DBPoolException
     */
    public void freeConnection(String name, Connection conn, Statement state, ResultSet rs) throws DBPoolException {
    	DBConnectionPool pool = null;
		synchronized (this) {
			pool = pools.get(name);
		}
		if (pool != null) {
			pool.freeConnection(conn, state, rs);
		} else {
			logger.info("this DBPOOL is not existed:" + name);
		}
		
    }
    /**
     * 获得一个可用连接.若没有可用连接,且已有连接数小于最大连接数限制,
     * 则创建并返回新连接.否则,在一定时间内等待其它线程释放连接.
     * @param name 连接池名字
     * @return Connection 可用连接或null
     * @throws DBPoolException 
     */
    public Connection getConnection(String name) throws DBPoolException {
    	DBConnectionPool pool = null;
		synchronized (this) {
			pool = pools.get(name);
		}
        if (pool == null) {
        	logger.error("this DBPOOL is not existed:"+name);
        	throw new DBPoolException("this DBPOOL is not existed:"+name);
        }
        Connection conn = pool.getConnection();
        synchronized (pool) {
			if (conn != null) {
				pool.busyConnNum++;
				logger.debug("利用者拿到一个连接,当前非空闲连接数："+pool.busyConnNum);
				logger.debug("非空闲连接数:"+pool.busyConnBeans.size()+"空闲连接数:"+pool.freeConnBeans.size());
			}else{
				logger.debug("利用者没有拿到一个可用的连接");
			}
		}
        return conn;
    }
    /**
     * 关闭所有连接,撤销驱动程序的注册
     */
	public synchronized void release() {
		Set<String> keys = pools.keySet();
		Iterator<String> it = keys.iterator();
		while (it.hasNext()) {
			String key = it.next();
			DBConnectionPool pool = pools.get(key);
			pool.release();
		}
		pools.clear();
	}

    /**
     * 根据指定属性创建连接池实例.
     * @param props 连接池属性
     * @throws DBPoolException 
     */
    private void createPools(Properties props) throws DBPoolException {
        Enumeration<?> propNames = props.propertyNames();
        int index = 0;
        while (propNames.hasMoreElements()) {
            String name = (String) propNames.nextElement();
            if (name.endsWith(CONNECTION_URL)) {
                String poolName = name.substring(0, name.lastIndexOf("."));
                String url = props.getProperty(poolName + CONNECTION_URL);
                if (url == null) {
                    logger.error("没有为连接池" + poolName + "指定URL");
                    continue;
                }
				String username = props.getProperty(poolName + USERNAME);
				String password = props.getProperty(poolName + PASSWORD);
				String maxconn = props.getProperty(poolName + MAX_CONNECTION, String.valueOf(MAX_CONNECTION_DEFAULT_VAL));
				String minconn = props.getProperty(poolName + MIN_CONNECTION, String.valueOf(MIN_CONNECTION_DEFAULT_VAL));
				String onceconn = props.getProperty(poolName + ONCE_CONNECTION, String.valueOf(ONCE_CONNECTION_DEFAULT_VAL));
                String driver = props.getProperty(poolName+ DRIVER);
                String requestTimeout = props.getProperty(poolName+ REQUEST_TIME_OUT, String.valueOf(REQUEST_TIME_OUT_DEFAULT_VAL));
                String maxFreetime = props.getProperty(poolName+ MAX_FREE_TIME, String.valueOf(MAX_FREE_TIME_DEFAULT_VAL));
                int maxConnection;
                int minConnection;
                int onceConnection;
                int timeout;
                int freetime;
                maxConnection = ComUtil.parseInt(maxconn, MAX_CONNECTION_DEFAULT_VAL, "错误的最大连接数限制: ", poolName);
                minConnection = ComUtil.parseInt(minconn, MIN_CONNECTION_DEFAULT_VAL, "错误的初始化连接数限制: ", poolName);
                onceConnection = ComUtil.parseInt(onceconn, ONCE_CONNECTION_DEFAULT_VAL, "错误的批量连接数生成: ", poolName);
                timeout = ComUtil.parseInt(requestTimeout, REQUEST_TIME_OUT_DEFAULT_VAL, "错误的请求等待时间: ", poolName);
                freetime = ComUtil.parseInt(maxFreetime, MAX_FREE_TIME_DEFAULT_VAL, "错误的连接最大空闲时间: ", poolName);
                
				timeout = timeout < 0 ? 0 : timeout;
                DBConnectionPool pool = new DBConnectionPool(poolName, driver, url, username, password, maxConnection, minConnection,onceConnection,timeout,freetime);
                synchronized (this) {
                	pools.put(poolName, pool);
                }
                index ++;
            }
        }
        if(index == 0){
        	String message = "连接池配置解析失败，连接池创建失败.";
        	logger.warn(message);
        	throw new DBPoolException(message);
        }
    }
    
    
    
    /**
     * 读取属性完成初始化
     * @throws DBPoolException 
     */
    private void initManager() throws DBPoolException {
    	logger = Logger.getLogger(DB_POOL_LOG4J_LOGGER);
        if (DBPoolPro != null) {
            createPools(DBPoolPro);
        } else {
            throw new DBPoolException("不能读取属性文件");
        }
    }
 
    /**
     * 数据库连接池.
     */
   private class DBConnectionPool {
	   
	   /**连接池的空闲组,其中的连接未被使用*/
	    private Vector<ConnectionBean> freeConnBeans = new Vector<ConnectionBean>();
	    /**连接池的非空闲组,其中的连接正在被使用*/
	    private Map<Integer, ConnectionBean> busyConnBeans = new Hashtable<Integer, ConnectionBean>();
	    
	    private Map<String,Thread> threads = new HashMap<String, Thread>();
	    
	    /**非空闲中的连接数*/
	    private int busyConnNum;
        private String poolname;
        private String driverName;
        private Driver driver;
        private String URL;
        private String password;
        private String username;
        /**最大连接数*/
        private int maxConn;
        /**最小连接数(初始化连接数)*/
        private int minConn;
        /**一次生成连接数*/
        private int onceConn;
        /**连接最大空闲时间*/
        private int maxFreetime;
        /**检测连接存活时间的时间间隔*/
        private int checkLifetimeInterval = maxFreetime/10;
        /**获得请求的等待时间*/
        private int requestTimeout;
        /**同步机制的锁对象*/
//        private int checkFreetimeInterval = 1000;
//        /**连接池的监控线程*/
//        private ConnLifeThread lifeThread;
        
        
        /**
         * 创建新的连接池
         * @param poolname 连接池名字
         * @param driverName 数据库驱动名字
         * @param URL 数据库的JDBC URL
         * @param username 数据库帐号,或 null
         * @param password 密码,或 null
         * @param maxConn 此连接池允许建立的最大连接数
         * @param minConn 此连接池的初始化和最小连接数
         * @param onceConn 每次新建连接的数量
         * @param requestTimeout 请求超时时间
         * @param maxFreetime 连接的最大空闲时间
         * @throws DBPoolException 
         */
        public DBConnectionPool(String poolname, String driverName, String URL, String username,
                                String password,int maxConn, int minConn, int onceConn, int requestTimeout, int maxFreetime) throws DBPoolException {
            this.poolname = poolname;
            this.driverName = driverName;
            this.URL = URL;
            this.username = username;
            this.password = password;
            this.maxConn = maxConn;
            this.minConn = minConn;
            this.onceConn = onceConn;
            this.requestTimeout = requestTimeout;
            this.maxFreetime = maxFreetime;
            logger.info("成功创建连接池" + poolname);
            //初始化连接池
            initConnPool();
        }
        
        /**
         * 新建ConnectionBean，并将其加入连接池的空闲组中
         * @param connNum 新建ConnecionBean的数量
         * @throws DBPoolException 
         */
        private void createConnBean(int connNum) throws DBPoolException{
        	for(int i = 0;i < connNum;i++){
        		ConnectionBean connBean = new ConnectionBean();
				this.addConnBean(connBean);
        	}
        }
        
        /**
         * 初始化连接，初始化连接池时调用
         * @throws DBPoolException 
         */
        private void initConnection() throws DBPoolException{
			createConnBean(minConn);
        }
        
        /**
         * 初始化连接池
         * @throws DBPoolException 
         */
        private void initConnPool() throws DBPoolException{
        	logger.info("初始化连接池："+poolname);
        	registerDriver();
        	initConnection();
        	//监控连接池中的连接是否已经过了存活时间
        	Thread lifeThread = new ConnLifeThread();
        	synchronized (threads) {
        		threads.put("lifeThread", lifeThread);
        	}
        	lifeThread.start();
        }
        
        /**
         * 将新生成的连接放入空闲组中
         * @param connBean 新生成的连接对象
         */
		private synchronized void addConnBean(ConnectionBean connBean) {
			freeConnBeans.addElement(connBean);
			notifyAll();
		}
        
        /**
         * 将不再使用的连接返回给连接池
         * @param conn 客户程序释放的连接
         */
		private synchronized void freeConnection(Connection conn) {
			if(conn == null){
				return;
			}
			int hashCode = conn.hashCode();
			ConnectionBean connBean = busyConnBeans.get(hashCode);
			if (connBean == null) {
				return;
			}
			connBean.lastUsedTime = System.currentTimeMillis();
			freeConnBeans.add(connBean);
			busyConnBeans.remove(hashCode);
			busyConnNum--;
			notifyAll();
			logger.debug("连接池" + this.poolname + "释放掉一个连接");
		}
		
		/**
         * 将不再使用的连接返回给连接池,并且关闭statement和 ResultSet
         * @param conn 客户程序释放的连接
         * @param state
         * @param rs
		 * @throws DBPoolException 
         */
		private synchronized void freeConnection(Connection conn, Statement state, ResultSet rs) throws DBPoolException {
			closeStatementRs(state, rs);
			
			if(conn == null){
				return;
			}
			int hashCode = conn.hashCode();
			ConnectionBean connBean = busyConnBeans.get(hashCode);
			if (connBean == null) {
				return;
			}
			connBean.lastUsedTime = System.currentTimeMillis();
			freeConnBeans.add(connBean);
			busyConnBeans.remove(hashCode);
			busyConnNum--;
			notifyAll();
			logger.debug("连接池" + this.poolname + "释放掉一个连接");
		}
		
		/**
		 * 关闭statement和 ResultSet
		 * @param state
		 * @param rs
		 * @throws DBPoolException
		 */
		private void closeStatementRs(Statement state, ResultSet rs) throws DBPoolException{
			try {
				if(state != null){
					state.close();
				}
				if(rs != null){
					rs.close();
				}
			} catch (SQLException e) {
				throw new DBPoolException(e);
			}
		}
        /**
         * 从连接池获得一个可用连接.如没有空闲的连接且当前连接数小于最大连接
         * 数限制,则创建新连接.
         * @throws DBPoolException 
         */
        private synchronized Connection getConn() throws DBPoolException {
			Connection conn = null;
			if (freeConnBeans.size() > 0) {
				conn = freeConnBeans.firstElement().proxyConneciton;
				ConnectionBean connBean = freeConnBeans.remove(0);
				busyConnBeans.put(conn.hashCode(), connBean);

				try {
					if (conn.isClosed()) {
						logger.info("从连接池" + poolname + "删除一个无效连接");
						// 递归调用自己,尝试再次获取可用连接
						busyConnBeans.remove(conn);
						conn = getConn();
					}
				} catch (SQLException e) {
					logger.warn("从连接池" + poolname + "删除一个无效连接");
					// 递归调用自己,尝试再次获取可用连接
					busyConnBeans.remove(conn);
					conn = getConn();
				}
			} else if (maxConn == 0 || busyConnNum < maxConn) {
				for (int i = 0; i < onceConn - 1; i++) {
					if (maxConn != 0 && busyConnNum + i + 1 >= maxConn) {
						break;
					}
					createConnBean(1);
				}
				ConnectionBean connBean = new ConnectionBean();
				conn = connBean.proxyConneciton;
				//hashCode()作为KEY,有隐含的风险，因为不同对象的hashCode有可能相同。
				busyConnBeans.put(conn.hashCode(), connBean);
			}
			return conn;
		}
        
        /**
         * 从连接池获取可用连接
         * @param timeout 以毫秒计的等待时间限制
         * @throws DBPoolException 
         */
		private synchronized Connection getConnection() throws DBPoolException {
			long startTime = new Date().getTime();
			Connection conn = null;
			while ((conn = getConn()) == null) {
				try {
					logger.debug("暂时没有可用连接，等待中...");
					wait(requestTimeout);
				} catch (InterruptedException e) {
				}
				logger.debug("等待结束.");
				if ((new Date().getTime() - startTime) >= requestTimeout) {
					logger.info("请求超时");
					return null;
				}
			}

			return conn;
		}
        
        /**
         * 关闭所有连接
         */
        private synchronized void release() {

			// 终止所有线程
			stopThreads();
			// 关闭空闲组中的所有连接.
			Enumeration<ConnectionBean> connectionBeans = freeConnBeans.elements();
			while (connectionBeans.hasMoreElements()) {
				// 真正关闭连接
				ConnectionBean connBean = connectionBeans.nextElement();
				try {
					connBean.close();
					logger.info("关闭连接池" + poolname + "中的一个空闲连接");
				} catch (SQLException e) {
					logger.error("无法关闭连接池" + poolname + "中的连接", e);
				}
			}
			freeConnBeans.removeAllElements();

			// 关闭非空闲组中的所有连接.
			Set<Integer> keys = busyConnBeans.keySet();
			Iterator<Integer> it = keys.iterator();
			while (it.hasNext()) {
				int key = it.next();
				try {
					ConnectionBean connBean = busyConnBeans.get(key);
					connBean.close();
					logger.info("关闭连接池" + poolname + "中的一个非空闲连接");
				} catch (SQLException e) {
					logger.error("无法关闭连接池" + poolname + "中的连接", e);
				}
			}
			busyConnBeans.clear();
			// 卸载驱动
			unloadDriver();

        }
        
        /**
         * 强制终止所有线程
         */
		private void stopThreads() {
			
			synchronized (threads) {
				Set<String> keys = threads.keySet();
				Iterator<String> it = keys.iterator();
				while (it.hasNext()) {
					String key = it.next();
					Thread t = threads.get(key);
					t.interrupt();
				}
			}
		}
        /**
         * 卸载驱动
         */
        private void unloadDriver(){
        	try {
				DriverManager.deregisterDriver(driver);
				logger.info("连接池" + poolname + "撤销JDBC驱动程序 " + driver.getClass().getName()+ "的注册");
			} catch (SQLException e) {
				logger.error("连接池" + poolname + "无法撤销下列JDBC驱动程序的注册: "+ driver.getClass().getName(),e);
			}
        }
        
        /**
         * 连接是否正在使用中
         * @param conn 连接
         * @return
         */
		private synchronized boolean isBusy(Connection conn) {
			ConnectionBean connBean = null;
			connBean = busyConnBeans.get(conn.hashCode());
			if (connBean == null) {
				return false;
			}
			return true;
		}
        
        private boolean registerDriver() {
			boolean flag = false;
			try {
				driver = (Driver)Class.forName(driverName).newInstance();
				flag = true;
				logger.info("连接池" + poolname + "成功注册JDBC驱动程序" + driverName);
			} catch (Exception e) {
				logger.error("连接池" + poolname + "无法注册JDBC驱动程序: " + driverName + ", 错误: " + e);
				stopThreads();
				throw new RuntimeException(e);
			}
			return flag;
        }
        
        /**
         * 监控连接池中连接的线程
         */
        private class ConnLifeThread extends Thread{
        	
        	private volatile boolean stopFlag = false;
			public void run() {
				logger.info("连接池" + poolname +"线程启动...");
				while (!stopFlag) {
					try {
						Thread.sleep(checkLifetimeInterval);
					} catch (InterruptedException e) {
						logger.error(this.getName()+" is interrupted:"+ e.getMessage());
//						throw new RuntimeException("打断线程睡眠，终止线程");
						break;
					}
					synchronized (DBConnectionPool.this) {
						Iterator<ConnectionBean> it = freeConnBeans.iterator();

						while (it.hasNext()) {
							ConnectionBean connBean = it.next();
							try {
								if (connBean.isClosed()){
									it.remove();
									logger.info("连接池" + poolname+ "中的一个连接废弃,已经从连接池删除.");
									continue;
								}else if (connBean.isDead()) {
									connBean.close();
									it.remove();
									logger.info("连接池" + poolname+ "中的一个连接生命周期已到，已经被关闭,并从连接池删除.");
									continue;
								}
								if(connBean.isTimeOut()){
									connBean.close();
									it.remove();
									logger.info("连接池" + poolname+ "中的一个连接空闲时间已经到期,从连接池删除.");
								}
								
							} catch (SQLException e) {
								logger.error("连接关闭失败", e);
							}
						}
						int size = 0;
						if((size = minConn - freeConnBeans.size()- busyConnNum )>0){
							logger.debug(size+":"+freeConnBeans.size()+":"+busyConnNum);
							try {
								createConnBean(size);
							} catch (DBPoolException e) {
								stopFlag = true;
							}
						}
					}
				}
				logger.info(poolname+"连接池: " + this.getName()+" is stoping normally...");
			}
        }
        
        /**
         * 封装了连接对象，用于动态产生连接的代理对象
         */
        private class ConnectionBean implements InvocationHandler{
        	
//        	private DBConnectionPool dbpool;
			private Connection connection;
			private Connection proxyConneciton;
			 /**连接初始化时间(毫秒)*/
        	private long inittime;
        	 /**连接最大存活时间(豪秒)*/
        	private long lifetime = CONNECTION_LIFETIME_DEFAULT_VAL;
        	/**最后一次被使用的时间(豪秒)*/
        	private long lastUsedTime;
        	
        	public ConnectionBean() throws DBPoolException{
//        		this.dbpool = dbpool;
        		this.connection = createConnection();
        		this.inittime = System.currentTimeMillis();
        		this.lastUsedTime = inittime;
        		this.bindProxy();
        	}
        	
        	 /**
             * 创建新的连接
        	 * @throws DBPoolException 
             * @throws SQLException 
             */
			private Connection createConnection() throws DBPoolException {
				
				String path = DB_File;
                try {
//                	path = ConnectionBean.class.getResource("/" + MailConfigInfo.COMMONMAIL + MailConfigInfo.MAIL_CONFIG_FILE_SUFFIX).toURI().getPath();
//                	File file = new File(path);
//                	path = file.getParentFile().getAbsolutePath() + "/../" + DATABASE_DIRECTORY + DATABASE_NAME;
                    if (username == null) {
                    	connection = DriverManager.getConnection(URL + path);
                    } else {
                    	connection = DriverManager.getConnection(URL, username, password);
                    }
                    logger.info("连接池" + poolname + "创建一个新的连接");
    			} catch (SQLException e) {
    				logger.error("无法创建下列URL的连接: " + URL + path, e);
    				throw new DBPoolException(e);
    			} 
//    			catch (URISyntaxException e) {
//    				logger.error("database path is not found:" + path, e);
//    				throw new DBPoolException(e);
//				}
                return connection;
            }

			
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				String methodName = method.getName();
				//代理掉close方法
				if ("close".equals(methodName)) {
					logger.debug("利用者试图关闭连接，将通过代理回收连接");
					freeConnection(proxyConneciton);
					return null;
					//若所有方法都拦截，会栈溢出，原因不明.
				}else if("createStatement".equals(methodName)||"prepareStatement".equals(methodName)||"prepareCall".equals(methodName)||"commit".equals(methodName)){
					
					if (!isBusy(proxyConneciton)) {
						logger.debug("该连接已经被释放掉了,利用者试图使用该连接");
						// 停止监控线程
						stopThreads();
						throw new RuntimeException("该连接已经被释放掉了,不能继续使用");
					}
				}
				return method.invoke(connection, args);
			}

			/**
			 * 创建连接的代理对象
			 * @return 连接的代理对象
			 */
			private Connection bindProxy(){
				Connection proxyConnection = (Connection)Proxy.newProxyInstance(ConnectionBean.class.getClassLoader(), connection.getClass().getInterfaces(), this);
				this.proxyConneciton = proxyConnection;
				return proxyConnection;
			}
			
			/**
			 * close connection.
			 * @throws SQLException
			 */
			private void close() throws SQLException{
				connection.close();
			}
			
			/**
			 * 连接是否已经被关闭
			 * @return
			 * @throws SQLException
			 */
			private boolean isClosed() throws SQLException{
				return connection.isClosed();
			}
			
			/**
			 * 是否已经超过最大存活时间
			 * @return
			 */
			private boolean isDead(){
				long nowtime = System.currentTimeMillis();
				long reallife = nowtime - inittime;
				return reallife >= lifetime;
			}
			
			/**
			 * 是否超过最大空闲时间
			 * @return
			 */
			private boolean isTimeOut(){
				long nowtime = System.currentTimeMillis();
				long realFreeTime = nowtime - lastUsedTime ;
				return realFreeTime >= maxFreetime;
			}
        }
    }
}
 
