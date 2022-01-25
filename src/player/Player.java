package player;

//import application.CombatEvent;
import armorments.*;
import javafx.geometry.Point2D;

public class Player {
	
	private Vehicle vehicle;
	
	
	public Player(double x, double y, Vehicle v) {
		this.vehicle = v;
		this.setPos(new Point2D(x,y));
	}
	public Player() {
		this(100,100, Vehicle.DEFAULT_TANK_A);
	}
	
	public void init() {
		
	}
	
	public Point2D getPos() {
		return this.vehicle.getPos();
	}
	public void setPos(Point2D pos) {
		this.vehicle.setPos(pos);
	}
	
	public Vehicle getVehicle() {
		return this.vehicle;
	}
	
	public double[] getStats() {
		Vehicle v = this.vehicle;
		return new double[] {v.getArmor(), v.getHealth()};
	}

}
