package com.coremail.cat.restful.protester.parser.argument;

import com.coremail.cat.restful.TestsuiteInfo;

/**
 * Created by FQY on 2016/12/5.
 */
public class ModuleOne extends ArgumentParser {
    private String argumentKey = "-module";

    @Override
    public void handle(TestsuiteInfo tsInfo) {
        String moduleOne = tsInfo.getModuleOne();
        if (moduleOne != null){
            this.setCommand(this.argumentKey + " " + moduleOne);
        }
        this.nextHandle(tsInfo);
    }
}
