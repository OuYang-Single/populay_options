package com.pine.populay_options.mvp.model.mvp.ui.Service;

import android.content.Context;
import android.util.Log;

import java.io.*;

public class FileUtils {
    public void writeFile(Context mContext,long start,long end,  InputStream byteStream,File futureStudioIconFile){
        try {
            mContext.openFileInput(futureStudioIconFile.getPath());
            //mContext.openFileOutput()
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void writeFile(long start, long end, InputStream byteStream, File futureStudioIconFile) {
        try {

            //初始化输出流
            OutputStream outputStream = null;

            try {
                //设置每次读写的字节
                byte[] fileReader = new byte[1024*1024];
                long fileSizeDownloaded = start;
                int  fileSizeDownloadeds = 0;
                //创建输出流
                outputStream = new FileOutputStream(futureStudioIconFile);
                //进行读取操作
                // BufferedWriter bw = new BufferedWriter(body.byteStream());

                while((fileSizeDownloadeds = byteStream.read(fileReader))!=-1){
                    if (fileSizeDownloadeds>=start&&fileSizeDownloadeds<=end){
                        outputStream.write(fileReader, 0, fileSizeDownloadeds);
                    }

                }


                //刷新
                outputStream.flush();

            } catch (IOException e) {
                Log.w("", "");

            } finally {

                if (outputStream != null) {
                    //关闭输出流
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            Log.w("", "");

        }
    }
}
