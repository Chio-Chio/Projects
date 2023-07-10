package controller;

import model.SelectionPolicy;
import model.Server;
import model.Task;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class Strategy {
    SelectionPolicy s;
    public Strategy() {
        this.s = SelectionPolicy.SHORTEST_TIME;
    }

    public abstract int addTask(ArrayList<Server> servers, Task task, Integer time);
}
