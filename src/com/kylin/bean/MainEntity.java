package com.kylin.bean;

import java.util.ArrayList;
import java.util.HashMap;

public class MainEntity {
	
	private int background ;
	
	BaseEntity logoEntity;
	BaseEntity networkEntity;
	BaseEntity timeEntity;
	ArrayList<TitleEntity> arrayTitle;
	
	ArrayList<ArrayList<ItemEntity>> arrTab;
	 

	public int getBackground() {
		return background;
	}

	public void setBackground(int background) {
		this.background = background;
	}

	public BaseEntity getLogoEntity() {
		return logoEntity;
	}

	public void setLogoEntity(BaseEntity logoEntity) {
		this.logoEntity = logoEntity;
	}

	public BaseEntity getNetworkEntity() {
		return networkEntity;
	}

	public void setNetworkEntity(BaseEntity networkEntity) {
		this.networkEntity = networkEntity;
	}

	public BaseEntity getTimeEntity() {
		return timeEntity;
	}

	public void setTimeEntity(BaseEntity timeEntity) {
		this.timeEntity = timeEntity;
	}

	public ArrayList<TitleEntity> getArrayTitle() {
		return arrayTitle;
	}

	public void setArrayTitle(ArrayList<TitleEntity> arrayTitle) {
		this.arrayTitle = arrayTitle;
	}

	public ArrayList<ArrayList<ItemEntity>> getArrTab() {
		return arrTab;
	}

	public void setArrTab(ArrayList<ArrayList<ItemEntity>> arrTab) {
		this.arrTab = arrTab;
	}
}
