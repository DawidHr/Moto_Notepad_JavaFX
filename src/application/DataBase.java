package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;

public class DataBase {
public static final String DRIVER = "org.sqlite.JDBC";
public static final String DB_URL = "jdbc:sqlite:Moto_Notepad_JavaFx_Database.s3db";
private Statement stat;

Connection conn;

DataBase() {
	try {
		Class.forName(DRIVER);
	}
	catch(ClassNotFoundException e) {
		e.printStackTrace();
	}
	try {
		conn = DriverManager.getConnection(DB_URL);
		stat = conn.createStatement();
		System.out.println("Po³aczono");
	}
	catch(Exception e) {
		e.printStackTrace();
	}
}
//Pobieranie nazw listy U¿ytkowników
public	List<String> getUserNames() {
	List<String> userList = new LinkedList<String>(); 
	String query = "select user_name from User";
	Statement stat;
	try {
		stat = conn.createStatement();
		ResultSet rs = stat.executeQuery(query);
		while(rs.next()) {
			String userName = rs.getString("user_name");
			userList.add(userName);
		}
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	return userList;
}

//Tworzenie nowego Urzytkownika
public void addNewUser(String userName, String userPassword, String userMail) {
	String query = "insert into User(user_name, user_password, user_mail) values(?, ?,?)";
	Statement stat;
	try {
		PreparedStatement preparedStatement = conn.prepareStatement(query);
		preparedStatement.setString(1, userName);
		preparedStatement.setString(2, userPassword);
		preparedStatement.setString(3, userMail);
		preparedStatement.executeUpdate();
		System.out.println("Tworze uzytkwnika");
	}
	catch(Exception e) {
		e.printStackTrace();
	}
}
public void close() {
	// TODO Auto-generated method stub
	try {
		conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public boolean searchUserbyName(String Name) {
	// TODO Auto-generated method stub
	String query = "select * from User where user_name=?";
	boolean findUser = false;
	try {
	PreparedStatement preparedStatement = conn.prepareStatement(query);
	preparedStatement.setString(1, Name);
	ResultSet rs = preparedStatement.executeQuery(); 
	while(rs.next()) {
		findUser = true;
	}
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	return findUser;
}
public int Login(String selectedItem, String text) {
	String query = "select id from User where user_name=? and user_password=?";
		int id=0;	
	try {
		PreparedStatement preparedStatement = conn.prepareStatement(query);
		preparedStatement.setString(1, selectedItem);	
		preparedStatement.setString(2, text);
		ResultSet rs = preparedStatement.executeQuery(); 
		while(rs.next()) {
			id = rs.getInt("id");
		}
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	return id;
}
public void addNewNote(int id, String trim, LocalDate date, String trim2) {
	// TODO Auto-generated method stub
	String query = "insert into Note(id_user, title, data_note, text_note) values(?,?, ?, ?)";
	
	try {
		PreparedStatement preparedStatement = conn.prepareStatement(query);
		preparedStatement.setInt(1, id);	
		preparedStatement.setString(2, trim);
		Date dateSQL =java.sql.Date.valueOf(date);
		preparedStatement.setDate(3, dateSQL);
		preparedStatement.setString(4, trim2);
		preparedStatement.executeUpdate();
	}
	catch(Exception e) {
		e.printStackTrace();
	}
}

public List<Note> getNotesList() {
	String query = "select * from Note";
	List<Note> notesList = new LinkedList<>();
		Statement stat;
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(query);
			while(rs.next()) {
				rs.getInt("id");
				notesList.add(new Note(rs.getInt("id"), rs.getInt("id_user"), rs.getString("title"), rs.getDate("data_note"), rs.getString("text_note")));
			}
	}
	catch(Exception e) {
		e.printStackTrace();
	}
		return notesList;
}
public Note getNote(int note_id) {
	String query = "select * from Note where id=?";
	Note note=null;
	try {
		PreparedStatement preparedStatement = conn.prepareStatement(query);
		preparedStatement.setInt(1, note_id);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()) {
			note = new Note(rs.getInt("id"), rs.getInt("id_user"), rs.getString("title"), rs.getDate("data_note"), rs.getString("text_note"));
		}
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	
	return note;
}
public void deleteNote(int note_id) {
	// TODO Auto-generated method stub
	String query = "delete from Note where id=?";
	try {
		PreparedStatement preparedStatement = conn.prepareStatement(query);
		preparedStatement.setInt(1, note_id);
		preparedStatement.executeUpdate();
	}
	catch(Exception e) {
		e.printStackTrace();
	}
}
public void updateNote(int id, String text, DatePicker data2, String note1) {
	// TODO Auto-generated method stub
	String query = "update Note set title=?, data_note=?, text_note=? where id=?";
	try {
		Note note11 = null;
		String query1 = "select * from Note where id=?";
		PreparedStatement stat1 = conn.prepareStatement(query1);
		stat1.setInt(1, id);
		ResultSet rs = stat1.executeQuery();
		while(rs.next()) {
			note11 = new Note(rs.getInt("id"), rs.getInt("id_user"), rs.getString("title"), rs.getDate("data_note"), rs.getString("text_note"));
			
		}
		if(!(note11.equals(text))) {
			//Update
			String query2 = "update Note set title=? where id=?";
			PreparedStatement stat2 = conn.prepareStatement(query2);
			stat2.setString(1, text);
			stat2.setInt(2, id);
			stat2.executeUpdate();
		}
		if(!(note11.getNote().equals(note1))) {
			String query3 = "update Note set text_note=? where id=?";
			PreparedStatement stat3 = conn.prepareStatement(query3);
			stat3.setString(1, note1);
			stat3.setInt(2, id);
			stat3.executeUpdate();
		}
	}
	catch(Exception e) {
		e.printStackTrace();
	}
}
}
