package controller;

import view.SimulationView;
import view.StartView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartController {
    private StartView startView;


    public StartController(StartView startView) {
        this.startView = startView;

        this.startView.startSimulationListener(new StartSimulationListener());
    }


    class StartSimulationListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            SimulationView simulationView = null;
            try {
                System.out.println("Start button was pressed.");

                simulationView = new SimulationView();
                SimulationController simulationController = new SimulationController(simulationView, startView);
                simulationController.simulate();

            } catch (NumberFormatException exception) {
                exception.printStackTrace();
                simulationView.dispose();
                startView.showErrorMessage("Invalid input\nInput MUST be number!");
            } catch (Exception exception) {
                exception.printStackTrace();
                simulationView.dispose();
                startView.showErrorMessage("bad!");
            }
        }
    }
}
