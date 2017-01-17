package com.coremail.cat.restful;

import com.coremail.cat.restful.framework.manager.ConfigMgr;
import com.coremail.cat.restful.framework.manager.SettingMgr;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by FQY on 2016/11/8.
 */
public class PropertiesMgrTester extends TestCase {
    @Test
    public void testGetproperty(){
        SettingMgr settingMgr = SettingMgr.getInstance();
        System.out.println(settingMgr.getProperty("CATPath"));
        System.out.println(settingMgr.getProperty("TestsuiteFileName"));
        System.out.println(settingMgr.getProperty("TestcaseJarName"));
    }

    @Test
    public void testSetProperty(){
        ConfigMgr configMgr = ConfigMgr.getInstance();
        configMgr.setProperty("Address", "192.168.201.104");
        System.out.println(configMgr.getProperty("Address"));
    }
}
