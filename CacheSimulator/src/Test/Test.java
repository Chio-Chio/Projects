package Test;

import Model.DirectMappedCache;
import Model.FullyAssociativeCache;
import Model.Process;
import Model.SetAssociativeCache;

public class Test {
    static SetAssociativeCache cache;
    static SetAssociativeCache cacheCopy;
    Process[] requests;
    int[] nrHits = new int[1];
    int[] nrMisses = new int[1];
    static String alg = "random";
    String addressingMode;
    String[] mainMemory;


     static String setAssoc2Way = "Set-Associative 2-Way";
    static String setAssoc4Way = "Set-Associative 4-Way";
    static String fullyAssoc = "Fully Associative";
    static String directMap = "Direct Mapped";
    static String writePolicyWB = "Write Back";
    static String writePolicyWT = "Write Through";

    static Integer sizeCache = 8;
    static Integer sizeAddress = 32;
    static Integer sizeMainMemory = 1000;

    String[][] expectedDataCacheSetAssoc4Way =
            {
                    {"0", "1", "2", "3"},
                    {"10", "11", "12", "13"},
                    {"4", "5", "6", "7"},
                    {"14", "15", "16", "17"},
                    {"8", "9", "a", "b"},
                    {"18", "19", "1a", "1b"},

                    {"c", "d", "e", "f"},

                    {"1c", "1d", "1e", "1f"}
            };
    String[] expectedTagsCacheSetAssoc4Way = {
            "0", "1", "0", "1", "0", "1", "0", "1"
    };

    String[][] expectedDataCacheSetAssoc2Way =
            {
                    {"0", "1", "2", "3"},
                    {"8", "9", "a", "b"},
                    {"10", "11", "12", "13"},
                    {"18", "19", "1a", "1b"},
                    {"4", "5", "6", "7"},
                    {"c", "d", "e", "f"},
                    {"14", "15", "16", "17"},
                    {"1c", "1d", "1e", "1f"}
            };
    String[] expectedTagsCacheSetAssoc2Way ={
            "0", "1", "2", "3", "0", "1", "2", "3"
    };

    String[][] expectedDataCacheDirectedMappedAndFullyAssoc = {
            {"0", "1", "2", "3"},
            {"4", "5", "6", "7"},
            {"8", "9", "a", "b"},
            {"c", "d", "e", "f"},
            {"10", "11", "12", "13"},
            {"14", "15", "16", "17"},
            {"18", "19", "1a", "1b"},
            {"1c", "1d", "1e", "1f"}
    };

    String[] expectedTagsCacheFullyAssoc={
            "0", "1", "2", "3", "4", "5", "6", "7"
    };

    String[] expectedTagsCacheDirectMapped={
            "0", "0", "0", "0", "0", "0", "0", "0"
    };

    public String generateBinaryAddressFromInt(int givenInt){
        String binaryString = Integer.toBinaryString(givenInt);
        int padding = sizeAddress - binaryString.length(); // Assuming 32-bit representation
        String paddedBinaryString = "0".repeat(padding) + binaryString;
        return paddedBinaryString;
    }

    public SetAssociativeCache generateCorrectSetAssoc2wayCache(String writePolicy){
        cacheCopy = new SetAssociativeCache(sizeAddress - 3, 1, 2, mainMemory, sizeCache, alg, writePolicy);
        int setId = 0;
        for(int i = setId; i < sizeCache; i++){
            cacheCopy.addToCache(generateBinaryAddressFromInt(setId+4));
        }
        return cacheCopy;
    }
//    public Object[] generateMainMemory(int sizeMainMemory, int sizeAddress) {
//        String[] mainMemory = new String[sizeMainMemory]; // nr of lines in cache * 3
//        String zero32 = null;
//        for (int i = 0; i < sizeAddress; i++) {
//            zero32 += "0";
//        }
//
//        for (int i = 0; i < sizeMainMemory; i++) {
//            mainMemory[i] = zero32;
//        }
//
//        for (int i = 0; i < sizeMainMemory; i++) {
//            String binaryString = Integer.toBinaryString(i);
//            int padding = 32 - binaryString.length(); // Assuming 32-bit representation
//            String paddedBinaryString = "0".repeat(padding) + binaryString;
//            mainMemory[i] = paddedBinaryString;
//        }
//        return mainMemory;
//    }

