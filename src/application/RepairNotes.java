package application;

import java.sql.Date;

public class RepairNotes {
	int id;
	int id_user;
	String id_mod;
	String title;
	Date data_note;
	String importatntLvl;
	int id_group_files;
	String note;
	String moto;
	
	public RepairNotes() {
		super();
	}
	public RepairNotes(int id, int id_user, String title, String note, String id_mod, Date data_note,
			String importatntLvl, int id_group_files, String repairMode) {
		super();
		this.id = id;
		this.id_user = id_user;
		this.title = title;
		this.note = note;
		this.id_mod = id_mod;
		this.data_note = data_note;
		this.importatntLvl = importatntLvl;
		this.id_group_files = id_group_files;
		this.moto = repairMode;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getId_mod() {
		return id_mod;
	}
	public void setId_mod(String id_mod) {
		this.id_mod = id_mod;
	}
	public Date getData_note() {
		return data_note;
	}
	public void setData_note(Date data_note) {
		this.data_note = data_note;
	}
	public String getImportatntLvl() {
		return importatntLvl;
	}
	public void setImportatntLvl(String importatntLvl) {
		this.importatntLvl = importatntLvl;
	}
	public String getRepairMode() {
		return moto;
	}
	public void setRepairMode(String repairMode) {
		moto = repairMode;
	}
	public int getId() {
		return id;
	}
	public int getId_user() {
		return id_user;
	}
	public int getId_group_files() {
		return id_group_files;
	}
	public String getMoto() {
		return moto;
	}

}
