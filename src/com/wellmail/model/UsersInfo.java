package com.wellmail.model;

public class UsersInfo {

	//用户信息编号
	private int usersinfoid;
	
	//用户性别
	private String sex;
	
	//用户生日
	private String birthday;
	
	//用户电话
	private String tel;

	//users外键
	private Users user;
	
	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public int getUsersinfoid() {
		return usersinfoid;
	}

	public void setUsersinfoid(int usersinfoid) {
		this.usersinfoid = usersinfoid;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
}
