package com.coremail.cat.restful.protester.parser;

import com.coremail.cat.restful.TestsuiteInfo;

/**
 * Created by FQY on 2016/12/5.
 */
public interface Parser {
    void handle(TestsuiteInfo tsInfo);
    void setNextParser(Parser parser);
}
