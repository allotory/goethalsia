package com.wellmail.model;

public class UserScore {

	//用户积分id
	private int scoreid;
	
	//用户等级
	private int level;
	
	//用户积分
	private int score;
	
	//用户外键
	private Users user;

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public int getScoreid() {
		return scoreid;
	}

	public void setScoreid(int scoreid) {
		this.scoreid = scoreid;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
