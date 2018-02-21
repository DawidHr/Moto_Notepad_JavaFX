package application;

import java.sql.Date;

public class Fuel {
private int id;
private String moto;
private String fuel;
private float howMuch;
private float howMuchPrize;
private Date date;
public Fuel(int id, String moto, String fuel, float howMuch, float howMuchPrize, Date date) {
	super();
	this.id = id;
	this.moto = moto;
	this.fuel = fuel;
	this.howMuch = howMuch;
	this.howMuchPrize = howMuchPrize;
	this.date = date;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getMoto() {
	return moto;
}
public void setMoto(String moto) {
	this.moto = moto;
}
public String getFuel() {
	return fuel;
}
public void setFuel(String fuel) {
	this.fuel = fuel;
}
public float getHowMuch() {
	return howMuch;
}
public void setHowMuch(float howMuch) {
	this.howMuch = howMuch;
}
public float getHowMuchPrize() {
	return howMuchPrize;
}
public void setHowMuchPrize(float howMuchPrize) {
	this.howMuchPrize = howMuchPrize;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}


}
