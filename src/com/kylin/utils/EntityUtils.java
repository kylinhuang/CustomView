package com.kylin.utils;

import java.util.ArrayList;

import com.kylin.R;
import com.kylin.bean.BaseEntity;
import com.kylin.bean.ItemEntity;
import com.kylin.bean.LogoEntity;
import com.kylin.bean.MainEntity;
import com.kylin.bean.TimeEntity;
import com.kylin.bean.TitleEntity;

public class EntityUtils {

	public static MainEntity getMainEntity() {
		MainEntity mainEntity = new MainEntity();
		mainEntity.setBackground("http://img.pconline.com.cn/images/upload/upc/tx/wallpaper/1501/04/c0/1477028_1420383934693_800x600.jpg");
		
		mainEntity.setLogoEntity(EntityUtils.getLogoEntity());
		mainEntity.setNetworkEntity(EntityUtils.getNetworkEntity());
		mainEntity.setTimeEntity(EntityUtils.getTimeEntity());
		
		mainEntity.setArrayTitle(EntityUtils.getArrayTitle());
		mainEntity.setArrTab(EntityUtils.getArrayTab());
		
		return mainEntity;
	}

	public static LogoEntity getLogoEntity() {
		LogoEntity logoEntity = new LogoEntity();
		logoEntity.x = 50;
		logoEntity.y = 30;
		
		logoEntity.width = 50;
		logoEntity.height = 50;
		logoEntity.background_url = "http://image.baidu.com/i?tn=download&ipn=dwnl&word=logo%20png&ie=utf-8&fr=result&url=http%3A%2F%2Fimg.article.pchome.net%2F00%2F37%2F47%2F30%2Fpic_lib%2Fs960x639%2F0e80039f04ea6106487069a0ffc82a8es960x639.JPG";
		return logoEntity;
	}

	public static BaseEntity getNetworkEntity() {
		BaseEntity networkEntity = new BaseEntity();
		networkEntity.x = 1100;
		networkEntity.y = 40;
		return networkEntity;
	}

	public static TimeEntity getTimeEntity() {
		TimeEntity timeEntity = new TimeEntity();
		timeEntity.timeType = 12;
		timeEntity.textSize = 16;
		timeEntity.x = 1150;
		timeEntity.y = 40;
		return timeEntity;
	}

	public static ArrayList<TitleEntity> getArrayTitle() {
		TitleEntity tvTitle = new TitleEntity();
		tvTitle.hasFocus = false;
		tvTitle.position = 1;
		tvTitle.text = "电视";
		tvTitle.textSize = 25;
		tvTitle.x = 350;
		tvTitle.y = 650;
		tvTitle.width = 100;
		tvTitle.height = 50;
		
		
		TitleEntity setTitle = new TitleEntity();
		tvTitle.hasFocus = false;
		setTitle.position = 2;
		setTitle.text = "设置";
		setTitle.textSize = 25;
		setTitle.x = 500;
		setTitle.y = 650;
		setTitle.width = 100;
		setTitle.height = 50;
		
		TitleEntity newTitle = new TitleEntity();
		newTitle.position = 3;
		newTitle.text = "新闻";
		newTitle.textSize = 25;
		newTitle.x = 650;
		newTitle.y = 650;
		newTitle.width = 100;
		newTitle.height = 50;
		
		TitleEntity userTitle = new TitleEntity();
		userTitle.position = 4;
		userTitle.text = "用户";
		userTitle.textSize = 25;
		userTitle.x = 800;
		userTitle.y = 650;
		userTitle.width = 100;
		userTitle.height = 50;
		
		
		ArrayList<TitleEntity> arrayTitle = new ArrayList<TitleEntity>();
		arrayTitle.add(tvTitle);
		arrayTitle.add(setTitle);
		arrayTitle.add(newTitle);
		arrayTitle.add(userTitle);
		return arrayTitle;
		
	}

