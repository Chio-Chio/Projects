package controller;

import model.Server;
import model.Task;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TimeStrategy extends Strategy {
    public int addTask(ArrayList<Server> servers, Task task, Integer time) {
        //add task
        //pick the queue with minimum waiting time
        //Server minTimeServer = servers.get(0);
        int minTimeServerPosition = 0;

        for (int i = 0; i < servers.size(); i++) {
            if (servers.get(i).isEmpty()) {
                minTimeServerPosition = i;
                break;
            }
            //compare waiting period
            if (servers.get(i).getWaitingPeriod().get() >= servers.get(minTimeServerPosition).getWaitingPeriod().get()) {
                minTimeServerPosition = i;
            }

        }
//        if(servers.isEmpty()){
//
//        }
        //add task to minTimeServerPosition
        //task.settAtFrontQueue(time);

        servers.get(minTimeServerPosition).getQueue().add(task);
        //servers.get(minTimeServerPosition).getWaitingPeriod() + task.getTService()
        //int i = servers.get(minTimeServerPosition).getWaitingPeriod().get() + task.getTService();
        servers.get(minTimeServerPosition).getWaitingPeriod().getAndAdd(task.getTService());

        return minTimeServerPosition;
    }

}
