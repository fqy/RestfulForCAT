package com.coremail.cat.restful.protester.parser;

import com.coremail.cat.restful.TestsuiteInfo;

/**
 * Created by FQY on 2016/12/5.
 */
public class End implements Parser {
    @Override
    public void handle(TestsuiteInfo tsInfo) {
        return;
    }

    @Override
    public void setNextParser(Parser parser) {

    }
}
