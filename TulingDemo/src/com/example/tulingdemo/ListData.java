package com.example.tulingdemo;

import android.R.integer;

/*Êý¾Ý·â×°*/
public class ListData {
	public static final int SEND = 1;
	public static final int RECEIVER = 2;
	private String content;
	private int flag;
	private String time;

	public ListData(String content,int flag, String time) {
		// TODO Auto-generated constructor stub
		setContent(content);
		setFlag(flag);
		setTime(time);
	}

	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	private void setContent(String content) {
		// TODO Auto-generated method stub
		this.content=content;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

}
