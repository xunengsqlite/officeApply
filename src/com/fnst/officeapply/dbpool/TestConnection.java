package com.fnst.officeapply.dbpool;

import java.sql.Connection;

public class TestConnection {

	private static class TestThread extends Thread{
		private DBPoolManager manager;
		private Connection conn;
		public TestThread(DBPoolManager manager, Connection conn){
			this.manager = manager;
			this.conn = conn;
		}
		public void run(){
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			manager.freeConnection("conn", conn);
		}
	}
	
	public static void main(String[] args) throws Exception {
		DBPoolManager manager = DBPoolManager.getInstance();
		Connection conn1 = manager.getConnection("conn");
		Connection conn2 = manager.getConnection("conn");
		Connection conn3 = manager.getConnection("conn");
		Connection conn4 = manager.getConnection("conn");
		Connection conn5 = manager.getConnection("conn");
		try{
		manager.freeConnection("conn", conn1);
//		conn1.createStatement();
//		conn1.commit();
//		manager.getConnection("connection");
		conn1.close();
		conn2.close();
		conn3.close();
		manager.freeConnection("conn", conn1);
		manager.freeConnection("conn", conn2);
		manager.freeConnection("conn", conn3);
		Thread.sleep(2000);
		manager.getConnection("conn");
		manager.getConnection("conn");
		manager.getConnection("conn");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
//		new TestThread(manager, conn4).start();
		manager.getConnection("conn");
//		conn4.close();
//		manager.freeConnection("connection", conn4);
//		manager.freeConnection("connection", conn5);
		manager.release();
	}
}
