package com.kylin.utils;

import java.util.ArrayList;

import com.kylin.R;
import com.kylin.bean.BaseEntity;
import com.kylin.bean.ItemEntity;
import com.kylin.bean.MainEntity;
import com.kylin.bean.TitleEntity;

public class EntityUtils {

	public static MainEntity getMainEntity() {
		MainEntity mainEntity = new MainEntity();
		mainEntity.setBackground(R.drawable.main_background_activity);
		
		mainEntity.setLogoEntity(EntityUtils.getLogoEntity());
		mainEntity.setNetworkEntity(EntityUtils.getNetworkEntity());
		mainEntity.setTimeEntity(EntityUtils.getTimeEntity());
		
		mainEntity.setArrayTitle(EntityUtils.getArrayTitle());
		mainEntity.setArrTab(EntityUtils.getArrayTab());
		
		return mainEntity;
	}

	public static BaseEntity getLogoEntity() {
		BaseEntity logoEntity = new BaseEntity();
		logoEntity.type = 1;
		logoEntity.x = 50;
		logoEntity.y = 50;
		logoEntity.background = R.drawable.icon;
		return logoEntity;
	}

	public static BaseEntity getNetworkEntity() {
		BaseEntity networkEntity = new BaseEntity();
		networkEntity.type = 1;
		networkEntity.x = 1100;
		networkEntity.y = 40;
		networkEntity.background = R.drawable.main_page_image_wired_disconnect;
		return networkEntity;
	}

	public static BaseEntity getTimeEntity() {
		BaseEntity timeEntity = new BaseEntity();
		timeEntity.type = 2;
		timeEntity.x = 1150;
		timeEntity.y = 40;
		return timeEntity;
	}

	public static ArrayList<TitleEntity> getArrayTitle() {
		TitleEntity title1 = new TitleEntity();
		title1.type = 3;
		title1.position = 1;
		title1.text = "电视";
		title1.textSize = 25;
		title1.x = 350;
		title1.y = 650;
		title1.width = 100;
		title1.height = 50;
		
		
		TitleEntity title2 = new TitleEntity();
		title2.type = 3;
		title2.position = 2;
		title2.text = "设置";
		title2.textSize = 25;
		title2.x = 500;
		title2.y = 650;
		title2.width = 100;
		title2.height = 50;
		
		TitleEntity title3 = new TitleEntity();
		title3.type = 3;
		title3.position = 3;
		title3.text = "新闻";
		title3.textSize = 25;
		title3.x = 700;
		title3.y = 650;
		title3.width = 100;
		title3.height = 50;
		
		
		ArrayList<TitleEntity> arrayTitle = new ArrayList<TitleEntity>();
		arrayTitle.add(title1);
		arrayTitle.add(title2);
		arrayTitle.add(title3);
		return arrayTitle;
		
	}

	public static ArrayList<ArrayList<ItemEntity>> getArrayTab() {
		ArrayList<ArrayList<ItemEntity>> arr = new ArrayList<ArrayList<ItemEntity>>();
//		HashMap tabMap = 	new HashMap<Integer, ArrayList<ItemEntity>>();
//		tabMap.put(1, initTabOne()) ;
//		tabMap.put(2, initTabTwo()) ;
		arr.add(initTabOne());
		arr.add(initTabTwo());
		arr.add(initTabNew());
		return arr;
	}
	
	
	private static ArrayList<ItemEntity> initTabNew() {
		ArrayList<ItemEntity> arrItem = new ArrayList<ItemEntity>();
		ItemEntity setItem = new ItemEntity();
		setItem.id = 50;
		setItem.NextFocusRightId = 51;
		setItem.hasFocus = true;
		setItem.x = 50;
		setItem.y = 20;
		setItem.width  = 250;
		setItem.height = 550;
		setItem.name = "匆匆那些年";
		setItem.nameSize = 25;
		setItem.image_content = R.drawable.cfdq;
//		setItem.image_icon = R.drawable.system_settings_image;
		arrItem.add(setItem);
		
		ItemEntity tvItem = new ItemEntity();
		tvItem.id = 51;
		tvItem.NextFocusLeftId = 50;
		tvItem.NextFocusRightId = 52;
		tvItem.hasFocus = true;
		tvItem.x = 300;
		tvItem.y = 20;
		tvItem.width  = 300;
		tvItem.height = 550;
		tvItem.name = "天降雄狮";
		tvItem.nameSize = 25;
		tvItem.image_content = R.drawable.cfdq;
//		tvItem.image_icon = R.drawable.system_settings_image;
		arrItem.add(tvItem);
		
		ItemEntity lookbackItem = new ItemEntity();
		lookbackItem.id = 52;
		lookbackItem.NextFocusLeftId = 51;
		lookbackItem.hasFocus = true;
		lookbackItem.x = 600;
		lookbackItem.y = 20;
		lookbackItem.width  = 250;
		lookbackItem.height = 550;
		lookbackItem.name = "回看";
		lookbackItem.nameSize = 25;
		lookbackItem.image_content = R.drawable.cfdq;
//		lookbackItem.image_icon = R.drawable.system_settings_image;
		arrItem.add(lookbackItem);
		
//		ItemEntity adItem = new ItemEntity();
//		adItem.id = 53;
//		adItem.hasFocus = true;
//		adItem.x = 300;
//		adItem.y = 250;
//		adItem.width  = 380;
//		adItem.height = 151;
////		adItem.name = "回看";
////		adItem.nameSize = 25;
//		adItem.image_content = R.drawable.recommend_jiangxi;
////		adItem.image_icon = R.drawable.system_settings_image;
//		arrItem.add(adItem);
		return arrItem;
	}

