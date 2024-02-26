package Model;

import java.util.Random;
import java.util.concurrent.TransferQueue;

import static java.lang.Integer.parseInt;

public class SetAssociativeCache {
    /*
        for each cacheMemory[i]
        there is cacheData[i] that holds <nrLines in one Block> 2octeti: "CF" (a string)
            cacheData[i][#wordNr], #wordNr ranges form 0 to 2^wordIdSize
     */
    private String[] cacheMemory;
    private Boolean[] dirtyBit;
    private Object[][] cacheData;
    private Integer sizeCache;
    private Integer tagSize;
    private Integer wordIdSize;
    private Integer setIdSize;

    public Object[][] mm;
    public Object[][] dt;

    private Integer numberChunks;
    private Integer sizeChunk;
    private String replacementAlg;
    private Integer sizeOfOneSet;
    private Integer nrSets;
    Integer nrWords;

    private String writePolicy;
    private String[] mainMemory;


    public SetAssociativeCache() {
    }

    public SetAssociativeCache(Integer tagSize, Integer setIdSize, Integer wordIdSize, String[] mainMemory,
                               int sizeCache, String replacementAlg, String writePolicy) {
        // create the size of the field in cache memory
        this.sizeCache = sizeCache;
        this.tagSize = tagSize;
        this.setIdSize = setIdSize;
        this.wordIdSize = wordIdSize;
        this.cacheMemory = new String[sizeCache];
        this.writePolicy = writePolicy;
        this.mainMemory = mainMemory;

        nrWords = numberAddressesInBlock();
        this.cacheData = new Object[sizeCache][nrWords];
        this.nrSets = numberSets();
        if(nrSets >0) {
            this.sizeOfOneSet = sizeCache / nrSets;
        }else{
            this.sizeOfOneSet = sizeCache;
        }

        this.replacementAlg = replacementAlg;
        this.dirtyBit = new Boolean[sizeCache];
        for(int  i = 0; i < sizeCache; i++){
            dirtyBit[i] = Boolean.FALSE;
        }
    }

    private int startAddressOfBlock(String givenAddress){
        int integerAddress = parseInt(givenAddress, 2);
        int rest = 0;
        if(sizeOfOneSet == 0) {
            rest = integerAddress % sizeOfOneSet;
        }
        return integerAddress - rest;

    }
    public String[] getCacheMemory() {
        return cacheMemory;
    }

//    public void setCacheMemory(String[] cacheMemory) {
//        this.cacheMemory = cacheMemory;
//    }
//
//    public Object[][] getCacheData() {
//        return cacheData;
//    }
//
//    public void setCacheData(Object[][] cacheData) {
//        this.cacheData = cacheData;
//    }
//
//    public Integer getSizeCache() {
//        return sizeCache;
//    }
//
//    public void setSizeCache(Integer sizeCache) {
//        this.sizeCache = sizeCache;
//    }
//
//    public Integer getTagSize() {
//        return tagSize;
//    }
//
//    public void setTagSize(Integer tagSize) {
//        this.tagSize = tagSize;
//    }
//
//    public Integer getWordIdSize() {
//        return wordIdSize;
//    }
//
//    public void setWordIdSize(Integer wordIdSize) {
//        this.wordIdSize = wordIdSize;
//    }
//
//    public Integer getSetIdSize() {
//        return setIdSize;
//    }
//
//    public void setSetIdSize(Integer setIdSize) {
//        this.setIdSize = setIdSize;
//    }
//
//    public Object[][] getMm() {
//        return mm;
//    }
//
//    public void setMm(Object[][] mm) {
//        this.mm = mm;
//    }
//
//    public Object[][] getDt() {
//        return dt;
//    }
//
//    public void setDt(Object[][] dt) {
//        this.dt = dt;
//    }
//
//    public Integer calculateSizeAddressCacheMemory() {
//        return tagSize + numberAddressesInBlock() * wordIdSize;
//    }

//    public void init(){
//        String tag = address.substring(0, tagSize);
//        //Integer nrWords = numberAddressesInBlock();
//        String setId = address.substring(tagSize, tagSize + setIdSize);
//        String wordId = address.substring(tagSize + setIdSize, tagSize + setIdSize+ wordIdSize);
//        Integer[] startBlock = findStartBlock(tag, numberChunks, sizeChunk); //coordinated in main memory
//    }
    public Integer numberAddressesInBlock() {
        // 2^wordIdSize give the number of addresses in a block
        Integer acc = 1;
        for (Integer i = 0; i < wordIdSize; i++) {
            acc *= 2;
        }
        return acc;
    }

