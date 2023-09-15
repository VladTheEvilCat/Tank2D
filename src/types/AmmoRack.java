package types;

public class AmmoRack implements AmmoContainer {
  private AmmoType[] types;
  private short[] count;
  private short maxCapacity;
  private short currentCapacity;

  AmmoRack(AmmoType[] types, short[] count, short maxCapacity){
    this.types = types;
    this.count = count;
    this.currentCapacity = 0;
    this.maxCapacity = maxCapacity;
  }

  // private AmmoType[] getTypes(){
  //   ArrayList<AmmoType> types = new ArrayList<>(Ammo.numberOfTypes);
  //   for(AmmoType t:this.sequence)
  //     for(AmmoType u:types)
  //       if(t!=u)
  //         types.add(t);
  //   return (AmmoType[])types.toArray();
  // }

  @Override
  public void setCount(AmmoType ofType, short toCount) {
    for(short i = 0;i<this.types.length;i++){
      if(this.types[i] == ofType){
        if((this.currentCapacity + toCount)>this.maxCapacity) toCount = (short) (this.maxCapacity-this.currentCapacity); // Y do i hafta cast to short????!
          this.count[i] = toCount;
          this.currentCapacity = this.maxCapacity;
      }
    }
  }

  @Override
  public void setCount(short atIndex, short toCount) {
    if((this.currentCapacity + toCount)>this.maxCapacity) toCount = (short) (this.maxCapacity-this.currentCapacity); // Y do i hafta cast to short????!
      this.count[atIndex] = toCount;
      this.currentCapacity = this.maxCapacity;
  }

  @Override
  public AmmoType getType(short index) {
    return this.types[index];
  }

  @Override
  public short getCount(AmmoType ofType) {
    for(short i = 0;i<this.types.length;i++)
      if(this.types[i] == ofType)
        return this.count[i];
    return 0;
  }

  @Override
  public short getCount(short atIndex) {
    return this.count[atIndex];
  }
}
