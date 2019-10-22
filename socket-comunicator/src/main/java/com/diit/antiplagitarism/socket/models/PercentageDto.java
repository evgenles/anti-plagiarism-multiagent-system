package com.diit.antiplagitarism.socket.models;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class PercentageDto extends BaseHandlerDto {
    @NonNull
    private UUID taskId;
    @NonNull
    private double percentage;
    private Handler handlerName = Handler.SendPercentage;

}
