import controller.StartController;
import view.StartView;

public class Main {
    public static void main(String[] args){
        StartView startView = new StartView();
        StartController startController = new StartController(startView);

        startView.setInNrClients(String.valueOf(5));
        startView.setInNrQueues(String.valueOf(3));
        startView.setInTMaxSimulation(String.valueOf(10));
        startView.setInArrivalMin(String.valueOf(1));
        startView.setInArrivalMax(String.valueOf(5));

        startView.setInServiceMin(String.valueOf(1));
        startView.setInServiceMax(String.valueOf(5));

    }
}
