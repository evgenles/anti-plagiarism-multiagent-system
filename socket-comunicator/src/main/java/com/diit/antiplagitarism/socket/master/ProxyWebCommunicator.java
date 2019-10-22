package com.diit.antiplagitarism.socket.master;
import com.diit.antiplagitarism.socket.helpers.CommunicatorHelper;
import com.diit.antiplagitarism.socket.models.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.*;
import java.io.*;
import java.util.UUID;
import java.util.function.Function;

public class ProxyWebCommunicator {
    //initialize socket and input stream
    private Socket          socket   = null;
    private ServerSocket    server   = null;
    private DataInputStream in       =  null;
    private DataOutputStream out = null;
    private ObjectMapper objectMapper = new ObjectMapper();

    public Function<AddTaskDto, Boolean> AddTaskHandler = task -> false;
    public Function<CancelTaskDto, Boolean> CancelTaskHandler = task -> false;


    public void SendPercentage(PercentageDto percentageDto){
        CommunicatorHelper.SendData(socket, out, percentageDto);
    }

    public void SendResult(ResultDto resultDto){
        CommunicatorHelper.SendData(socket, out, resultDto);
    }

    public void SendAssigned(SendAssignedDto assignedDto){
        CommunicatorHelper.SendData(socket, out, assignedDto);
    }

    public void SendAgentJoined(AgentJoinedDto agentJoinedDto){
        CommunicatorHelper.SendData(socket, out, agentJoinedDto);
    }

    public void SendAgentLeft(UUID agentId){
        CommunicatorHelper.SendData(socket, out, new AgentLeftDto(agentId));
    }

    public ProxyWebCommunicator(){
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Server();
    }
    // constructor with port
    private void Server() {
        Server(6173);
    }

    // constructor with port
    private void Server(int port)
    {
        // starts server and waits for a connection


        Thread th = new Thread(() -> {
        try
        {
            String data = "";
            server = new ServerSocket(port);
            socket = server.accept();
            System.out.println("Connected to " + socket.getPort());
            out = new DataOutputStream(
                    new BufferedOutputStream(socket.getOutputStream()));

            in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));
            CommunicatorHelper.PushQueue(out);
                while (socket.isConnected()) {
                    try {
                        data = in.readUTF();
                        System.out.println("Received " + data);

                        BaseHandlerDto baseDto = objectMapper.readValue(data, BaseHandlerDto.class);
                        switch (baseDto.getHandlerName()) {
                            case AddTask:
                                AddTaskHandler.apply(objectMapper.readValue(data, AddTaskDto.class));
                                break;
                            case CancelTask:
                                CancelTaskHandler.apply(objectMapper.readValue(data, CancelTaskDto.class));
                                break;
                        }
                    } catch (IOException i) {
                        System.out.println(i);
                        socket = server.accept();
                    //    out = new PrintWriter(socket.getOutputStream(),true);
                        in = new DataInputStream(
                                new BufferedInputStream(socket.getInputStream()));
                    }
                }
            
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    });
        th.start();
    }

}
