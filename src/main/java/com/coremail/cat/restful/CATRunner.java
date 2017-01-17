package com.coremail.cat.restful;

import com.coremail.cat.restful.framework.manager.FileMgr;
import com.coremail.cat.restful.framework.manager.PathMgr;
import com.coremail.cat.restful.framework.manager.SettingMgr;
import com.coremail.cat.restful.framework.observer.Observer;
import com.coremail.cat.restful.protester.parser.ParserEntry;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by FQY on 2016/11/8.
 */
public class CATRunner implements Runnable {
    private SettingMgr settingMgr = SettingMgr.getInstance();
    private PathMgr pathMgr = PathMgr.getInstance();
    private FileMgr fileMgr = FileMgr.getInstance();

    private String queueID;
    private CATTask catTask;
    private String command;
    private String batPath;
    private String batName;
    private List<Observer> observerList = new ArrayList<>();

    public CATRunner(String queueID, CATTask catTask) {
        this.queueID = queueID;
        this.catTask = catTask;
        ParserEntry parserEntry = new ParserEntry();
        parserEntry.startParser(this.catTask.getTsInfo());
        this.command = "java -jar -Dfile.encoding=UTF-8 CoremailAutoTest.jar -ui false" + parserEntry.getCommand() + " -run";
    }

    public void addObserver(Observer observer){
        this.observerList.add(observer);
    }

    @Override
    public void run(){
        /*开始*/
        catTask.setTaskStatus(CATTask.TaskStatus.EXCUTING);
        for (int i = 0; i < this.observerList.size(); i++){
            this.observerList.get(i).update(this.queueID);
        }
        Runtime rt = Runtime.getRuntime();
        Process ps = null;
        try {
            createBatFile();
            ps = rt.exec(batPath + "\\" + batName);
            /*ps.getInputStream().close();
            ps.getOutputStream().close();
            ps.getErrorStream().close();*/
            BufferedReader reader = new BufferedReader(new InputStreamReader(ps.getInputStream(), "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            int t = ps.waitFor();
            System.out.println(t);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int exitValue = ps.exitValue();
        if (exitValue == 0){
            System.out.println("close normal");
        }else {
            System.out.println("something wrong");
        }
        ps.destroy();
        /*finish*/
        catTask.setTaskStatus(CATTask.TaskStatus.DONE);
        for (int i = 0; i < this.observerList.size(); i++){
            this.observerList.get(i).update(this.queueID);
        }
    }

    public void createBatFile() throws IOException {
        String catPath = settingMgr.getProperty("CATPath");
        batPath = pathMgr.getBatPath();
        batName = settingMgr.getProperty("BatName");
        File file = fileMgr.createFile(batPath, batName);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
        writer.write("set JAVA_HOME=" + catPath + "\\jre");
        writer.newLine();
        writer.write("set PATH=%path%;%JAVA_HOME%\\bin;");
        writer.newLine();
        writer.write("cd /d " + catPath + "");
        writer.newLine();
        writer.write(command + "");
        writer.newLine();
        writer.write("exit");
        writer.close();
    }
}
