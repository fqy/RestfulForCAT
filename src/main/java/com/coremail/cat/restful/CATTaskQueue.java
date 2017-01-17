package com.coremail.cat.restful;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by FQY on 2016/12/28.
 */
public class CATTaskQueue {
    /*队列标识*/
    private List<String> queueIDList;
    /*CAT任务*/
    private List<CATTask> catTaskList;

    public CATTaskQueue(){
        this.queueIDList = new ArrayList<>();
        this.catTaskList = new ArrayList<>();
    }

    public void add(String queueID, CATTask catTask){
        this.queueIDList.add(queueID);
        this.catTaskList.add(catTask);
    }

    public void add(TestsuiteInfo tsInfo){
        String queueID = "queue" + (new SimpleDateFormat("yyyyMMdd_HH:mm:ss")).format(new Date());
        CATTask catTask = new CATTask(tsInfo);
        this.add(queueID, catTask);
    }

    public String getFirstQueueID(){
        return this.queueIDList.get(0);
    }

    public CATTask getFirstTask(){
        return this.catTaskList.get(0);
    }

    public CATTask getTask(String queueID){
        int index = this.queueIDList.indexOf(queueID);
        return this.catTaskList.get(index);
    }

    public void remove(String queueID){
        int index = this.queueIDList.indexOf(queueID);
        this.queueIDList.remove(index);
        this.catTaskList.remove(index);
    }

    public int getQueueSize(){
        return this.queueIDList.size();
    }
}
