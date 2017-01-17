package com.coremail.cat.restful;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * Created by FQY on 2017/1/9.
 */
public class CATLauncherTester extends TestCase{
    @Test
    public void testStartTest(){
        CATLauncher catLauncher = CATLauncher.getInstance();
        TestsuiteInfo testsuiteInfo = new TestsuiteInfo();
        testsuiteInfo.setTaskName("testTask");
        testsuiteInfo.setProjectName("XT5.0.4");
        testsuiteInfo.setServerIP("192.168.201.130");
        List<TestcaseInfo> tcList = new ArrayList<>();
        TestcaseInfo tc1 = new TestcaseInfo();
        tc1.setTcName("com.coremail.testcase.cmxt.web.webmail.logininterface.LoginTest");
        TestcaseInfo tc2 = new TestcaseInfo();
        tc2.setTcName("com.coremail.testcase.cmxt.web.webmail.logoutinterface.LogoutTest");
        TestcaseInfo tc3 = new TestcaseInfo();
        tc3.setTcName("com.coremail.testcase.cmxt.web.webadmin.LoginTest");
        tcList.add(tc1);
        tcList.add(tc2);
        tcList.add(tc3);
        testsuiteInfo.setTcInfoList(tcList);
        if (!catLauncher.isQueueFull()){
            catLauncher.addTask(testsuiteInfo);
            CATLauncher.ServerStatus serverStatus = catLauncher.getStatus();
            if (serverStatus == CATLauncher.ServerStatus.FREE){
                catLauncher.startTest();
            }
        }
    }
}
