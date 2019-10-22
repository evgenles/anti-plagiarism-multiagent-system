package com.diit.antiplagitarism.socket.models;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class AgentJoinedDto extends BaseHandlerDto {
    @NonNull
    private UUID agentId;
    @NonNull
    private String ip;

    private Handler handlerName =  Handler.AgentJoined;

}
