package com.coremail.cat.restful.framework.manager;

import java.io.*;
import java.util.*;

/**
 * Created by FQY on 2016/11/9.
 */
public abstract class PropertiesMgr {
    protected File confFile;
    protected Map<String, String> dataMap = new LinkedHashMap<>();
    private boolean hasLoad = false;

    protected PropertiesMgr(){
    }

    public void load() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(confFile), "UTF-8"));
        String line, key, value;
        boolean removeDateFlag = false;
        while ((line = reader.readLine()) != null){
            if (!removeDateFlag && line.toLowerCase().contains("last modified")){
                removeDateFlag = true;
                continue;
            }
            else if (!line.startsWith("#") && line.length() > 0 && line.contains("=")){
                key = line.substring(0, line.indexOf("=")).trim();
                value = line.substring(line.indexOf("=") + 1).trim();
            }
            else {
                key = line;
                value = null;
            }
            dataMap.put(key, value);
        }
        reader.close();
    }

    public String getProperty(String key){
        if (!hasLoad){
            try {
                this.load();
                hasLoad = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String property = "";
        property = this.dataMap.get(key);
        return property;
    }

    public void setProperty(String key, String value){
        try {
            if (!this.hasLoad){
                this.load();
            }
            this.dataMap.put(key, value);
            this.store();
            hasLoad = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void store() throws IOException{
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(confFile), "UTF-8"));
        writer.write("##Last Modified On " + new Date());
        writer.newLine();
        Iterator<Map.Entry<String, String>> it = dataMap.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<String, String> entry = it.next();
            if (entry.getValue() != null){
                writer.write(entry.getKey() + " = " + entry.getValue());
            }
            else {
                writer.write(entry.getKey());
            }
            writer.newLine();
        }
        writer.close();
    }
}
