package com.atense.utils;

import java.io.File;

import android.os.Environment;
import android.os.StatFs;

/**
 * Storage Util
 * 
 * @author <a href="#" target="_blank">Kyle</a> 2015-11-04
 * 
 */
public class StorageUtil {
	
	/**
	 * 计算sdcard上的剩余空间
	 */
	@SuppressWarnings("deprecation")
	public int freeSpaceOnSd() {
		StatFs stat = new StatFs(Environment.getExternalStorageDirectory()
				.getPath());
		double sdFreeMB = ((double) stat.getAvailableBlocks() * (double) stat
				.getBlockSize()) / (1024 * 1024);
		return (int) sdFreeMB;
	}

	/**
	 * 外部存储是否可用 /SD卡是否存在
	 * 
	 * @return
	 */
	static public boolean externalMemoryAvailable() {
		return android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);
	}

	/**
	 * 获取手机内部可用空间大小
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	static public long getAvailableInternalMemorySize() {
		File path = Environment.getDataDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long availableBlocks = stat.getAvailableBlocks();
		return availableBlocks * blockSize;
	}

	/**
	 * 获取手机外部可用空间大小
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	static public long getAvailableExternalMemorySize() {
		if (externalMemoryAvailable()) {
			// 前提外部存储可用 externalMemoryAvailable()
			File path = Environment.getExternalStorageDirectory();
			StatFs stat = new StatFs(path.getPath());
			long blockSize = stat.getBlockSize();
			long availableBlocks = stat.getAvailableBlocks();
			return availableBlocks * blockSize;
		} else {
			return 0;
		}
	}

	// 计算可用空间
	static public String formatSize(long size) {
		String suffix = null;

		if (size >= 1024) {
			suffix = "KiB";
			size /= 1024;
			if (size >= 1024) {
				suffix = "MiB";
				size /= 1024;
			}
		}

		StringBuilder resultBuffer = new StringBuilder(Long.toString(size));
		// int commaOffset = resultBuffer.length() - 3;
		// while (commaOffset > 0) {
		// resultBuffer.insert(commaOffset, ',');
		// commaOffset -= 3;
		// }

		if (suffix != null) {
			resultBuffer.append(suffix);
		}
		return resultBuffer.toString();
	}
}