    public Integer numberSets() {
        // 2^wordIdSize give the number of addresses in a block
        Integer acc = 1;
        for (Integer i = 0; i < setIdSize; i++) {
            acc *= 2;
        }
        return acc;
    }

    public Integer findStartOfChunk(String address) {
        /*
            find the start of the chunk
            ex: wordid size = 2 => 4 addresses in one chunk,
                given address in dec: 3
                => address starts at 0, obtained by (3 - (3 % 4))
         */
        int nrAddressesInChunk = numberAddressesInBlock();
        int addressDecimal = parseInt(address, 2);
        int rest = addressDecimal % nrAddressesInChunk;
        return addressDecimal - rest;

    }

    public int replaceRandom(String tag, Integer startSet, Integer sizeOfOneSet, Integer startBlock){//, String newData) {
        // returns the line of cache address was placed

        // get the data from main data
        String[] blockData = new String[nrWords];
        for (int i = 0; i < nrWords; i++) {
            blockData[i] = mainMemory[startBlock + i].toString();

        }

        if (sizeOfOneSet > 1) {
            Random random = new Random();
            int randomNumber = random.nextInt(sizeOfOneSet);  // generate random number between 0 and sizeOfSet
            Integer replaceLine = startSet + randomNumber;
            // TODO based on write policy, sent to main memoryx
            // check dirty bit
            // TODO: HERE IS WRITING TO CACHE MEMORY x

            //writePolicyReplacement(replaceLine, pos);
            cacheMemory[replaceLine] = tag;
            if("Write Through".equals(this.writePolicy)){
                // modify the data from main memory
                for(int i = 0; i< nrWords; i++) {
//                    dt[chunkAndLine[0]][chunkAndLine[1] + i] = cacheData[replaceLine][i];
                    mainMemory[startBlock + i] = (String) cacheData[replaceLine][i];
                }
            }else {
                if (this.writePolicy.equals("Write Back")) {
                    if(dirtyBit[replaceLine] == Boolean.TRUE){
                        // write to main mememory data
                        for(int i = 0; i< nrWords; i++) {
                            mainMemory[startBlock + i] = (String) cacheData[replaceLine][i];
                        }
                        dirtyBit[replaceLine] = Boolean.FALSE;
                    }else{
                        for(int i = 0; i< nrWords; i++) {
                            mainMemory[startBlock + i] = (String) cacheData[replaceLine][i];
                        }
                    }
                }
            }


            // TO DO UPDATE CACHE DATA
//            for(int i = 0; i < nrWords; i++) {
//                cacheData[replaceLine] =;// data
//            }
            return replaceLine;
        } else {
            //writePolicyReplacement(startSet, pos);
            cacheMemory[startSet] = tag;
            if(this.writePolicy.equals("Write Through")){
                // modify the data from main memory
                for(int i = 0; i< nrWords; i++) {
                    mainMemory[startBlock + i] = (String) cacheData[startSet][i];
                }
            }else {
                if (this.writePolicy.equals("Write Back")) {
                    if(dirtyBit[startSet] == Boolean.TRUE){
                        // write to main mememory data
                        for(int i = 0; i< nrWords; i++) {
                            mainMemory[startBlock + i] = (String) cacheData[startSet][i];
                        }
                        dirtyBit[startSet] = Boolean.FALSE;
                    }else{
                        for(int i = 0; i< nrWords; i++) {
                            mainMemory[startBlock + i] = (String) cacheData[startSet][i];
                        }
                    }
                }
            }
//            cacheData[replaceLine]  = ;// data
            return startSet;
        }
    }

