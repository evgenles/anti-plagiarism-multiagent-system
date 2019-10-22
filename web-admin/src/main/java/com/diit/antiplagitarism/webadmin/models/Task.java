package com.diit.antiplagitarism.webadmin.models;

import lombok.*;

import java.util.UUID;


@Getter @Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Task {
    @NonNull
    private UUID id;

    private String agentId;
    @NonNull
    private String name;
    @NonNull
    private Boolean isActive;
    @NonNull
    private Double completedPercentage;
    @NonNull
    private Double needPercentage;

    private double uniquePercentage;
    private double errorRate;
    private String log;

}
