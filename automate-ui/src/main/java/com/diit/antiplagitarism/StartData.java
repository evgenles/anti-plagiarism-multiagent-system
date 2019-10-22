package com.diit.antiplagitarism;


import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class StartData implements Serializable {

    @Getter
    @Setter
    private Boolean HasUpdates = false;
    private Boolean IsSuccessStart = false;

    private String Message = "";
    public static StartData HasUpdates(UpdateInfo updateInfo){return new StartData(true,false,updateInfo.getUpdateText());}
    public static StartData Error(String message){
        return new StartData(false,false, message);
    }
    public static StartData Success(){
        return new StartData(false,true, "");
    }

    public StartData(Boolean hasUpdates, Boolean isSuccessStart, String message){
       HasUpdates = hasUpdates;
        IsSuccessStart = isSuccessStart;
        Message = message;
    }
    public Boolean getSuccessStart() {
        return IsSuccessStart;
    }

    public void setSuccessStart(Boolean successStart) {
        IsSuccessStart = successStart;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
