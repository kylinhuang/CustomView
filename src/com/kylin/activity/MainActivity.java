package com.kylin.activity;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.teleal.cling.android.AndroidUpnpService;
import org.teleal.cling.binding.LocalServiceBindingException;
import org.teleal.cling.binding.annotations.AnnotationLocalServiceBinder;
import org.teleal.cling.model.DefaultServiceManager;
import org.teleal.cling.model.ValidationException;
import org.teleal.cling.model.meta.Device;
import org.teleal.cling.model.meta.DeviceDetails;
import org.teleal.cling.model.meta.DeviceIdentity;
import org.teleal.cling.model.meta.LocalDevice;
import org.teleal.cling.model.meta.LocalService;
import org.teleal.cling.model.meta.ManufacturerDetails;
import org.teleal.cling.model.meta.ModelDetails;
import org.teleal.cling.model.meta.RemoteDevice;
import org.teleal.cling.model.types.DeviceType;
import org.teleal.cling.model.types.ServiceId;
import org.teleal.cling.model.types.UDADeviceType;
import org.teleal.cling.model.types.UDAServiceId;
import org.teleal.cling.model.types.UDN;
import org.teleal.cling.registry.DefaultRegistryListener;
import org.teleal.cling.registry.RegistrationException;
import org.teleal.cling.registry.Registry;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Instrumentation;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.kylin.MyApplication;
import com.kylin.R;
import com.kylin.bean.BaseEntity;
import com.kylin.bean.ItemEntity;
import com.kylin.bean.LogoEntity;
import com.kylin.bean.MainEntity;
import com.kylin.bean.PagerEntity;
import com.kylin.bean.TimeEntity;
import com.kylin.bean.TitleEntity;
import com.kylin.utils.BitmapHelp;
import com.kylin.utils.BitmapViewUtils;
import com.kylin.utils.EntityUtils;
import com.kylin.view.ITabPage;
import com.kylin.view.Tab;
import com.kylin.view.TimeView;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;
import com.utils.LoggerUtils;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class MainActivity extends Activity {

	public static final String TAG = "MainActivity";
	private Context mContext;
	private ImageView ivLogo;
	private LayoutInflater mLayoutInflater;
	private View view;
	private RelativeLayout rl_content;
	private LogoEntity logoEntity;
	private RelativeLayout rl;
	private MainEntity mainEntity;
	private BaseEntity networkEntity;
	private TimeEntity timeEntity;
	private ArrayList<TitleEntity> arrayTitle;
	ArrayList<TextView> arrTitle = new  ArrayList<TextView>();
	private ViewPager mViewPager;
	
//	private HashMap<Integer, ArrayList<ItemEntity>>  tabMap;
	private ArrayList<ArrayList<ItemEntity>> arrTab;
	
	/**  为VIewPager保存VIew界面的集合*/
	private List<View> mViewCache = new ArrayList<View>();
	private MyPagerAdapter mViewPageAdapter;
	private Tab tabTV;
	
	/** 最后 按键值  */  
	private int mLastKeyCode;
	public int mSelectedPageIndex;
	/**  焦点是否保持在菜单下   */ 
	private boolean mKeepMenuState ;
	private ImageView networkView;
	private TimeView timeView;
	public int mViewPageScrollState;
	private boolean isMenuPager = false;
	private DeviceListRegistryListener deviceListRegistryListener;
	protected AndroidUpnpService upnpService;
	private Handler mHandler;
	private PagerEntity pagerEntity;
	public static MainActivity mInstance;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		mInstance= this;
		
		 DisplayMetrics dm = new DisplayMetrics();  
	     getWindowManager().getDefaultDisplay().getMetrics(dm); 
	     String s =  "density:"+dm.density+";densityDpi:"+dm.densityDpi  
	            +";height:"+dm.heightPixels+";width:"+dm.widthPixels  
	            +";scaledDensity:"+dm.scaledDensity+";xdpi:"+dm.xdpi  
	            +";ydpi:"+dm.ydpi;  
	    MyApplication.mDisplayScale =   dm.heightPixels /720.0;
//	     density:1.0;
//	     densityDpi:160;
//	     height:1080;
//	     width:1920; 
//	     scaledDensity:1.0;
//	     xdpi:144.0;
//	     ydpi:144.0
	     
	     
//	     density:1.0;
//	     densityDpi:160;
//	     height:720;
//	     width:1280;
//	     scaledDensity:1.0;
//	     xdpi:160.15764;
//	     ydpi:160.42105
	     
	     
//	     density:1.5;
//	     densityDpi:240;
//	     height:782;
//	     width:480;
//	     scaledDensity:1.5;
//	     xdpi:240.0;
//	     ydpi:240.0
	     
	     
	     Log.e(TAG, s);
		
		init();
		initEntity();
		initView();
		
		initRemote();
		initHandler();
	}

	private void initHandler() {
		mHandler= new Handler() {
			@Override
			public void handleMessage(Message msg) {
				
			final String  commend = msg.getData().getString("msg");
			
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					new Instrumentation().sendKeyDownUpSync(Integer.parseInt(commend));
				}
			}).start();
			
			Toast.makeText(mInstance, commend, 1).show();

			}
		};
	}

	private void initRemote() {
		deviceListRegistryListener = new DeviceListRegistryListener();

		getApplicationContext().bindService(
				new Intent(this, DemoUpnpService.class), serviceConnection,
				Context.BIND_AUTO_CREATE);
		
	}

	private void init() {
		BitmapUtils bitmapUtils = BitmapHelp.getBitmapUtils(mContext);
//        bitmapUtils.configDefaultLoadingImage(R.drawable.ic_launcher);
//        bitmapUtils.configDefaultLoadFailedImage(R.drawable.bitmap);
        bitmapUtils.configDefaultBitmapConfig(Bitmap.Config.RGB_565);
	}

	private void initEntity() {
		mainEntity 		= EntityUtils.getMainEntity();
		Gson gson = new Gson();
	    String jsonStudents = gson.toJson(mainEntity);
	    LoggerUtils.isDebug = true;
		LoggerUtils.e(TAG, jsonStudents);
		
		logoEntity 		= mainEntity.getLogoEntity();
		networkEntity 	= mainEntity.getNetworkEntity();
		timeEntity 		= mainEntity.getTimeEntity();
		arrayTitle 		= mainEntity.getArrayTitle();
		arrTab 			= mainEntity.getArrTab();
		
		pagerEntity =	mainEntity.getPagerEntity();
	}

	private void initView() {
		 rl = new RelativeLayout(this); 
		 RelativeLayout.LayoutParams mainParams = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT); 
		 setContentView(rl, mainParams);
		 initViewMainBackground();
		 initView_logo(logoEntity, R.drawable.icon);
		 initViewNetwork(networkEntity);
		 initViewTime(timeEntity);
		 for (TitleEntity titleEntity : arrayTitle) {
			 initViewTitle(titleEntity);
		}
		initViewPager(pagerEntity);
		changeViewPageScroller();
		
	}




	private void initViewTime(TimeEntity timeEntity ) {
		timeView = new TimeView(mContext);
		timeView.setTextSize((int)(timeEntity.textSize*MyApplication.mDisplayScale));
		initViewParameter(timeEntity, rl, timeView);
	}

	private void initViewPager(PagerEntity pagerEntity) {
		Tab tab ;
		for (int i = 0; i < arrTab.size()+2; i++) {
			if(i == 0){ // add 最后一页
				tab  =  new Tab(mContext);
				tab.setData(arrTab.get(arrTab.size()-1));
				mViewCache.add(tab);
			}else if (i == (arrTab.size() +2-1)) { //add 
				tab  =  new Tab(mContext);
				tab.setData(arrTab.get(0));
				mViewCache.add(tab);
			}else {
				tab  =  new Tab(mContext);
				tab.setData(arrTab.get(i-1));
				mViewCache.add(tab);
			}
			
//			tab  =  new Tab(mContext);
//			tab.setData(arrTab.get(i));
//			mViewCache.add(tab);
		}
		
		RelativeLayout.LayoutParams relLayoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT); 
		relLayoutParams.setMargins((int)(pagerEntity.x*MyApplication.mDisplayScale), (int)(pagerEntity.y*MyApplication.mDisplayScale), 0,  0);
		 
		
		 
		 
		 if (pagerEntity.width != 0)  relLayoutParams.width  = (int)(pagerEntity.width  * MyApplication.mDisplayScale) ;
		 if (pagerEntity.height != 0) relLayoutParams.height = (int)(pagerEntity.height * MyApplication.mDisplayScale);
		 
	 
		mViewPager = new ViewPager(mContext);
