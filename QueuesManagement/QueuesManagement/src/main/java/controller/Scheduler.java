package controller;

import model.Server;
import model.Task;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Scheduler {
    private ArrayList<Server> serves;
    private Integer maxNoServers;
    private Integer maxTasksPerServer;
    private Strategy strategy;

    //change staregy

    public Scheduler() {
        this.serves = new ArrayList<Server>(10000);
        this.maxNoServers = 0;
        maxTasksPerServer = 0;
        strategy = new TimeStrategy();
    }

    public Scheduler(ArrayList<Server> serves, Integer maxNoServers, Integer maxTasksPerServer) {
        this.serves = serves;
        //this.serves = serves;
        this.maxNoServers = maxNoServers;
        this.maxTasksPerServer = maxTasksPerServer;
        this.strategy = new TimeStrategy();
    }

//    public LinkedList<Server> getServes() {
//        return serves;
//    }
//
//    public void setServes(LinkedList<Server> serves) {
//        this.serves = serves;
//    }


    public ArrayList<Server> getServes() {
        return serves;
    }

    public void setServes(ArrayList<Server> serves) {
        this.serves = serves;
    }

    public Integer getMaxNoServers() {
        return maxNoServers;
    }

    public void setMaxNoServers(Integer maxNoServers) {
        this.maxNoServers = maxNoServers;
    }

    public Integer getMaxTasksPerServer() {
        return maxTasksPerServer;
    }

    public void setMaxTasksPerServer(Integer maxTasksPerServer) {
        this.maxTasksPerServer = maxTasksPerServer;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public int dispatchTask(Task task, Integer time) {
        // call the strategy addTack method
        return this.strategy.addTask(serves, task, time);

    }

    public void deleteClient(Task task, int currentTime) {
        if (serves.isEmpty()) {
            System.out.println("empty server (Scheduler/deleteClient)");
            return;
        }
        // sent to method the first element of the queue as task
        if (currentTime == task.getTArrival() + task.getTService()) {
            //delete client from list
            for (int i = 0; i < serves.size(); i++) {
                Server server = serves.get(i);
                if (server.getQueue().contains(task)) {
                    server.getQueue().remove(task);
                    //decrement total queue time
                    System.out.println("init per: " + server.getWaitingPeriod() + "\n");//test
                    server.getWaitingPeriod().getAndAdd(-task.getTService());
                    System.out.println("final per: " + server.getWaitingPeriod() + "\n");//test
                    break; // break out of the loop once task is found and deleted
                }
            }
        }
    }

    public String printQueue(int idQueue) {

        String aux = "\n" + "        " + serves.get(idQueue).printServer();
        if (serves.get(idQueue).isEmpty()) {
            aux = aux + " closed";
        }
       // aux = aux + "\n";
        return aux;
    }

    public String printScheduler() {
        String aux = "";
        for (int i = 0; i < maxNoServers; i++) {
            String temp = printQueue(i);
            aux = aux + temp + "\n";
        }
        return aux;
    }
}

