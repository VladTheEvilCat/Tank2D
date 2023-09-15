package types;

public interface AmmoContainer {

  public void setCount(AmmoType ofType, short toCount);
  public void setCount(short atIndex, short toCount);
  public AmmoType getType(short index);
  public short getCount(AmmoType ofType);
  public short getCount(short atIndex);
}
