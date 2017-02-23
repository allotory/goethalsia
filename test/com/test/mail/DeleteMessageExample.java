package com.test.mail;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;

public class DeleteMessageExample   {   
    public static void main(String   args[])throws   Exception   {   
        String host = "localhost";   
        String username = "jerry";   
        String password =  "861213";   
  
        //Getsession   
        Session session = Session.getInstance(System.getProperties(), null);   
  
        //Getthestore   
        Store store = session.getStore("pop3");   
        store.connect(host, username, password);   
  
        //Get folder   
        Folder folder = store.getFolder("INBOX");   
        folder.open(Folder.READ_WRITE);   
  
        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));   
  
        //Getdirectory   
        Message message[]= folder.getMessages();   
        for(int i=0,n=message.length;i<n;i++)   {   
            message[i].setFlag(Flags.Flag.DELETED,   true);   
        }   
  
        //   Close   connection     
        folder.close(true);   
        store.close();   
        System.out.println("Çå¿ÕÍê±Ï");
    }   
}   

