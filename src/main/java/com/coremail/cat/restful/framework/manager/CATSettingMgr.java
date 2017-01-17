package com.coremail.cat.restful.framework.manager;

import java.io.File;

/**
 * Created by FQY on 2016/11/9.
 */
public class CATSettingMgr extends PropertiesMgr {
    private static CATSettingMgr catSettingMgr = new CATSettingMgr();

    private CATSettingMgr(){
        super();
        this.confFile = new File(SettingMgr.getInstance().getProperty("CATSettingPath"));
    }

    public static CATSettingMgr getInstance(){
        return catSettingMgr;
    }
}
