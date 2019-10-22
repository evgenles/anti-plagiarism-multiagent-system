package com.diit.antiplagitarism.webadmin.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Agent {
    private UUID id;
    private String ip;
    private Boolean hasTask;
}
