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
public class Tab extends FrameLayout implements ITabPage{

	
	
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
	
	
	public Tab(Context context) {
		super(context);
		mContext = context;
//		init();
	}

	public Tab(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
//		init();
	}

	public Tab(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
//		init();
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
		View view = this.findFocus();
		if (null != view && View.NO_ID != view.getNextFocusUpId()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean canGoLeft() {
		View view = this.findFocus();
		if (null != view && View.NO_ID != view.getNextFocusLeftId()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean canGoDown() {
		View view = this.findFocus();
		if (null != view && View.NO_ID != view.getNextFocusDownId()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean canGoRight() {
		View view = this.findFocus();
		if (null != view && View.NO_ID != view.getNextFocusRightId()) {
			return true;
		}
		return false;
	}
	
	
	private void initViewItem(ItemEntity item) {	
		ArrayList<CommonItemView> arrCommonItemView = new ArrayList<CommonItemView>();
		CommonItemView commonItemView = new CommonItemView(mContext);
		commonItemView.setName(item.name);
		commonItemView.setNameSize(item.nameSize);
		commonItemView.setIcon(item.image_icon);
		commonItemView.setItemBackground(item.image_content);
		commonItemView.setFocusable(item.hasFocus);
		commonItemView.setBackgroundResource(R.drawable.image_button_bg);
		commonItemView.setOnFocusChangeListener(mFocusListener);
		commonItemView.setId(item.id);
		commonItemView.setNextFocusLeftId(item.NextFocusLeftId);
		commonItemView.setNextFocusUpId(item.NextFocusUpId);
		commonItemView.setNextFocusRightId(item.NextFocusRightId);
		commonItemView.setNextFocusDownId(item.NextFocusDownId);
		
		 RelativeLayout.LayoutParams relLayoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT); 
		 relLayoutParams.setMargins(item.x, item.y, 0, 0);
		 
		 if (item.width != 0)  relLayoutParams.width  = item.width;
		 if (item.height != 0) relLayoutParams.height = item.height;
		 rl.addView(commonItemView, relLayoutParams);
		 
		 arrCommonItemView.add(commonItemView);
		 
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


	public void setData(ArrayList<ItemEntity> arrayList) {

//		LayoutInflater mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//		View view = (View) mLayoutInflater.inflate(R.layout.view_tab_tv,null);
		rl = new RelativeLayout(mContext);
		
		arrItem = arrayList;
//		arrItem = (ArrayList<ItemEntity>)getTag();
		
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT); 
		addView(rl, params);
		
		
		for (ItemEntity item : arrItem) {
			initViewItem(item);
		}
		rl.setBackgroundColor(R.color.green);
	}


	 
	
}
