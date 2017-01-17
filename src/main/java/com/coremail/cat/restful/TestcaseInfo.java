package com.coremail.cat.restful;

import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by FQY on 2016/10/27.
 */

@XmlRootElement
public class TestcaseInfo {
    private String tcName;

    public TestcaseInfo(){

    }

    public String getTcName(){
        return this.tcName;
    }

    public void setTcName(String tcName){
        this.tcName = tcName;
    }

    /*@Override
    public String toString() {
        return this.tcName;
    }*/

    public TestcaseInfo(String tcName){
        this.tcName = tcName;
    }
}