    public String[] generateData(int sizeMainMemory) {
        String [] data = new String[sizeMainMemory]; // contains 2 digits in hexa
//        System.out.println("index, Integer index, indexToHex, data[i]");
        for (int i = 0; i < sizeMainMemory; i++) {
            Integer a = i;
            //String hexValue = Integer.toHexString(random.nextInt(16)) + Integer.toHexString(i);
//            if(a == 0){
//                data[i] = "00";
//            }else{
            String converted = new String(Integer.toHexString(a));
                data[i] = converted;
//                System.out.println(i+ " " +a+ " " + converted + " " + data[i]); // dt sare unele valori // dar aici local nu sare
//            }
        }
        return data;
    }

    public String[] generateMainMemory(){
        return generateData(sizeMainMemory);
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

    /*
    String alg1 = "random";

        String addressingMode = String.valueOf(startView.getComboBoxAddressingMode());
        System.out.println(addressingMode);

        Integer sizeAddress = startView.getComboBoxAddressSize();
        System.out.println(sizeAddress);

        Integer sizeCache = startView.getComboBoxCacheSize();
        System.out.println(sizeCache);

        String writePolicy = String.valueOf(startView.getComboBoxWritePolicy());
        System.out.println(writePolicy);
     */
    public Test(String addressingMode, Integer sizeCache, String writePolicy, String alg){

        mainMemory = generateMainMemory();


        int szAddress = sizeAddress;
        int szCache = sizeCache;



        if (addressingMode.equals("Set-Associative 2-Way")) {
            cache = new SetAssociativeCache(szAddress - 3, 1, 2, mainMemory, szCache, alg, writePolicy);
//            cacheCopy = new SetAssociativeCache(szAddress - 3, 1, 2, mainMemory, szCache, alg, writePolicy);
        } else {
            if (addressingMode.equals("Set-Associative 4-Way")) {
                cache = new SetAssociativeCache(szAddress - 4, 2, 2, mainMemory, szCache, alg, writePolicy);
                cacheCopy = new SetAssociativeCache(szAddress - 4, 2, 2, mainMemory, szCache, alg, writePolicy);
            } else {
                if (addressingMode.equals("Fully Associative")) {
                    cache = new FullyAssociativeCache(szAddress - 2, 0, 2, mainMemory, szCache, alg, writePolicy);
                    cacheCopy = new FullyAssociativeCache(szAddress - 2, 0, 2, mainMemory, szCache, alg, writePolicy);

                } else {
                    //Direct Mapped
                    int sizeSet = Integer.toBinaryString(szCache).length();
                    cache = new DirectMappedCache(szAddress - 7 - 2, 7, 2, mainMemory, szCache, alg, writePolicy);
                    cacheCopy = new DirectMappedCache(szAddress - 7 - 2, 7, 2,mainMemory, szCache, alg, writePolicy);
                }
            }
        }
    }

    public void populateCacheMemory(){
        int indexAddress = 0;
        int indexChunk = 0;
        int[] nrHits = new int[1];
        int[] nrMisses = new int[1];
        //System.out.println("chunk: " + indexChunk+ " address: " + indexAddress);
        while(!cache.isFull()){
            //Process request1 = new Process((String) mm[indexChunk][indexAddress], 0, "none", cache, null);
            //request1.executeRequest(nrHits ,nrMisses);
            cache.addToCache(generateBinaryAddressFromInt(indexAddress));
//            cacheCopy.addToCache(mainMemory[indexAddress]);
            indexAddress += 4;
        }
    }

    public int checkTags(SetAssociativeCache testedCache, String[] expectedTag){
        for(Integer i = 0; i < sizeCache; i++) {
            if ( Integer.parseInt(cache.tag(i), 2) != Integer.parseInt(expectedTag[i])) {
                System.out.println(testedCache.tag(i) +" != " +expectedTag[i]+"\n");
                return 0;
            }
        }
        return 1;
    }

    public int checkCacheData(SetAssociativeCache testedCache, String[][] expectedCacheData){
        for(int i = 0; i < sizeCache; i++){
            for(int j = 0; j < 4; j++){
                if(!testedCache.word(i, j). equals(expectedCacheData[i][j])){
                    return 0;
                }
            }
        }
        return 1;
    }

    public int checkCacheTagPassed(String cacheType, SetAssociativeCache testedCache){
        if(cacheType.equals(setAssoc2Way)){
            return checkTags(testedCache, expectedTagsCacheSetAssoc2Way);
        }
        if(cacheType.equals(setAssoc4Way)){
            return checkTags(testedCache, expectedTagsCacheSetAssoc4Way);
        }
        if(cacheType.equals(directMap)){
            return checkTags(testedCache, expectedTagsCacheDirectMapped);
        }

        if(cacheType.equals(fullyAssoc)){
            return checkTags(testedCache, expectedTagsCacheFullyAssoc);
        }
        return 0;
    }
    public int checkCacheDataPassed(String cacheType, SetAssociativeCache testedCache){
        if(cacheType.equals(setAssoc2Way)){
            return checkCacheData(testedCache, expectedDataCacheSetAssoc2Way);
        }
        if(cacheType.equals(setAssoc4Way)){
            return checkCacheData(testedCache, expectedDataCacheSetAssoc4Way);
        }
        if(cacheType.equals(directMap)){
            return checkCacheData(testedCache, expectedDataCacheDirectedMappedAndFullyAssoc);
        }

        if(cacheType.equals(fullyAssoc)){
            return checkCacheData(testedCache, expectedDataCacheDirectedMappedAndFullyAssoc);
        }
        return 0;
    }
    public int testNotNullData(String cacheType){
        String acc = "Test 1, "+ cacheType +": all cache memory (addresses) different form null";
//              System.out.printf(prettyPrintMainMemoryAndData(mainMemory, sizeMainMemory));
        populateCacheMemory();
        System.out.println(cache.prettyPrintCache());

        for(int i = 0 ; i< sizeCache; i++) {
            if(cache.getCacheMemory()[i] == null){
                System.out.println("FAILED " + acc);
                return 0;
            }

        }
        System.out.println("PASSED " + acc);
        return 1;

    }

    public static void main(String[] args) {
        Test test2way = new Test(setAssoc2Way, sizeCache , writePolicyWB, alg);

       // System.out.printf(prettyPrintMainMemoryAndData(test.nrChunks, test.sizeChunk));
        int totalPassedTest = 0;
        int totalTests = 12;

        totalPassedTest += test2way.testNotNullData(setAssoc2Way);
        totalPassedTest += test2way.checkCacheTagPassed(setAssoc2Way, cache);
        totalPassedTest += test2way.checkCacheDataPassed(setAssoc2Way, cache);

        Test test4way = new Test(setAssoc4Way, sizeCache , writePolicyWB, alg);
        totalPassedTest += test4way.testNotNullData(setAssoc4Way);
        totalPassedTest += test4way.checkCacheTagPassed(setAssoc4Way, cache);
        totalPassedTest += test4way.checkCacheDataPassed(setAssoc4Way, cache);

        Test testDirect = new Test(directMap, sizeCache , writePolicyWB, alg);
        totalPassedTest += testDirect.testNotNullData(directMap);
        totalPassedTest += testDirect.checkCacheTagPassed(directMap, cache);
        totalPassedTest += testDirect.checkCacheDataPassed(directMap, cache);

        Test testFully = new Test(fullyAssoc, sizeCache , writePolicyWB, alg);
        totalPassedTest += testFully.testNotNullData(fullyAssoc);
        totalPassedTest += testFully.checkCacheTagPassed(fullyAssoc, cache);
        totalPassedTest += testFully.checkCacheDataPassed(fullyAssoc, cache);


        System.out.println("Total passed tests: "+totalPassedTest+"\n"+"Nr of tests: "+ totalTests+"\n");
    }
}