//		mViewPager.setFocusable(true);
		mViewPageAdapter = new MyPagerAdapter();
		mViewPager.setOffscreenPageLimit(6);
		mViewPager.setAdapter(mViewPageAdapter);
		mViewPager.setCurrentItem(0);
		mViewPager.setCurrentItem(1);
		setCurpager(0);	
		rl.addView(mViewPager, relLayoutParams);
		mViewPager.setOnPageChangeListener(new MyOnPageChangeListener());
	}


	private void initViewTitle(TitleEntity titleEntity) {
		
		TextView textView = new TextView(mContext);
		textView.setText(titleEntity.text);
		textView.setFocusable(true);
		textView.setOnFocusChangeListener(new OnFocusChangeListener(titleEntity));
		textView.setTextSize((float)(titleEntity.textSize * MyApplication.mDisplayScale));
		
		textView.setTag(titleEntity.position);
		
		textView.setGravity(Gravity.CENTER);
		// TODO Auto-generated method stub
		
		 RelativeLayout.LayoutParams relLayoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT); 
		 relLayoutParams.setMargins((int)(titleEntity.x*MyApplication.mDisplayScale), (int)(titleEntity.y*MyApplication.mDisplayScale), 0, 0);
		 
		 
		 if (titleEntity.width != 0)  relLayoutParams.width  = (int)(titleEntity.width  * MyApplication.mDisplayScale) ;
		 if (titleEntity.height != 0) relLayoutParams.height = (int)(titleEntity.height * MyApplication.mDisplayScale);
		 rl.addView(textView, relLayoutParams);
		 
		 arrTitle.add(textView);
	}

	private void initViewMainBackground() {
		
//		BitmapUtils.
		BitmapUtils bitmapUtils = BitmapHelp.getBitmapUtils(mContext);
		bitmapUtils.display(rl, mainEntity.getBackground());
//		rl.setBackgroundResource(mainEntity.getBackground());
	}

	
	private void initViewNetwork(BaseEntity networkEntity ) {
		networkView = new ImageView(mContext);
		networkView.setBackgroundResource(R.drawable.main_page_image_wired_disconnect);
		initViewParameter(networkEntity, rl, networkView);
	}

	private void initView_logo(LogoEntity logoEntity,final int resid) {
		final ImageView view = new ImageView(mContext);
		if(logoEntity.background_url == null || "".equals(logoEntity.background_url)){
			view.setBackgroundResource(resid);
		}else {
			BitmapUtils bitmapUtils = BitmapHelp.getBitmapUtils(mContext);
			bitmapUtils.display(view, logoEntity.background_url,new BitmapLoadCallBack<View>() {
				@Override
				public void onLoadCompleted(View container, String uri,
						Bitmap bitmap, BitmapDisplayConfig config,
						BitmapLoadFrom from) {
					 BitmapViewUtils.fadeInDisplay(view, bitmap);
				}

				@Override
				public void onLoadFailed(View container, String uri,
						Drawable drawable) {
					view.setBackgroundResource(resid);
				}
			}  );
		} 
		 
		initViewParameter(logoEntity, rl, view);
		
	}
 
	private void initViewParameter(BaseEntity entity, RelativeLayout  parentView,View view) {
		view.setFocusable(entity.hasFocus);
		RelativeLayout.LayoutParams relLayoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT); 
		relLayoutParams.setMargins((int)(entity.x * MyApplication.mDisplayScale),(int) (entity.y * MyApplication.mDisplayScale), 0, 0);
		 
		if (entity.width != 0)  relLayoutParams.width  = (int)(entity.width  * MyApplication.mDisplayScale);
		if (entity.height != 0) relLayoutParams.height = (int)(entity.height * MyApplication.mDisplayScale);
		parentView.addView(view, relLayoutParams);
	}

