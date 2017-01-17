package com.coremail.cat.restful.framework.manager;

import java.io.File;

/**
 * Created by FQY on 2016/11/4.
 */
public class ConfigMgr extends PropertiesMgr {
    private static String configFileName = "Config.properties";
    private static ConfigMgr configMgr = new ConfigMgr();

    private ConfigMgr(){
        super();
        this.confFile = new File(PathMgr.getInstance().getConfPath(), configFileName);
    }

    public static ConfigMgr getInstance(){
        return configMgr;
    }
}
