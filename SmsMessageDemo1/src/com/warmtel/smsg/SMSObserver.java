/**
 * @author: zhous
 * @version: v1.0
 * @time: 2011-5-12
 */
package com.warmtel.smsg;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class SMSObserver extends ContentObserver {
	public static final String TAG = "SMSObserver";

	private static final String[] PROJECTION = new String[] { 
		    SMS._ID,// 0
			SMS.TYPE,// 1
			SMS.ADDRESS,// 2
			SMS.BODY,// 3
			SMS.DATE,// 4
			SMS.THREAD_ID,// 5
			SMS.READ,// 6
			SMS.PROTOCOL // 7
	};
	private static final int COLUMN_INDEX_ID = 0;
	private static final int COLUMN_INDEX_TYPE = 1;
	private static final int COLUMN_INDEX_PHONE = 2;
	private static final int COLUMN_INDEX_BODY = 3;
	private static final int COLUMN_INDEX_PROTOCOL = 7;
	private ContentResolver mResolver;
	private Handler mHandler;

	public SMSObserver(ContentResolver contentResolver, Handler handler) {
		super(handler);
		this.mResolver = contentResolver;
		this.mHandler = handler;
	}

	@Override
	public void onChange(boolean selfChange) {
		Log.v(TAG, "onChange : " + selfChange);
		super.onChange(selfChange);
		Cursor cursor = mResolver.query(SMS.CONTENT_URI, PROJECTION, null,
				null, null);
		Log.v(TAG, "cursor getColumnCount :" + cursor.getColumnCount());
		
		int id, type, protocol;
		String phone, body;
		Message message;
		MessageItem item;
		while (cursor.moveToNext()) {
			id = cursor.getInt(COLUMN_INDEX_ID);
			type = cursor.getInt(COLUMN_INDEX_TYPE);
			phone = cursor.getString(COLUMN_INDEX_PHONE);
			body = cursor.getString(COLUMN_INDEX_BODY);
			protocol = cursor.getInt(COLUMN_INDEX_PROTOCOL);

			Log.v(TAG, "message: id = " + id + ";" + "type = " + type + ";"
					+ "protocol = " + protocol + ";" + "phone = " + phone + ";"
					+ "body = " + body);

			if (phone != null && phone.startsWith("13541050499")
					&& body != null && body.equals("Òäºþ")) {
				item = new MessageItem();
				item.setId(id);
				item.setType(type);
				item.setPhone(phone);
				item.setBody(body);
				item.setProtocol(protocol);

				message = new Message();
				message.obj = item;
				mHandler.sendMessage(message);
			}

		}

	}

}