package com.diit.antiplagitarism;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class UpdateInfo {
    private Boolean hasUpdates;
    private String updateText;


    public static UpdateInfo noUpdates = new UpdateInfo(false,"");
}
