package com.coremail.cat.restful.framework.manager;

import java.io.File;

/**
 * Created by FQY on 2016/11/4.
 */
public class SettingMgr extends PropertiesMgr {
    private static String settingFileName = "Setting.properties";
    private static final SettingMgr settingMgr = new SettingMgr();

    private SettingMgr(){
        super();
        this.confFile = new File(PathMgr.getInstance().getConfPath(), this.settingFileName);
    }

    public static SettingMgr getInstance(){
        return settingMgr;
    }
}