//	private void initView(BaseEntity entity) {
//		View view = null;
//		switch (entity.type) {
//		case 1:
//			view = new ImageView(mContext);
//			
//			if(entity.background_type == 0 ){
//				view.setBackgroundResource(entity.background_id);
//			}else {
//				BitmapUtils bitmapUtils = BitmapHelp.getBitmapUtils(mContext);
//				bitmapUtils.display(view, entity.background_url);
//			} 
//			break;
//		case 2:
//			view = new TimeView(mContext);
//			break;
//		case 4:
//			break;
//		case 5:
//			break;
//		default:
//			break;
//		}
//		view.setFocusable(entity.hasFocus);
//		RelativeLayout.LayoutParams relLayoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT); 
//		relLayoutParams.setMargins(entity.x, entity.y, 0, 0);
//		 
//		if (entity.width != 0)  relLayoutParams.width  = entity.width;
//		if (entity.height != 0) relLayoutParams.height = entity.height;
//		rl.addView(view, relLayoutParams);
//	}
	
	
	class OnFocusChangeListener implements View.OnFocusChangeListener{

		private TitleEntity titleEntity;

		public OnFocusChangeListener(TitleEntity titleEntity) {
			this.titleEntity = titleEntity;
		}

		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			mKeepMenuState = hasFocus;
			if (hasFocus) {
				((TextView)v).setTextSize(35);
				mViewPager.setCurrentItem(titleEntity.position );
				Log.e(TAG, ((TextView)v).getText().toString() + titleEntity.position +  "");
				v.setBackgroundResource(R.drawable.text_view_bg);
				v.bringToFront();
//				v.startAnimation(AnimationUtil.getInstance(mContext).mBigAnimation);
			}else {
				v.setBackgroundResource(android.R.color.transparent);
				((TextView)v).setTextSize(25);
				
				setCurpager(mSelectedPageIndex);
//				v.startAnimation(AnimationUtil.getInstance(mContext).mLittleAnimation);
			}
//			mContainer.invalidate();
		}
		
	}
	
	//反射机制   控制 viewpager滑动时间  为800
	 private void changeViewPageScroller() {
		try {
			Field mField = ViewPager.class.getDeclaredField("mScroller");
			mField.setAccessible(true);
			FixedSpeedScroller scroller;
			scroller = new FixedSpeedScroller(MainActivity.this,new AccelerateDecelerateInterpolator());
			mField.set(mViewPager, scroller);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	 
	 class FixedSpeedScroller extends Scroller {
			private int mDuration = 800;

			public FixedSpeedScroller(Context context) {
				super(context);
			}

			public FixedSpeedScroller(Context context, Interpolator interpolator) {
				super(context, interpolator);
			}

			@Override
			public void startScroll(int startX, int startY, int dx, int dy,
					int duration) {
				// Ignore received duration, use fixed one instead
				super.startScroll(startX, startY, dx, dy, mDuration);
			}

			@Override
			public void startScroll(int startX, int startY, int dx, int dy) {
				// Ignore received duration, use fixed one instead
				super.startScroll(startX, startY, dx, dy, mDuration);
			}

			public void setmDuration(int time) {
				mDuration = time;
			}

			public int getmDuration() {
				return mDuration;
			}

		};
		
		/**
		 * 页卡切换监听
		 */
		public class MyOnPageChangeListener implements OnPageChangeListener {

			@Override
			public void onPageScrollStateChanged(int state) {
				Log.e(TAG, "onPageScrollStateChanged  " + state );

				mViewPageScrollState = state;
				if(state == ViewPager.SCROLL_STATE_IDLE){
				
					if(mViewPager.getCurrentItem() == 0){
						mViewPager.setCurrentItem(mViewCache.size()-2  , false);
					}else if(mViewPager.getCurrentItem() == (mViewCache.size()-1)){
						mViewPager.setCurrentItem(1, false);
					}
				}
			
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				Log.e(TAG, "onPageScrolled  " + arg0 + "   "  + arg1 +"   " + arg2);
			}

			@Override
			public void onPageSelected(int position) {
				setCurpager(position -1);
				mSelectedPageIndex = position;
				Log.e(TAG, "onPageSelected  " + position  );
				if (mKeepMenuState) {
					View lastItem = null;
					View currentItem = null;
					if (mLastKeyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
//						((ITabPage) mViewCache.get(position)).requestDefaultFocusRight();
//						switch (position) {
//						
//							case 0:
//								lastItem = mMenuItemList.get(0);	
//								onTextViewGetSelect2Normal((ImageView) lastItem);
//								currentItem = mMenuItemList.get(5);
//								onTextViewGetNormal2Select((ImageView) currentItem);
//								
//								break;
//							case 1:
//								
//								lastItem = mMenuItemList.get(1);	
//								onTextViewGetSelect2Normal((ImageView) lastItem);
//								currentItem = mMenuItemList.get(0);
//								onTextViewGetNormal2Select((ImageView) currentItem);
//								break;
//							case 2:
//								
//								lastItem = mMenuItemList.get(2);	
//								onTextViewGetSelect2Normal((ImageView) lastItem);
//								currentItem = mMenuItemList.get(1);
//								onTextViewGetNormal2Select((ImageView) currentItem);
//								
//								break;
//							case 3:
//								
//								lastItem = mMenuItemList.get(3);	
//								onTextViewGetSelect2Normal((ImageView) lastItem);
//								currentItem = mMenuItemList.get(2);
//								onTextViewGetNormal2Select((ImageView) currentItem);
//								
//								break;
//							case 4:
//								
//								lastItem = mMenuItemList.get(4);	
//								onTextViewGetSelect2Normal((ImageView) lastItem);
//								currentItem = mMenuItemList.get(3);
//								onTextViewGetNormal2Select((ImageView) currentItem);
//								break;
//							case 5:
//								
//								lastItem = mMenuItemList.get(5);	
//								onTextViewGetSelect2Normal((ImageView) lastItem);
//								currentItem = mMenuItemList.get(4);
//								onTextViewGetNormal2Select((ImageView) currentItem);
//								
//								break;
//							case 6:
//								
//								lastItem = mMenuItemList.get(0);	
//								onTextViewGetSelect2Normal((ImageView) lastItem);
//								currentItem = mMenuItemList.get(5);
//								onTextViewGetNormal2Select((ImageView) currentItem);
//								
//								break;
//							case 7:
//								lastItem = mMenuItemList.get(5);	
//								onTextViewGetSelect2Normal((ImageView) lastItem);
//								currentItem = mMenuItemList.get(0);
//								onTextViewGetNormal2Select((ImageView) currentItem);
//								break;
//						}
					}else if(mLastKeyCode == KeyEvent.KEYCODE_DPAD_RIGHT){
//						((ITabPage) mViewCache.get(position)).requestDefaultFocusLeft();
						switch (position) {
							case 0:
								lastItem = mViewCache.get(0);	
//								onTextViewGetSelect2Normal((ImageView) lastItem);
								currentItem = mViewCache.get(5);
//								onTextViewGetNormal2Select((ImageView) currentItem);
								
								break;
							case 1:
								
								lastItem = mViewCache.get(5);	
//								onTextViewGetSelect2Normal((ImageView) lastItem);
								currentItem = mViewCache.get(0);
//								onTextViewGetNormal2Select((ImageView) currentItem);
								break;
							case 2:
								
								lastItem = mViewCache.get(0);	
//								onTextViewGetSelect2Normal((ImageView) lastItem);
								currentItem = mViewCache.get(1);
//								onTextViewGetNormal2Select((ImageView) currentItem);
								
								break;
							case 3:
								
								lastItem = mViewCache.get(1);	
//								onTextViewGetSelect2Normal((ImageView) lastItem);
								currentItem = mViewCache.get(2);
//								onTextViewGetNormal2Select((ImageView) currentItem);
								
								break;
							case 4:
								
								lastItem = mViewCache.get(2);	
//								onTextViewGetSelect2Normal((ImageView) lastItem);
								currentItem = mViewCache.get(3);
//								onTextViewGetNormal2Select((ImageView) currentItem);
								break;
							case 5:
								
								lastItem = mViewCache.get(3);	
//								onTextViewGetSelect2Normal((ImageView) lastItem);
								currentItem = mViewCache.get(4);
//								onTextViewGetNormal2Select((ImageView) currentItem);
								
								break;
							case 6:
								lastItem = mViewCache.get(4);	
//								onTextViewGetSelect2Normal((ImageView) lastItem);
								currentItem = mViewCache.get(5);
//								onTextViewGetNormal2Select((ImageView) currentItem);
								
								break;
							case 7:
								
								lastItem = mViewCache.get(5);	
//								onTextViewGetSelect2Normal((ImageView) lastItem);
								currentItem = mViewCache.get(0);
//								onTextViewGetNormal2Select((ImageView) currentItem);
								
								break;
						}
					}
				}
			
				
				
				
			}
			
		}

		public void setCurpager(int arg0) {
			for (int i = 0,  size = arrayTitle.size() ; i < size; i++) {
				if(i == arg0){
					arrTitle.get(i).setTextSize(35);
				}else {
					arrTitle.get(i).setTextSize(25);
				}
			}
		}

		@Override
		public boolean dispatchKeyEvent(KeyEvent event) {
			if (KeyEvent.ACTION_DOWN == event.getAction()) {
				mLastKeyCode = event.getKeyCode();
				
				if (mViewPageScrollState != ViewPager.SCROLL_STATE_IDLE) return true;
				View view = this.getCurrentFocus();
				ITabPage selectedPage = (ITabPage) mViewCache.get(mSelectedPageIndex);
				if (null != view) {
					if(mKeepMenuState ){//如果在tab menu而且  向up
						switch (mLastKeyCode) {
						case KeyEvent.KEYCODE_DPAD_UP :// UP
							
							break;
						case KeyEvent.KEYCODE_DPAD_DOWN :// Down
							if (!selectedPage.canGoDown()) {// tab不能向下
								try {
									arrTitle.get(mSelectedPageIndex).requestFocus();
								} catch (Exception e) {
									e.printStackTrace();
								}
								return true;
							}
							
							break;
//						case KeyEvent.KEYCODE_DPAD_LEFT :// LEFT
//							isMenuPager  = true;
//							UPPage();
//							return true;
//						case KeyEvent.KEYCODE_DPAD_RIGHT :// RIGHT
//							isMenuPager  = true;
//							nextPage();
//							return true;
						default:
							break;
						}
					}else { //----------------------- 非菜单下
						switch (mLastKeyCode) {
						case KeyEvent.KEYCODE_DPAD_UP :// UP
							
							break;
						case KeyEvent.KEYCODE_DPAD_DOWN :// Down
							if (!selectedPage.canGoDown()) {// tab不能向下
								
								arrTitle.get(mSelectedPageIndex-1).requestFocus();
								return true;
							}
							break;
						case KeyEvent.KEYCODE_DPAD_LEFT :// LEFT
//							if (mSelectedPageIndex == 0) {// 第一页
//								if (!selectedPage.canGoLeft()) {
//									mViewPager.setCurrentItem(arrTab.size()-1, false);
//									ITabPage page = (ITabPage) mViewCache.get(arrTab.size()-1);
//									page.requestDefaultFocusLeft();
//									return true;
//								}
//							}
							
							break;
						case KeyEvent.KEYCODE_DPAD_RIGHT :// RIGHT
//							if (mSelectedPageIndex == arrTab.size()-1) {// 最后一页
//								if (!selectedPage.canGoRight()) {
//									mViewPager.setCurrentItem(0, false);
//									ITabPage page = (ITabPage) mViewCache.get(0);
//									page.requestDefaultFocusRight();
//									return true;
//								}
//							}
							break;
						default:
							break;
						}
					}
				}
				
			}
			return super.dispatchKeyEvent(event);
		}

		
		private void UPPage() {
			
//			if(mSelectedPageIndex  == 1 ){ 
//				arrTitle.get(arrTitle.size()-1).requestFocus();
//			}else {
//				arrTitle.get(mSelectedPageIndex-1).requestFocus();
//			}
			
			try {
				arrTitle.get(mSelectedPageIndex-1).requestFocus();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		private void nextPage() {
//			if(mSelectedPageIndex  == (arrTitle.size()-1)){ 
//				arrTitle.get(1).requestFocus();
//			}else {
//				arrTitle.get(mSelectedPageIndex+1).requestFocus();
//			}
			
			try {
				arrTitle.get(mSelectedPageIndex+1).requestFocus();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// 适配器
		class MyPagerAdapter extends PagerAdapter {

			public void destroyItem(View view, int position, Object arg2) {
				((ViewPager) view).removeView((View) arg2);
			}

			public int getCount() {
				return mViewCache.size();
			}

			public Object instantiateItem(View arg0, int arg1) {
				Log.i("instantiateItem", arg1 + "");
				((ViewPager) arg0).addView(mViewCache.get(arg1), 0);

				return mViewCache.get(arg1);
			}

			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == (arg1);
			}

			@Override
			public void startUpdate(View arg0) {
			}

		}
		
		

		private ServiceConnection serviceConnection = new ServiceConnection() {

			public void onServiceConnected(ComponentName className, IBinder service) {
				upnpService = (AndroidUpnpService) service;

				if (upnpService == null) {
					return;
				}
				// Getting ready for future device advertisements
				upnpService.getRegistry().addListener(deviceListRegistryListener);
				try {
					upnpService.getRegistry().addDevice(createDevice());
				} catch (RegistrationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (LocalServiceBindingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ValidationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

//				deviceListAdapter.clear();

				for (Device device : upnpService.getRegistry().getDevices()) {
					Log.i("zeng", "device:" + device.getDisplayString());
					deviceListRegistryListener.deviceAdded(device);
				}
				// Refresh device list
				// upnpService.getControlPoint().search();
			}

			public void onServiceDisconnected(ComponentName className) {
				upnpService = null;
			}
		};

		
		/**设备注册的监听*/
		public class DeviceListRegistryListener extends DefaultRegistryListener {

			@Override
			public void localDeviceAdded(Registry registry, LocalDevice device) {
				// TODO Auto-generated method stub
				Log.i("zeng", "localDeviceAdded");
				deviceAdded(device);
			}

			@Override
			public void remoteDeviceAdded(Registry registry, RemoteDevice device) {
				Log.i("zeng", "remoteDeviceAdded");
				deviceAdded(device);
			}

			@Override
			public void remoteDeviceRemoved(Registry registry, RemoteDevice device) {
				final DeviceItem display = new DeviceItem(device, device.getDisplayString());
				deviceRemoved(display);
			}

			public void deviceAdded(final Device device) {
				// if (!(device instanceof RemoteDevice)) {
				// return;
				// }
				ServiceId serviceId = new UDAServiceId("MessageHandler");

				if (device.findService(serviceId) == null) {
					return;
				}

				DeviceItem item = new DeviceItem(device);
//				deviceAdded(item);
			}

//			public void deviceAdded(final DeviceItem di) {
//				runOnUiThread(new Runnable() {
//					public void run() {
//
//						int position = deviceListAdapter.getPosition(di);
//						if (position >= 0) {
//							// Device already in the list, re-set new value at same
//							// position
//							deviceListAdapter.remove(di);
//							deviceListAdapter.insert(di, position);
//						} else {
//							deviceListAdapter.add(di);
//						}
//						deviceListView.setAdapter(deviceListAdapter);
//					}
//				});
//			}

			public void deviceRemoved(final DeviceItem di) {
				runOnUiThread(new Runnable() {
					public void run() {
//						deviceListAdapter.remove(di);
					}
				});
			}
		}

		
		// DOC: CREATEDEVICE
		private LocalDevice createDevice() throws ValidationException,
				LocalServiceBindingException, IOException {
			DeviceIdentity identity = new DeviceIdentity(
					UDN.uniqueSystemIdentifier("UpnpDemo"));

			DeviceType type = new UDADeviceType("UpnpDemo", 1);

			DeviceDetails details = new DeviceDetails(android.os.Build.MODEL,
					new ManufacturerDetails(android.os.Build.MANUFACTURER),
					new ModelDetails("UpnpDemo", "A demo for chat", "v1"));

			LocalService<MessageHandler> messageHandlerService = new AnnotationLocalServiceBinder()
					.read(MessageHandler.class);

			messageHandlerService.setManager(new DefaultServiceManager(
					messageHandlerService, MessageHandler.class));

			return new LocalDevice(identity, type, details, messageHandlerService);
		}

		
		public void messageReceived(String msg,String from) {
			 Message message = mHandler.obtainMessage();
			 Bundle data = new Bundle();
			 data.putString("msg", msg);
			 message.setData(data);
			
			 mHandler.sendMessage(message);
			
			
			
			
//			if (msg.equals("play")) {
//				mHandler.sendEmptyMessage(COMMAND_PLAY);
//			}
//			if (msg.equals("pause")) {
//				mHandler.sendEmptyMessage(COMMAND_PAUSE);
//			}
//			if (msg.equals("stop")) {
//				mHandler.sendEmptyMessage(COMMAND_STOP);
//			}
			
			
			
		}

}
