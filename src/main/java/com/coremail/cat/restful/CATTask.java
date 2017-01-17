package com.coremail.cat.restful;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by FQY on 2016/12/28.
 */
public class CATTask {
    public enum TaskStatus{
        WAITTING, EXCUTING, DONE
    }

    /*任务标识*/
    private String taskID;
    /*任务名*/
    private String taskName;
    /*任务状态*/
    private TaskStatus taskStatus;
    /*任务发起人*/
    private String taskUser;
    /*请求参数*/
    private TestsuiteInfo tsInfo;
    /*todo:任务执行信息*/

    public CATTask(TestsuiteInfo tsInfo){
        this.taskID = "task" + (new SimpleDateFormat("yyyyMMdd_HH:mm:ss")).format(new Date());
        this.taskName = tsInfo.getTaskName();
        this.taskStatus = TaskStatus.WAITTING;
        this.tsInfo = tsInfo;
    }

    public String getTaskID(){
        return this.taskID;
    }
    public String getTaskName(){
        return this.taskName;
    }
    public TaskStatus getTaskStatus(){
        return this.taskStatus;
    }
    public String getTaskUser(){
        return this.taskUser;
    }
    public TestsuiteInfo getTsInfo(){
        return this.tsInfo;
    }

    public void setTaskStatus(TaskStatus taskStatus){
        this.taskStatus = taskStatus;
    }

}
