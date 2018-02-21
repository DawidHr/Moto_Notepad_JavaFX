package application;

import java.sql.Date;

public class Vehicle {

	private int id;
	private int id_user;
	private String marka;
	private String model;
	private String type;
	private int size;
	private Date dataOc;
	private Date dataTech;
	public Vehicle(int id, int id_user, String marka, String model, String type, int size, Date dataTech, Date dataOc) {
		super();
		this.id = id;
		this.id_user = id_user;
		this.marka = marka;
		this.model = model;
		this.type = type;
		this.size = size;
		this.dataOc = dataOc;
		this.dataTech = dataTech;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	public String getMarka() {
		return marka;
	}
	public void setMarka(String marka) {
		this.marka = marka;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public Date getDataOc() {
		return dataOc;
	}
	public void setDataOc(Date dataOc) {
		this.dataOc = dataOc;
	}
	public Date getDataTech() {
		return dataTech;
	}
	public void setDataTech(Date dataTech) {
		this.dataTech = dataTech;
	}
	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", id_user=" + id_user + ", marka=" + marka + ", model=" + model + ", type=" + type
				+ ", size=" + size + ", dataOc=" + dataOc + ", dataTech=" + dataTech + "]";
	}
	
}
