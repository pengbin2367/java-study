package com.gqy.usersystem.povo;

import java.io.Serializable;

public class User implements Serializable {
	private String userId;
	private String userPwd;
	private String name;
	private String sex;
	private String birth;
	private String nation;
	private String hobby;
	private String memo;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	public String toString() {
		return "User{" +
				"userId='" + userId + '\'' +
				", userPwd='" + userPwd + '\'' +
				", name='" + name + '\'' +
				", sex='" + sex + '\'' +
				", birth='" + birth + '\'' +
				", nation='" + nation + '\'' +
				", hobby='" + hobby + '\'' +
				", memo='" + memo + '\'' +
				'}';
	}
}