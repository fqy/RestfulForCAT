package com.coremail.cat.restful;

import com.coremail.cat.restful.framework.manager.*;
import com.coremail.cat.restful.framework.observer.Observer;


/**
 * Created by FQY on 2016/12/23.
 */
public class CATLauncher {

    private static CATLauncher ourInstance = new CATLauncher();

    public enum ServerStatus {
        FREE, BUSY, OFFLINE
    }

    /**
     * 维护一些需要用的管理器
     */
    private ConfigMgr configMgr = ConfigMgr.getInstance();
    private SettingMgr settingMgr = SettingMgr.getInstance();
    private CATSettingMgr catSettingMgr = CATSettingMgr.getInstance();
    private PathMgr pathMgr = PathMgr.getInstance();
    private TestcaseJarMgr tcJarMgr = TestcaseJarMgr.getInstance();
    private TestsuiteFileMgr tsFileMgr = TestsuiteFileMgr.getInstance();

    /*启动器状态*/
    private ServerStatus status = ServerStatus.FREE;
    /*任务线程队列*/
    private CATTaskQueue taskQueue = new CATTaskQueue();


    public static CATLauncher getInstance() {
        return ourInstance;
    }
    private CATLauncher() {
    }

    public ServerStatus getStatus(){
        return this.status;
    }

    public boolean isQueueFull(){
        /*任务线程允许最大等待数*/
        int taskQueueMaxSize = Integer.parseInt(configMgr.getProperty("TaskQueueMaxSize"));
        int queueSize = taskQueue.getQueueSize();
        return queueSize>=taskQueueMaxSize;
    }

    public void addTask(TestsuiteInfo testsuiteInfo){
        taskQueue.add(testsuiteInfo);
    }

    public void startTest(){
        if (taskQueue.getQueueSize() > 0){
            Observer catObserver = new CATObserver();
            CATRunner catRunner = new CATRunner(taskQueue.getFirstQueueID(), taskQueue.getFirstTask());
            catRunner.addObserver(catObserver);
            Thread thread = new Thread(catRunner);
            thread.start();
        }
    }

    private class CATObserver implements Observer<String>{

        @Override
        public void update(String queueID) {
            CATTask.TaskStatus state = taskQueue.getTask(queueID).getTaskStatus();
            switch (state){
                case EXCUTING:
                    status = ServerStatus.BUSY;
                    break;
                case DONE:
                    taskQueue.remove(queueID);
                    if (taskQueue.getQueueSize() > 0){
                        startTest();
                    }
                    else {
                        status = ServerStatus.FREE;
                    }
                    break;
                default:;
            }
        }
    }
}
