package controller;

import model.SelectionPolicy;
import model.Server;
import model.Task;
import view.SimulationView;
import view.StartView;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class SimulationController implements Runnable {
    private Scheduler scheduler;
    private SimulationView simulationView;

    private StartView startView;

    private List<Task> tasks;

    private SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_TIME;

    private Integer numberClients;
    private Integer numberQueues;
    private Integer timeLimit;
    private Integer tArrivalMin;
    private Integer tArrivalMax;
    private Integer tServiceMin;
    private Integer tServiceMax;
    private static Semaphore semaphore = new Semaphore(1);
    //private static Semaphore printTime = new Semaphore(1);
    private AtomicInteger timp = new AtomicInteger(0);
    // private AtomicInteger timpTrecut = new AtomicInteger(0); //make themc

    private AtomicInteger averageWaitingTime = new AtomicInteger(0);
//    private AtomicInteger serviceTime = new AtomicInteger(0);
//    private AtomicInteger peackhour = new AtomicInteger(0);

    private AtomicInteger threadNumber = new AtomicInteger(0);
    private boolean printStats = false;
    private double aserviceTime = 0.0;
    private int peackH = -1;

    private FileWriter writer = new FileWriter("./output.txt");


//    public SimulationController(SimulationView simulationView){
//        this.simulationView = simulationView;
//    }

    public SimulationController() throws IOException {
    }

    public SimulationController(SimulationView simulationView, StartView startView) throws IOException {
        this.simulationView = simulationView;
        this.startView = startView;

        this.numberClients = Integer.valueOf(startView.getInNrClients());
        this.numberQueues = Integer.valueOf(startView.getInNrQueues());
        this.timeLimit = Integer.valueOf(startView.getInTMaxSimulation());
        this.tArrivalMin = Integer.valueOf(startView.getInArrivalMin());
        this.tArrivalMax = Integer.valueOf(startView.getInArrivalMax());
        this.tServiceMin = Integer.valueOf(startView.getInServiceMin());
        this.tServiceMax = Integer.valueOf(startView.getInServiceMax());
    }

    public void simulate() {
        try {
            generateRandomTasks();
            aserviceTime = printServiceTime();
            peackH = printPeackHour();
            simulationView.appendToTextArea("Generated tasks:\n");
            //simulationView.appendToTextArea("buna");
            System.out.println("Generated tasks:");
            for (Task t : tasks) {
                String aux = t.printTask();
                simulationView.appendToTextArea(aux);
            }
            simulationView.appendToTextArea("\n");
            //AtomicInteger toatalWaitingPeriod = new AtomicInteger(0);

            // genereate the servers for each threads
            //Task emptyTask = new Task();

//           for(int i = 0; i < numberQueues; i++){
//               servers[i] = new Server(100);
//           }
            ArrayList list = new ArrayList<Server>(100);

            for (int i = 0; i < numberQueues; i++) {
                Server server = new Server(i);
                list.add(server); //
            }
            scheduler = new Scheduler(list, numberQueues, 100);

            Thread[] threads = new Thread[numberQueues];
            for (int i = 0; i < numberQueues; i++) {
                threads[i] = new Thread(this);
                threads[i].start(); //threads place the tasks in the corresponding queues
            }/* each queue has a threads that funnels tasks into them
                threads can put any corresponding task in any appropriate queue

                trebuia sa fie
             */


        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public void printTab(int tabSize) {
        for (int i = 0; i < tabSize; i++) {
            simulationView.appendToTextArea("        ");
        }
    }

    public void thread0(int id, int currentTime) {
        if (threadNumber.get() == 0) {
            //print time
            simulationView.appendToTextArea("Time: " + currentTime + "\n");
            //print waiting clients
            simulationView.appendToTextArea("Waiting clients: ");
            for (Task t : tasks) {
                simulationView.appendToTextArea(t.printTask());
            }
            simulationView.appendToTextArea("\n");

            //prettyprint
            //print waiting times
            for (int i = 0; i < numberQueues; i++) {
                int x = scheduler.getServes().get(i).getWaitingPeriod().get();
                printTab(1);
                simulationView.appendToTextArea("Queue " + i + " waitingTime = " + x + "\n");
            }

            //print the queues and the elements of them
            for (int i = 0; i < numberQueues; i++) {
                simulationView.appendToTextArea(scheduler.printQueue(i));
                //
            }
            simulationView.appendToTextArea("\n");
        }
    }

    @Override
    public void run() {
        // each thread is a queue => server
        int currentTime = 0;
        while (currentTime < timeLimit) {
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            synchronized (this) {
                thread0(threadNumber.get(), currentTime);
                threadNumber.getAndIncrement();
                if (threadNumber.get() == numberQueues) {
                    threadNumber.set(0);
                    // timp.getAndIncrement();
                    for (int i = 0; i < numberQueues; i++) {
                        if (scheduler.getServes().get(i).getWaitingPeriod().get() != 0) {
                            scheduler.getServes().get(i).getWaitingPeriod().getAndDecrement();
                            averageWaitingTime.getAndAdd(scheduler.getServes().get(i).getWaitingPeriod().get());
                        } else {
                            //case that there is no waiting time
                            Task task = scheduler.getServes().get(i).getQueue().poll(); // remove first task
                            if (task != null) {
                                printTab(1);
                                simulationView.appendToTextArea("deleted from Queue " + i + ": " + task.printTask() + "\n");
                                //averageWaitingTime.getAndAdd(currentTime);
                            }
                        }

                        //time to complete the task is gone
                        Task frontTask = scheduler.getServes().get(i).getQueue().peek();
                        // remove completed task currentTime == tarrival+ task.tService
                        if (frontTask != null && (frontTask.getTArrival() + frontTask.getTService() == currentTime)) {
                            Task task = scheduler.getServes().get(i).getQueue().poll();
                            if (task != null) {
                                printTab(1);
                                simulationView.appendToTextArea("deleted from Queue " + i + ": " + task.printTask() + "\n");
                            }
                        }
                    }
                    simulationView.appendToTextArea("\n");
                }

            }
            // place task in best queue
            for (Task t : tasks) {
                if (t.getTArrival() == currentTime) {
                    int queueId = this.scheduler.dispatchTask(t, currentTime); // added in queue
                    tasks.remove(t);
                    String aux = t.printTask();
                    //simulationView.appendToTextArea("\n");
                    printTab(1);
                    simulationView.appendToTextArea("added to Queue " + queueId + ": " + aux + "\n");
                    //averageWaitingTime.getAndAdd(-currentTime);
                    break;
                }
            }


            currentTime++;

            semaphore.release();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        } // end while

        synchronized (this) {
            if (!printStats) {
                printStats = true;
                printAvgWTime();
                simulationView.appendToTextArea("\nAverage service time: " + aserviceTime + "\n");
                simulationView.appendToTextArea("\nPeack hour: " + peackH + "\n");
                try {

                    writer.write(simulationView.getTextArea());
                    writer.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    public void printAvgWTime() {
        // sum of all the waiting times decided by number of clients from all the queues
        if (threadNumber.get() == 0) {
            synchronized (this) {
                double a = averageWaitingTime.get();
                double rez = 0;
                rez = a / numberClients;
                simulationView.appendToTextArea("Average waiting time: " + rez + "\n");
            }
        }
    }

    public double printServiceTime() {
        // how long does it take for the average client to be served
        double avgServiceTime = 0.0;
        //int peackHour = 0;
        for (Task t : tasks) {
            avgServiceTime += t.getTService();
        }
        avgServiceTime = avgServiceTime / numberClients;

        return avgServiceTime;
        // simulationView.appendToTextArea("\nAverage service time: " + printServiceTime() +"\n");
    }

    public int printPeackHour() {
        // the hour with the most arrivals
        Map<Integer, Integer> arrivalCounts = new HashMap<>();

        // Count the number of tasks for each arrival time
        for (Task task : tasks) {
            int arrivalTime = task.getTArrival();
            arrivalCounts.put(arrivalTime, arrivalCounts.getOrDefault(arrivalTime, 0) + 1);
        }

        // Find the arrival time with the highest count
        int maxCount = 0;
        int peakHour = -1;
        for (Map.Entry<Integer, Integer> entry : arrivalCounts.entrySet()) {
            int arrivalTime = entry.getKey();
            int count = entry.getValue();
            if (count > maxCount) {
                maxCount = count;
                peakHour = arrivalTime;
            }
        }

        // Return the peak hour (arrival time with the highest count)
        return peakHour;
    }

    public void generateRandomTasks() {
        tasks = new LinkedList<Task>();
        for (Integer i = 0; i < numberClients; i++) {
            Integer tArrival, tService;
            tArrival = getRandomNumberUsingNextInt(tArrivalMin, tArrivalMax + 1);
            tService = getRandomNumberUsingNextInt(tServiceMin, tServiceMax + 1);
            Task aux = new Task(i, tArrival, tService, -1);
            System.out.println(aux.printTask());
            tasks.add(aux);
        }
    }
}