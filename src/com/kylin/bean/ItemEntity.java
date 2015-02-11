package com.kylin.bean;

import java.io.Serializable;

public class ItemEntity extends BaseEntity implements Serializable{

	/** 
	* @Fields serialVersionUID :  
	*/ 
	private static final long serialVersionUID = 1L;
	public int id = -1;
	public String name;
	public int nameSize;
	public int image_content;
	public int image_icon;
	public int NextFocusUpId	= -1;
	public int NextFocusRightId	= -1;
	public int NextFocusDownId	= -1;
	public int NextFocusLeftId 	= -1;

}
