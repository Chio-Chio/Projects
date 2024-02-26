package Controller;

import Model.Simulator;
import View.SimulationView;
import View.StartView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartController {
    private StartView startView;
    public StartController(StartView startView){
        this.startView = startView;
        this.startView.startSimulationListener(new SimulationListener());
    }
    class SimulationListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                System.out.println("Start button was pressed");
                SimulationView simulationView = new SimulationView();
                SimulationController simulationController = new SimulationController(simulationView);
                Simulator simulator = new Simulator(simulationView, startView);
                simulator.simulate();

            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

}

