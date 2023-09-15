package types;


import java.io.File;
import java.util.concurrent.TimeUnit;

import application.CombatEvent;
import player.Player;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.media.*;
import javafx.scene.text.Text;

import com.mgnt.utils.TimeUtils;


public enum Vehicle implements VehicleMotion{
	DEFAULT_TANK_A(Weapon.CANNON_75MM, Weapon.GUN_12MM,Weapon.PLACE_HOLDER,65.0,12.0,120.0,100.0,new int[][] {{100,20},{10,0}}),
	DEFAULT_TANK_B(Weapon.CANNON_90MM, Weapon.GUN_12MM,Weapon.PLACE_HOLDER,65.0,12.0,120.0,100.0,new int[][] {{90,15},{10,0}}),
	LIGHT_TANK(Weapon.CANNON_75MM_AUTO, Weapon.GUN_8MM,Weapon.PLACE_HOLDER,70.0,15.0,100.0,50.0,new int[][] {{125,25},{20,0}}),
	MEDIUM_TANK(Weapon.CANNON_90MM, Weapon.GUN_12MM,Weapon.PLACE_HOLDER,50.0,10,150.0,120.0,new int[][] {{75,25},{12,0}}),
	HEAVY_TANK(Weapon.CANNON_120MM, Weapon.GUN_12MM, Weapon.CANNON_20MM_CHAIN,50.0,8.5,180.0,200.0,new int[][] {{80,20},{10,0},{6,0}});
	
  public final Weapon[][] guns;
	public final Weapon mainGun;
	public final Weapon secondaryGun;
	public final Weapon tertiaryGun;
	
	public final double maxSpeed;
	//private double speed;
	public final double acceleration;
	public final double maxRotationSpeed;
	//private double rotationSpeed;
	public final double rotationAcceleration;
	
	private final double maxHealth;
	private double health;
	private final double maxArmor;
	private double armor;
	
	private int[][] ammoCount;
	private AmmoTypes selectedPrimAmmo;
	private AmmoTypes selectedSecAmmo;
	private AmmoTypes selectedTertAmmo;
	public final int maxPrimAP;
	public final int maxPrimHE;
	public final int maxSecAP;
	public final int maxSecHE;
	public final int maxTertAP;
	public final int maxTertHE;
	
			
	public final int primIndex = 0;
	public final int secIndex = 1;
	public final int tertIndex = 2;
	
	public final int APIndex = 0;
	public final int HEIndex = 1;
	
	private Player attachedPlayer;
	private Group vehicleGroup;
	private Group turretGroup;
	private Group chasisGroup;
	
	private Text primAmmoCounter;
	private Text secAmmoCounter;
	private Text tertAmmoCounter;
	
	private Point2D pos;
	
	/*File audioFile = new File("src/sounds/med-gunshot.wav");
	String audioPath = audioFile.toURI().toString();
	MediaPlayer m = new MediaPlayer(new Media(audioPath));
	*/
	
	
	private Vehicle(Weapon[][] guns, Weapon secondary, Weapon tertiary, double maxSpeed, double acceleration, double maxHealth, double maxArmor, int[][] ammoCounts) {
		/*m.setStopTime(m.getTotalDuration());
		
		this.mainGun = main;
		this.secondaryGun = secondary;
		this.tertiaryGun = tertiary;
		
		this.selectedPrimAmmo = this.mainGun.getAPAmmo();
		this.selectedSecAmmo = this.secondaryGun.getAPAmmo();
		this.selectedTertAmmo = this.tertiaryGun.getAPAmmo();
		
		this.ammoCount = ammoCounts;
		
		this.maxPrimAP = ammoCounts[0][0];
		this.maxPrimHE = ammoCounts[0][1];
		
		this.maxSecAP = ammoCounts[1][0];
		this.maxSecHE = ammoCounts[1][1];
		
		this.maxTertAP = (ammoCounts.length==3)? ammoCounts[2][0]:0;
		this.maxTertHE = (ammoCounts.length==3)? ammoCounts[2][1]:0;
		*/
		
		this.maxSpeed = maxSpeed;
		//this.speed = 0;
		this.acceleration = acceleration;
		this.maxRotationSpeed = 100*(acceleration/maxSpeed);
		this.rotationAcceleration = maxSpeed/acceleration;
		
		this.maxHealth = maxHealth;
		this.health = maxHealth;
		this.maxArmor = maxArmor;
		this.armor = maxArmor;
	}
	private Vehicle(Weapon main, Weapon secondary, double maxSpeed, double acceleration, double maxHealth, double maxArmor, int[][] ammoCounts) {
		this(main,secondary,Weapon.PLACE_HOLDER,maxSpeed,acceleration,maxHealth,maxArmor,
			new int[][] {
				{ammoCounts[0][0],ammoCounts[0][1]},
				{ammoCounts[1][0],ammoCounts[1][1]},
				{0,0}
			}
		);
	}
  private Vehicle(Weapon[][] guns, double maxSpeed, double acceleration, double maxHealth, double maxArmor, int[][] ammoCounts) {
		this(main,secondary,Weapon.PLACE_HOLDER,maxSpeed,acceleration,maxHealth,maxArmor,
			new int[][] {
				{ammoCounts[0][0],ammoCounts[0][1]},
				{ammoCounts[1][0],ammoCounts[1][1]},
				{0,0}
			}
		);
	}
	
