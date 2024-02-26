import Controller.StartController;
import Model.SetAssociativeCache;
//import Test.Test;
import View.StartView;

import java.util.Random;


public class Main {
//   public void generateMainMemory

//    public static Object[] generateChunkMainMemory(int pos, int addtoStart, int sizeChunk) {
//        String[] mainMemory = new String[sizeChunk]; // nr of lines in cache * 3
//        String zero32 = null;
//        for (int i = 0; i < 32; i++) {
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
//
//    public static Object[] generateData(int pos, int sizeChunk) {
//        String[] data = new String[sizeChunk]; // contains 2 digits in hexa
//        for (int i = 0; i < sizeChunk; i++) {
//            Random random = new Random();
//            String hexValue = Integer.toHexString(random.nextInt(16)) + Integer.toHexString(random.nextInt(16)); // genrerate a hex nr of 2
//            data[i] = hexValue;
//        }
//        return data;
//    }

    public static void main(String[] args) {
        StartView startView = new StartView();
        StartController startController = new StartController(startView);

       // SimulationView simulationView = new SimulationView();

//        String alg1 = "random";
//
//        // Tests:
//
//
//        Integer sizeChunk = 64;
//        Integer smallValue = 192;
////        String [] mainMemory = new String[sizeMainMemory]; // nr of lines in cache * 3
////        String [] data = new String[sizeMainMemory]; // contains 2 digits in hexa
//
//        Object[][] mm = new Object[5][];
//        /*
//            mm is the "main memory", nor real life main memory, simulated main memory
//            mm is made out of multiple main memory chunks,
//                in one chunk there are <sizeChunk>s consecutive binary 32-bit addresses
//                starting from the number <addtoStart> (parameter of generateChunkMainMemory),
//
//         */
//
//        Object[][] dt = new Object[5][];
//        /*
//            dt contains data (2 hexa digits) that is at the address in mm
//            address m[i][j] has data dt[i][j]
//         */
//
//        mm[0] = generateChunkMainMemory(0, 0, sizeChunk);
//        dt[0] = generateData(0, sizeChunk);
//
//        mm[1] = generateChunkMainMemory(1, 1024, sizeChunk);
//        dt[1] = generateData(1, sizeChunk);
//
//        mm[2] = generateChunkMainMemory(2, 1024 * 1024, sizeChunk);
//        dt[2] = generateData(2, sizeChunk);
//
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < sizeChunk; j++) {
//                System.out.println("address = " + mm[i][j] + ", data = " + dt[i][j]);
//            }
//            System.out.println("next chunk:");
//        }
//
//
//        //0123456789
//        //00000000000000000000010000110011
//
//        SetAssociativeCache cache = new SetAssociativeCache(32-4, 2, 2, mm, dt, 64);
//        cache.printCacheToConsole();
//        cache.addToCache("00000000000000000000010000110011", 3, sizeChunk, alg1); // example of procces request
//        cache.printCacheToConsole();
//        // wants the address above => adds to cahce the hole blocl
//        cache.addToCache("00000000000000000000010000110001", 3, sizeChunk, alg1);
//        cache.printCacheToConsole();
//        cache.addToCache("00000000000000000000000000000000", 3, sizeChunk, alg1);
//        cache.printCacheToConsole();
    }
}