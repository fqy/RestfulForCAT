package com.coremail.cat.restful;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by FQY on 2016/10/24.
 */

@Path("CAT")
public class CATResource {
    private CATLauncher catLauncher = CATLauncher.getInstance();

    /**
     * connect test
     * @return
     */
    @Path("connectTest")
    @GET
    public String connectTest() {
        return "OK";
    }

    @Path("serverStatus")
    @GET
    public String serverStatus(){
        return catLauncher.getStatus().name();
    }

    /**
     * test task entry
     * @param testsuiteInfo
     * @return
     */
    @Path("tsInfo")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String startTest(TestsuiteInfo testsuiteInfo) {
        /*检测必须参数是否不为空*/
        if (testsuiteInfo.getTaskName() == null){
            return "task name null";
        }
        else if (testsuiteInfo.getProjectName() == null){
            return "project name null";
        }

        if (catLauncher.isQueueFull()){
            return "Queue Full!";
        }
        else {
            catLauncher.addTask(testsuiteInfo);
            CATLauncher.ServerStatus serverStatus = catLauncher.getStatus();
            if (serverStatus == CATLauncher.ServerStatus.FREE){
                catLauncher.startTest();
            }
            return "Insert Success!";
        }
    }
}
