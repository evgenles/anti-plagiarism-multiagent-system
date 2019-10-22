package com.diit.antiplagitarism.socket.models;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class ResultDto extends BaseHandlerDto {
    @NonNull
    private UUID taskId;

    @NonNull
    private double uniquePercentage;

    @NonNull
    private double errorRate;

    @NonNull
    private String logs;

    private Handler handlerName = Handler.SendResult;

}
