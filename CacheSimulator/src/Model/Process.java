package Model;

import View.SimulationView;

public class Process {
    private String address;
    private int requestType; // read 0 or write 1
    private String writeData;

    private SetAssociativeCache cache;
    private SimulationView simulationView;

    public Process(String address, int requestType){
        this.address = address;
        this.requestType = requestType;

    }

    public Process(String address, int requestType, String writeData, SetAssociativeCache cache, SimulationView simulationView) {
        this.address = address;
        this.requestType = requestType;
        this.writeData = writeData;
        this.cache = cache;
        this.simulationView = simulationView;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWriteData() {
        return writeData;
    }

    public void setWriteData(String writeData) {
        this.writeData = writeData;
    }

    public String prettyPrintRequest(){
        String acc = "";
        if(requestType == 0){
            acc += "Read from address "+ cache.converBinaryToHexa(address) +"\n";
        }else{
            acc += "Write data " + writeData + " to address " + cache.converBinaryToHexa( address)+ "\n";
        }

        return acc;
    }

    public String executeRequest(int[] nrHits, int []nrMisses){
        String acc = "";
        acc+= this.prettyPrintRequest();
        if(requestType == 0){
            acc+=cache.readData(this.getAddress(), nrHits, nrMisses);
        }else{
            acc+=cache.writeData(this.getAddress(), this.getWriteData(), nrHits, nrMisses);
        }
        if(simulationView != null){
            simulationView.appendToTextAreaCpuRequests(acc+"\n\n\n");
        }
        return acc;
    }
}
