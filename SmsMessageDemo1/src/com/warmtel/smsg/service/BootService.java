/**
 * @author: zhous
 * @version: v1.0
 * @time: 2011-5-12
 */
package com.warmtel.smsg.service;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import com.warmtel.smsg.SMS;
import com.warmtel.smsg.SMSHandler;
import com.warmtel.smsg.SMSObserver;

public class BootService extends Service {
	public static final String TAG = "BootService";
	private ContentObserver mObserver;

	@Override
	public void onCreate(){
		Log.v(TAG, "onCreate().");
		super.onCreate();
		addSMSObserver();
	}

	public void addSMSObserver(){
		Log.v(TAG, "add a SMS observer. ");

		ContentResolver resolver = getContentResolver();

		Handler handler = new SMSHandler(this);

		mObserver = new SMSObserver(resolver, handler);
		resolver.registerContentObserver(SMS.CONTENT_URI, true, mObserver);
	}

	@Override
	public IBinder onBind(Intent intent){
		return null;
	}

	@Override
	public void onDestroy(){
		Log.v(TAG, "onDestroy().");
		this.getContentResolver().unregisterContentObserver(mObserver);
		super.onDestroy();
	}

}