    public int findTagInCache(String tag, Integer startOfSet, Integer endOfSet){
        if(sizeOfOneSet == 0){
            if (cacheMemory[startOfSet] != null && cacheMemory[startOfSet].equals(tag)) {
                return startOfSet;
            }
        }
        for (int i = startOfSet; i < endOfSet; i++) {
            if (cacheMemory[i] != null && cacheMemory[i].equals(tag)) {
                return i;
            }
        }
        return -1;
    }

    public String[] setTagSetIdWordId(String address){
        String tag ="";
        String setId ="";
        String wordId ="";
        String[] all = new String[3];

        if(setIdSize == sizeCache){
            tag = address.substring(0, tagSize);
            wordId = address.substring(tagSize, tagSize + wordIdSize);

        }else{
            tag = address.substring(0, tagSize);
            setId = address.substring(tagSize, tagSize + setIdSize);
            wordId = address.substring(tagSize + setIdSize, tagSize + setIdSize+ wordIdSize);

        }

        all[0] = tag;
        all[1] = setId;
        all[2] = wordId;
        return all;
    }
    public String addToCache(String address) {
        /*
            get set id form main address

            entering this method it is know the addres does not exit in cache
         */

        String[] all = setTagSetIdWordId(address);
        String tag = all[0];
        String setId = all[1];
        String wordId = all[2];


//        Integer[] startBlock = findStartBlock(tag, numberChunks, sizeChunk); //coordinated in main memory
        Integer mainMemoryPosition = findStartOfChunk(address);// convert the binary address to decimal
        // as in [0] is chunk
        //       [1] is line of chunk

        //String currentCacheAddress =  tag;
//        String currentCacheAddress =  tag+ setId ;
        // obtain the data from main memory
        String[] wordIds = new String[nrWords];
        String[] blockData = new String[nrWords];


        // add address to cache memory

//        Integer nrSets = numberSets();
//        Integer sizeOfOneSet = sizeCache / nrSets;
        Integer setNrInDec;
        if (setId.equals("")) {
            setNrInDec = 0;
        } else {
            setNrInDec = parseInt(setId, 2);
        }
        Integer startOfSet = sizeOfOneSet * setNrInDec;


        Integer endOfSet = startOfSet + sizeOfOneSet;

        if(sizeOfOneSet == 0){ // directly mapped
            endOfSet = startOfSet + sizeOfOneSet - 1;
            startOfSet = parseInt(setId, 2);
        }
        //add to an empty line
        // if there are none, use replacement algorithm

        for (int i = 0; i < nrWords; i++) {
            blockData[i] = mainMemory[mainMemoryPosition + i].toString();
        }
        //int placedAtCacheLine = placeInCache(tag, startOfSet, endOfSet, wordIds, blockData); //function
        int placedAtCacheLine = -1;

        if(endOfSet == -1){ // Direct mapped
            cacheMemory[startOfSet] = tag;
            for (int j = 0; j < nrWords; j++) {
                // modify data
                // TODO: HERE IS WRITING TO CACHE MEMORY
                //writePolicyReplacement(startOfSet, startBlock);
                cacheData[startOfSet][j] = blockData[j];
                // Delete under?
//                if(this.writePolicy.equals("Write Through")){
//                    // modify the data from main memory
//                    dt[startBlock[0]][startBlock[1] +j] = blockData[j];
//                }else{
//                    if(this.writePolicy.equals("Write Back")) {
//                        // set dirty bit
//                    }
//                }
            }
            placedAtCacheLine = startOfSet;
        }

        for (int i = startOfSet; i < endOfSet && placedAtCacheLine == -1; i++) {
            if (cacheMemory[i] == null) {
               // cacheMemory[i] = tag;
                for (int j = 0; j < nrWords; j++) {
                    // modify data
                    // TODO: HERE IS WRITING TO CACHE MEMORY
                    // todo: here i don't think it need a write policy bc data is not modified in the cache
                    // todo: space is empty
                    //writePolicyReplacement(i, startBlock)
                    cacheData[i][j] = blockData[j];
                }
                cacheMemory[i] = tag;
                placedAtCacheLine = i ;
            }
        }

        if (placedAtCacheLine == -1) {
            if (replacementAlg.equals("random")) {
                placedAtCacheLine = replaceRandom(tag, startOfSet, sizeOfOneSet, mainMemoryPosition);
            } else {
                System.out.println("invalid algorithm. not implemented");
            }
        }
        String acc = ">";
//        System.out.println(">added to cache: fullAddress = " + address + " that consists of: tag = " + tag + " setid = " + setId + " (in decimal :" + parseInt(setId, 2) + ")");
//        System.out.println(">>words: ");
//        for (int i = 0; i < nrWords; i++) {
//            System.out.println(">> " + i + ") " + blockData[i]);
//        }
        int setIdDec = 0;
        if(setId.equals("")){
            setIdDec = 0;
        }else{
            setIdDec =parseInt(setId, 2);
        }
        acc = acc + ">added to cache: fullAddress = " + converBinaryToHexa(address) + "("+address+")"+ " that consists of:\n>> tag = " + converBinaryToHexa(tag) + " \n>setid = "
                + setId + " (in decimal :" +setIdDec + ") wordId = "+ wordId +"( in decimal: "+parseInt(wordId, 2) +" )\n";
        for (int i = 0; i < nrWords; i++) {
            acc = acc + ">>> " + i + ") " + blockData[i] + " ";
        }
        acc+= "\n>>at cache line: " + placedAtCacheLine+"\n";
        acc+="> Block starts in main memory from: \n>>> [ "+ mainMemoryPosition +" ] \n";
       // acc+= "\n";
        System.out.println(acc);

        return acc;

    }
    
