package com.atense.util;

import android.content.Context;
import android.widget.Toast;

/**
 * ToastUtil
 * 
 * @author <a href="#" target="_black">Kyle</a> 2015-11-04
 */
public class ToastUtils {
	
	private static Toast mToast;
	
    public static void show(Context context, CharSequence text, int duration) {
    	if (null == mToast) {
			mToast = Toast.makeText(context,
					text, Toast.LENGTH_LONG);
		} else {
			mToast.setDuration(Toast.LENGTH_LONG);
			mToast.setText(text);
		}
		mToast.show();
    }
	
    public static void show(Context context, int resId) {
        show(context, context.getResources().getText(resId), Toast.LENGTH_SHORT);
    }

    public static void show(Context context, int resId, int duration) {
        show(context, context.getResources().getText(resId), duration);
    }

    public static void show(Context context, CharSequence text) {
        show(context, text, Toast.LENGTH_SHORT);
    }

    public static void show(Context context, int resId, Object... args) {
        show(context, String.format(context.getResources().getString(resId), args), Toast.LENGTH_SHORT);
    }

    public static void show(Context context, String format, Object... args) {
        show(context, String.format(format, args), Toast.LENGTH_SHORT);
    }

    public static void show(Context context, int resId, int duration, Object... args) {
        show(context, String.format(context.getResources().getString(resId), args), duration);
    }

    public static void show(Context context, String format, int duration, Object... args) {
        show(context, String.format(format, args), duration);
    }

}