	public double getMaxHealth() {
		return this.maxHealth;
	}
	public double getHealth() {
		return this.health;
	}
	public double getMaxArmor() {
		return this.maxArmor;
	}
	public double getArmor() {
		return this.armor;
	}
	
	public Group getVehicleGroup() {
		return this.vehicleGroup;
	}
	public Group getTurretGroup() {
		return this.turretGroup;
	}
	
	public double getMaxSpeed() {
		return this.maxSpeed;
	}
	public double speed() {
		return this.speed;
	}
	public double getRotationalSpeed() {
		return this.rotationSpeed;
	}
	public double getAcceleration() {
		return this.acceleration;
	}
	public double getAccel() {
		return this.getAcceleration();
	}
	
	public double getVehicleRotation() {
		return this.vehicleGroup.getRotate();
	}
	public double getTurretRotation() {
		return this.turretGroup.getRotate();
	}
	
	public Weapon[] getWeapons() {
		return new Weapon[] {this.mainGun,this.secondaryGun,this.tertiaryGun};
	}
	public AmmoTypes getPrimSelectedAmmo() {
		return this.selectedPrimAmmo;
	}
	public int getPrimSelectedAmmoAsIndex() {
		return (getPrimSelectedAmmo().displayName.equals("AP"))? 0:1;
	}
	public AmmoTypes getSecSelectedAmmo() {
		return this.selectedSecAmmo;
	}
	public int getSecSelectedAmmoAsIndex() {
		return (getSecSelectedAmmo().displayName.equals("AP"))? 0:1;
	}
	public AmmoTypes getTertSelectedAmmo() {
		return this.selectedTertAmmo;
	}
	public int getTertSelectedAmmoAsIndex() {
		return (getTertSelectedAmmo().displayName.equals("AP"))? 0:1;
	}
	
	public int getAmmoCount(int weapon, int type) {
		try {
			if(weapon==0) {
				if(this.mainGun.hasMagazine())
					return this.mainGun.getMagazineCapacity()*this.ammoCount[0][this.getPrimSelectedAmmoAsIndex()];
				else
					return this.ammoCount[0][this.getPrimSelectedAmmoAsIndex()];
			}else if(weapon==1) {
				if(this.secondaryGun.hasMagazine())
					return this.secondaryGun.getMagazineCapacity()*this.ammoCount[1][this.getSecSelectedAmmoAsIndex()];
				else
					return this.ammoCount[1][this.getSecSelectedAmmoAsIndex()];
			} else if(weapon==2)
				if(this.tertiaryGun.hasMagazine())
					return this.tertiaryGun.getMagazineCapacity()*this.ammoCount[2][this.getTertSelectedAmmoAsIndex()];
				else
					return this.ammoCount[2][this.getTertSelectedAmmoAsIndex()];
		} catch(IndexOutOfBoundsException e) {
			System.out.println("Tried to access tertiary weapon ammo, no usable weapon found; skipping...");
		}
		return 0;
	}
	
	public int getPrimAmmoCount() {
		return getAmmoCount(0,this.getPrimSelectedAmmoAsIndex());
	}
	public int getSecAmmoCount() {
		return getAmmoCount(1,this.getSecSelectedAmmoAsIndex());
	}
	public int getTertAmmoCount() {
		return (this.tertiaryGun != Weapon.PLACE_HOLDER)? getAmmoCount(0,this.getTertSelectedAmmoAsIndex()):0;
	}
	
	public Player getAttachedPlayer() {
		return this.attachedPlayer;
	}
	
	public Point2D getPos() {
		return this.pos;
	}
	
	
	public void setAttachedPlayer(Player p) {
		this.attachedPlayer = p;
	}
	
	public void setAmmoCount(int weapon, int type, int count) {
		try {
		ammoCount[weapon][type] = count;
		} catch(IndexOutOfBoundsException e) {
			System.out.println("Tried to access tertiary weapon ammo, no usable weapon found; skipping...");
		}
	}
	public void setHealth(double to) {
		this.health = to;
		this.checkHealth();
	}
	public void addHealth(double value) {
		this.health += value;
	}
	