    public String readData(String address, int[] nrHits, int[] nrMisses){
        /*
            find the location of the asked tag in cache memory
            if no such tag foud, add the address (together with the whole block) to cache
                get the line nr where the address was placed
            print the read data
         */
        //, String tag, String setId , String wordId
        String[] all = new String[3];
        all = setTagSetIdWordId(address);

        String tag =all[0];
        String setId =all[1];
        String wordId =all[2];

        String acc = "";
        int startOfSet;
        if(setId.equals("")){
             startOfSet = 0;
        }else {
            startOfSet = sizeOfOneSet * parseInt(setId, 2);

        }

        int endOfSet =    startOfSet + sizeOfOneSet - 1    ;
        if(sizeOfOneSet == 0){
            startOfSet = parseInt(setId, 2);
        }
        int nrLineCacheOfAddress = findTagInCache(tag, startOfSet, endOfSet);
        if(nrLineCacheOfAddress == -1){
            // here is a miss
            acc += addToCache(address);
            nrLineCacheOfAddress = findTagInCache(tag, startOfSet, endOfSet);
            nrMisses[0]++;
        }else{
            //hit
            nrHits[0] ++;
        }

        acc += ">data read from address " + converBinaryToHexa( address)+ "("+address+")"+"(cacheLine : "+nrLineCacheOfAddress+"  | ):\n>>" + cacheData[nrLineCacheOfAddress][parseInt(wordId, 2)] +"\n\n";
        System.out.println(acc);
        return acc;

    }

