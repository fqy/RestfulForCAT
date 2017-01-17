package com.coremail.cat.restful.framework.manager;

import java.io.File;
import java.net.URISyntaxException;

/**
 * Created by FQY on 2016/11/4.
 */
public class PathMgr {
    private static final PathMgr pathMgr = new PathMgr();
    private String confPath = "\\resources\\conf";
    private String testcasePath = "\\resources\\testcase";
    private String batPath = "\\resources\\bat";

    private PathMgr(){}

    public static PathMgr getInstance(){
        return pathMgr;
    }

    public String getConfPath(){
        return System.getProperty("user.dir") + this.confPath;
    }

    public String getTestcasePath(){
        return System.getProperty("user.dir") + this.testcasePath;
    }

    public String getBatPath(){
        return System.getProperty("user.dir") + this.batPath;
    }
}
