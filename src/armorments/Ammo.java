package armorments;

public enum Ammo {
	
	AP_8MM(8,16,1200),
	AP_12MM(16,32,1150),
	
	AP_20MM(64,32,1000),
	HE_20MM(16,64,1000),
	
	AP_30MM(72,64,980),
	HE_30MM(24,96,970),
	
	AP_40MM(96,96,850),
	HE_40MM(32,128,860),
	
	AP_75MM(120,75,990),
	HE_75MM(50,100,990),
	
	AP_80MM(128,96,980),
	HE_80MM(75,128,985),
	
	AP_90MM(150,100,1024),
	HE_90MM(80,135,1024),
	
	AP_120MM(240,120,1100),
	HE_120MM(100,240,1100),
	
	AP_130MM(200,125,1024),
	HE_130MM(110,200,1020),
	
	AP_150MM(250,150,750),
	HE_150MM(96,275,500),
	
	AP_PLACE_HOLDER(0,0,0),
	HE_PLACE_HOLDER(0,0,0);
	
	
	//private final double 
	//private final double pen;
	public final double armorDamage;
	public final double damage;
	public final double baseVelocity;
	public final String displayName;
	
	private Ammo(double armorDamage, double damage, double baseVelocity) {
		this.baseVelocity = baseVelocity;
		this.armorDamage = armorDamage;
		this.damage = damage;
		this.displayName = this.name().substring(0,2);
	}
}
