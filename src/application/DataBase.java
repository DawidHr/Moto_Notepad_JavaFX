package application;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.ReadOnlyFileSystemException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

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
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(DB_URL);
			stat = conn.createStatement();
			System.out.println("Po³aczono");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Pobieranie nazw listy U¿ytkowników
	public List<String> getUserNames() {
		List<String> userList = new LinkedList<String>();
		String query = "select user_name from User";
		Statement stat;
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(query);
			while (rs.next()) {
				String userName = rs.getString("user_name");
				userList.add(userName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userList;
	}

	// Tworzenie nowego Urzytkownika
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
		} catch (Exception e) {
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
			while (rs.next()) {
				findUser = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return findUser;
	}

	public int Login(String selectedItem, String text) {
		String query = "select id from User where user_name=? and user_password=?";
		int id = 0;
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, selectedItem);
			preparedStatement.setString(2, text);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (Exception e) {
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
			Date dateSQL = java.sql.Date.valueOf(date);
			preparedStatement.setDate(3, dateSQL);
			preparedStatement.setString(4, trim2);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
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
			while (rs.next()) {
				rs.getInt("id");
				notesList.add(new Note(rs.getInt("id"), rs.getInt("id_user"), rs.getString("title"),
						rs.getDate("data_note"), rs.getString("text_note")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return notesList;
	}

	public Note getNote(int note_id) {
		String query = "select * from Note where id=?";
		Note note = null;
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, note_id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				note = new Note(rs.getInt("id"), rs.getInt("id_user"), rs.getString("title"), rs.getDate("data_note"),
						rs.getString("text_note"));
			}
		} catch (Exception e) {
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
		} catch (Exception e) {
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
			while (rs.next()) {
				note11 = new Note(rs.getInt("id"), rs.getInt("id_user"), rs.getString("title"), rs.getDate("data_note"),
						rs.getString("text_note"));

			}
			if (!(note11.equals(text))) {
				// Update
				String query2 = "update Note set title=? where id=?";
				PreparedStatement stat2 = conn.prepareStatement(query2);
				stat2.setString(1, text);
				stat2.setInt(2, id);
				stat2.executeUpdate();
			}
			if (!(note11.getNote().equals(note1))) {
				String query3 = "update Note set text_note=? where id=?";
				PreparedStatement stat3 = conn.prepareStatement(query3);
				stat3.setString(1, note1);
				stat3.setInt(2, id);
				stat3.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveRepairNote(int id_user, String id_mod, String moto,  String title, String note,  String importantLvl, List<File> listFiles) {
		System.out.println("saveRepairNote()");
		try {
			int id_group =0;
			if(!(listFiles.isEmpty())) {
			id_group = saveFiles(listFiles);
			}
			String query = "insert into Repair_Note(id_user, id_mod, title, data_note, importatntLvl, id_group_files, note, moto)  values(?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement prs = conn.prepareStatement(query);
			prs.setInt(1, id_user);
			prs.setString(2, id_mod);
			prs.setString(3, title);
			LocalDate date1  = LocalDate.now();
			Date date2 = Date.valueOf(date1); 
			prs.setDate(4, date2);
			prs.setString(5, importantLvl);
			prs.setInt(6, id_group);
			prs.setString(7, note);
			prs.setString(8, moto);
			prs.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int saveFiles(List<File> listFiles) {
		try {
			//Znalezienie ostatniej numeru grupy plików
			int id_group = 0;
			String query1 = "select * from Pliki";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query1);
			while(rs.next()) {
				id_group = rs.getInt("id_grupy");
			}
			//wybranie nastepnego numeru grupy plików
			id_group = id_group + 1;
			//Zapisanie plików do bazy danych
			for (int i = 1; i <= listFiles.size(); i++) {
				String query2 = "insert into Pliki(id_grupy, plik) values(?,?)";
				PreparedStatement prs = conn.prepareStatement(query2);
				prs.setInt(1, id_group);
				prs.setBytes(2, readFile(listFiles.get(i).getAbsolutePath()));
				/*Blob blob1 = conn.createBlob();
				int of = 0;
				OutputStream out = blob1.setBinaryStream(of);*/
			}
			// wypis pliku
			String query3 = "select * from Pliki";
			Statement stat3 = conn.createStatement();
			ResultSet rs2 = stat3.executeQuery(query3);
			System.out.println("Wypisywanie plikow z Bazy danych");
			while (rs2.next()) {
				System.out.println("Plik o id :" + rs2.getInt("id") + " objekt " + rs2.getObject("plik"));
			}
			return id_group;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	 private byte[] readFile(String file) {
	        ByteArrayOutputStream bos = null;
	        try {
	            File f = new File(file);
	            FileInputStream fis = new FileInputStream(f);
	            byte[] buffer = new byte[1024];
	            bos = new ByteArrayOutputStream();
	            for (int len; (len = fis.read(buffer)) != -1;) {
	                bos.write(buffer, 0, len);
	            }
	        } catch (FileNotFoundException e) {
	            System.err.println(e.getMessage());
	        } catch (IOException e2) {
	            System.err.println(e2.getMessage());
	        }
	        return bos != null ? bos.toByteArray() : null;
	    }
	
	
	// Sprawdzamy czy u¿ytkownik o id posiada jakies pojazdy
	public boolean findIfIsMoto(int id) {
		System.out.println("ID u¿ytkownika"+id);
		try {
			String query1 = "select * from Moto";
			Statement st = conn.createStatement();
			ResultSet rs1 = st.executeQuery(query1);
			while(rs1.next()) {
				System.out.println(rs1.getString("id")+" "+rs1.getInt("id_user") + " "+rs1.getString("marka"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		try {
			String query = "select * from Moto where id_user=?";
			PreparedStatement prs = conn.prepareStatement(query);
			prs.setInt(1, id);
			ResultSet rs = prs.executeQuery();
			while (rs.next()) {
				System.out.println("Return findIfIsMoto true");
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void addVehicle(int id, String trim, String trim2, int intNumber, String selectedItem, Date dataOC,
			Date dataTechnical) {

		try {
			String query = "Insert into Moto(id_user, marka, model, Type, capacity, technical_data, oc_data) values(?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement prs = conn.prepareStatement(query);
			prs.setInt(1, id);
			prs.setString(2, trim);
			prs.setString(3, trim2);
			prs.setFloat(5, intNumber);
			prs.setString(4, selectedItem);
			prs.setDate(6, dataOC);
			prs.setDate(7, dataTechnical);
			prs.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<Vehicle> getVehicles(int id) {
		List<Vehicle> list = new LinkedList<>();
		try {
			String query = "select * from Moto";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				list.add(new Vehicle(rs.getInt("id"), rs.getInt("id_user"), rs.getString("marka"),
						rs.getString("model"), rs.getString("Type"), rs.getInt("capacity"),
						rs.getDate("technical_data"), rs.getDate("oc_data")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// Funkcja zwracaj¹ca wszystkie pojazdy nale¿¹ce do u¿ytkownika o id
	public Vehicle getVehiclebyId(int id) {
		try {
			String query = "select * from Moto where id=?";
			PreparedStatement prs = conn.prepareStatement(query);
			prs.setInt(1, id);
			ResultSet rs = prs.executeQuery();
			while (rs.next()) {
				return new Vehicle(rs.getInt("id"), rs.getInt("id_user"), rs.getString("marka"), rs.getString("model"),
						rs.getString("Type"), rs.getInt("capacity"), rs.getDate("technical_data"),
						rs.getDate("oc_data"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Funkcja usuwaj¹ca wybrany pojazd z Bazy Danych
	public void deleteVehicle(int id_moto) {
		try {
			String query = "delete from Moto where id=?";
			PreparedStatement prs = conn.prepareStatement(query);
			prs.setInt(1, id_moto);
			prs.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateVehicle(int id, String trim, String trim2, int floatNumber, String selectedItem, Date dataOC,
			Date dataTechnical) {
		try {
			Vehicle moto = null;
			String query = "select * from Moto where id=?";
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				moto = new Vehicle(rs.getInt("id"), rs.getInt("id_user"), rs.getString("marka"), rs.getString("model"),
						rs.getString("Type"), rs.getInt("capacity"), rs.getDate("technical_data"),
						rs.getDate("oc_data"));
			}
			if (!(moto.getMarka().equals(trim))) {
				String query2 = "update Moto set marka=? where id=?";
				PreparedStatement prs1 = conn.prepareStatement(query2);
				prs1.setString(1, trim);
				prs1.setInt(2, id);
				prs1.executeUpdate();
			}
			if (!(moto.getModel().equals(trim2))) {
				String query2 = "update Moto set model=? where id=?";
				PreparedStatement prs1 = conn.prepareStatement(query2);
				prs1.setString(1, trim2);
				prs1.setInt(2, id);
				prs1.executeUpdate();
			}
			if (moto.getSize() != floatNumber) {
				String query4 = "update Moto set capacity=? where id=?";
				PreparedStatement prs4 = conn.prepareStatement(query4);
				prs4.setInt(1, floatNumber);
				prs4.setInt(2, id);
				prs4.executeUpdate();
			}
			if (!(moto.getType().equals(selectedItem))) {
				String query5 = "update Moto set Type=? where id=?";
				PreparedStatement prs5 = conn.prepareStatement(query5);
				prs5.setString(1, selectedItem);
				prs5.setInt(2, id);
				prs5.executeUpdate();
			}
			if (!(moto.getDataOc().equals(dataOC))) {
				String query6 = "update Moto set oc_data=? where id=?";
				PreparedStatement prs6 = conn.prepareStatement(query6);
				prs6.setDate(1, dataOC);
				prs6.setInt(2, id);
				prs6.executeUpdate();
			}
			if (!(moto.getDataTech().equals(dataTechnical))) {
				String query7 = "update Moto set technical_data=? where id=?";
				PreparedStatement prs7 = conn.prepareStatement(query7);
				prs7.setDate(1, dataTechnical);
				prs7.setInt(2, id);
				prs7.executeUpdate();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void addNewFuel(int id, String string, String string2, Float fsize, Float fprize, Date data2) {
		try {
			String query = "insert into Fuel(id_user, moto, fuel, howMuch, howMuchPrize, Date) values(?, ?, ?, ?, ?, ?)";
			PreparedStatement prs = conn.prepareStatement(query);
			prs.setInt(1, id);
			prs.setString(2, string);
			prs.setString(3, string2);
			prs.setFloat(4, fsize);
			prs.setFloat(5, fprize);
			prs.setDate(6, data2);
			prs.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public List<Fuel> getFule(int id) {
		List<Fuel> list = new LinkedList<>();
		try {
			String query = "select * from Fuel where id_user=?";
			PreparedStatement prs = conn.prepareStatement(query);
			prs.setInt(1, id);
			ResultSet rs = prs.executeQuery();
			while(rs.next()) {
				list.add(new Fuel(rs.getInt("id"), rs.getString("moto"), rs.getString("fuel"), rs.getFloat("howMuch"), rs.getFloat("howMuchPrize"), rs.getDate("Date")));
			System.out.println("====================="
				+"\n"+rs.getString("moto"));
			
			}
			return list;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteFuel(int id) {
		// TODO Auto-generated method stub
		try {
			String query = "delete from Fuel where id=?";
			PreparedStatement prs = conn.prepareStatement(query);
			prs.setInt(1, id);
			prs.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public String getProfileName(int id) {
		try {
			String query = "select user_name from User where id=?";
			PreparedStatement prs = conn.prepareStatement(query);
			prs.setInt(1, id);
			ResultSet rs = prs.executeQuery();
			while(rs.next()) {
				return rs.getString("user_name");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void updateProfile(int id, String pass, String mail1) {
		try {
			String password="";
			String mail="";
			String query = "select * from User where id=?";
			PreparedStatement prs = conn.prepareStatement(query);
			prs.setInt(1, id);
			ResultSet rs = prs.executeQuery();
			while(rs.next()) {
				password = rs.getString("user_password");
				mail=rs.getString("user_mail");
			}
			if(!(password.equals(pass) || password=="")) {
			String query1 = "update User set user_password=? where id = ?";
			PreparedStatement prs1 = conn.prepareStatement(query1);
			prs1.setString(1, pass);
			prs1.setInt(2, id);
			prs1.executeUpdate();
			}
			if(!(mail.equals(mail1) || mail=="")) {
				String query2 = "update User set user_mail=? where id = ?";
				PreparedStatement prs2 = conn.prepareStatement(query2);
				prs2.setString(1, mail1);
				prs2.setInt(2, id);
				prs2.executeUpdate();
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public String getUserMail(int id) {
		try {
			String query = "select user_mail from User where id=?";
			PreparedStatement prs = conn.prepareStatement(query);
			prs.setInt(1, id);
			ResultSet rs = prs.executeQuery();
			while(rs.next()) {
				return rs.getString("user_mail");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int Login(String selectedItem) {
		String query = "select id from User where user_name=?";
		int id = 0;
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, selectedItem);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	public boolean isClose() {
		// TODO Auto-generated method stub
		try {
			return conn.isClosed();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	public List<RepairNotes> getRepairList(int id_user) {
		List<RepairNotes> list = new LinkedList<>();
		try {
			String query = "select * from Repair_Note where id_user = ?";
		PreparedStatement prs = conn.prepareStatement(query);
			prs.setInt(1, id_user);
			ResultSet rs = prs.executeQuery();
			while(rs.next()) {
				list.add(new RepairNotes(rs.getInt("id"), id_user, rs.getString("title"), rs.getString("note"), rs.getString("id_mod"), rs.getDate("data_note"), rs.getString("importatntLvl"), rs.getInt("id_group_files"), rs.getString("moto")));
			}
			return list;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
