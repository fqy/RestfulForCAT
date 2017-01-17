package com.coremail.cat.restful.protester.parser;

import com.coremail.cat.restful.TestsuiteInfo;
import com.coremail.cat.restful.protester.parser.argument.*;
import com.coremail.cat.restful.protester.parser.config.ConfigParser;

/**
 * Created by FQY on 2016/12/5.
 */
public class ParserEntry {
    private String command;

    public String getCommand(){
        return this.command;
    }

    public void startParser(TestsuiteInfo tsInfo){
        /**
         * 进行命令行参数相关解析
         */
        ArgumentParser testsuite = new Testsuite();
        ArgumentParser moduleOne = new ModuleOne();
        ArgumentParser priority = new Priority();
        ArgumentParser tcType = new TcType();
        Parser end = new End();

        /*构造责任链*/
        testsuite.setNextParser(moduleOne);
        moduleOne.setNextParser(priority);
        priority.setNextParser(tcType);
        tcType.setNextParser(end);

        testsuite.initCommand();
        testsuite.handle(tsInfo);
        this.command = testsuite.getCommand();

        /**
         * 进行配置更改相关解析
         */
        Parser configParser = new ConfigParser();
        configParser.handle(tsInfo);
    }
}
