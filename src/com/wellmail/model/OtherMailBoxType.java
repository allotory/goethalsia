package com.wellmail.model;

public class OtherMailBoxType {

	private int tid;
	
	private String tname;
	
	private String tsmtp;
	
	private String tpop3;

	public int getTid() {
		return tid;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getTsmtp() {
		return tsmtp;
	}

	public void setTsmtp(String tsmtp) {
		this.tsmtp = tsmtp;
	}

	public String getTpop3() {
		return tpop3;
	}

	public void setTpop3(String tpop3) {
		this.tpop3 = tpop3;
	}
}
