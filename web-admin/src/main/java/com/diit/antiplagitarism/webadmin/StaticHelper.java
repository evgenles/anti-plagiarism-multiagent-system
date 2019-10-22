package com.diit.antiplagitarism.webadmin;

import com.diit.antiplagitarism.socket.web.ProxyMasterCommunicator;
import com.diit.antiplagitarism.webadmin.models.Agent;
import com.diit.antiplagitarism.webadmin.models.Task;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class StaticHelper {
    public static final ProxyMasterCommunicator communicator = new ProxyMasterCommunicator();
    public static Map<UUID, Task> tasks = new ConcurrentHashMap();
    public static Map<UUID, Agent> agents = new ConcurrentHashMap();

    static {
        communicator.AssignedTaskHandler = (task)->{
            Task inTask = tasks.get(task.getTaskId());
            inTask.setIsActive(true);
            agents.get(task.getAgentId()).setHasTask(true);
            inTask.setAgentId(task.getAgentId().toString());
            return true;
        };
        communicator.PercentageHandler = (task)->{
            tasks.get(task.getTaskId()).setIsActive(true);
            tasks.get(task.getTaskId()).setCompletedPercentage(task.getPercentage());
            return true;
        };
        communicator.ResultHandler = (task)->{
            Task taskIn =  tasks.get(task.getTaskId());
            if(agents.get(taskIn.getAgentId()) != null) agents.get(taskIn.getAgentId()).setHasTask(false);
            taskIn.setIsActive(false);
            taskIn.setCompletedPercentage(100.0);
            taskIn.setUniquePercentage(task.getUniquePercentage());
            taskIn.setErrorRate(task.getErrorRate());
            taskIn.setLog(task.getLogs());
            return true;
        };
        communicator.AgentJoinedHandler = (agent)->{
            agents.put(agent.getAgentId(), new Agent(agent.getAgentId(), agent.getIp(), false));
            return true;
        };
        communicator.AgentLefttHandler = (agent)->{
            agents.remove(agent.getAgentId());
            return true;
        };
    }
}
