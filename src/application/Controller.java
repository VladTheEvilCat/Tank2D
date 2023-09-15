package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
//import javafx.scene.control.TextArea;
import javafx.scene.input.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


//import javafx.scene.control.TextField;

//import armorments.*;

import player.Player;
import types.Vehicle;
import types.Weapon;

public class Controller implements Initializable{
	
	@FXML
	private Player p;
	
	@FXML
	private Text primWeaponName;
	@FXML
	private Text secWeaponName;
	@FXML
	private Text tertWeaponName;
	@FXML
	private Text primWeaponAmmo;
	@FXML
	private Text secWeaponAmmo;
	@FXML
	private Text tertWeaponAmmo;
	@FXML
	private Text selectedPrimAmmo;
	@FXML
	private Text selectedSecAmmo;
	@FXML
	private Text selectedTertAmmo;
	@FXML
	private Button restockAmmo;
	
	@FXML
	private Rectangle healthBar, armorBar;
	
	@FXML
	private Rectangle tank_chasis_track_l, tank_chasis_track_r, tank_chasis_hull;
	
	@FXML
	private Rectangle tank_turret_gun_main, tank_turret_gun_secondary;
	@FXML
	private Circle tank_turret_house;
	
	@FXML
	private Group tank_group, tank_chasis, tank_turret;
	
	@FXML
	private boolean mouseIsPressed = false;
	
	@FXML
	private Line l;
	
	@FXML
	private void restock(ActionEvent e) {
		Vehicle v = this.p.getVehicle();
		v.setAmmoCount(v.primIndex, v.APIndex, v.maxPrimAP);
		v.setAmmoCount(v.primIndex,v.HEIndex, v.maxPrimHE);
		
		v.setAmmoCount(v.secIndex, v.APIndex, v.maxSecAP);
		v.setAmmoCount(v.secIndex,v.HEIndex, v.maxSecHE);
		
		v.setAmmoCount(v.tertIndex, v.APIndex, v.maxTertAP);
		v.setAmmoCount(v.tertIndex,v.HEIndex, v.maxTertHE);
	}
	
	//Suggested by MrDuck557/MoarWaterUnits on discord
	@FXML
	private void killPlayer(ActionEvent e) {
		this.p.getVehicle().setHealth(0);
	}
	
	@FXML
	private void mousePressed(MouseEvent e) {
		System.out.println("MOUSE PRESSED");
		if(e.getSource().getClass()==Button.class)
			((Button) e.getSource()).setStyle("-fx-background-color: #444444;");
		else {
			this.mouseIsPressed = true;
			tank_fired(e);
		}
	}
	@FXML
	private void mouseReleased(MouseEvent e) {
		System.out.println("MOUSE RELEASED");
		this.mouseIsPressed = false;
		if(e.getSource().getClass()==Button.class)
			((Button) e.getSource()).setStyle("-fx-background-color: #bbbbbb;");
	}
	@FXML
	private void mouseClicked(MouseEvent e) {
		System.out.println(e);
		System.out.println("Attempting to fire a weapon...");
		tank_fired(e);
	}
	
	
	@FXML
	private void tankTurretRotate(MouseEvent e) {
		
	}
	
	@FXML
	private void keyPressed(KeyEvent e) {
		if(e.getCharacter().toLowerCase().equals("esc"))
			exit();
		else
			tankMove(e);
	}
	
	@FXML
	private void tankMove(KeyEvent e) {
		System.out.println(e);
		if(e.getCharacter().toLowerCase().equals("q"))
			this.p.getVehicle().rotateTankLeft(this.p);
		else if(e.getCharacter().toLowerCase().equals("e"))
			this.p.getVehicle().rotateTankRight(this.p);
	}
	
	@FXML
	private void tank_fired(MouseEvent e) {
		System.out.println("Tank fire attemp: ");//+e);
		this.l = new Line(this.p.getPos().getX(), this.p.getPos().getY(), e.getX(), e.getY());
		//l.set
		l.setDisable(false);
		/*
		if(mouseIsPressed) {
			if(e.getButton() == MouseButton.PRIMARY) {
				System.out.println("Is Reloading?: "+this.p.getVehicle().mainGun.isReloading());
				if(!this.p.getVehicle().mainGun.isReloading()){
					this.p.getVehicle().shoot(this.p, 0);
				}
			}else if(e.getButton() == MouseButton.SECONDARY) {
				if(!this.p.getVehicle().secondaryGun.isReloading()) {
					System.out.println("Is Reloading?: "+this.p.getVehicle().secondaryGun.isReloading());
					this.p.getVehicle().shoot(this.p, 1);
				}
			}else if(e.getButton() == MouseButton.MIDDLE) {
				if(!this.p.getVehicle().tertiaryGun.equals(Weapon.PLACE_HOLDER)) {
					System.out.println("Is Reloading?: "+this.p.getVehicle().tertiaryGun.isReloading());
					if(!this.p.getVehicle().tertiaryGun.isReloading())
					this.p.getVehicle().shoot(this.p, 2);	
				}
			}
		}
		*/
	}
	
	@FXML
	private void exit() {
		System.exit(0);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.p = new Player();
		Vehicle v = this.p.getVehicle();
		v.setAttachedPlayer(this.p);
		v.setVehicleGroup(this.tank_group);
		v.setAmmoCounterText(selectedPrimAmmo, selectedSecAmmo, selectedTertAmmo);
		primWeaponName.setText(v.mainGun.displayName);
		secWeaponName.setText(v.secondaryGun.displayName);
		tertWeaponName.setText(v.tertiaryGun.displayName);
		
		selectedPrimAmmo.setText(v.getPrimSelectedAmmo().displayName+": "+v.getPrimAmmoCount());
		selectedSecAmmo.setText(v.getSecSelectedAmmo().displayName+": "+v.getSecAmmoCount());
		selectedTertAmmo.setText(v.getTertSelectedAmmo().displayName+": "+v.getTertAmmoCount());
	}
	
}
