package com.test.mail.test;


import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeUtility;

public class TestReceive {
	
	private StringBuffer bodytext = new StringBuffer();
	
	
	public void receive() {
		
		String host = "localhost";

		String username = "Postmaster";

		String password = "861213";

		// Create empty properties
		Properties props = new Properties();

		// Get session
		Session session = Session.getDefaultInstance(props, null);

		try{
		
			// Get the store
			Store store = session.getStore("pop3");
			store.connect(host, username, password);
	
			// Get folder
			Folder folder = store.getFolder("INBOX");
			folder.open(Folder.READ_ONLY);
	
			// Get directory
			Message message[] = folder.getMessages();
	
			
			for (int i=0, n=message.length; i<n; i++) {
				
			   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			   String sentdate = sdf.format(message[i].getSentDate());
			   
			   this.getMailContent((Part) message[i]);

			   System.out.println();
			   System.out.println("========================================");
			   System.out.println();
			   System.out.println("编号："+i);
			   System.out.println("发件人："+ message[i].getFrom()[0]);
			   System.out.println("收件人："+ getMailAddress("TO",message[i]) );
			   System.out.println("CC："+ getMailAddress("CC",message[i]));
			   System.out.println("BCC："+ getMailAddress("BCC",message[i]));
			   System.out.println("发送时间：" + sentdate);
			   System.out.println("邮件类型：" + message[i].getContentType()); 
			   System.out.println("邮件大小：" +message[i].getSize());
			   System.out.println("邮件主题：" + message[i].getSubject());
			   System.out.println("邮件内容：" + bodytext);
			}
	
			// Close connection 
			folder.close(false);
			store.close();

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
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
	
	 public String getMailAddress(String type,Message msg) throws Exception {    
	        String mailaddr = "";    
	        String addtype = type.toUpperCase();    
	        InternetAddress[] address = null;    
	        if (addtype.equals("TO") || addtype.equals("CC")|| addtype.equals("BCC")) {    
	            if (addtype.equals("TO")) {    
	                address = (InternetAddress[]) msg.getRecipients(Message.RecipientType.TO);    
	            } else if (addtype.equals("CC")) {    
	                address = (InternetAddress[]) msg.getRecipients(Message.RecipientType.CC);    
	            } else {    
	                address = (InternetAddress[]) msg.getRecipients(Message.RecipientType.BCC);    
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
	                    
	                    /*System.out.println("_----------------------------------");
	                    System.out.println("Personal"+personal);
	                    System.out.println("_----------------------------------");*/
	                    
	                    if (personal == null)    
	                        personal = "";    
	                    else {    
	                        personal = MimeUtility.decodeText(personal);    
	                    }    
	                    String compositeto = personal + "<" + email + ">";    
	                    mailaddr += "," + compositeto;    
	                }    
	                mailaddr = mailaddr.substring(1);    
	            }    
	        } else {    
	            throw new Exception("Error emailaddr type!");    
	        }    
	        return mailaddr;    
	    }    
	
	public static void main(String[] args) {
		new TestReceive().receive();
	}
}
