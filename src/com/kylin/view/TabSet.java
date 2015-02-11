package com.kylin.view;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.kylin.R;
import com.kylin.bean.ItemEntity;

@SuppressLint("ResourceAsColor")
public class TabSet extends FrameLayout implements ITabPage{

	
	
	private static final String TAG = "TabTV";

	private static final int TIME_OUT = 440;
	private Context mContext;
	private RelativeLayout mContainer;
	private LinearLayout llPlay;
	private LinearLayout llRightMiddle;
	private LinearLayout llRightTop;
	public String url;

	private ArrayList<Thread> requsetThread = new ArrayList<Thread>();

	private RelativeLayout rl;

	private ArrayList<ItemEntity> arrItem;
	
	
	public TabSet(Context context) {
		super(context);
		mContext = context;
		init();
	}

	public TabSet(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
		init();
	}

	public TabSet(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		init();
	}

	private void init() {
//		LayoutInflater mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//		View view = (View) mLayoutInflater.inflate(R.layout.view_tab_tv,null);
		rl = new RelativeLayout(mContext);
		
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT); 
		addView(rl, params);
		initEntity();
		
		for (ItemEntity item : arrItem) {
			initViewItem(item);
		}
		
		rl.setBackgroundColor(R.color.green);
	}

	private void initEntity() {
		arrItem = new ArrayList<ItemEntity>();
		
		ItemEntity setItem = new ItemEntity();
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
		tvItem.x = 350;
		tvItem.y = 20;
		tvItem.width  = 850;
		tvItem.height = 250;
		tvItem.name = "设置节目";
		tvItem.nameSize = 25;
		tvItem.image_content = R.drawable.item_bg;
		tvItem.image_icon = R.drawable.system_settings_image;
		arrItem.add(tvItem);
		
		ItemEntity lookbackItem = new ItemEntity();
		lookbackItem.x = 50;
		lookbackItem.y = 250;
		lookbackItem.width  = 250;
		lookbackItem.height = 250;
		lookbackItem.name = "设置回看";
		lookbackItem.nameSize = 25;
		lookbackItem.image_content = R.drawable.item_bg;
		lookbackItem.image_icon = R.drawable.system_settings_image;
		arrItem.add(lookbackItem);
		
		ItemEntity adItem = new ItemEntity();
		adItem.x = 300;
		adItem.y = 250;
		adItem.width  = 380;
		adItem.height = 151;
//		adItem.name = "回看";
//		adItem.nameSize = 25;
		adItem.image_content = R.drawable.recommend_jiangxi;
//		adItem.image_icon = R.drawable.system_settings_image;
		arrItem.add(adItem);
	}

	@Override
	public boolean requestDefaultFocusLeft() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean requestDefaultFocusRight() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean requestDefaultFocusBottom() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean requestDefaultFocusUp() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canGoUp() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canGoLeft() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canGoDown() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canGoRight() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	private void initViewItem(ItemEntity item) {	
		CommonItemView commonItemView = new CommonItemView(mContext);
		commonItemView.setName(item.name);
		commonItemView.setNameSize(item.nameSize);
		commonItemView.setIcon(item.image_icon);
		commonItemView.setItemBackground(item.image_content);
		commonItemView.setFocusable(true);
		commonItemView.setBackgroundResource(R.drawable.image_button_bg);
		commonItemView.setOnFocusChangeListener(mFocusListener);
		
		 RelativeLayout.LayoutParams relLayoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT); 
		 relLayoutParams.setMargins(item.x, item.y, 0, 0);
		 
		 if (item.width != 0)  relLayoutParams.width  = item.width;
		 if (item.height != 0) relLayoutParams.height = item.height;
		 rl.addView(commonItemView, relLayoutParams);
		 
	}
		
	private OnFocusChangeListener mFocusListener = new View.OnFocusChangeListener() {
		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			if(hasFocus){
				v.bringToFront();
//				v.startAnimation(new AnimationUtil(mContext).mBigAnimation);
				v.setBackgroundResource(R.drawable.bg_b);
//				recommomBottom2.setNameVisibility(View.VISIBLE);
			}else {
//				recommomBottom2.setNameVisibility(View.GONE);
				v.setBackgroundResource(R.drawable.bg_a);
//				v.startAnimation(new AnimationUtil(mContext).mLittleAnimation);
			}
//			mContainer.invalidate();
		}
	};
	 
	
}
