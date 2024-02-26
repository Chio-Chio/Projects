package Controller;

import Model.Process;
import Model.Simulator;
import View.SimulationView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationController {
    private SimulationView simulationView;
    private Integer requestNumber = 0;
    Process[] requests;
    Simulator simulator;
    public SimulationController (SimulationView simulationView){
        this.simulationView = simulationView;
        //this.simulationView.nextRequestListener(new NextRequestListener());
        this.simulator =  new Simulator(simulationView);
//        Process[] requests = new Process[4];
//        requests[0] = new Process("00000000000000000000010000110011", 0, "none", simulator.getCache());
//        requests[1] = new Process("00000000000000000000010000110011", 1, "77", simulator.getCache());
//        requests[2] = new Process("00000000000000000000000000000000", 0, "none", simulator.getCache());


    }

    class NextRequestListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                //System.out.println("NextRequest was pressed #"+requestNumber);
                //simulationView.appendToTextAreaCpuRequests(requests[0].prettyPrintRequest());
                requestNumber++;
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    public int getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(int requestNumber) {
        this.requestNumber = requestNumber;
    }
}
