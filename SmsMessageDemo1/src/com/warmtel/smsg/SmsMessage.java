package com.warmtel.smsg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
/**
 * �յ�ָ���ֻ��Ŷ��ţ��Զ�ɾ��
 * @author Administrator
 *
 */
public class SmsMessage extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        startService(new Intent(Globals.IMICHAT_SERVICE));
    }
}