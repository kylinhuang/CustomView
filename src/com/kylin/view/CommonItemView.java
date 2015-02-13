package com.kylin.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kylin.R;
import com.kylin.utils.BitmapHelp;
import com.kylin.utils.BitmapViewUtils;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;

public class CommonItemView extends RelativeLayout {

	private Context mContext;
	private View view;
	private TextView nameText;
	private ImageView image_content;
	private ImageView image_icon;
	
	public CommonItemView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public CommonItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public CommonItemView(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		mContext = context;
		LayoutInflater mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = (View) mLayoutInflater.inflate(R.layout.view_common_item, null);
		addView(view);
		image_content = (ImageView) view.findViewById(R.id.image_content);
		image_icon 	  = (ImageView) view.findViewById(R.id.image_icon);
		
		nameText = (TextView) view.findViewById(R.id.name);
	}

	public void setName(String string) {
		nameText.setText(string);
	}

	public void setIcon(String imageurl, final int resId) {
		if(imageurl == null || "".equals(imageurl) ){
//			image_icon.setImageResource(resId);
		}else {
			BitmapViewUtils.setImage(image_icon,imageurl,resId,mContext);
		}
	} 

	
	public void setItemBackground(String imageurl, int resId) {
		if(imageurl == null || "".equals(imageurl) ){
			image_content.setImageResource(resId);
		}else {
			BitmapViewUtils.setImage(image_content,imageurl,resId,mContext);
		}
	}

	public void setNameSize(int size) {
		nameText.setTextSize(size);
	}
	
	public void setNameBg(String resString) {
		nameText.setBackgroundColor(Color.parseColor(resString));
//		nameText.setBackgroundResource(resid);
	}

	public void setItemBackgroundUrl(String url) {
		BitmapUtils bitmapUtils = BitmapHelp.getBitmapUtils(mContext);
//        bitmapUtils.configDefaultLoadingImage(R.drawable.ic_launcher);
////        bitmapUtils.configDefaultLoadFailedImage(R.drawable.bitmap);
//        bitmapUtils.configDefaultBitmapConfig(Bitmap.Config.RGB_565);
		bitmapUtils.display(image_content, url);		
	}

	public void setNameColor(String nameColor) {
		nameText.setTextColor(Color.parseColor(nameColor));
	}
	
}