	public void setArmor(double to) {
		this.armor = to;
	}
	public void addArmor(double value) {
		this.armor += value;
	}
	
	public void setVehicleGroup(Group g) {
		this.vehicleGroup = g;
		for(Object g2 : g.getChildren().toArray()) {
			if(((Node) g2).getId().equals("tank_turret"))
				this.turretGroup = (Group)g2;
			else if(((Node) g2).getId().equals("tank_chasis")) {
				this.chasisGroup = (Group)g2;
				break;
			}
		}
	}
	public void setVehicleRotation(double r) {
		this.vehicleGroup.setRotate(r);
	}
	public void setTurretRotation(double r) {
		this.turretGroup.setRotate(r);
	}
	
	public void setPos(Point2D p) {
		this.pos = p;
	}
	
	public void checkHealth() {
		if(this.health<=0) {
			System.out.println("--Player Killed--");
			this.health = 0;
		}
	}
	
	//--GUI--//
	
	public void setAmmoCounterText(Text prim, Text sec, Text tert) {
		this.primAmmoCounter = prim;
		this.secAmmoCounter = sec;
		this.tertAmmoCounter = tert;
	}
	
	public void updateAmmoCounters() {
		this.primAmmoCounter.setText(getPrimSelectedAmmo().displayName+": "+getPrimAmmoCount());
		this.secAmmoCounter.setText(getSecSelectedAmmo().displayName+": "+getSecAmmoCount());
		this.tertAmmoCounter.setText(getTertSelectedAmmo().displayName+": "+getTertAmmoCount());
	}
	
	//--GRAPHICS--//
	/*
	public void drawTank() {
		drawTurret(0,0,0,0);
		drawChasis(this.attachedPlayer.getPos().getX(), this.attachedPlayer.getPos().getY());
	}
	
	private void drawTurret(double xOffset, double yOffset, double gunXOffset, double gunYOffset) {
		drawMainGun(gunXOffset,gunYOffset);
	}
	private void drawMainGun(double xOffset, double yOffset) {
		
	}
	
	private void drawChasis(double x, double y) {
		
	}
	*/
	
	@Override
	public Vehicle hit(Player p, AmmoTypes shell) {
		if((shell.armorDamage-p.getVehicle().getArmor())>0)
			p.getVehicle().addHealth((-(shell.damage/shell.armorDamage)));
		p.getVehicle().addArmor((-(shell.armorDamage-p.getVehicle().getArmor())));
		p.getVehicle().checkHealth();
		return p.getVehicle();
	}
	@Override
	public Vehicle shoot(Player p, int wIndex) {
		Vehicle v = p.getVehicle();
		Weapon w = v.getWeapons()[wIndex];
		System.out.println("Vehicle Shot: "+v.name());
		System.out.println("Using Weapon: "+w.displayName);
		if(!w.isReloading()) {
			if(wIndex==0) {
				new MediaPlayer(new Media(audioPath)).play();
				v.ammoCount[0][v.getPrimSelectedAmmoAsIndex()] -= 1;
				v.updateAmmoCounters();
				//v.checkHealth();
				w.shootGraphics();
				//w.reload();
				//m.stop();
			}else if(wIndex==1) {
				new MediaPlayer(new Media(audioPath)).play();
				v.ammoCount[1][v.getSecSelectedAmmoAsIndex()] -= 1;
				v.updateAmmoCounters();
				//v.checkHealth();
				w.shootGraphics();
				//w.reload();
				//m.stop();
			}else if(wIndex==2 && !(v.tertiaryGun.equals(Weapon.PLACE_HOLDER))) {
				new MediaPlayer(new Media(audioPath)).play();
				v.ammoCount[2][v.getTertSelectedAmmoAsIndex()] -= 1;
				v.updateAmmoCounters();
				//v.checkHealth();
				w.shootGraphics();
				//w.reload();
				//m.stop();
			}
		}
		return v;
	}
	@Override
	public void moved(Player p) {
		//System.out.println("Vehicle Moved: "+p);
		
	}
	@Override
	public void moveUp(Player p) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void moveDown(Player p) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void rotateTankRight(Player p) {
		if(p.equals(this.attachedPlayer)) {
			this.rotationSpeed+=this.rotationAcceleration;
			this.chasisGroup.setRotate(this.chasisGroup.getRotate()-2/*this.rotationSpeed*/);
		}
	}
	@Override
	public void rotateTankLeft(Player p) {
		if(p.equals(this.attachedPlayer))
			this.rotationSpeed-=this.rotationAcceleration;
			this.chasisGroup.setRotate(this.chasisGroup.getRotate()+2/*this.rotationSpeed*/);
	}
	@Override
	public void rotateTurretLeft(Player p) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void rotateTurretRight(Player p) {
		// TODO Auto-generated method stub
		
	}
	
}
