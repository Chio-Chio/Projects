package Model;

import Controller.SimulationController;
import Controller.StartController;
import View.SimulationView;
import View.StartView;

import java.util.Random;

import static java.lang.Integer.parseInt;

public class Simulator {
    private SimulationView simulationView;
    private StartView startView;
    SetAssociativeCache cache;
    int nrChunks;
    Process[] requests;
    int[] nrHits = new int[1];
    int[] nrMisses = new int[1];


//    public Object[] generateChunkMainMemory(int pos, int addtoStart, int sizeChunk, int sizeAddress) {
//        String[] mainMemory = new String[sizeChunk]; // nr of lines in cache * 3
//        String zero32 = null;
//        for (int i = 0; i < sizeAddress; i++) {
//            zero32 += "0";
//        }
//
//        for (int i = 0; i < sizeChunk; i++) {
//            mainMemory[i] = zero32;
//        }
//
//        for (int i = 0; i < sizeChunk; i++) {
//            String binaryString = Integer.toBinaryString(i + addtoStart);
//            int padding = 32 - binaryString.length(); // Assuming 32-bit representation
//            String paddedBinaryString = "0".repeat(padding) + binaryString;
//            mainMemory[i] = paddedBinaryString;
//        }
//        return mainMemory;
//    }

    public String[] generateData(int sizeChunk) {
        String[] data = new String[sizeChunk]; // contains 2 digits in hexa
        for (int i = 0; i < sizeChunk; i++) {
            Random random = new Random();
            String hexValue = Integer.toHexString(random.nextInt(16)) + Integer.toHexString(random.nextInt(16)); // genrerate a hex nr of 2
            data[i] = hexValue;
        }
        return data;
    }

    public String prettyPrintMainMemoryAndData(String[] mainMemory, int sizeMainMemory) {
        String acc = "memory and data\n";
        for (Integer i = 0; i < sizeMainMemory; i++) {
            //System.out.println("address = " + cache.converBinaryToHexa((String) mm[i][j]) + ", data = " + dt[i][j]);
            acc = acc + "[ " + i + " ] address = " + Integer.toHexString(i)  + ", data = " +mainMemory[i] + "\n";
        }
        acc += "\n\n\n\n\n\n";
        return acc;
    }

    public Simulator(SimulationView simulationView) {
        this.simulationView = simulationView;
    }

    public Simulator(SimulationView simulationView, StartView startView) {
        this.simulationView = simulationView;
        this.startView = startView;
    }

    public void printRequest(Process request, int reqNr, String[] mainMemory, int sizeMainMemory) {
        simulationView.appendToTextAreaCpuRequests("REQUEST#" + reqNr + "\n");
        request.executeRequest(nrHits, nrMisses);
        simulationView.appendToTextAreaCacheMemory("\nREQUEST#" + reqNr + "\n" + cache.prettyPrintCache());

        simulationView.appendToTextAreaMainMemory("\n\nREQUEST#" + reqNr + "\n" + prettyPrintMainMemoryAndData(mainMemory, sizeMainMemory) + "\n\n");
        simulationView.appendTextAreaHitMiss("REQUEST#" + reqNr + "\nnrHits: " + nrHits[0] +"\nnrMisses: "+nrMisses[0] +"\n\n");



    }

    public void simulate() {
        String alg1 = "random";

        String addressingMode = String.valueOf(startView.getComboBoxAddressingMode());
        System.out.println(addressingMode);

        Integer sizeAddress = startView.getComboBoxAddressSize();
        System.out.println(sizeAddress);

        Integer sizeCache = startView.getComboBoxCacheSize();
        System.out.println(sizeCache);

        String writePolicy = String.valueOf(startView.getComboBoxWritePolicy());
        System.out.println(writePolicy);

        // Tests:
        Object[][] mm = new Object[5][];
        /*
            mm is the "main memory", nor real life main memory, simulated main memory
            mm is made out of multiple main memory chunks,
                in one chunk there are <sizeChunk>s consecutive binary 32-bit addresses
                starting from the number <addtoStart> (parameter of generateChunkMainMemory),

         */

        Object[][] dt = new Object[5][];
        /*
            dt contains data (2 hexa digits) that is at the address in mm
            address m[i][j] has data dt[i][j]
         */


        Integer sizeChunk = 64;
        nrChunks = 3;

//        mm[0] = generateChunkMainMemory(0, 0, sizeChunk, sizeAddress);// make one chunk
//        dt[0] = generateData(0, sizeChunk);
//
//        mm[1] = generateChunkMainMemory(1, 1024, sizeChunk, sizeAddress);
//        dt[1] = generateData(1, sizeChunk);
//
//        mm[2] = generateChunkMainMemory(2, 1024 * 1024, sizeChunk, sizeAddress);
//        dt[2] = generateData(2, sizeChunk);

        int sizeMM = 2000;
        String[] mainMemory = new String[sizeMM];
        mainMemory = generateData(sizeMM);


        if (addressingMode.equals("Set-Associative 2-Way")) {
            cache = new SetAssociativeCache(sizeAddress - 3, 1, 2, mainMemory, sizeCache, alg1, writePolicy);
        }
        else {
            if (addressingMode.equals("Set-Associative 4-Way")) {
                cache = new SetAssociativeCache(sizeAddress - 4, 2, 2, mainMemory, sizeCache, alg1, writePolicy);
            } else {
                if (addressingMode.equals("Fully Associative")) {
                    cache = new FullyAssociativeCache(sizeAddress - 2, 0, 2, mainMemory, sizeCache, alg1, writePolicy);

                } else {
                    //Direct Mapped
                    int sizeSet = Integer.toBinaryString(sizeCache).length();
                    cache = new DirectMappedCache(sizeAddress - 7 - 2, 7, 2, mainMemory, sizeCache, alg1, writePolicy);


                }
            }
        }
        //        requests = new Process[4];
        Process request1 = new Process("00000000000000000000010000110011", 0, "none", cache, simulationView);
        Process request2 = new Process("00000000000000000000010000110010", 0, "none", cache, simulationView);
        Process request3 = new Process("00000000000000000000010000110011", 1, "77", cache, simulationView);
        Process request4 = new Process("00000000000000000000000000000000", 0, "none", cache, simulationView);
        Process request5 = new Process("00000000000000000000010000110011", 1, "99", cache, simulationView);
        Process request6 = new Process("00000000000000000000010000110011", 1, "11", cache, simulationView);


        simulationView.appendToTextAreaCacheMemory(cache.prettyPrintCache());
//        simulationView.appendToTextAreaMainMemory(prettyPrintMainMemoryAndData(mainMemory, sizeMM));

        printRequest(request1, 1, mainMemory, sizeMM);

        printRequest(request2, 2, mainMemory, sizeMM);
        printRequest(request3, 3, mainMemory, sizeMM);
        printRequest(request4, 4, mainMemory, sizeMM);
        printRequest(request5, 5, mainMemory, sizeMM);
        printRequest(request6, 6, mainMemory, sizeMM);
    }

    public SetAssociativeCache getCache() {
        return cache;
    }

    public void setCache(SetAssociativeCache cache) {
        this.cache = cache;
    }
}
