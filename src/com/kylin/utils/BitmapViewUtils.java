package com.kylin.utils;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.view.View;
import android.widget.ImageView;

public class BitmapViewUtils {

	
	private static final ColorDrawable TRANSPARENT_DRAWABLE = new ColorDrawable(android.R.color.transparent);
	public static void fadeInDisplay(ImageView imageView, Bitmap bitmap) {
        final TransitionDrawable transitionDrawable =
                new TransitionDrawable(new Drawable[]{
                        TRANSPARENT_DRAWABLE,
                        new BitmapDrawable(imageView.getResources(), bitmap)
                });
        imageView.setImageDrawable(transitionDrawable);
        transitionDrawable.startTransition(500);
    }
	public static void setImage(final ImageView imageview, String imageurl, final int resId, Context mContext) {
		BitmapUtils bitmapUtils = BitmapHelp.getBitmapUtils(mContext);
		bitmapUtils.display(imageview, imageurl , new BitmapLoadCallBack<View>() {
			@Override
			public void onLoadCompleted(View container, String uri,
					Bitmap bitmap, BitmapDisplayConfig config,
					BitmapLoadFrom from) {
				 BitmapViewUtils.fadeInDisplay(imageview, bitmap);
			}

			@Override
			public void onLoadFailed(View container, String uri, Drawable drawable) {
				imageview.setBackgroundResource(resId);
			}
		}  );
		
	}
}
