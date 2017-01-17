package com.coremail.cat.restful.framework.manager;

import java.io.*;
import java.util.Date;
import java.util.List;

/**
 * Created by FQY on 2016/11/3.
 */
public class TestsuiteFileMgr {
    private static final TestsuiteFileMgr tsFileMgr = new TestsuiteFileMgr();
    private String suffix = ".ts";

    private TestsuiteFileMgr(){}

    public static TestsuiteFileMgr getInstance(){
        return tsFileMgr;
    }

    public void createTsFile(List<String> tcNameList, String tcPkgName, String filePath, String fileName) throws Exception {
        fileName = this.addSuffix(fileName);
        File file = FileMgr.getInstance().createFile(filePath, fileName);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
        writer.write("#Last Modified On " + new Date());
        writer.newLine();
        writer.write("pkgName=" + tcPkgName);
        writer.newLine();
        for (int i = 0; i < tcNameList.size(); i++){
            writer.write(i + "=" + tcNameList.get(i));
            writer.newLine();
        }
        writer.close();
    }

    public String addSuffix(String fileName){
        if (!fileName.endsWith(this.suffix)){
            fileName += this.suffix;
        }
        return fileName;
    }
}
