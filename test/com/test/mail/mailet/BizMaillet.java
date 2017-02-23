package com.test.mail.mailet;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.apache.mailet.GenericMailet;
import org.apache.mailet.Mail;

/**  
 * Mailet处理程序  
 */  
public class BizMaillet extends GenericMailet {   
    public void init() throws MessagingException {   
    }   
    
    
    Connection conn = null;   
    Statement stmt = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    
	private MimeMessage mimeMessage = null;    
    //附件下载后的存放目录    
    private String saveAttachPath = ""; 
    //存放邮件内容    
    private StringBuffer bodytext = new StringBuffer();
    //默认的日前显示格式 
    private String dateformat = "yy-MM-dd HH:mm";    
   
    //将msg[i]传到mimeMessage里
    public void setMimeMessage(MimeMessage mimeMessage) {    
        this.mimeMessage = mimeMessage;    
    }    
   
    /**   
     * 获得发件人的地址和姓名   
     */   
    public String getFrom() throws Exception {    
        InternetAddress address[] = (InternetAddress[]) mimeMessage.getFrom();    
        String from = address[0].getAddress();    
        if (from == null)    
            from = "";    
        String personal = address[0].getPersonal();    
        if (personal == null)    
            personal = "";    
        String fromaddr = personal  + from  ;    
        return fromaddr;    
    }    
   
    /**   
     * 获得邮件的收件人，抄送，和密送的地址和姓名，根据所传递的参数的不同 "to"----收件人 "cc"---抄送人地址 "bcc"---密送人地址   
     */   
    private String mailaddrto = new String();
    
    public String getMailAddressTO() throws Exception {    
        mailaddrto = "";    
        String addtype = "TO";    
        InternetAddress[] address = null;    
        if (addtype.equals("TO")) {    
            if (addtype.equals("TO")) {    
                address = (InternetAddress[]) mimeMessage.getRecipients(Message.RecipientType.TO);    
            }   
            if (address != null) {    
                for (int i = 0; i < address.length; i++) {    
                    String email = address[i].getAddress();    
                    if (email == null)    
                        email = "";    
                    else {    
                        email = MimeUtility.decodeText(email);    
                    }    
                    String personal = address[i].getPersonal();    
                    
                    if (personal == null)    
                        personal = "";    
                    else {    
                        personal = MimeUtility.decodeText(personal);    
                    }    
                    String compositeto = personal+ email ;    
                    mailaddrto += "," + compositeto;    
                }    
                mailaddrto = mailaddrto.substring(1);    
                
            }    
        } else {    
            throw new Exception("Error emailaddr type!");    
        }    
        
        return mailaddrto;    
    }    
    
    private String mailaddrcc = new String();
    
    public String getMailAddressCC() throws Exception {    
        mailaddrcc = "";    
        String addtype = "CC";    
        InternetAddress[] address = null;    
        if (addtype.equals("CC")) {    
           if (addtype.equals("CC")) {    
                address = (InternetAddress[]) mimeMessage.getRecipients(Message.RecipientType.CC);    
            }  
            if (address != null) {    
                for (int i = 0; i < address.length; i++) {    
                    String email = address[i].getAddress();    
                    if (email == null)    
                        email = "";    
                    else {    
                        email = MimeUtility.decodeText(email);    
                    }    
                    String personal = address[i].getPersonal();    
                    if (personal == null)    
                        personal = "";    
                    else {    
                        personal = MimeUtility.decodeText(personal);    
                    }    
                    String compositeto = personal+ email ;    
                    mailaddrcc += "," + compositeto;    
                }    
                mailaddrcc = mailaddrcc.substring(1);    
                
               
            }    
        } else {    
            throw new Exception("Error emailaddr type!");    
        }    
        
        return mailaddrcc;    
    }    
   
    /**   
     * 获得邮件主题   
     */   
    public String getSubject() throws MessagingException {    
        String subject = "";    
        try {    
            subject = MimeUtility.decodeText(mimeMessage.getSubject());    
            if (subject == null)    
                subject = "";    
        } catch (Exception exce) {}    
        return subject;    
    }    
   
    /**   
     * 获得邮件发送日期   
     */   
    public String getSentDate() throws Exception {    
        Date sentdate = mimeMessage.getSentDate();    
        SimpleDateFormat format = new SimpleDateFormat(dateformat);    
        return format.format(sentdate);    
    }    
   
