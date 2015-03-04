package com.kylin.view;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.kylin.MyApplication;
import com.kylin.R;
import com.kylin.bean.ItemEntity;

@SuppressLint("ResourceAsColor")
public class Tab extends FrameLayout implements ITabPage{

	
	
	private static final String TAG = "TabTV";

	private Context mContext;
	public String url;


	private RelativeLayout rl;

	private ArrayList<ItemEntity> arrItem;
	ArrayList<CommonItemView> arrCommonItemView = new ArrayList<CommonItemView>();
	
	
	public Tab(Context context) {
		super(context);
		mContext = context;
	}

	public Tab(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
	}

	public Tab(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
	}



	@Override
	public boolean requestDefaultFocusLeft() {
		try {
			arrCommonItemView.get(arrCommonItemView.size()-1).requestFocus();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean requestDefaultFocusRight() {
		try {
			arrCommonItemView.get(0).requestFocus();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		arrCommonItemView = new ArrayList<CommonItemView>();
		CommonItemView commonItemView = new CommonItemView(mContext);
		commonItemView.setName(item.name);
		commonItemView.setNameSize((int)(item.nameSize*MyApplication.mDisplayScale));
		commonItemView.setIcon(item.image_icon,R.drawable.system_settings_image);
		
		commonItemView.setItemBackground(item.image_content ,R.drawable.item_bg);
		
		if (item.nameBg !=null && !"".equals(item.nameBg))   commonItemView.setNameBg(item.nameBg);
		if (item.nameColor !=null && !"".equals(item.nameColor))   commonItemView.setNameColor(item.nameColor);
		commonItemView.setFocusable(item.hasFocus);
		commonItemView.setBackgroundResource(R.drawable.image_button_bg);
		
		
		if(item.hasFocus)  commonItemView.setOnFocusChangeListener(mFocusListener);
		
		
		commonItemView.setOnClickListener(new OnClickListener(item));
		
		commonItemView.setId(item.id);
		commonItemView.setNextFocusLeftId(item.NextFocusLeftId);
		commonItemView.setNextFocusUpId(item.NextFocusUpId);
		commonItemView.setNextFocusRightId(item.NextFocusRightId);
		commonItemView.setNextFocusDownId(item.NextFocusDownId);
		
		 RelativeLayout.LayoutParams relLayoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT); 
		 relLayoutParams.setMargins((int)(item.x*MyApplication.mDisplayScale), (int)(item.y*MyApplication.mDisplayScale), 0, 0);
		 
		 if (item.width != 0)  relLayoutParams.width  = (int)(item.width*MyApplication.mDisplayScale);
		 if (item.height != 0) relLayoutParams.height = (int)(item.height*MyApplication.mDisplayScale);
		 rl.addView(commonItemView, relLayoutParams);
		 
		 arrCommonItemView.add(commonItemView);
		 
	}
	
	
	class OnClickListener implements  View.OnClickListener{

		private ItemEntity item;

		public OnClickListener(ItemEntity item) {
			this.item=item;
		}

		@Override
		public void onClick(View v) {
			try {
				Intent intent = new Intent(item.action);   
				mContext.startActivity(intent);
			} catch (Exception e) {
				Toast.makeText(mContext, "无法启动", 1).show();
				e.printStackTrace();
			}   
		}
		
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
