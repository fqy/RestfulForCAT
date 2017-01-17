package com.coremail.cat.restful.protester.parser.argument;

import com.coremail.cat.restful.TestsuiteInfo;

/**
 * Created by FQY on 2016/12/5.
 */
public class TcType extends ArgumentParser {
    private String argumentKey = "-type";

    @Override
    public void handle(TestsuiteInfo tsInfo) {
        String type = tsInfo.getTestLevel();
        /**
         * todo
         * 将传过来的类型映射为适应CAT的类型
         */
        if (type != null){
            this.setCommand(this.argumentKey + " " + type);
        }
        this.nextHandle(tsInfo);
    }
}
