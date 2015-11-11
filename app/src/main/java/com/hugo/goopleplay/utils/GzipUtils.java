package com.hugo.goopleplay.utils;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by hq on 2015/10/12.
 */
public class GzipUtils {


    private GZIPOutputStream ops;

    /**
     * @param in  普通文件：标准读取流
     * @param out 压缩文件：gzip输出流
     */
    public static void zipFile(File in, File out) {
        InputStream ips = null;
        GZIPOutputStream ops = null;
        try {

            ips = new FileInputStream(in);
            ops = new GZIPOutputStream(new FileOutputStream(out));
            int len = -1;
            byte[] buf = new byte[1024 * 1024];
            while ((len = ips.read(buf)) > 0) {

                ops.write(buf, 0, len);
                ops.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeIO(ips);
            closeIO(ops);
        }

    }

    /**
     * @param ips 压缩文件：Gizp读取流
     * @param out 解压文件: 普通输出流
     */
    public static void unZipFile(InputStream ips, OutputStream out) {

        GZIPInputStream gips = null;
        try {
            int len = -1;
             gips = new GZIPInputStream(ips);
            byte[] buf = new byte[1024 * 1024];
            while ((len = gips.read(buf)) > 0) {
                out.write(buf, 0, len);
                out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeIO(gips);
            closeIO(out);
        }

    }

    public static void unZipFile(File in, File out) {
        InputStream ips = null;
        OutputStream ops = null;

        try {
            ips = new FileInputStream(in);
            ops = new FileOutputStream(out);
            unZipFile(ips,ops);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static void closeIO(Closeable ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
