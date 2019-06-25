package com.example.lenovo.homework9.utils;

import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import me.jessyan.progressmanager.ProgressListener;
import me.jessyan.progressmanager.ProgressManager;
import me.jessyan.progressmanager.body.ProgressInfo;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 下载工具类
 */
public class DownloadUtils {

    private static DownloadUtils instance;
    private OkHttpClient okHttpClient;
    private Handler mHandler; //所有监听器在 Handler 中被执行,所以可以保证所有监听器在主线程中被执行

    public static DownloadUtils getInstance() {
        if (instance == null) instance = new DownloadUtils();
        return instance;
    }

    private DownloadUtils() {
        this.mHandler = new Handler(Looper.getMainLooper());
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        okHttpClient = ProgressManager.getInstance().with(builder).build();
    }

    public interface OnDownloadListener{
        /**
         * 下载成功
         */
        void onDownloadSuccess();

        /**
         * @param progress 下载进度
         */
        void onDownloading(ProgressInfo progress);

        /**
         * 下载失败
         */
        void onDownloadFailed();
    }

    /**
     * @param url      下载连接
     * @param saveDir  储存下载文件的SDCard目录
     * @param listener 下载监听
     */
    public void download(final String url, final String saveDir, final String saveName, final OnDownloadListener listener) {
        Request request = new Request.Builder().url(url).build();

        final String path = Environment.getExternalStorageDirectory()+"/"+saveDir+"/"+saveName;

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 下载失败
                listener.onDownloadFailed();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // Okhttp/Retofit 下载监听
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                // 储存下载文件的目录
                try {
                    is = response.body().byteStream();
                    File file = new File(path, saveName);
                    if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
                    fos = new FileOutputStream(file);
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                    }
                    fos.flush();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            // 下载完成
                            listener.onDownloadSuccess();
                        }
                    });

                } catch (Exception e) {
                    Log.e("下载异常", e.getMessage());
                    listener.onDownloadFailed();
                } finally {
                    try {
                        if (is != null) is.close();
                    } catch (IOException e) {
                    }
                    try {
                        if (fos != null) fos.close();
                    } catch (IOException e) {
                    }
                }
            }
        });
        ProgressManager.getInstance().addResponseListener(url, new ProgressListener() {
            @Override
            public void onProgress(ProgressInfo progressInfo) {
                listener.onDownloading(progressInfo);
            }

            @Override
            public void onError(long l, Exception e) {
                listener.onDownloadFailed();
            }
        });
    }

    /**
     * 判断文件是否存在
     * @param name
     * @return
     */
    public static boolean isExistFile(String name){
        File file = new File(Environment.getExternalStorageDirectory(),name);
        return file.exists();
    }

    /**
     * @param url
     * @return
     * 从下载连接中解析出文件名
     */
    public static String getNameFromUrl(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }

    /**
     * 获取网络文件的本地路径
     * @return
     */
    public static String getFileByUrl(String dir,String url){
        return Environment.getExternalStorageDirectory()+"/"+dir+"/"+getNameFromUrl(url);
    }

}
