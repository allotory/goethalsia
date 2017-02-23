package com.test;

import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;

public class MailTest {

	public static void main(String[] args) {

		MailUserData mud = new MailUserData();

		Properties props = System.getProperties();
		props.put("mail.smtp.host", "smtp.126.com");
		props.put("mail.smtp.auth", "true");
		Session session = Session.getDefaultInstance(props, null);
		URLName urln = new URLName("pop3", "pop.126.com", 110, null, "bauble",
				"severus125874693");

		try {

			Store store = session.getStore(urln);
			store.connect();

			// save stuff into MUD
			mud.setSession(session);
			mud.setStore(store);

			// create and open default Trash, Draft and sendbox folder

			Folder folder = store.getFolder("Trash");
			if (!folder.exists())
				folder.create(Folder.HOLDS_MESSAGES);

			folder = store.getFolder("SendBox");
			if (!folder.exists())
				folder.create(Folder.HOLDS_MESSAGES);

			folder = store.getFolder("Draft");
			if (!folder.exists())
				folder.create(Folder.HOLDS_MESSAGES);
			
			folder = store.getFolder("Inbox");
			if (!folder.exists())
				folder.create(Folder.HOLDS_MESSAGES);

			folder.open(Folder.READ_WRITE);

			// save draft into MUD
			// URLName url = new URLName("pop3",hostname, -1, "inbox", username,
			// password);
			mud.setURLName(urln);

			String operation = "";
			String folderName = "";
			String newFolderName = "";

			if (operation != null && operation.equals("create")) {
				mud.doCreateFolder(newFolderName);
			}
			if (operation != null && operation.equals("delete")) {
				mud.doDeleteFolder(folderName);
			}
			if (operation != null && operation.equals("rename")) {
				mud.doRenameFolder(folderName, newFolderName);
			}

			Folder folder2;
			Store store2 = mud.getStore();
			folder2 = store2.getDefaultFolder();
			if (folder2 == null)
				System.out.println("No folder is available");
			Folder[] f = folder2.list("%");
			
			for(int i=0; i<f.length;i++){ 
			   int j=f.length;
			   String[] str=new String[j];
			   if(f[i].getName().equals("Draft")==true){
				   str[i]=new String("草稿箱");
			   }
			   else if(f[i].getName().equals("inbox")==true){ 
				   str[i]=new String("收件箱");
			   }
			   else if(f[i].getName().equals("Trash")==true){
				   str[i]=new String("废件箱");
			   }
			   else if(f[i].getName().equals("SendBox")==true){
				   str[i]=new String("发件箱");
			   }
			   else{
				   str[i]=new String(f[i].getName());
			   }
			
			   System.out.println(str[i]+"-----"+f[i].getMessageCount()+"-----"+f[i].getUnreadMessageCount());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
