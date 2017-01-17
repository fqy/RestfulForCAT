package com.coremail.cat.restful.protester.parser.argument;

import com.coremail.cat.restful.framework.manager.*;
import com.coremail.cat.restful.TestsuiteInfo;
import com.coremail.cat.restful.protester.parser.Parser;

/**
 * Created by FQY on 2016/12/5.
 */
public abstract class ArgumentParser implements Parser {
    protected SettingMgr settingMgr = SettingMgr.getInstance();
    protected PathMgr pathMgr = PathMgr.getInstance();

    protected Parser next;
    /*最后生成的总参数*/
    private static String command = "";

    public void initCommand(){
        command = "";
    }
    public void setCommand(String argument){
        command += " " + argument;
    }
    public String getCommand(){
        return command;
    }

    public void setNextParser(Parser parser){
        this.next = parser;
    }

    public void nextHandle(TestsuiteInfo tsInfo){
        if (this.next != null){
            next.handle(tsInfo);
        }
    }
}
