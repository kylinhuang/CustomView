package com.kylin.bean;

import java.util.ArrayList;
import java.util.HashMap;

public class MainEntity {
	
	private String background ;
	
	LogoEntity logoEntity;
	BaseEntity networkEntity;
	PagerEntity pagerEntity;
	TimeEntity timeEntity;
	ArrayList<TitleEntity> arrayTitle;
	
	ArrayList<ArrayList<ItemEntity>> arrTab;
	 

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public LogoEntity getLogoEntity() {
		return logoEntity;
	}

	public void setLogoEntity(LogoEntity logoEntity) {
		this.logoEntity = logoEntity;
	}

	public BaseEntity getNetworkEntity() {
		return networkEntity;
	}

	public void setNetworkEntity(BaseEntity networkEntity) {
		this.networkEntity = networkEntity;
	}

	public TimeEntity getTimeEntity() {
		return timeEntity;
	}

	public void setTimeEntity(TimeEntity timeEntity) {
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

	public PagerEntity getPagerEntity() {
		return pagerEntity;
	}

	public void setPagerEntity(PagerEntity pagerEntity) {
		this.pagerEntity = pagerEntity;
	}

	
	
}
