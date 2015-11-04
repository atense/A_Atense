package com.atense.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * http请求工具类
 * 
 * @author <a href="#" target="_black">Kyle</a> 2015-11-04
 * 
 */
public class HttpUtils {
	private static final String TAG = "HttpUtils";

	public static HttpClient createHttpClient() {
		// 创建 HttpParams 以用来设置 HTTP 参数
		HttpParams httpParams = new BasicHttpParams();

		// 设置连接超时和 Socket 超时，以及 Socket 缓存大小
		HttpConnectionParams.setConnectionTimeout(httpParams, 20 * 1000);
		HttpConnectionParams.setSoTimeout(httpParams, 20 * 1000);
		HttpConnectionParams.setSocketBufferSize(httpParams, 8192);

		HttpProtocolParams.setUserAgent(httpParams,
				System.getProperty("http.agent"));

		HttpClient httpClient = new DefaultHttpClient(httpParams);

		return httpClient;
	}

	/**
	 * 下载图片资源文件
	 * 
	 * @param webUrl
	 *            网络路径
	 * @param localUrl
	 *            本地路径
	 * @param rerresh
	 *            0 不会覆盖本地图片 1-覆盖本地图片
	 */
	public static void getImageFile(String webUrl, String localPath,
			String refresh) {
		Log.e(TAG, "webUrl------" + webUrl);
		Log.e(TAG, "localPath------" + localPath);
		if (StringUtils.isBlank(webUrl)) {
			return;
		}

		if (!webUrl.equals("") && !webUrl.equals("null")) {
			File file = new File(localPath);// 保存文件
			File file1 = new File(localPath.substring(0,
					localPath.lastIndexOf("/") + 1));
			if (!file.exists() || file.length() == 0L || refresh.equals("1")) {
				if (!file1.exists()) {
					FileUtils.makeDirs(localPath.substring(0,
							localPath.lastIndexOf("/") + 1));
				}
				try {
					FileOutputStream fos = new FileOutputStream(file);
					InputStream is = new URL(webUrl).openStream();
					int data = is.read();
					while (data != -1) {
						fos.write(data);
						data = is.read();
					}
					fos.close();
					is.close();
					Log.e("webUrl", webUrl
							+ "########下载图片资源文件 end..............");
				} catch (IOException e) {
					Log.e(TAG, e.toString() + "下载及保存时出现异常！");
					file.delete();
				}
			}
		}

	}

	/**
	 * POST上传表单
	 * */
	public static String postTableData(String url, List<NameValuePair> params,
			String str_agent) throws ConnectTimeoutException,
			ClientProtocolException, IOException, XmlPullParserException {
		String result = null;
		Log.e(TAG, "POST:" + url);
		HttpClient httpclient = createHttpClient();// 创建一个默认的HttpClient
		HttpPost httppost = new HttpPost(url);// 创建一个POST请求
		httppost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));// 添加请求参数到请求对象
		httppost.setHeader("User-Agent", str_agent);
		HttpResponse response = httpclient.execute(httppost);
		HttpEntity resEntity = response.getEntity();
		if (resEntity != null) {
			result = EntityUtils.toString(resEntity);
			Log.e(TAG, "POST.RESULT:" + result);
		}
		httpclient.getConnectionManager().shutdown();
		return result;
	}

	/**
	 * Get请求数据
	 * */
	public static String getListData(String url, List<NameValuePair> params,
			String str_agent) throws ConnectTimeoutException,
			ClientProtocolException, IOException, XmlPullParserException {
		// 构建url
		if (params != null) {
			StringBuffer buf = new StringBuffer();
			for (int i = 0; i < params.size(); i++) {
				buf.append("&").append(params.get(i).getName()).append("=")
						.append(params.get(i).getValue());
			}
			if (url.indexOf("?") != -1)// 已经有参数
			{
				url = url + buf.toString();
			} else {
				url = url + "?" + buf.toString();
			}
		}
		String result = null;
		Log.e(TAG, "GET:" + url);
		HttpClient httpclient = createHttpClient();// 创建一个默认的HttpClient
		HttpGet httpGet = new HttpGet(url);// 创建一个Get请求
		httpGet.setHeader("User-Agent", str_agent);
		HttpResponse response = httpclient.execute(httpGet);
		HttpEntity resEntity = response.getEntity();
		if (resEntity != null) {
			result = EntityUtils.toString(resEntity);
			Log.e(TAG, "GET.RESULT:" + result);
		}
		httpclient.getConnectionManager().shutdown();
		return result;
	}

	/**
	 * Get请求数据
	 * */
	public static String getListWeatherData(String url,
			List<NameValuePair> params) throws ConnectTimeoutException,
			ClientProtocolException, IOException, XmlPullParserException {
		// 构建url
		if (params != null) {
			StringBuffer buf = new StringBuffer();
			for (int i = 0; i < params.size(); i++) {
				buf.append("&").append(params.get(i).getName()).append("=")
						.append(params.get(i).getValue());
			}
			if (url.indexOf("?") != -1)// 已经有参数
			{
				url = url + buf.toString();
			} else {
				url = url + "?" + buf.toString();
			}
		}
		String result = null;
		Log.e(TAG, "GET:" + url);
		HttpClient httpclient = createHttpClient();// 创建一个默认的HttpClient
		HttpGet httpGet = new HttpGet(url);// 创建一个Get请求
		HttpResponse response = httpclient.execute(httpGet);
		HttpEntity resEntity = response.getEntity();
		if (resEntity != null) {
			result = EntityUtils.toString(resEntity);
			Log.e(TAG, "GET.RESULT:" + result);
		}
		httpclient.getConnectionManager().shutdown();
		return result;
	}

	/**
	 * 网络是否可用
	 * 
	 * @param context
	 * @return
	 */
	public boolean isNetworkAvailable(Context context) {
		ConnectivityManager mgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo[] info = mgr.getAllNetworkInfo();
		if (info != null) {
			for (int i = 0; i < info.length; i++) {
				if (info[i].getState() == NetworkInfo.State.CONNECTED) {
					return true;
				}
			}
		}
		return false;
	}
}