    /**   
     * 获得邮件正文内容   
     */   
    public String getBodyText() {    
        return bodytext.toString();    
    }    
   
    /**   
     * 解析邮件，把得到的邮件内容保存到一个StringBuffer对象中，解析邮件 主要是根据MimeType类型的不同执行不同的操作，一步一步的解析   
     */   
    public void getMailContent(Part part) throws Exception {    
        String contenttype = part.getContentType();    
        int nameindex = contenttype.indexOf("name");    
        boolean conname = false;    
        if (nameindex != -1)    
            conname = true;    
       // System.out.println("CONTENTTYPE: " + contenttype);    
        if (part.isMimeType("text/plain") && !conname) {    
            bodytext.append((String) part.getContent());    
        } else if (part.isMimeType("text/html") && !conname) {    
            bodytext.append((String) part.getContent());    
        } else if (part.isMimeType("multipart/*")) {    
            Multipart multipart = (Multipart) part.getContent();    
            int counts = multipart.getCount();    
            for (int i = 0; i < counts; i++) {    
                getMailContent(multipart.getBodyPart(i));    
            }    
        } else if (part.isMimeType("message/rfc822")) {    
            getMailContent((Part) part.getContent());    
        } else {}    
    }    
   
    /**    
     * 判断此邮件是否需要回执，如果需要回执返回"true",否则返回"false"   
     */    
    public boolean getReplySign() throws MessagingException {    
        boolean replysign = false;    
        String needreply[] = mimeMessage    
                .getHeader("Disposition-Notification-To");    
        if (needreply != null) {    
            replysign = true;    
        }    
        return replysign;    
    }    
   
    /**   
     * 获得此邮件的Message-ID   
     */   
    public String getMessageId() throws MessagingException {    
        return mimeMessage.getMessageID();    
    }    
   
    /**   
     * 【判断此邮件是否已读，如果未读返回返回false,反之返回true】   
     */   
    public boolean isNew() throws MessagingException {    
        boolean isnew = false;    
        Flags flags = ((Message) mimeMessage).getFlags();    
        Flags.Flag[] flag = flags.getSystemFlags();    
        System.out.println("flags's length: " + flag.length);    
        for (int i = 0; i < flag.length; i++) {    
            if (flag[i] == Flags.Flag.SEEN) {    
                isnew = true;    
                System.out.println("seen Message.......");    
                break;    
            }    
        }    
        return isnew;    
    }    
   
    /**   
     * 判断此邮件是否包含附件   
     */   
    public boolean isContainAttach(Part part) throws Exception {    
        boolean attachflag = false;    
        //String contentType = part.getContentType();    
        if (part.isMimeType("multipart/*")) {    
            Multipart mp = (Multipart) part.getContent();    
            for (int i = 0; i < mp.getCount(); i++) {    
                BodyPart mpart = mp.getBodyPart(i);    
                String disposition = mpart.getDisposition();    
                if ((disposition != null) && ((disposition.equals(Part.ATTACHMENT)) || (disposition.equals(Part.INLINE))))    
                    attachflag = true;    
                else if (mpart.isMimeType("multipart/*")) {    
                    attachflag = isContainAttach((Part) mpart);    
                } else {    
                    String contype = mpart.getContentType();    
                    if (contype.toLowerCase().indexOf("application") != -1)    
                        attachflag = true;    
                    if (contype.toLowerCase().indexOf("name") != -1)    
                        attachflag = true;    
                }    
            }    
        } else if (part.isMimeType("message/rfc822")) {    
            attachflag = isContainAttach((Part) part.getContent());    
        }    
        return attachflag;    
    }    
   
    /**    
     * 【保存附件】    
     */    
    public String fileName2 = new String();
    
