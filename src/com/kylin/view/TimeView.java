package com.kylin.view;

import java.util.Calendar;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.widget.DigitalClock;

public class TimeView extends DigitalClock {

	private Context mContext;
	private Calendar mCalendar;
	private FormatChangeObserver mFormatChangeObserver;
	private String mFormat;
	
	private boolean mTickerStopped;
	private Runnable mTicker;
	private Handler mHandler;
	
	private final static String m = "yyyy-MM-dd kk:mm";
	private final static String m24 = "kk:mm";
	private final static String m12 = "hh:mm";

	public TimeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		 initClock(context);
		 mContext = context;
	}


	public TimeView(Context context) {
		super(context);
		 initClock(context);
		 mContext = context;
	}

    private void initClock(Context context) {
        if (mCalendar == null) {
            mCalendar = Calendar.getInstance();
        }

        mFormatChangeObserver = new FormatChangeObserver();
        getContext().getContentResolver().registerContentObserver(
                Settings.System.CONTENT_URI, true, mFormatChangeObserver);

        setFormat();
    }
	
    private void setFormat() {
    	 mFormat = m;
	}
    
    
    @Override
    protected void onAttachedToWindow() {
        mTickerStopped = false;
        super.onAttachedToWindow();
        mHandler = new Handler();

        /**
         * requests a tick on the next hard-second boundary
         */
        mTicker = new Runnable() {
                public void run() {
                    if (mTickerStopped) return;
                    mCalendar.setTimeInMillis(System.currentTimeMillis());
                    setText(DateFormat.format(mFormat, mCalendar));
//                    setHourIn24(DateFormat.format("AA", mCalendar).toString());
                    invalidate();
                    long now = SystemClock.uptimeMillis();
                    long next = now + (1000 - now % 1000);
                    mHandler.postAtTime(mTicker, next);
                }
            };
        mTicker.run();
    }

	private class FormatChangeObserver extends ContentObserver {
        public FormatChangeObserver() {
            super(new Handler());
        }

        @Override
        public void onChange(boolean selfChange) {
            setFormat();
        }
    }

}
