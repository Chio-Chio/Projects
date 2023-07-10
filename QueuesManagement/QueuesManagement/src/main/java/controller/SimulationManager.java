package controller;

import model.Task;
import view.SimulationView;
import view.StartView;

import java.util.List;
//delete
public class SimulationManager {
    private Scheduler scheduler;
    private SimulationView simulationView;
    private StartView startView;
    private List<Task> tasks;
    private Integer selectionPolicy = 0;



    public SimulationManager(Scheduler scheduler, SimulationView simulationView, List<Task> tasks, Integer selectionPolicy) {
        this.scheduler = scheduler;
        this.simulationView = simulationView;
        this.tasks = tasks;
        this.selectionPolicy = selectionPolicy;
    }

    public void generateRandomTasks(){
        Integer numberOfClients = Integer.valueOf(startView.getInNrClients());

    }
}
