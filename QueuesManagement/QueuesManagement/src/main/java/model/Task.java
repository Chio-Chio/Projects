package model;

public class Task {
    Integer id;
    Integer tArrival;
    Integer tService;

    Integer tAtFrontQueue;
    public Task(){
        this.id = -1;
        this.tArrival = -1;
        this.tService = -1;
        this.tAtFrontQueue = -1;
    }

    public Task(Integer id, Integer tArrival, Integer tService, Integer tAtFrontQueue) {
        this.id = id;
        this.tArrival = tArrival;
        this.tService = tService;
        this.tAtFrontQueue = tAtFrontQueue;
    }

    public Integer gettAtFrontQueue() {
        return tAtFrontQueue;
    }

    public void settAtFrontQueue(Integer tAtFrontQueue) {
        this.tAtFrontQueue = tAtFrontQueue;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTArrival() {
        return tArrival;
    }

    public void setTArrival(Integer tArrival) {
        this.tArrival = tArrival;
    }

    public Integer getTService() {
        return tService;
    }

    public void setTService(Integer tService) {
        this.tService = tService;
    }

    public String printTask(){
        String task = "(" + this.id + ", " + this.tArrival + ", " + this.tService + "); ";
        return task;
    }
}