	public static ArrayList<ArrayList<ItemEntity>> getArrayTab() {
		ArrayList<ArrayList<ItemEntity>> arr = new ArrayList<ArrayList<ItemEntity>>();
		arr.add(initTabTV());
		arr.add(initTabSet());
		arr.add(initTabNew());
		arr.add(initTabUser());
		return arr;
	}
	
	
	private static ArrayList<ItemEntity> initTabNew() {
		ArrayList<ItemEntity> arrItem = new ArrayList<ItemEntity>();
		ItemEntity setItem = new ItemEntity();
		setItem.nameBg = "#CC000000";
		setItem.id = 50;
		setItem.NextFocusRightId = 51;
		setItem.hasFocus = true;
		setItem.x = 20;
		setItem.y = 20;
		setItem.width  = 750;
		setItem.height = 550;
		setItem.name = "匆匆那些年";
		setItem.nameSize = 25;
		setItem.image_content = "http://f.hiphotos.baidu.com/baike/c0%3Dbaike116%2C5%2C5%2C116%2C38/sign=82e87630262dd42a4b0409f9625230d0/96dda144ad345982e775c6cb0ff431adcaef84ef.jpg";
//		setItem.image_icon = R.drawable.system_settings_image;
		arrItem.add(setItem);
		
		ItemEntity tvItem = new ItemEntity();
		tvItem.nameBg = "#CC000000";
		tvItem.id = 51;
		tvItem.NextFocusLeftId = 50;
		tvItem.NextFocusRightId = 52;
		tvItem.hasFocus = true;
		tvItem.x = 750;
		tvItem.y = 20;
		tvItem.width  = 300;
		tvItem.height = 550;
		tvItem.name = "重返地球";
		tvItem.nameSize = 25;
		tvItem.image_content  = "http://image.baidu.com/i?tn=download&ipn=dwnl&word=%E7%94%B5%E5%BD%B1%E6%B5%B7%E6%8A%A5%E5%9B%BE&ie=utf-8&fr=result&url=http%3A%2F%2Fdown1.sucaitianxia.com%2Fpsd02%2Fpsd179%2Fpsds36925.jpg";
//		tvItem.image_icon = R.drawable.system_settings_image;
		arrItem.add(tvItem);
		
		ItemEntity lookbackItem = new ItemEntity();
		lookbackItem.nameBg = "#CC000000";
		lookbackItem.id = 52;
		lookbackItem.NextFocusLeftId = 51;
		lookbackItem.hasFocus = true;
		lookbackItem.x = 1020;
		lookbackItem.y = 20;
		lookbackItem.width  = 250;
		lookbackItem.height = 550;
		lookbackItem.name = "银河护卫队";
		lookbackItem.nameSize = 25;
		lookbackItem.image_content = "http://image.baidu.com/i?tn=download&ipn=dwnl&word=%E7%94%B5%E5%BD%B1&ie=utf-8&fr=result&url=http%3A%2F%2Fwww.ln.chinanews.com%2FUploadFiles%2F2014%2F8%2F201408051648340612.jpg";
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
	
	
	private static ArrayList<ItemEntity> initTabUser() {
		ArrayList<ItemEntity> arrItem = new ArrayList<ItemEntity>();
		ItemEntity setItem = new ItemEntity();
		setItem.nameBg = "#CC000000";
		setItem.nameColor = "#000080";
		setItem.id = 50;
		setItem.NextFocusRightId = 51;
		setItem.hasFocus = true;
		setItem.x = 30;
		setItem.y = 20;
		setItem.width  = 250;
		setItem.height = 550;
		setItem.name = "魁拔";
		setItem.nameSize = 25;
		setItem.image_content = "http://e.hiphotos.baidu.com/baike/c0%3Dbaike272%2C5%2C5%2C272%2C90/sign=e0e4cc8f39292df583cea447dd583705/838ba61ea8d3fd1f353eab9a334e251f95ca5f5e.jpg";
//		setItem.image_icon = R.drawable.system_settings_image;
		arrItem.add(setItem);
		
		ItemEntity tvItem = new ItemEntity();
		tvItem.nameBg = "#33FF0000";
		tvItem.nameColor = "#000080";
		tvItem.id = 51;
		tvItem.NextFocusLeftId = 50;
		tvItem.NextFocusRightId = 52;
		tvItem.hasFocus = true;
		tvItem.x = 250;
		tvItem.y = 20;
		tvItem.width  = 300;
		tvItem.height = 550;
		tvItem.name = "天降雄狮";
		tvItem.nameSize = 25;
		tvItem.image_content = "http://image.baidu.com/i?tn=download&ipn=dwnl&word=%E5%A4%A9%E9%99%8D%E9%9B%84%E7%8B%AE&ie=utf-8&fr=result&url=http%3A%2F%2Ff.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2Fdcc451da81cb39dbfb89d81ed3160924ab183028.jpg";
//		tvItem.image_icon = R.drawable.system_settings_image;
		arrItem.add(tvItem);
		
		ItemEntity lookbackItem = new ItemEntity();
		lookbackItem.nameBg = "#33000000";
		lookbackItem.id = 52;
		lookbackItem.NextFocusLeftId = 51;
		lookbackItem.hasFocus = true;
		lookbackItem.x = 520;
		lookbackItem.y = 20;
		lookbackItem.width  = 800;
		lookbackItem.height = 550;
		lookbackItem.name = "中国合伙人";
		lookbackItem.nameSize = 25;
		
		lookbackItem.image_content = "http://image.baidu.com/i?tn=download&ipn=dwnl&word=%E4%B8%AD%E5%9B%BD%E5%90%88%E4%BC%99%E4%BA%BA&ie=utf-8&fr=result&url=http%3A%2F%2Fwww.lizhi123.net%2Fuploads%2Fallimg%2F130525%2F4_130525175139_1.png";

//		lookbackItem.image_content = R.drawable.cfdq;
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

	private static ArrayList<ItemEntity> initTabSet() {
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
		setItem.image_content = "";
		setItem.image_icon = "http://image.baidu.com/i?tn=download&ipn=dwnl&word=icon%20png&ie=utf-8&fr=result&url=http%3A%2F%2Fwww.icosky.com%2Ficon%2Fpng%2FSystem%2FCandied%2520Apples%2FCandy%2520Apple%2520Red%25202.png";
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
		tvItem.image_content = "";
		tvItem.image_icon = "http://image.baidu.com/i?tn=download&ipn=dwnl&word=icon%20png&ie=utf-8&fr=result&url=http%3A%2F%2Fwww.icosky.com%2Ficon%2Fpng%2FSystem%2FCandied%2520Apples%2FCandy%2520Apple%2520Red%25202.png";
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
		lookbackItem.image_content = "";
		lookbackItem.image_icon = "http://image.baidu.com/i?tn=download&ipn=dwnl&word=icon%20png&ie=utf-8&fr=result&url=http%3A%2F%2Fwww.icosky.com%2Ficon%2Fpng%2FSystem%2FCandied%2520Apples%2FCandy%2520Apple%2520Red%25202.png";
		arrItem.add(lookbackItem);
		
		ItemEntity adItem = new ItemEntity();
		adItem.id = 53;
		adItem.hasFocus = true;
		adItem.x = 300;
		adItem.y = 250;
		adItem.width  = 600;
		adItem.height = 250;
//		adItem.name = "回看";
//		adItem.nameSize = 25;
		adItem.image_content = "http://image.baidu.com/i?tn=download&ipn=dwnl&word=%E5%B9%BF%E5%91%8A&ie=utf-8&fr=result&url=http%3A%2F%2Fpic17.nipic.com%2F20111030%2F178350_181133315001_2.jpg";
//		adItem.image_icon = R.drawable.system_settings_image;
		arrItem.add(adItem);
		return arrItem;
	}

	private static ArrayList<ItemEntity> initTabTV() {
		ArrayList<ItemEntity> arrItem = new ArrayList<ItemEntity>();
		
		ItemEntity setItem = new ItemEntity();
		setItem.action = "com.kylin.action";
		setItem.id = 11;
		setItem.NextFocusDownId = 15;
		setItem.NextFocusRightId = 12;
		setItem.hasFocus = true;
		setItem.x = 50;
		setItem.y = 20;
		setItem.width  = 250;
		setItem.height = 250;
		setItem.name = "电视";
		setItem.nameSize = 25;
		setItem.image_icon = "http://image.baidu.com/i?tn=download&ipn=dwnl&word=icon%20png&ie=utf-8&fr=result&url=http%3A%2F%2Fwww.icosky.com%2Ficon%2Fpng%2FSystem%2FCandied%2520Apples%2FCandy%2520Apple%2520Red%25202.png";
		setItem.image_content = "";
		arrItem.add(setItem);
		
		ItemEntity tvItem = new ItemEntity();
		tvItem.action = "com.kylin.action";
		tvItem.id = 12;
		tvItem.NextFocusLeftId = 11;
		tvItem.NextFocusRightId= 13;
		tvItem.hasFocus = true;
		tvItem.x = 300;
		tvItem.y = 20;
		tvItem.width  = 250;
		tvItem.height = 250;
		tvItem.name = "TV";
		tvItem.nameSize = 25;
		tvItem.image_content = "";
		tvItem.image_icon = "http://image.baidu.com/i?tn=download&ipn=dwnl&word=icon%20png&ie=utf-8&fr=result&url=http%3A%2F%2Fwww.sucaitianxia.com%2Fpng%2FUploadFiles_6130%2F200807%2F20080702002356141.png";
		arrItem.add(tvItem);
		
		ItemEntity ghItem = new ItemEntity();
		ghItem.action = "com.kylin.action";
		ghItem.id = 13;
		ghItem.NextFocusLeftId = 12;
		ghItem.NextFocusRightId = 14;
		ghItem.hasFocus = true;
		ghItem.x = 550;
		ghItem.y = 20;
		ghItem.width  = 250;
		ghItem.height = 250;
		ghItem.name = "歌华有线";
		ghItem.nameSize = 25;
		ghItem.image_content = "";
		ghItem.image_icon = "http://image.baidu.com/i?tn=download&ipn=dwnl&word=png%20%E5%B0%8F%E5%9B%BE&ie=utf-8&fr=result&url=http%3A%2F%2Fimg.sj33.cn%2Fuploads%2Fallimg%2F201009%2F20100918144426835.png";
		arrItem.add(ghItem);
		
 
		
		ItemEntity bstItem = new ItemEntity();
		bstItem.action = "com.kylin.action";
		bstItem.id = 14;
		bstItem.NextFocusLeftId = 13;
		bstItem.hasFocus = true;
		bstItem.x = 800;
		bstItem.y = 20;
		bstItem.width  = 250;
		bstItem.height = 250;
		bstItem.name = "百事通";
		bstItem.nameSize = 25;
		bstItem.image_content = "";
		bstItem.image_icon = "http://image.baidu.com/i?tn=download&ipn=dwnl&word=png%20%E5%B0%8F%E5%9B%BE&ie=utf-8&fr=result&url=http%3A%2F%2Fp1.qqyou.com%2Fpic%2Fuploadpic%2F2011-4%2F201143921218128.png";
		arrItem.add(bstItem);
		
		
		ItemEntity lookbackItem = new ItemEntity();
		lookbackItem.action = "com.kylin.action";
		lookbackItem.id = 15;
		lookbackItem.NextFocusUpId= 11;
		lookbackItem.hasFocus = true;
		lookbackItem.x = 50;
		lookbackItem.y = 250;
		lookbackItem.width  = 250;
		lookbackItem.height = 250;
		lookbackItem.name = "回看";
		lookbackItem.nameSize = 25;
		lookbackItem.image_content = "";
		lookbackItem.image_icon = "http://image.baidu.com/i?tn=download&ipn=dwnl&word=icon%20png&ie=utf-8&fr=result&url=http%3A%2F%2Fwww.icosky.com%2Ficon%2Fpng%2FSystem%2FCandied%2520Apples%2FCandy%2520Apple%2520Red%25202.png";
		arrItem.add(lookbackItem);
		
		return arrItem;
	}

	 
	

}
