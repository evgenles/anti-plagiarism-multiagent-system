package com.diit.antiplagitarism;

import com.diit.antiplagitarism.utils.ProcessHelper;
import lombok.var;
import org.sikuli.script.*;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EtxtAntiplagitarismExequtable implements IAntiplagitarismExequtable {
    public static final String InstallUrl = "https://www.etxt.ru/downloads/etxt_antiplagiat.exe";
    public static final String ResultFileName = "etxt_antiplagiat.exe";
    public static final String x64Executable = "C:\\Program Files (x86)\\Etxt Antiplagiat\\EtxtAntiplagiat.exe";
    public static final String x86Executable = "C:\\Program Files\\Etxt Antiplagiat\\EtxtAntiplagiat.exe";
    public static final String ProcessName = "EtxtAntiplagiat.exe";

    public EtxtAntiplagitarismExequtable() {
        //ImagePath.add("resources\\main");
        ImagePath.add("resources/main/Etxt");
        ImagePath.add(getClass().getName()+"/Etxt");
    }

    public void TrySleep(int miliseconds){
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Boolean Install() {
        try {
            ReadableByteChannel readableByteChannel = Channels.newChannel(new URL(InstallUrl).openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(ResultFileName);
            fileOutputStream.getChannel()
                    .transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        try {
            Process install = new ProcessBuilder(ResultFileName).start();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Region screen = App.focusedWindow();
        screen.highlight(2);
        try {
            Region reg = screen.find("LicensingYES.PNG");
            reg.wait(1000);
            reg.click();
            screen.type(Key.ENTER);
            screen.type(Key.ENTER);
            screen.type(Key.ENTER);
        } catch (FindFailed | InterruptedException findFailed) {
            findFailed.printStackTrace();
        }

        return null;
    }

    @Override
    public StartData StartWork(String patchToAnalyze) {
        App antiplagitarismNet = null;
        try {
            antiplagitarismNet = ProcessHelper.StartApp(x86Executable,x64Executable);
        } catch (IOException e) {
            return StartData.Error("Can`t run antiplagitarism program\n" + e.getMessage());
        }
        antiplagitarismNet.open(15000);
        TrySleep(10000);
        antiplagitarismNet.focus();
        UpdateInfo upd = CheckUpdates();
        if(upd.getHasUpdates())
            return StartData.HasUpdates(upd);

        App.focusedWindow().type(Key.UP, KeyModifier.WIN);
        TrySleep(2000);
        Region screen = App.focusedWindow();
        System.out.println("h: " + screen.h + " w: " + screen.w + " x " + screen.x + " y " + screen.y);
        screen.type("o", KeyModifier.CTRL);
        screen.paste(patchToAnalyze);
        screen.type(Key.ENTER);
        TrySleep(2000);
        try {
            screen.find("CheckUnique.PNG").click();
        } catch (FindFailed findFailed) {
            return StartData.Error("Can`t find check unique button");
        }
        return StartData.Success();
    }

    @Override
    public UpdateInfo CheckUpdates() {
        Region screen = App.focusedWindow();
        if(screen.has("UpdateAvailable.PNG")){
            try {
                var updateTop = screen.find("UpdateInfoTop.PNG").below(20);
                updateTop.click();
                updateTop.type("a", KeyModifier.CTRL);
                updateTop.type("c", KeyModifier.CTRL);
                String str = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
                return new UpdateInfo(true, str);
            } catch (FindFailed findFailed) {
                findFailed.printStackTrace();
            } catch (UnsupportedFlavorException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return UpdateInfo.noUpdates;
    }

    String matchFirst(String regex, String text) {
        java.util.regex.Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(text);
        if (m.find()) {
            return m.group(1);
        } else {
            return "0";//or empty string, or maybe throw exception
        }
    }

    @Override
    public double GetProcessPercentage() {
        Region activePercentage = App.focusedWindow();
        int w = activePercentage.w;
        if(activePercentage.has("Complited.PNG"))
            return 100;
        try {
            activePercentage = activePercentage.find("PercentageBar.PNG");
            activePercentage.setX(0);
            activePercentage.setW(w);
            activePercentage.setY(activePercentage.y+activePercentage.h/2);
            BufferedImage img = App.focusedWindow().getScreen().capture(activePercentage).getImage();
            int[] pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
            int notProcessed = 0;
            for (int i = 0; i < img.getWidth(); i++) {
                //System.out.println("I: " + i + " b " + (pixels[i*3] & 0xff) + " g " + (pixels[i*3 +  1]& 0xff) + " r "+ (pixels[i*3 + 2]& 0xff));
                if ((pixels[i * 3] & 0xff) == 255 && (pixels[i * 3 + 1] & 0xff) == 255 && (pixels[i * 3 + 2] & 0xff) == 255) {
                    notProcessed++;
                }
            }
            System.out.println("np: " + notProcessed + " " + (1 - (double) notProcessed / (img.getWidth() - 10)) + "%");
            return (1 - (double) notProcessed / (img.getWidth() - 10));
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
            return 0;
        }
    }

    @Override
    public ResultData GetResult() {
        Region screen = App.focusedWindow();
        try {
            screen.find("Complited.PNG");
            Region journal = screen.find("Journal.PNG").below(100);
            journal.click();
            journal.type("a", KeyModifier.CTRL);
            journal.type("c", KeyModifier.CTRL);
            String str = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
            String log = str.substring(str.indexOf("[")).replace("\n\n\n", "\n");
            System.out.println(log);
            String pattern = new String("\\] Уникальность текста ([\\d\\.]+)%");
            String unique = matchFirst(pattern, log);
            Double percentage = Double.parseDouble(unique);
            System.out.println(unique);
            System.out.println(percentage);

            pattern = new String("Обнаружено ошибок: ([\\d\\.]+)%");
            String errorRateStr = matchFirst(pattern, log);
            Double errorRate = Double.parseDouble(errorRateStr);
            Cancel();
            return new ResultData(percentage, log, errorRate);
        } catch (FindFailed findFailed) {
            Cancel();
            return new ResultData(0, "Can`t find complited", 100);
        } catch (UnsupportedFlavorException | IOException e) {
            Cancel();
            return new ResultData(0, "Can`t get data from clipboard", 100);
        }
    }

    @Override
    public StartData Cancel() {
        Boolean kill = ProcessHelper.KillProcess(ProcessName);
        return kill?StartData.Success():StartData.Error("Can`t kill application");
    }

    public static ResultData Demonstrate(){
        int timeout = 30*60*1000;
        IAntiplagitarismExequtable app = new EtxtAntiplagitarismExequtable();
        StartData start = app.StartWork("D:\\Labs\\2018(1)\\Diploma\\Diploma.docx");
        if(!start.getSuccessStart()){
            System.out.println(start.getMessage());
            return new ResultData(0,start.getMessage(),100);
        }
        System.out.println("Started");
        int summ = 0;
        while(summ < timeout) {
            try {
                Thread.sleep(5000);
                summ += 5000;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            double percentage = app.GetProcessPercentage();
            System.out.println(percentage);
            if(percentage==100){
                ResultData ret = app.GetResult();
                app.Cancel();
                return ret;
            }
        }
        app.Cancel();
        return new ResultData(0,"Timeouted", 100);
    }
}