    public void saveAttachMent(Part part) throws Exception {    
         String fileName = "";
    	 //int j = 0;
        if (part.isMimeType("multipart/*")) {    
            Multipart mp = (Multipart) part.getContent();    
            for (int i = 0; i < mp.getCount(); i++) {    
                BodyPart mpart = mp.getBodyPart(i);    
                String disposition = mpart.getDisposition();    
                if ((disposition != null)    
                        && ((disposition.equals(Part.ATTACHMENT)) || (disposition    
                                .equals(Part.INLINE)))) {    
                    fileName = mpart.getFileName();    
                    if (fileName.toUpperCase().indexOf("GB18030") != -1) {    
                        fileName = MimeUtility.decodeText(fileName);    
                    }    
                  
                    
                    fileName2 += ","+fileName;
                   //System.out.println(j+":"+fileName2);
                    
                    //j++;
                    
                } else if (mpart.isMimeType("multipart/*")) {    
                    saveAttachMent(mpart);    
                } else {    
                    fileName = mpart.getFileName();    
                    if ((fileName != null)    
                            && (fileName.toUpperCase().indexOf("GB18030") != -1)) {    
                        fileName = MimeUtility.decodeText(fileName);    
                        
                        
                        fileName2 += ","+fileName;
                      //  System.out.println(j+":"+fileName2);
                        
                       // j++;
                        
                        
                    }    
                }    
            }    
        } else if (part.isMimeType("message/rfc822")) {    
            saveAttachMent((Part) part.getContent());    
        }    
    }    
   
    /**    
     * 【设置附件存放路径】    
     */    
   
    public void setAttachPath(String attachpath) {    
        this.saveAttachPath = attachpath;    
    }    
   
    /**   
     * 【设置日期显示格式】   
     */   
    public void setDateFormat(String format) throws Exception {    
        this.dateformat = format;    
    }    
   
    /**   
     * 【获得附件存放路径】   
     */   
    public String getAttachPath() {    
        return saveAttachPath;    
    }    
  