    public String writeData(String address, String data, int[] nrHits, int[] nrMisses){
        String[] all = new String[3];
        all = setTagSetIdWordId(address);

        String tag =all[0];
        String setId =all[1];
        String wordId =all[2];

        String acc = "";
        int setIdDec =0;
        if(!setId.equals("")){
            setIdDec = parseInt(setId, 2);
        }
        int startOfSet = sizeOfOneSet * setIdDec;

        int endOfSet =    startOfSet + sizeOfOneSet - 1    ;
        if(sizeOfOneSet == 0){
            startOfSet = parseInt(setId, 2);
        }
        int nrLineCacheOfAddress = findTagInCache(tag, startOfSet, endOfSet);

        Integer startBlock = findStartOfChunk(address);

        if(nrLineCacheOfAddress == -1){
            // here is a miss
            acc += addToCache(address);
            nrLineCacheOfAddress = findTagInCache(tag, startOfSet, endOfSet);
            nrMisses[0]++;
        }else{
            //hit
            // TODO: HERE IS WRITING TO CACHE MEMORY


            if(this.writePolicy.equals("Write Through")){
                // modify the data from main memory
                mainMemory[startBlock + parseInt(wordId, 2)] = data; // good
            }else{
                if(this.writePolicy.equals("Write Back")){
                    if(dirtyBit[nrLineCacheOfAddress] == Boolean.TRUE){
                        mainMemory[startBlock + parseInt(wordId, 2)] = (String) cacheData[nrLineCacheOfAddress][parseInt(wordId, 2)];
                        dirtyBit[nrLineCacheOfAddress] = Boolean.FALSE;
                    }else {
                        dirtyBit[nrLineCacheOfAddress] = Boolean.TRUE;
                    }
                }
            }

            cacheData[nrLineCacheOfAddress][parseInt(wordId, 2)] = data;
            // after the cacheData is writen, need to makrk the dirty bit
            if(this.writePolicy.equals("Write Back")){
                dirtyBit[nrLineCacheOfAddress] = Boolean.TRUE;

            }

            nrHits[0]++;
        }
        acc += ">data written at address [" + startBlock+ "] "
                + converBinaryToHexa(address) + "("+address+")"+"(cacheLine : "+nrLineCacheOfAddress+" | ):\n>>"
                + cacheData[nrLineCacheOfAddress][parseInt(wordId, 2)] +"\n\n";
        System.out.println(acc);
        return acc;
    }


    public void printCacheToConsole() {
        Integer linenr = 0;
        Integer nrSets = numberSets();
        Integer currentSet = 0;
        Integer sizeOfOneSet = sizeCache / nrSets;
        int count = 0;
        for (int i = 0; i < sizeCache; i++) {
            System.out.print("set " + currentSet + " line " + linenr + ") " + cacheMemory[i] + " words: ");
            for (int j = 0; j< nrWords; j++){
                System.out.print(cacheData[i][j] +" ");
            }
            System.out.println();
            linenr++;
            if (count < sizeOfOneSet - 1) {
                count++;
            } else {
                currentSet++;
                count = 0;
                linenr = 0;
            }
        }
        //
        // System.out.println();
    }

    public String converBinaryToHexa(String binary){
        if(binary != null){
          int num = Integer.parseInt(binary, 2);
          return Integer.toHexString(num);
        }
        return "null";
    }
    public String prettyPrintCache() {
        String acc = "";
        Integer linenr = 0;
        Integer nrSets = numberSets();
        Integer currentSet = 0;
        if(nrSets > 0) {
            Integer sizeOfOneSet = sizeCache / nrSets;
        }else{
            Integer sizeOfOneSet = sizeCache;
        }
        int count = 0;
        acc += "                                 tag       /   words\n";
        for (int i = 0; i < sizeCache; i++) {

            acc = acc +i+ "     |       set " + currentSet + "       line " + linenr + ")       " + converBinaryToHexa(cacheMemory[i]) + " words: ";
            for (int j = 0; j< nrWords; j++){
                acc = acc + cacheData[i][j] +" ";
            }
            acc +="\n";
            linenr++;
            if (count < sizeOfOneSet - 1) {
                count++;
            } else {
                currentSet++;
                count = 0;
                linenr = 0;
            }
        }
        acc+="\n\n\n";
        return acc;
    }

//    public boolean isEmpty(){
//        for(int i = 0; i < sizeCache; i++){
//            if(cacheMemory[i] != null){
//                return false;
//            }
//        }
//        return true;
//    }

    public boolean isFull(){
        boolean isFull = true;
        int emptySpaces = 0;
        for(int i = 0; i < sizeCache; i++){
            if(cacheMemory[i] == null ){
                isFull = false;
                emptySpaces++;
            }
        }
       // System.out.println("----------------------------------------------------Empty plases in cache: "+ emptySpaces);
        return isFull;
    }

    public String tag(Integer lineCache){
        return cacheMemory[lineCache];
    }

    public String word(Integer lineCache, Integer wordId){
        return (String) cacheData[lineCache][wordId];
    }

    public void dummy() {
        return;
    }
}
