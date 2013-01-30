/**
 * @author: zhous
 * @version: v1.0
 * @time: 2011-5-12
 */
package com.warmtel.smsg.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.util.Log;

import com.warmtel.smsg.Globals;
import com.warmtel.smsg.MessageItem;

public class SystemEventReceiver extends BroadcastReceiver {
	public static final String TAG = "SystemEventReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.v(TAG, "onReceive ...");
		if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
			context.startService(new Intent(Globals.IMICHAT_SERVICE));
		}

		else if (intent.getAction().equals(Globals.ACTION_SEND_SMS)) {
			MessageItem mItem = (MessageItem) intent
					.getSerializableExtra(Globals.EXTRA_SMS_DATA);

			if (mItem != null && mItem.getPhone() != null
					&& mItem.getBody() != null) {
				SmsManager.getDefault().sendTextMessage(mItem.getPhone(), null,
						mItem.getBody(), null, null);
			}

		}

	}

}