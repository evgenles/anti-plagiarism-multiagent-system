package com.diit.antiplagitarism.socket.web;

import com.diit.antiplagitarism.socket.helpers.CommunicatorHelper;
import com.diit.antiplagitarism.socket.master.ProxyWebCommunicator;
import com.diit.antiplagitarism.socket.models.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.omg.CORBA.Environment;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Function;

public class ProxyMasterCommunicator {
    //initialize socket and input stream
    private Socket server = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;
    private ObjectMapper objectMapper = new ObjectMapper();

    public Function<SendAssignedDto, Boolean> AssignedTaskHandler = task -> false;
    public Function<PercentageDto, Boolean> PercentageHandler = task -> false;
    public Function<ResultDto, Boolean> ResultHandler = task -> false;
    public Function<AgentJoinedDto, Boolean> AgentJoinedHandler = task -> false;
    public Function<AgentLeftDto, Boolean> AgentLefttHandler = task -> false;

    public ProxyMasterCommunicator() {
        Client();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private void Client() {
        Client("localhost", 6173);
    }

    public void AddTask(AddTaskDto taskDto) {
        CommunicatorHelper.SendData(server, out, taskDto);
    }

    public void CancelTask(CancelTaskDto taskDto) {
        CommunicatorHelper.SendData(server, out, taskDto);
    }

    private void makeConnection(String address, int port) {
        Boolean isConnected = false;
        while (!isConnected) {
            try {
                server = new Socket(address, port);


                System.out.println("Connected to " + address + ":" + port);
                out = new DataOutputStream(
                        new BufferedOutputStream(server.getOutputStream()));

                in = new DataInputStream(new BufferedInputStream(server.getInputStream()));
                isConnected = true;
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    // constructor with port
    private void Client(String address, int port) {
        // starts server and waits for a connection
        Thread th = new Thread(() -> {
            makeConnection(address, port);
            String line = "";
            // reads message from client until "Over" is sent
            while (server.isConnected()) {
                try {
                    line = in.readUTF();
                    System.out.println("Recived: " + line);

                    BaseHandlerDto baseDto = objectMapper.readValue(line, BaseHandlerDto.class);
                    switch (baseDto.getHandlerName()) {
                        case SendAssigned:
                            AssignedTaskHandler.apply(objectMapper.readValue(line, SendAssignedDto.class));
                            break;
                        case SendPercentage:
                            PercentageHandler.apply(objectMapper.readValue(line, PercentageDto.class));
                            break;
                        case SendResult:
                            ResultHandler.apply(objectMapper.readValue(line, ResultDto.class));
                            break;
                        case AgentJoined:
                            AgentJoinedHandler.apply(objectMapper.readValue(line, AgentJoinedDto.class));
                            break;
                        case AgentLeft:
                            AgentLefttHandler.apply(objectMapper.readValue(line, AgentLeftDto.class));
                            break;
                    }
                } catch (IOException i) {
                    System.out.println(i);
                    makeConnection(address, port);
                }
            }
        });
        th.start();
    }

}
