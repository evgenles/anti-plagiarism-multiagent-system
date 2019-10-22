package com.diit.antiplagitarism.socket.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class CommunicatorHelper {

    private static ObjectMapper objectMapper = new ObjectMapper();

    private static Queue<Object> objects = new ConcurrentLinkedQueue<>();

    static {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    public static Boolean PushQueue( DataOutputStream out){
        if(out != null){
            while (!objects.isEmpty()){
                String writeValue = null;
                try {
                    writeValue = objectMapper.writeValueAsString(objects.poll());
                    out.writeUTF(writeValue);
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return true;
        }
        return false;
    }
    public static void SendData(Socket socket, DataOutputStream out, Object obj) {
        if(socket == null || out == null || !socket.isConnected()){
            try {
                System.out.println("Added to queue: " +  objectMapper.writeValueAsString(obj));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            objects.add(obj);
            return;
        }
        if (socket.isConnected()) {
            try {
                String writeValue = objectMapper.writeValueAsString(obj);
                out.writeUTF(writeValue);
                out.flush();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
