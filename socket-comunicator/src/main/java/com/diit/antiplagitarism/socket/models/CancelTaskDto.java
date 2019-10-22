package com.diit.antiplagitarism.socket.models;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class CancelTaskDto extends BaseHandlerDto {
    @NonNull
    private UUID taskId;
    private Handler handlerName =  Handler.CancelTask;

}
