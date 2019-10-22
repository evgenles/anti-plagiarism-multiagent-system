package com.diit.antiplagitarism.socket.models;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class AddTaskDto extends BaseHandlerDto
{
    @NonNull
    private String name;
    @NonNull
    private Boolean priority;
    @NonNull
    private List<String> analyzers;
    @NonNull
    private byte[] file;
    private Handler handlerName = Handler.AddTask;
    @NonNull
    private UUID taskId;
}
