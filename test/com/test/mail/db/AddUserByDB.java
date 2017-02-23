package com.test.mail.db;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.james.security.DigestUtil;
  
/**  
 * 通过操作数据库来添加James用户  
 *   
 * @author Michael.Zhang  
 *         Mail:bauble@126.com 
 *         qq:675980656
 */  
public class AddUserByDB {   
    public static void main(String[] args) {   
        
    	// 连接数据库   
        String driverName = "com.mysql.jdbc.Driver";   
        String dbURL = "jdbc:mysql://localhost/testmail?autoReconnect=true";   
        String userName = "root";   
        String userPwd = "root";   
        
        Connection conn = null;   
        Statement stmt = null;
        
        try {   
            Class.forName(driverName);   
            conn = DriverManager.getConnection(dbURL, userName, userPwd);  
            
            // 构造并执行SQL语句，关键在于DigestUtil.digestString("881213", "SHA")，实现对密码的SHA加密   
            // 注：用户信息的后四个属性需要使用（'SHA',0,NULL,0,null）此四个默认值，若用错，则新建用户可能不能使用   
            String sql = "insert into users values('severus','"  
                + DigestUtil.digestString("861213", "SHA")   
                + "','SHA',0,NULL,0,null)";   
	        stmt = conn.createStatement();
	        stmt.executeUpdate(sql);   
	        conn.close();   
	        System.out.println("用户添加成功"); 
        } catch (ClassNotFoundException e) {   
            e.printStackTrace();   
        } catch (SQLException e) {   
            e.printStackTrace();   
        } catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} finally{
			if(stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(conn != null) {
				try {
					conn.close();
					conn = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}  
    }   
}  
