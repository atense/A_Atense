package com.atense.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;

/**
 * ImageUtils
 * <ul>
 * convert between Bitmap, byte array, Drawable
 * <li>{@link #bitmapToByte(Bitmap)}</li>
 * <li>{@link #bitmapToDrawable(Bitmap)}</li>
 * <li>{@link #byteToBitmap(byte[])}</li>
 * <li>{@link #byteToDrawable(byte[])}</li>
 * <li>{@link #drawableToBitmap(Drawable)}</li>
 * <li>{@link #drawableToByte(Drawable)}</li>
 * </ul>
 * <ul>
 * get image
 * <li>{@link #getInputStreamFromUrl(String, int)}</li>
 * <li>{@link #getBitmapFromUrl(String, int)}</li>
 * <li>{@link #getDrawableFromUrl(String, int)}</li>
 * </ul>
 * <ul>
 * scale image
 * <li>{@link #scaleImageTo(Bitmap, int, int)}</li>
 * <li>{@link #scaleImage(Bitmap, float, float)}</li>
 * </ul>
 * 
 * @author <a href="#" target="_blank">Kyle</a> 2015-11-04
 */
public class ImageUtil {
	/**
     * convert Bitmap to byte array
     * 
     * @param b
     * @return
     */
    public static byte[] bitmapToByte(Bitmap b) {
        if (b == null) {
            return null;
        }

        ByteArrayOutputStream o = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, o);
        return o.toByteArray();
    }

    /**
     * convert byte array to Bitmap
     * 
     * @param b
     * @return
     */
    public static Bitmap byteToBitmap(byte[] b) {
        return (b == null || b.length == 0) ? null : BitmapFactory.decodeByteArray(b, 0, b.length);
    }

    /**
     * convert Drawable to Bitmap
     * 
     * @param d
     * @return
     */
    public static Bitmap drawableToBitmap(Drawable d) {
        return d == null ? null : ((BitmapDrawable)d).getBitmap();
    }

    /**
     * convert Bitmap to Drawable
     * 
     * @param b
     * @return
     */
    @SuppressWarnings("deprecation")
	public static Drawable bitmapToDrawable(Bitmap b) {
        return b == null ? null : new BitmapDrawable(b);
    }

    /**
     * convert Drawable to byte array
     * 
     * @param d
     * @return
     */
    public static byte[] drawableToByte(Drawable d) {
        return bitmapToByte(drawableToBitmap(d));
    }

    /**
     * convert byte array to Drawable
     * 
     * @param b
     * @return
     */
    public static Drawable byteToDrawable(byte[] b) {
        return bitmapToDrawable(byteToBitmap(b));
    }

    /**
     * get input stream from network by imageurl, you need to close inputStream yourself
     * 
     * @param imageUrl
     * @param readTimeOutMillis
     * @return
     * @see ImageUtils#getInputStreamFromUrl(String, int, boolean)
     */
    public static InputStream getInputStreamFromUrl(String imageUrl, int readTimeOutMillis) {
        return getInputStreamFromUrl(imageUrl, readTimeOutMillis, null);
    }

    /**
     * get input stream from network by imageurl, you need to close inputStream yourself
     * 
     * @param imageUrl
     * @param readTimeOutMillis read time out, if less than 0, not set, in mills
     * @param requestProperties http request properties
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public static InputStream getInputStreamFromUrl(String imageUrl, int readTimeOutMillis,
            Map<String, String> requestProperties) {
        InputStream stream = null;
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            // HttpUtils.setURLConnection(requestProperties, con);
            if (readTimeOutMillis > 0) {
                con.setReadTimeout(readTimeOutMillis);
            }
            stream = con.getInputStream();
        } catch (MalformedURLException e) {
            closeInputStream(stream);
            throw new RuntimeException("MalformedURLException occurred. ", e);
        } catch (IOException e) {
            closeInputStream(stream);
            throw new RuntimeException("IOException occurred. ", e);
        }
        return stream;
    }

    /**
     * get drawable by imageUrl
     * 
     * @param imageUrl
     * @param readTimeOutMillis
     * @return
     * @see ImageUtils#getDrawableFromUrl(String, int, boolean)
     */
    public static Drawable getDrawableFromUrl(String imageUrl, int readTimeOutMillis) {
        return getDrawableFromUrl(imageUrl, readTimeOutMillis, null);
    }

    /**
     * get drawable by imageUrl
     * 
     * @param imageUrl
     * @param readTimeOutMillis read time out, if less than 0, not set, in mills
     * @param requestProperties http request properties
     * @return
     */
    public static Drawable getDrawableFromUrl(String imageUrl, int readTimeOutMillis,
            Map<String, String> requestProperties) {
        InputStream stream = getInputStreamFromUrl(imageUrl, readTimeOutMillis, requestProperties);
        Drawable d = Drawable.createFromStream(stream, "src");
        closeInputStream(stream);
        return d;
    }

    /**
     * get Bitmap by imageUrl
     * 
     * @param imageUrl
     * @param readTimeOut
     * @return
     * @see ImageUtils#getBitmapFromUrl(String, int, boolean)
     */
    public static Bitmap getBitmapFromUrl(String imageUrl, int readTimeOut) {
        return getBitmapFromUrl(imageUrl, readTimeOut, null);
    }

    /**
     * get Bitmap by imageUrl
     * 
     * @param imageUrl
     * @param requestProperties http request properties
     * @return
     */
    public static Bitmap getBitmapFromUrl(String imageUrl, int readTimeOut, Map<String, String> requestProperties) {
        InputStream stream = getInputStreamFromUrl(imageUrl, readTimeOut, requestProperties);
        Bitmap b = BitmapFactory.decodeStream(stream);
        closeInputStream(stream);
        return b;
    }

    /**
     * scale image
     * 
     * @param org
     * @param newWidth
     * @param newHeight
     * @return
     */
    public static Bitmap scaleImageTo(Bitmap org, int newWidth, int newHeight) {
        return scaleImage(org, (float)newWidth / org.getWidth(), (float)newHeight / org.getHeight());
    }

    /**
     * scale image
     * 
     * @param org
     * @param scaleWidth sacle of width
     * @param scaleHeight scale of height
     * @return
     */
    public static Bitmap scaleImage(Bitmap org, float scaleWidth, float scaleHeight) {
        if (org == null) {
            return null;
        }

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        return Bitmap.createBitmap(org, 0, 0, org.getWidth(), org.getHeight(), matrix, true);
    }

    /**
     * close inputStream
     * 
     * @param s
     */
    private static void closeInputStream(InputStream s) {
        if (s == null) {
            return;
        }

        try {
            s.close();
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred. ", e);
        }
    }
    
    /**
	 * 质量压缩
	 * 
	 * @param imgPath
	 * @return
	 */
	public static String compressImage(String imgPath) {
		Bitmap bitmap = getBitmap(imgPath);
		if (bitmap == null) {
			return "";
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 60, baos);
		int options = 50;
		while (baos.toByteArray().length > 5 * 100 * 1024) { // 循环判断如果压缩后图片是否大于0.5M,大于继续压缩
			baos.reset();// 重置baos即清空baos
			bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
			options -= 10;// 每次都减少10
		}
		byte[] appicon = baos.toByteArray();// 转为byte数组
		// 释放原始图片占用的内存，防止out of memory异常发生
		if (bitmap != null && !bitmap.isRecycled()) {
			// 回收并且置为null
			bitmap.recycle();
			bitmap = null;
		}
		return Base64.encodeToString(appicon, Base64.DEFAULT);
	}

	/**
	 * 获取照片
	 * 
	 * @param imgPath
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static Bitmap getBitmap(String imgPath) {
		// Get bitmap through image path
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		newOpts.inJustDecodeBounds = false;
		newOpts.inPurgeable = true;
		newOpts.inInputShareable = true;
		// Do not compress
		newOpts.inSampleSize = 1;
		newOpts.inPreferredConfig = Config.RGB_565;
		return BitmapFactory.decodeFile(imgPath, newOpts);
	}
	
	/**
	 * 获得圆角图片
	 * @param bitmap
	 * @param roundPx
	 * @return
	 */
	public  static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx) {
		 int w = bitmap.getWidth();
		 int h = bitmap.getHeight();
		 Bitmap output = Bitmap.createBitmap(w, h, Config.ARGB_8888);
		 Canvas canvas = new Canvas(output);
		 final int color = 0xff424242;
		 final Paint paint = new Paint();
		 final Rect rect = new Rect(0, 0, w, h);
		 final RectF rectF = new RectF(rect);
		 paint.setAntiAlias(true);
		 canvas.drawARGB(0, 0, 0, 0);
		 paint.setColor(color);
		 canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		 paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		 canvas.drawBitmap(bitmap, rect, rect, paint);
		 return output;
	 }
	
	/**
	 * 对分辨率的的图片进行缩放
	 * @param bitmap
	 * @param width
	 * @param height
	 * @return
	 */
	public static Bitmap zoomBitmap(Bitmap bitmap, float width, float height) {
		Bitmap newbmp =null;
		if (bitmap != null){
			int w = bitmap.getWidth();
			int h = bitmap.getHeight();
			Matrix matrix = new Matrix();
			float scaleWidth = ((float) width / w);
			float scaleHeight = ((float) height / h);
			matrix.postScale(scaleWidth, scaleHeight);
			newbmp = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, false);
		}
	
		return newbmp;
	}
	
	/**
	 * 利用BASE64Encoder对图片进行base64转码将图片转为string
	 * @param  
	 * @return 
	 */
	public static String f_imageToString(String imgFile) {
		 int DEFAULT = 0;
		//  将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		InputStream in = null;
		byte[] data = null;
		//  读取图片字节数组
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (OutOfMemoryError  e) {
			e.printStackTrace();
		} 
		//  返回Base64编码过的字节数组字符串
		String str="";
		if (data != null){
			str= new String(Base64.encode(data, DEFAULT));
		}
		return str;
	}
}
