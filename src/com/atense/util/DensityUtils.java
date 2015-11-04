package com.atense.util;

import android.content.Context;

/**
 * DensityUtil
 * <p>提供px和dp之间相互转换的方法</p>
 * 
 * @author <a href="#" target="_black">Kyle</a> 2015-11-04
 */
public class DensityUtils {
	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 * @param context
	 * @param dp
	 * @return
	 */
	public static float dpToPx(Context context, float dp) {
        if (context == null) {
            return -1;
        }
        return dp * context.getResources().getDisplayMetrics().density;
    }
	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 * @param context
	 * @param px
	 * @return
	 */
    public static float pxToDp(Context context, float px) {
        if (context == null) {
            return -1;
        }
        return px / context.getResources().getDisplayMetrics().density;
    }

    public static float dpToPxInt(Context context, float dp) {
        return (int)(dpToPx(context, dp) + 0.5f);
    }

    public static float pxToDpInt(Context context, float px) {
        return (int)(pxToDp(context, px) + 0.5f);
    }

	/**
	 * 将px值转换为sp值，保证文字大小不变
	 * 
	 * @param pxValue
	 * @return
	 */
	public static float pxToSp(Context context, float pxValue) {
		if (context == null) {
            return -1;
        }
		return context.getResources().getDisplayMetrics().scaledDensity;
	}

	/**
	 * 将sp值转换为px值，保证文字大小不变
	 * 
	 * @param spValue
	 * @return
	 */
	public static float spToPx(Context context, float spValue) {
		if (context == null) {
            return -1;
        }
		return context.getResources().getDisplayMetrics().scaledDensity;
	}
	
	/**
	 * 将px值转换为sp值，保证文字大小不变
	 * 
	 * @param pxValue
	 * @return
	 */
	public static int pxToSpInt(Context context, float pxValue) {
		return (int) (pxToSp(context, pxValue) + 0.5f);
	}

	/**
	 * 将sp值转换为px值，保证文字大小不变
	 * 
	 * @param spValue
	 * @return
	 */
	public static int spToPxInt(Context context, float spValue) {
		return (int) (spToPx(context, spValue) + 0.5f);
	}
}
