package com.coremail.cat.restful.protester.parser.argument;

import com.coremail.cat.restful.TestsuiteInfo;

/**
 * Created by FQY on 2016/12/5.
 */
public class Priority extends ArgumentParser {
    private String argumentKey = "-priority";

    @Override
    public void handle(TestsuiteInfo tsInfo) {
        String priority = tsInfo.getPriorities();
        /**
         * todo
         * 将传过来的优先级映射为适应CAT的优先级
         */
        if (priority != null){
            this.setCommand(this.argumentKey + " " + priority);
        }
        this.nextHandle(tsInfo);
    }
}
