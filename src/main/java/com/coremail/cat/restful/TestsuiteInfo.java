package com.coremail.cat.restful;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by FQY on 2016/10/31.
 */

@XmlRootElement
public class TestsuiteInfo {

    public TestsuiteInfo(){}

    private String taskName;
    private String serverIP;
    private String projectName;
    private String moduleOne;
    private String priorities;
    private String testLevel;
    @XmlElement(name="casesArray")
    private List<TestcaseInfo> tcInfoList = new ArrayList<>();

    public String getTaskName(){
        return this.taskName;
    }
    public String getServerIP(){
        return this.serverIP;
    }
    public String getProjectName(){
        return this.projectName;
    }
    public String getModuleOne(){
        return this.moduleOne;
    }
    public String getPriorities(){
        return this.priorities;
    }
    public String getTestLevel(){
        return this.testLevel;
    }
    public List<TestcaseInfo> getTcInfoList(){
        return this.tcInfoList;
    }

    public void setTaskName(String taskName){
        this.taskName = taskName;
    }
    public void setServerIP(String serverIP){
        this.serverIP = serverIP;
    }
    public void setProjectName(String projectName){
        this.projectName = projectName;
    }
    public void setModuleOne(String moduleOne){
        this.moduleOne = moduleOne;
    }
    public void setPriorities(String priorities){
        this.priorities = priorities;
    }
    public void setTestLevel(String testLevel){
        this.testLevel = testLevel;
    }
    public void setTcInfoList(List<TestcaseInfo> tcInfoList){
        this.tcInfoList = tcInfoList;
    }
}
