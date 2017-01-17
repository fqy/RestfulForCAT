package com.coremail.cat.restful.framework.manager;

/**
 * Created by FQY on 2016/11/8.
 */
public class TestcaseJarMgr {
    private static final TestcaseJarMgr tcJarMgr = new TestcaseJarMgr();

    private TestcaseJarMgr(){}

    public static TestcaseJarMgr getInstance(){
        return tcJarMgr;
    }

    public String getTcJarName(String version){
        String tcJarName = SettingMgr.getInstance().getProperty(version);
        return tcJarName;
    }
}
