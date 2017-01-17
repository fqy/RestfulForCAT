package com.coremail.cat.restful.protester.parser.argument;

import com.coremail.cat.restful.framework.manager.TestcaseJarMgr;
import com.coremail.cat.restful.framework.manager.TestsuiteFileMgr;
import com.coremail.cat.restful.TestsuiteInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FQY on 2016/12/5.
 */
public class Testsuite extends ArgumentParser {
    private String[] argumentKey = new String[]{"-jar", "-testsuite"};

    @Override
    public void handle(TestsuiteInfo tsInfo) {
        TestcaseJarMgr tcJarMgr = TestcaseJarMgr.getInstance();
        TestsuiteFileMgr tsFileMgr = TestsuiteFileMgr.getInstance();
        String version = tsInfo.getProjectName();
        String tcJarName = tcJarMgr.getTcJarName(version);
        String tcPath = pathMgr.getTestcasePath();
        List<String> tcNameList = new ArrayList<>();
        for (int i = 0; i < tsInfo.getTcInfoList().size(); i++){
            tcNameList.add(tsInfo.getTcInfoList().get(i).getTcName());
        }
        /**
         * 无指定运行用例，则按整个用例包进行测试
         */
        if (tcNameList.size() <= 0){
            this.setCommand(this.argumentKey[0] + " " + tcPath + "\\" + tcJarName);
        }
        else {
            String tsFileName = settingMgr.getProperty("TestsuiteFileName");
            try {
                tsFileMgr.createTsFile(tcNameList, tcJarName, tcPath, tsFileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.setCommand(this.argumentKey[1] + " " + tcPath + "\\" + tsFileName);
        }
        this.nextHandle(tsInfo);
    }
}
