package com.kylin.bean;

import java.io.Serializable;

public class ItemEntity extends BaseEntity implements Serializable{

	/** 
	* @Fields serialVersionUID :  
	*/ 
	private static final long serialVersionUID = 1L;
	public int id = -1;
	public String name;
	public String action;
	public int nameSize;
	public int image_content;
	public int image_icon;
	public int NextFocusUpId	= -1;
	public int NextFocusRightId	= -1;
	public int NextFocusDownId	= -1;
	public int NextFocusLeftId 	= -1;
	/** image 资源类型   0 ---资源获取  1 ---uri获取 */ 
	public int image_content_type = 0;
	public String image_content_uri;
	public boolean hasNameBg = false;

}