    public void service(Mail mail) throws MessagingException {   
        MimeMessage mmp = (MimeMessage) mail.getMessage();   
        this.setMimeMessage(mmp);
        
        String mailtype="text/html";
        
        
        try{
        
        	this.getMailContent((Part) mmp); 
        	
        	//------------------------------------保存收件人开始---------------------------------------------------
        	this.getMailAddressTO();
        	String to[] = mailaddrto.split(",");
        	String toname = to[0];
        	//System.out.println("to name"+toname);
        	
        	//保存收件人
        	String sql2 = "select * from folder where foldername='收件箱' and username='"+toname+"'";
        	ResultSet rs2 = this.queryResult(sql2);
        	int folderid = 0;
        	int mailcount = 0;
        	double folderspace = 0;
        	while(rs2.next()) {
        		folderid = rs2.getInt("folderid");
	        	//System.out.println("folderid:"+folderid);
        		mailcount = rs2.getInt("mailcount");
        		folderspace = rs2.getDouble("folderspace");
	        }
        	rs2.close();
        	stmt.close();
        	conn.close();
        	
        	
        	//System.out.println("this.getbodycontext:"+this.getBodyText());
        	//System.out.println("mimeMessage.getSize():"+mimeMessage.getSize());
        	float msgsize = (float)mimeMessage.getSize();
        	msgsize = msgsize/1024/1024;
        	//System.out.println("msgssize"+msgsize);
            String sql = "insert into email values(0,'" + this.getFrom()+"','"+toname 
            	+"','"+this.getSubject()+"','"+this.getBodyText()+"','"+this.getSentDate() +"','"
            	+mailtype+"',"+msgsize+",1,"+folderid+",2,9,'"+toname+"')"; 
            
            int emailid = this.UpdateSQL(sql);
            
            //更新folder
            mailcount = mailcount +1;
            folderspace = folderspace + msgsize;
        	String foldersql = "update folder set folderspace="+folderspace+",mailcount="+mailcount+" where foldername='收件箱' and username='"+toname+"'";
        	this.UpdateSQL(foldersql);
        	
        	
        	//更新emailspace
        	String essql = "select * from emailspace where username='"+toname+"'";
        	ResultSet rses = this.queryResult(essql);
        	double spaceleft = 0;
        	while(rses.next()) {
        		spaceleft = rses.getDouble("spaceleft");
	        }
        	rses.close();
        	stmt.close();
        	conn.close();
        	
        	spaceleft = spaceleft - msgsize;
        	String esupdatesql = "update emailspace set spaceleft=" +spaceleft+ " where username='"+toname+"'";
        	this.UpdateSQL(esupdatesql);
            
            
            //保存附件
            this.saveAttachMent((Part) mmp);   
            //System.out.println(fileName2);
            if(fileName2 != null && fileName2 != "") {
	            String attach[] = fileName2.substring(1).split(",");
	            for(int i = 0; i<attach.length; i++) {
	            	//System.out.println(attach[i]);
	            	File f = new File("d:/upload/"+attach[i]);
	            	float flength = (float)f.length();
	            	float filesize = flength/1024/1024;
	            	//System.out.println("f.length1:"+f.length());
	            	//System.out.println("filesize1"+filesize);
	            	String sql4 = "insert into attachment values(0,'d:/upload/"+attach[i]
	            	           +"','"+attach[i]+"',"+filesize+","+emailid+")";
	            	this.UpdateSQL(sql4);
	            }
	            fileName2 = "";
            }
            
            //保存cc
            this.getMailAddressCC();
            if(mailaddrcc != null && mailaddrcc != "") {
	            String cc[] = mailaddrcc.split(",");
	            
	            for(int i =0; i<cc.length; i++) {
	            	//System.out.println("cc"+i+":"+cc[i]);
	            	String sql5 = "insert into cc values(null,'"+cc[i]+"',"+ emailid+")";
	            	this.UpdateSQL(sql5);
	            }
            }
            
            //----------------------------------------保存收件人结束----------------------------------------------
       
            
            //*****************************************保存抄送人开始********************************************
            
            if(mailaddrcc != null && mailaddrcc != "") {
	            String cc[] = mailaddrcc.split(",");
	            
	            for(int i =0; i<cc.length; i++) {
	            	
	            	//保存cc email
	            	String sql6 = "select * from folder where foldername='收件箱' and username='"+cc[i]+"'";
	            	ResultSet rs4 = this.queryResult(sql6);
	            	int folderid2 = 0;
	            	int mailcount2 = 0;
	            	double folderspace2 = 0;
	            	while(rs4.next()) {
	            		folderid2 = rs4.getInt("folderid");
	            		mailcount2 = rs4.getInt("mailcount");
	            		folderspace2 = rs4.getDouble("folderspace");
	            	}
	            	rs4.close();
	            	stmt.close();
	            	conn.close();

	                String sql7 = "insert into email values(0,'" + this.getFrom()+"','"+toname 
	                	+"','"+this.getSubject()+"','"+this.getBodyText()+"','"+this.getSentDate() +"','"
	                	+mailtype+"',"+msgsize+",1,"+folderid2+",2,9,'"+cc[i]+"')"; 
	                
	               int emailid2 =  this.UpdateSQL(sql7);
	               
	               mailcount2 = mailcount2 +1;
	               folderspace2 = folderspace2 + msgsize;
	               String foldersql2 = "update folder set folderspace="+folderspace2+",mailcount="+mailcount+" where foldername='收件箱' and  username='"+cc[i]+"'";
	               this.UpdateSQL(foldersql2);
	               
	               
	             //更新emailspace
	           	String essql2 = "select * from emailspace where username='"+cc[i]+"'";
	           	ResultSet rses2 = this.queryResult(essql2);
	           	double spaceleft2 = 0;
	           	while(rses2.next()) {
	           		spaceleft2 = rses2.getDouble("spaceleft");
	   	        }
	           	rses2.close();
	           	stmt.close();
	           	conn.close();
	           	
	           	spaceleft2 = spaceleft2 - msgsize;
	           	String esupdatesql2 = "update emailspace set spaceleft=" +spaceleft2+ " where username='"+cc[i]+"'";
	           	this.UpdateSQL(esupdatesql2);
	                
	            	
	            //保存附件
	            this.saveAttachMent((Part) mmp);   
	            //System.out.println(fileName2);
	            if(fileName2 != null && fileName2 != "") {
		            String attach[] = fileName2.substring(1).split(",");
		            for(int j = 0; j<attach.length; j++) {
		            	//System.out.println(attach[i]);
		            	File f2 = new File("d:/upload/"+attach[j]);
		            	float flength2 = (float)f2.length();
		            	float filesize2 = flength2/1024/1024;
		            	//System.out.println("f2.length2:"+f2.length());
		            	//System.out.println("filesize2"+filesize2);
		            	String sql9 = "insert into attachment values(0,'d:/upload/"+attach[j]
		            	           +"','"+attach[j]+"',"+filesize2+","+emailid2+")";
		            	this.UpdateSQL(sql9);
		            }
		            fileName2 = "";
	            }
	            
	            //保存cc
	            //this.getMailAddressCC();
		            
		            for(int k =0; k<cc.length; k++) {
		            	//System.out.println("cc"+i+":"+cc[i]);
		            	String sql10 = "insert into cc values(0,'"+cc[k]+"',"+ emailid2+")";
		            	this.UpdateSQL(sql10);
		            }
	            
	            }
            }
            

        //******************************************保存抄送人结束**********************************************
            
        	bodytext = bodytext.delete(0, bodytext.length());
            
            
            
      /*      
       
        System.out.println("=============================");   
        System.out.println("Message   subject: " + this.getSubject());   
        System.out.println("Message   sentdate: "+ this.getSentDate());   
        System.out.println("Message   replysign: "+ this.getReplySign());   
        System.out.println("Message   hasRead: " + this.isNew());   
        System.out.println("Message   containAttachment: "+ this.isContainAttach((Part) mmp));   
        System.out.println("Message   form: " + this.getFrom());   
        this.getMailAddressTO(); 

        String too[] = mailaddrto.split(",");
        for(int i =0; i<too.length; i++) {
        	System.out.println("too"+i+":"+too[i]);
        }
       
        // System.out.println("Message   cc: "+ this.getMailAddressCC());   
        this.getMailAddressCC();
        String cc[] = mailaddrcc.split(",");
        
        for(int i =0; i<cc.length; i++) {
        	System.out.println("cc"+i+":"+cc[i]);
        }
        
        System.out.println("Message   bcc: ");   
        this.setDateFormat("yy年MM月dd日 HH:mm:ss");   
        System.out.println("Message   sentdate: "+ this.getSentDate());   
        System.out.println("Message   Message-ID: "+ this.getMessageId());   
        System.out.println();
       	// 获得邮件内容===============   
        this.getMailContent((Part) mmp);   
      	System.out.println("Message   bodycontent: \r\n"   
                + this.getBodyText());   
       	//this.setAttachPath("e:\\mailTemp");    
       
      	// mmp.setFlag(Flags.Flag.DELETED,   true); 
       
        this.saveAttachMent((Part) mmp);   
        System.out.println(fileName2);
        String attach[] = fileName2.substring(1).split(",");
        for(int i = 0; i<attach.length; i++) {
        	System.out.println(attach[i]);
        }
        fileName2 = "";
        
        */
        
        }catch(Exception e) {
        	e.printStackTrace();
        }
    }
    
