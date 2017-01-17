package com.coremail.cat.restful;

import com.coremail.cat.restful.framework.manager.TestsuiteFileMgr;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FQY on 2016/11/3.
 */
public class TestsuiteFileMgrTester extends TestCase {
    @Test
    public void testCreateTsFile(){
        TestsuiteFileMgr tsFileMgr = TestsuiteFileMgr.getInstance();
        List<String> tcName = new ArrayList<>();
        tcName.add("com.coremail.testcase.cmxt.web.webmail.logininterface.LoginTest");
        tcName.add("com.coremail.testcase.cmxt.web.webmail.logoutinterface.LogoutTest");
        tcName.add("com.coremail.testcase.cmxt.web.webadmin.LoginTest");
        tcName.add("com.coremail.testcase.cmxt.web.webadmin.LogoutTest");
        String tcPkgName = "cmtest.jar";
        String filePath = "D:\\FQY\\Auto\\CoremailAutoTest";
        String fileName = "test";
        try {
            tsFileMgr.createTsFile(tcName, tcPkgName, filePath, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
