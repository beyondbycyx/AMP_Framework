package com.hugo.common.utils;

import java.io.Closeable;
import java.io.IOException;

public class IOUtils {
	/** 关闭流 */
	public static boolean close(Closeable io) {
		if (io != null) {
			try {
				io.close();
			} catch (IOException e) {
				LogUtils.e(e);
			}
		}
		return true;
	}

	public static boolean close(Closeable ... ios) {
		for(Closeable io : ios){
			close(io);
		}
		return true;
	}
}
