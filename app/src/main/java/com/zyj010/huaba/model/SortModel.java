package com.zyj010.huaba.model;

import android.graphics.Bitmap;

public class SortModel {

	private String name;   //��ʾ������
	private String sortLetters;  //��ʾ����ƴ��������ĸ
	private Bitmap avater;
	public  void setModel(String name,Bitmap avater){
		this.name=name;
		this.avater=avater;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSortLetters() {
		return sortLetters;
	}
	public void setSortLetters(String sortLetters) {
		this.sortLetters = sortLetters;
	}
	public Bitmap getAvater(){return avater;}
	public void setAvater(Bitmap avater){this.avater=avater;}
}
