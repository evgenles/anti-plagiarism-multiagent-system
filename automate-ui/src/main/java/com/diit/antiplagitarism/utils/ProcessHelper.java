package com.diit.antiplagitarism.utils;

import com.diit.antiplagitarism.StartData;
import org.sikuli.script.App;

import java.io.File;
import java.io.IOException;

public class ProcessHelper {
    public static App StartApp(String x86, String x64) throws IOException {
        String arch = System.getProperty("os.arch");
        String path = arch.equalsIgnoreCase("x86")?x86:x64;
        System.out.println("Arch: " + arch + " path: " + path);
        String workDir = path.substring(0, path.lastIndexOf(File.separatorChar));
        ProcessBuilder pb = new ProcessBuilder(path);
        pb.directory(new File(workDir));
            pb.start();
        App exequtableApp = new App(path);
        exequtableApp.setWorkDir(workDir);
        return exequtableApp;
    }

    public static Boolean KillProcess(String name){
        try {
            Runtime.getRuntime().exec("taskkill /F /IM "+name);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
