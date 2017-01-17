package com.coremail.cat.restful.framework.manager;

import java.io.File;
import java.io.IOException;

/**
 * Created by FQY on 2016/11/8.
 */
public class FileMgr {
    private static FileMgr ourInstance = new FileMgr();

    public static FileMgr getInstance() {
        return ourInstance;
    }

    private FileMgr() {
    }

    public File createFile(String filePath, String fileName) throws IOException {
        File path = new File(filePath);
        File file = new File(path, fileName);
        if (!path.exists()){
            path.mkdirs();
        }
        if (file.exists()){
            file.delete();
        }
        file.createNewFile();
        return file;
    }
}
