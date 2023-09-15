package types;

import javafx.animation.*;

import utils.ReloadTimerThread;
import utils.exceptions.ReloadFinished;
import utils.exceptions.ReloadingException;

import java.util.ArrayList;

public enum Weapon 	{
	
	GUN_8MM("8mm",	new AmmoType[] {AmmoType.AP_8MM, null}, 12, true, 120, 6, 1, 1),
	GUN_12MM("12mm",	new AmmoType[] {AmmoType.AP_12MM,	null}, 5, true, 100, 7, 1, 1),
	CANNON_20MM("20mm",	new AmmoType[] {AmmoType.AP_20MM,	AmmoType.HE_20MM}, 4, true, 8,	 1,	1.5, 1.125),
	CANNON_20MM_CHAIN("20mm Belt-fed", new AmmoType[] {AmmoType.AP_20MM, AmmoType.HE_20MM},	4, true, 64, 7.5, 1, 0.9),
	CANNON_30MM("30mm", new AmmoType[] {AmmoType.AP_30MM,	AmmoType.HE_30MM}, 3, true, 8, 1.5,	1, 1),
	CANNON_40MM("40mm", new AmmoType[] {AmmoType.AP_40MM,	AmmoType.HE_40MM}, 2, true, 4, 1.75, 1.125, 1),
	CANNON_75MM("75mm", new AmmoType[] {AmmoType.AP_75MM,	AmmoType.HE_75MM}, (1/4), false,	0, 4,	1.125, 1.1),
	CANNON_75MM_AUTO("75mm Auto", new AmmoType[] {AmmoType.AP_75MM,	AmmoType.HE_75MM}, 1.5, true, 3, 3.375,	0.875, 0.8),
	CANNON_80MM("80mm", new AmmoType[] {AmmoType.AP_80MM,	AmmoType.HE_80MM}, (1/3), false,	0, 3,	1.2, 1.2),
	CANNON_80MM_AUTO("80mm Auto",	new AmmoType[] {AmmoType.AP_80MM,	AmmoType.HE_80MM}, 1, true, 5, 7.5,	1, 0.9),
	CANNON_90MM("90mm", new AmmoType[] {AmmoType.AP_90MM,	AmmoType.HE_90MM}, (1/2), false,	0, 2,	1, 1.375),
	CANNON_90MM_AUTO("90mm Auto", new AmmoType[] {AmmoType.AP_90MM,	AmmoType.HE_90MM}, 1.75, true, 5, 5, 0.875, 0.98),
	CANNON_120MM("120mm", new AmmoType[] {AmmoType.AP_120MM,AmmoType.HE_120MM},	(1/4), false,	0, 4,	1, 1.125),
	CANNON_130MM("130mm", new AmmoType[] {AmmoType.AP_130MM,AmmoType.HE_130MM},	(1/6), false,	0, 6,	1, 1),
	CANNON_150MM("150mm Howitzer", new AmmoType[] {AmmoType.AP_150MM,AmmoType.HE_150MM}, (1/3), true, 4, 8, 1, 0.875),
	PLACE_HOLDER("No Weapon",new AmmoType[] {},0,false,0,0,0,0);
	
	public final double fireRate;					// shots per second
	public final boolean hasMagazine;			// quick to load the next round, slower to change the magazine
	public final int magazineCapacity;			// shots per magazine
	public final double reloadTime; 				// time to reload the magazine
	public final double damageMultiplier;	// base damage multiplier; better differentiates AP vs HE
	public final double velocityMultiplier;// base velocity multiplier; differentiation between cannon types
	private AmmoType[] shoots = new AmmoType[2];		// what ammo the weapon object shoots
	public final String displayName;				// the name visible to the player
	private final ReloadTimerThread rt;
	private boolean isReloading;
	
	private Weapon(String displayName, AmmoType[] ammoTypes, double fireRate, boolean hasMag, int magCap, double reloadTime, double damageMultiplier, double velocityMultiplier) {
		this.displayName = displayName;
		this.shoots = shoots;
		this.fireRate = fireRate;
		this.hasMagazine = hasMag;
		this.magazineCapacity = magCap;
		this.reloadTime = reloadTime;
		this.damageMultiplier = damageMultiplier;
		this.velocityMultiplier = velocityMultiplier;
		this.rt = new ReloadTimerThread(this.displayName,((long)(this.reloadTime*100)));
		
	}

	public double getFireRate() {
		return this.fireRate;
	}
	
	public boolean hasMagazine() {
		return this.hasMagazine;
	}
	
	public int getMagazineCapacity() {
		return this.magazineCapacity;
	}
	
	public double getReloadTime() {
		return this.reloadTime;
	}
	public long getReloadTimeInMili() {
		return (long) (this.reloadTime*1000);
	}
	
	public double getDamageMultiplier() {
		return this.damageMultiplier;
	}
	public double getDmgMult() {
		return this.damageMultiplier;
	}
	
	public double getVelocityMultiplier() {
		return this.velocityMultiplier;
	}
	public double getVelMult() {
		return this.velocityMultiplier;
	}
	
	public AmmoType getAPAmmo() {
		return (this==PLACE_HOLDER)? AmmoType.AP_PLACE_HOLDER:this.shoots[0];
	}
	public AmmoType getHEAmmo() {
		return (this==PLACE_HOLDER)? AmmoType.HE_PLACE_HOLDER:this.shoots[1];
	}
	
	public String getName() {
		return this.displayName;
	}
	
	public ArrayList<Number> getStats(){
		ArrayList<Number> stats = new ArrayList<>(5);
		stats.add(this.fireRate);
		stats.add(this.magazineCapacity);
		stats.add(this.reloadTime);
		stats.add(this.damageMultiplier);
		stats.add(this.velocityMultiplier);
		return stats;
	}
	
	public void shootGraphics() {
		
	}
	
	
	public boolean equals(Weapon w) {
		return w.getStats().equals(this.getStats());
	}
	
	//--Weapon Handling--//
	
	public boolean reload() {
		
		return false;
	}
	public boolean isReloading() {
		return this.isReloading;
	}
	
}
