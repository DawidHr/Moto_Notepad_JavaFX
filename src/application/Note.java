package application;

import java.sql.Date;

public class Note {

	private int id;
	private int id_user;
	private String title;
	private Date date;
	private String note;
	
	Note(int id, int id_user, String title, Date date, String note) {
		this.id=id;
		this.id_user=id_user;
		this.title=title;
		this.date=date;
		this.note=note;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
				return "Note [id=" + id + ", id_user=" + id_user + ", title=" + title + ", date=" + date + ", note=" + note
				+ "]";
	}
	
}
