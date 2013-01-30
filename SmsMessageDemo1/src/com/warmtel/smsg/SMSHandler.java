/**
 * @author: zhous
 * @version: v1.0
 * @time: 2011-5-12
 */
package com.warmtel.smsg;

import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class SMSHandler extends Handler {
	public static final String TAG = "SMSObserver";

	private Context mContext;

	public SMSHandler(Context context) {
		super();
		this.mContext = context;
	}

	public void handleMessage(Message message) {

		MessageItem item = (MessageItem) message.obj;
		
		Log.v(TAG, "Message: " + item.toString());
		// delete the sms
		Uri uri = ContentUris.withAppendedId(SMS.CONTENT_URI, item.getId());
		
		mContext.getContentResolver().delete(uri, null, null);

		Log.v(TAG, "delete sms item: " + item+"uri: " + uri.toString());

	}

}
