package com.diit.antiplagitarism;

import lombok.Getter;

public class ResultData {

    private double percentage = 0;

    public double getPercentage() { return percentage;}

    public void  setPercentage(double value) { percentage = value;}

    private double errorRate = 100.0;

    public double getErrorRate() { return errorRate;}

    public void  setErrorRate(double value) { errorRate = value;}

    private String log = "";

    public  String getLog(){return log;}

    public void  setLog(String value){log = value;}

    public ResultData(){}

    public ResultData(double percentage, String log, double errorRate){
        this.percentage = percentage;
        this.log = log;
        this.errorRate = errorRate;
    }
}
