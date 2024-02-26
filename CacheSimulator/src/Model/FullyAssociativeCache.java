package Model;

public class FullyAssociativeCache extends SetAssociativeCache{
    // one way set associative => 1 set =>set id size  = 0

    public FullyAssociativeCache(Integer tagSize, Integer setIdSize, Integer wordIdSize, String[] mainMemory,
                                 int sizeCache, String replacementAlg, String writePolicy) {

        super(tagSize, 0, wordIdSize, mainMemory, sizeCache, replacementAlg, writePolicy);}
    @Override
    public void dummy() {
        super.dummy();
    }
}
