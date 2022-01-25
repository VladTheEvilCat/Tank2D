package application;

import armorments.*;
import player.Player;

public interface CombatEvent {
	
	public Vehicle hit(Player p, Ammo shell);
	public Vehicle shoot(Player p, int wIndex);
	
	public void moved(Player p);
	public void moveUp(Player p);
	public void moveDown(Player p);
	public void rotateTankRight(Player p);
	public void rotateTankLeft(Player p);
	public void rotateTurretLeft(Player p);
	public void rotateTurretRight(Player p);
	
	
}