	private static ArrayList<ItemEntity> initTabTwo() {
		ArrayList<ItemEntity> arrItem = new ArrayList<ItemEntity>();
		
		ItemEntity setItem = new ItemEntity();
		setItem.id = 50;
		setItem.NextFocusDownId = 52;
		setItem.NextFocusRightId = 51;
		setItem.hasFocus = true;
		setItem.x = 50;
		setItem.y = 20;
		setItem.width  = 250;
		setItem.height = 250;
		setItem.name = "设置";
		setItem.nameSize = 25;
		setItem.image_content = R.drawable.item_bg;
		setItem.image_icon = R.drawable.system_settings_image;
		arrItem.add(setItem);
		
		ItemEntity tvItem = new ItemEntity();
		tvItem.id = 51;
		tvItem.NextFocusLeftId = 50;
		tvItem.hasFocus = true;
		tvItem.x = 350;
		tvItem.y = 20;
		tvItem.width  = 850;
		tvItem.height = 250;
		tvItem.name = "TV";
		tvItem.nameSize = 25;
		tvItem.image_content = R.drawable.item_bg;
		tvItem.image_icon = R.drawable.system_settings_image;
		arrItem.add(tvItem);
		
		ItemEntity lookbackItem = new ItemEntity();
		lookbackItem.id = 52;
		lookbackItem.NextFocusUpId= 50;
		lookbackItem.NextFocusRightId= 51;
		lookbackItem.hasFocus = true;
		lookbackItem.x = 50;
		lookbackItem.y = 250;
		lookbackItem.width  = 250;
		lookbackItem.height = 250;
		lookbackItem.name = "回看";
		lookbackItem.nameSize = 25;
		lookbackItem.image_content = R.drawable.item_bg;
		lookbackItem.image_icon = R.drawable.system_settings_image;
		arrItem.add(lookbackItem);
		
		ItemEntity adItem = new ItemEntity();
		adItem.id = 53;
		adItem.hasFocus = true;
		adItem.x = 300;
		adItem.y = 250;
		adItem.width  = 380;
		adItem.height = 151;
//		adItem.name = "回看";
//		adItem.nameSize = 25;
		adItem.image_content = R.drawable.recommend_jiangxi;
//		adItem.image_icon = R.drawable.system_settings_image;
		arrItem.add(adItem);
		return arrItem;
	}

	private static ArrayList<ItemEntity> initTabOne() {
		ArrayList<ItemEntity> arrItem = new ArrayList<ItemEntity>();
		
		ItemEntity setItem = new ItemEntity();
		setItem.id = 50;
		setItem.NextFocusDownId = 52;
		setItem.NextFocusRightId = 51;
		setItem.hasFocus = true;
		setItem.x = 50;
		setItem.y = 20;
		setItem.width  = 250;
		setItem.height = 250;
		setItem.name = "电视";
		setItem.nameSize = 25;
		setItem.image_content = R.drawable.item_bg;
		setItem.image_icon = R.drawable.system_settings_image;
		arrItem.add(setItem);
		
		ItemEntity tvItem = new ItemEntity();
		tvItem.id = 51;
		tvItem.NextFocusLeftId = 50;
		tvItem.hasFocus = true;
		tvItem.x = 350;
		tvItem.y = 20;
		tvItem.width  = 350;
		tvItem.height = 250;
		tvItem.name = "TV";
		tvItem.nameSize = 25;
		tvItem.image_content = R.drawable.item_bg;
		tvItem.image_icon = R.drawable.system_settings_image;
		arrItem.add(tvItem);
		
		ItemEntity lookbackItem = new ItemEntity();
		lookbackItem.id = 52;
		lookbackItem.NextFocusUpId= 50;
		lookbackItem.hasFocus = true;
		lookbackItem.x = 50;
		lookbackItem.y = 250;
		lookbackItem.width  = 250;
		lookbackItem.height = 250;
		lookbackItem.name = "回看";
		lookbackItem.nameSize = 25;
		lookbackItem.image_content = R.drawable.item_bg;
		lookbackItem.image_icon = R.drawable.system_settings_image;
		arrItem.add(lookbackItem);
		
//		ItemEntity adItem = new ItemEntity();
//		adItem.id = 53;
//		adItem.hasfocus = false;
//		adItem.x = 300;
//		adItem.y = 250;
//		adItem.width  = 380;
//		adItem.height = 151;
////		adItem.name = "回看";
////		adItem.nameSize = 25;
//		adItem.image_content = R.drawable.recommend_jiangxi;
////		adItem.image_icon = R.drawable.system_settings_image;
//		arrItem.add(adItem);
//		
//		
//		
//		ItemEntity l  = new ItemEntity();
//		l.id = 54;
////		l.NextFocusUpId= 50;
//		l.hasfocus = false;
//		l.x = 850;
//		l.y = 250;
//		l.width  = 250;
//		l.height = 250;
//		l.name = "ssssss";
//		l.nameSize = 25;
//		l.image_content = R.drawable.item_bg;
//		l.image_icon = R.drawable.system_settings_image;
//		arrItem.add(l);
		return arrItem;
	}

	 
	

}
