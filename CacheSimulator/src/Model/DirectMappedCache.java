package Model;

import static java.lang.Integer.parseInt;

public class DirectMappedCache extends SetAssociativeCache {
    // no replacement algorithm
    // n ways set associative mapped => n sets => n = size of cache memory;

    public DirectMappedCache(Integer tagSize, Integer setIdSize, Integer wordIdSize, String[] mainMemory,
                             int sizeCache, String replacementAlg, String writePolicy) {

                super(tagSize, 7, wordIdSize, mainMemory, sizeCache, replacementAlg, writePolicy);
    }

//    public String writeData(String address, String data) {
//        String tag = address.substring(0, tagSize);
//        String setId = address.substring(tagSize, tagSize + setIdSize);
//        String wordId = address.substring(tagSize + setIdSize, tagSize + setIdSize + wordIdSize);
//        return "";
//    }

//    public String readData(String address) {
//        String tag = address.substring(0, tagSize);
//        String setId = address.substring(tagSize, tagSize + setIdSize);
//        String wordId = address.substring(tagSize + setIdSize, tagSize + setIdSize + wordIdSize);
//        return "";
//    }

//    @Override
//    public String addToCache(String address) {
//        String tag = address.substring(0, tagSize);
//        String setId = address.substring(tagSize, tagSize + setIdSize);
//        String wordId = address.substring(tagSize + setIdSize, tagSize + setIdSize + wordIdSize);
//
//        Integer[] startBlock = findStartBlock(tag, numberChunks, sizeChunk);
//
//        int line = parseInt(setId, 2);
//        for(int i = 0; i < nrWords; i++) {
//            cacheMemory[line] = tag;
//            cacheData[line][i] = dt[startBlock[0]][startBlock[1]+i].toString();
//        }
//        // TODO:write back and throu
//        String acc =">>added to cache: fullAddress = " + address + " that consists of:\n>> tag = " + tag + " \n>setid = "
//                + setId + " (in decimal :" +line + ") wordId = "+ wordId +"( in decimal: "+parseInt(wordId, 2) +" )\n";
//        for (int i = 0; i < nrWords; i++) {
//            acc = acc + ">>> " + i + ") " + cacheData[line][i] + " ";
//        }
//        acc+= "\n>>at cache line: " + line+"\n";
//        acc+="> Block starts in main memory from: \n>>> [ "+startBlock[0] +" ][ " + startBlock[1]+" ]\n";
//        // acc+= "\n";
//        return acc;
//    }


    @Override
    public void dummy() {
        super.dummy();
    }
}