    public ResultSet queryResult(String sql){
    	
        // 连接数据库   
        String driverName = "com.mysql.jdbc.Driver";   
        String dbURL = "jdbc:mysql://localhost/wellmail";   
        String userName = "root";   
        String userPwd = "root";   
        
        
        
        try {   
            Class.forName(driverName);   
            conn = DriverManager.getConnection(dbURL, userName, userPwd);  

	        stmt = conn.createStatement();
	        rs = stmt.executeQuery(sql);   
	        
	       /* while(rs.next()) {
	        	int id = rs.getInt("folderid");
	        	System.out.println("folderid:"+id);
	        }*/
	        
	       // System.out.println("邮件查询成功"); 
	        return rs;
	       
        } catch (ClassNotFoundException e) {   
            e.printStackTrace();   
        } catch (SQLException e) {   
            e.printStackTrace();   
        } finally{
        
			/*if(stmt != null) {
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
			}*/
		}
        return null;
}
    
    
 public int UpdateSQL(String sql){
    	
        
        // 连接数据库   
        String driverName = "com.mysql.jdbc.Driver";   
        String dbURL = "jdbc:mysql://localhost/wellmail?autoReconnect=true";   
        String userName = "root";   
        String userPwd = "root";   
        int newid = 0; 
        
        try {   
            Class.forName(driverName);   
            conn = DriverManager.getConnection(dbURL, userName, userPwd);  

            
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);   
            pstmt.executeUpdate();   
            
            rs = pstmt.getGeneratedKeys();   
             
            if(rs.next()) {
            	newid = rs.getInt(1);
            }
            
	        //System.out.println("邮件插入成功"); 
        } catch (ClassNotFoundException e) {   
            e.printStackTrace();   
        } catch (SQLException e) {   
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
        return newid;
 	}
 	
}

