package com.test.mail;


public class Test2 {
	public static void main(String[] args) {
		String qftemp = "a@mymail.com;b@mymail.com;c@mymail.com";
		String qf[] = null;
		String qf2[] = null;
		if(qftemp != null && qftemp != "") {
			qf = new String[qftemp.toCharArray().length];
			qf = qftemp.split(";");
			for(int i = 0; i<qf.length; i++) {
				System.out.println("qf"+i+":"+qf[i]);
			}
			qf2 = new String[qf.length+1];
			for(int i = 0; i<qf.length; i++) {
				qf2[i] = qf[i];
			}
			qf2[qf.length] = "z@mymail.com";
		}
		
		for(int i = 0 ; i< qf2.length; i++) {
			System.out.println("qf2"+i+":"+qf2[i]);
		}
	}
}
