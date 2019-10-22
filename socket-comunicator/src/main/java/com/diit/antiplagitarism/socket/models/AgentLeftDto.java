package com.diit.antiplagitarism.socket.models;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class AgentLeftDto extends BaseHandlerDto {
    @NonNull
    private UUID agentId;

    private Handler handlerName =  Handler.AgentJoined;

}
