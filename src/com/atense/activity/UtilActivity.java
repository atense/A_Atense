package com.atense.activity;

import java.io.UnsupportedEncodingException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.atense.R;
import com.atense.utils.DigestUtil;
import com.atense.utils.ToastUtil;
import com.atense.utils.WindowUtil;

public class UtilActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.util);
	}

	public void digest(View v) {
		String s = "中国人但是";
//		String hex;
//		try {
//			hex = DigestUtil.bytes2Hex(s.getBytes("utf-8"));
//			String lo = new String(DigestUtil.hex2Bytes(hex));
//			String log = s + "\n\r" + hex + "\n\r" + lo;
//			Log.e(this.getClass().getSimpleName(), log);
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		
//		String AES_PWD = "OfCsePVPmbdslnu4";
//		String hex = DigestUtil.AESEncrypt(s, AES_PWD);
//		String lo = DigestUtil.AESDecrypt(hex, AES_PWD);
//		String log = s + "\n\r" + hex + "\n\r" + lo;
//		Log.e(this.getClass().getSimpleName(), log);
				
//		new Thread(new Runnable(){
//			@Override
//			public void run() {
//				String s = "中国人但是";
//				try {
//					StringBuffer sb = new StringBuffer();
//					for(int i=0; i<100000; i++) {
//						sb.append(s);
//						if(i % 10000 == 0) {
//							byte[] ss = sb.toString().getBytes("utf-8");
//							long start = System.currentTimeMillis();
//							String s1 = DigestUtil.byte2hex(ss);
//							Log.e(this.getClass().getSimpleName() + "-byte2hex-" + i, (System.currentTimeMillis()-start) + "" );
//							start = System.currentTimeMillis();
//							String s2 = DigestUtil.bytes2Hex(ss);
//							Log.e(this.getClass().getSimpleName() + "-encodeHex-" + i, (System.currentTimeMillis()-start) + "" );
//						}
//					}
////					byte[] ss = s.getBytes("utf-8");
////					String s1 = DigestUtil.byte2hex(ss);
////					char[] cs = DigestUtil.encodeHex(ss);
////					String s2 = new String(cs, 0, cs.length);
////					Log.e(this.getClass().getSimpleName(), s1 + "\n\r" + s2);
//					//ToastUtil.show(UtilActivity.this, s1 + "\n\r" + s2);
//				} catch (UnsupportedEncodingException e) {
//					e.printStackTrace();
//				}
//			}
//		}).start();
		
	}
}
