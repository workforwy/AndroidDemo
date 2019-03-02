package com.ceshi.ha.update;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by WY on 2017/12/23 0023.
 */

public class FileUtils {

    private String SD_PATH;

    public String getSD_PATH() {
        return SD_PATH;
    }

    public FileUtils() {
        //得到当前外部存储设备的目录
        SD_PATH = Environment.getExternalStorageDirectory() + "/";
    }

    public File createSDFile(String fileName) throws IOException {
        File file = new File(SD_PATH + fileName);
        file.createNewFile();
        return file;
    }

    public File createSDDir(String dirName) {
        File dir = new File(SD_PATH + dirName);
        dir.mkdir();
        return dir;
    }

    public boolean isFileExist(String fileName) {
        File file = new File(SD_PATH + fileName);
        return file.exists();
    }

    public File write2SDFromInput(String path, String fileName, InputStream input) {
        File file = null;
        OutputStream output = null;
        try {
            createSDDir(path);
            file = createSDFile(path + fileName);
            output = new FileOutputStream(file);
            byte buffer[] = new byte[4 * 1024];
            while (input.read(buffer) != -1) {
                output.write(buffer);
            }
            //清掉缓存
            output.flush();
        } catch (Exception e) {
            Log.e("write2SDFromInput", e.getMessage());
        } finally {
            try {
                if (output != null) {
                    output.close();
                }
            } catch (IOException ioe) {
                Log.e("write2SDFromInput", ioe.getMessage());
            }
        }
        return file;
    }
}
