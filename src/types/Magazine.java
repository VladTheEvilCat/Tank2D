package types;

//import java.util.ArrayList;

public class Magazine implements AmmoContainer{
  private AmmoType[] sequence;
  private short sequenceCount;
  private int currentCapacity = 0;
  private byte currentSequenceIndex = 0;

  Magazine(AmmoType[] sequence, short sequenceCount){
    this.sequence = sequence;
    this.sequenceCount = sequenceCount;
    this.currentCapacity = this.sequence.length * this.sequenceCount;
  }

  // private AmmoType[] getTypes(){
  //   ArrayList<AmmoType> types = new ArrayList<>(Ammo.numberOfTypes);
  //   for(AmmoType t:this.sequence)
  //     for(AmmoType u:types)
  //       if(t!=u)
  //         types.add(t);
  //   return (AmmoType[])types.toArray();
  // }

  public AmmoType unload(){
    if(this.currentCapacity==0) return AmmoType.PLACE_HOLDER;
    this.currentCapacity-=1;
    if(currentSequenceIndex+1<=this.sequence.length-1 && this.sequenceCount>=1){
      this.sequenceCount--;
      this.currentSequenceIndex = (byte) (this.currentSequenceIndex % sequence.length);
    }
    return true;
  }

  @Override
  public void setCount(AmmoType ofType, short toCount) {
    // for(short i = 0;i<this.sequence.length;i++){
    //   if(this.sequence[i] == ofType)
    //     this.count[i] = toCount;
    // }
  }

  @Override
  public void setCount(short atIndex, short toCount){
    //beep
  }

  @Override
  public AmmoType getType(short index) {
    return this.sequence[index];
  }

  @Override
  public short getCount(AmmoType ofType) {
    for(short i = 0;i<this.sequence.length;i++)
      if(this.sequence[i] == ofType)
        return 1;
    return 0;
  }

  @Override
  public short getCount(short atIndex) {
    return 1;//this.count[atIndex];
  }
}
