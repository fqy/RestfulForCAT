package com.coremail.cat.restful.protester.parser.config;

import com.coremail.cat.restful.framework.manager.CATSettingMgr;
import com.coremail.cat.restful.TestsuiteInfo;
import com.coremail.cat.restful.protester.parser.Parser;

/**
 * Created by FQY on 2016/12/5.
 */
public class ConfigParser implements Parser {
    protected CATSettingMgr catSettingMgr = CATSettingMgr.getInstance();

    @Override
    public void handle(TestsuiteInfo tsInfo) {
        String serverIP = tsInfo.getServerIP();
        if (serverIP != null){
            catSettingMgr.setProperty("Address", tsInfo.getServerIP());
        }
    }

    @Override
    public void setNextParser(Parser parser) {

    }
}
