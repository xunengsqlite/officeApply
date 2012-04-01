package com.fnst.officeapply.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement; 
import java.sql.ResultSet; 


public class DBCreator {

	public static void main(String[] args) {   
		  
  
        try {   
            Class.forName("org.sqlite.JDBC");   
            
            Connection conn = DriverManager   
                    .getConnection("jdbc:sqlite:src/OfficeApply.db");   
            // 建立事务机制,禁止自动提交，设置回滚点   
            conn.setAutoCommit(false);   
            Statement stat = conn.createStatement(); 
           
            stat.executeUpdate("create table Department(" +
            		"id integer not null primary key,"+
            		"departmentName varchar(20) not null unique);");
            
            stat.executeUpdate("create table SectionOffice(" +
            		"id integer not null primary key,"+
            		"officeName varchar(20) not null unique);");
            
            stat.executeUpdate("create table ProjectGroup(" +
            		"id integer not null primary key,"+
            		"groupName varchar(20) not null unique);");
            
            stat.executeUpdate("create table Authority(" +
            		"id integer not null primary key,"+
            		"permission integer not null unique," +
            		"permissionName varchar(20) not null unique);");
            //创建UserInfo数据表
            stat.executeUpdate("create table UserInfo(" +
            		"id integer not null primary key,"+
            		"userName varchar(20) not null unique," +
            		"password varchar(20) not null," +
            		"userRealName varchar(20) not null," +
            		"userMail varchar(50) not null," +
            		"permissionID integer not null," +
            		"departmentID integer not null," +
            		"officeID integer not null," +
            		"groupID integer not null," +
            		"foreign key(permissionID) references Authority(id)," +
            		"foreign key(groupID) references ProjectGroup(id)," +
            		"foreign key(departmentID) references Department(id)," +
            		"foreign key(officeID) references SectionOffice(id));");                                
            
            //创建Goods数据表
            stat.executeUpdate("create table Goods(" +
            		"id integer not null primary key,"+
            		"goodsName varchar(20) not null unique," +
            		"goodsUnit varchar(5) not null);"); 
                                                     
            //创建CurrentRecord数据表
            stat.executeUpdate("create table CurrentRecord(" +
            		"id integer not null primary key,"+
            		"goodsID integer not null ," +
            		"userID integer not null," +
            		"count integer," +
            		"unique(goodsID,userID),"+
            		"foreign key(goodsID) references Goods(id)," +
            		"foreign key(userID) references UserInfo(id));"); 
    
            //创建HistoryRecordList数据表
            stat.executeUpdate("create table HistoryRecordList(" +
            		"historyRecordListID integer not null primary key," +
            		"historyRecordDate Datetime not null," +
            		"groupID integer not null," +
            		"note text," +
            		"foreign key (groupID) references ProjectGroup(id));"); 
            
            //创建HistoryRecord数据表
            stat.executeUpdate("create table HistoryRecord (" +
            		"id integer not null primary key,"+
            		"historyRecordListID integer not null," +
            		"goodsName varchar(20) not null," +
            		"userName varchar(20) not null," +
            		"userRealName varchar(20) not null," +
            		"count integer," +
            		"foreign key(historyRecordListID) references HistoryRecordList(historyRecordListID));");

            stat.executeUpdate("insert into Authority values(null,'0','普通用户');");
            stat.executeUpdate("insert into Authority values(null,'1','管理员');");
            stat.executeUpdate("insert into Authority values(null,'10','超级管理员');");
            stat.executeUpdate("insert into ProjectGroup values(null,'proasq');");
            stat.executeUpdate("insert into ProjectGroup values(null,'fjcit');");
            stat.executeUpdate("insert into ProjectGroup values(null,'admin-private');");
            stat.executeUpdate("insert into Department values(null,'第二软件事业部第一开发部');");
            stat.executeUpdate("insert into Department values(null,'第二软件事业部第二开发部');");
            stat.executeUpdate("insert into Department values(null,'第二软件事业部第三开发部');");
            stat.executeUpdate("insert into SectionOffice values(null,'一课');");
            stat.executeUpdate("insert into SectionOffice values(null,'二课');");
            stat.executeUpdate("insert into SectionOffice values(null,'三课');");
            stat.executeUpdate("insert into SectionOffice values(null,'四课');");
            stat.executeUpdate("insert into SectionOffice values(null,'五课');");
            stat.executeUpdate("insert into SectionOffice values(null,'六课');");
            //向UserInfo表增加管理员
            stat.executeUpdate("insert into UserInfo values (null,'weicl', 'ACDBA30953782C2A6CCB26D644830894','韦灿乐','weicl@cn.fujitsu.com','2','1','4','1');");   
            stat.executeUpdate("insert into UserInfo values (null,'yuanly', 'ACDBA30953782C2A6CCB26D644830894','袁凌雁','jy_yuanlingyan@cn.fujitsu.com','1','1','4','1');");
            stat.executeUpdate("insert into UserInfo values (null,'xuneng', 'ACDBA30953782C2A6CCB26D644830894','徐能','xuneng@cn.fujitsu.com','2','1','4','1');");
            stat.executeUpdate("insert into UserInfo values (null,'admin', 'ACDBA30953782C2A6CCB26D644830894','系统管理员','weicl@cn.fujitsu.com','3','1','4','3');");
            stat.executeUpdate("insert into UserInfo values (null,'admin2', 'ACDBA30953782C2A6CCB26D644830894','系统管理员2','xuneng@cn.fujitsu.com','3','1','4','3');");
            //向Goods表增加办公用品信息
            stat.executeUpdate("insert into Goods(goodsName,goodsUnit)values('签字笔（黑）','支');"); 
            stat.executeUpdate("insert into Goods(goodsName,goodsUnit)values('签字笔（红）','支');"); 
            stat.executeUpdate("insert into Goods(goodsName,goodsUnit)values('自动铅笔','支');"); 
            stat.executeUpdate("insert into Goods(goodsName,goodsUnit)values('圆珠笔','支');"); 
            stat.executeUpdate("insert into Goods(goodsName,goodsUnit)values('白板笔(红)','支');"); 
            stat.executeUpdate("insert into Goods(goodsName,goodsUnit)values('白板笔（兰）','支');"); 
            stat.executeUpdate("insert into Goods(goodsName,goodsUnit)values('白板笔（黑）','支');"); 
            stat.executeUpdate("insert into Goods(goodsName,goodsUnit)values('荧光笔','支');"); 
            stat.executeUpdate("insert into Goods(goodsName,goodsUnit)values('签字笔笔芯(黑)','支');"); 
            stat.executeUpdate("insert into Goods(goodsName,goodsUnit)values('签字笔笔芯(红)','支');"); 
            stat.executeUpdate("insert into Goods(goodsName,goodsUnit)values('铅芯','支');"); 
            stat.executeUpdate("insert into Goods(goodsName,goodsUnit)values('橡皮','块');"); 
            stat.executeUpdate("insert into Goods(goodsName,goodsUnit)values('胶棒','支');"); 
            stat.executeUpdate("insert into Goods(goodsName,goodsUnit)values('A4复印纸(80g)','包');"); 
            stat.executeUpdate("insert into Goods(goodsName,goodsUnit)values('A4打印纸(70g)','包');"); 
            stat.executeUpdate("insert into Goods(goodsName,goodsUnit)values('A3复印纸','包');"); 
            stat.executeUpdate("insert into Goods(goodsName,goodsUnit)values('60页笔记本(A5)','本');"); 
            stat.executeUpdate("insert into Goods(goodsName,goodsUnit)values('60页笔记本(B5)','本');"); 
            stat.executeUpdate("insert into Goods(goodsName,goodsUnit)values('A4文件夹','个');"); 
            stat.executeUpdate("insert into Goods(goodsName,goodsUnit)values('拉杆文件夹','个');"); 
            stat.executeUpdate("insert into Goods(goodsName,goodsUnit)values('书立','付');"); 
            stat.executeUpdate("insert into Goods(goodsName,goodsUnit)values('文件栏','个');"); 
            stat.executeUpdate("insert into Goods(goodsName,goodsUnit)values('网纹袋','只');"); 
            stat.executeUpdate("insert into Goods(goodsName,goodsUnit)values('长尾夹(2023大)','个');"); 
            stat.executeUpdate("insert into Goods(goodsName,goodsUnit)values('长尾夹(2024中)','个');"); 
            stat.executeUpdate("insert into Goods(goodsName,goodsUnit)values('长尾夹(2025小)','个');"); 
            stat.executeUpdate("insert into Goods(goodsName,goodsUnit)values('订书机(小)','个');"); 
            stat.executeUpdate("insert into Goods(goodsName,goodsUnit)values('订书机(标准)','个');"); 
            stat.executeUpdate("insert into Goods(goodsName,goodsUnit)values('订书针','盒');"); 
            stat.executeUpdate("insert into Goods(goodsName,goodsUnit)values('计算器','个');"); 
            stat.executeUpdate("insert into Goods(goodsName,goodsUnit)values('电池','节');"); 
            stat.executeUpdate("insert into Goods(goodsName,goodsUnit)values('剪刀','把');"); 
            stat.executeUpdate("insert into Goods(goodsName,goodsUnit)values('裁纸刀','把');"); 
            stat.executeUpdate("insert into Goods(goodsName,goodsUnit)values('直尺','把');"); 
            stat.executeUpdate("insert into Goods(goodsName,goodsUnit)values('回形针','盒');"); 
            stat.executeUpdate("insert into Goods(goodsName,goodsUnit)values('印油','个');"); 
            stat.executeUpdate("insert into Goods(goodsName,goodsUnit)values('涂改液','支');"); 
            stat.executeUpdate("insert into Goods(goodsName,goodsUnit)values('透明胶带','支');"); 
            stat.executeUpdate("insert into Goods(goodsName,goodsUnit)values('N次贴','本');"); 
            stat.executeUpdate("insert into Goods(goodsName,goodsUnit)values('标签贴纸','本');"); 
            stat.executeUpdate("insert into Goods(goodsName,goodsUnit)values('水瓶（5人一个）','个');"); 
            stat.executeUpdate("insert into Goods(goodsName,goodsUnit)values('风扇（5人一个）','个');"); 
            
           conn.commit();
        
            ResultSet rs = stat.executeQuery("select * from UserInfo;");   
            while (rs.next()) {   
                System.out.println("userName = " + rs.getString("userName"));   
                System.out.println("password = " + rs.getString("password")); 
                System.out.println("userRealName = " + rs.getString("userRealName"));   
                System.out.println("userMail = " + rs.getString("userMail"));   
                System.out.println("permissionID = " + rs.getString("permissionID"));   
                System.out.println("groupID = " + rs.getString("groupID"));
            }    
        
            rs = stat.executeQuery("select * from Goods;"); 
           while (rs.next()) { 
        	   System.out.println("goodsID = " + rs.getInt("id"));
               System.out.println("goodsName = " + rs.getString("goodsName"));   
               System.out.println("goodsUnit = " + rs.getString("goodsUnit")); 
                
           }    
            rs.close();   
            rs = stat.executeQuery("select * from ProjectGroup;");
            while(rs.next()){
            	System.out.println("groupID = " + rs.getInt(1));
            	System.out.println("groupName = " + rs.getString(2));
            }
            conn.close();     
        } catch (Exception e) {    
            e.printStackTrace();   
        }     
    }   	
	}

