package com.priyanka.demorest;
import java.sql.*;
import java.util.*;
public class portalRepo {
	Connection con;
	public portalRepo(){
		try {
			getClass().forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public List<Record> getAllRecord(){
		List<Record> recList = new ArrayList<Record>();
		try {
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("Select * from recorduser");
		while(rs.next()) {
			Record rec = new Record();
			rec.setCustomerId(rs.getString(1));
			rec.setCustomerName(rs.getString(2));
			rec.setPlace(rs.getString(3));
			rec.setPhoneNo(rs.getString(4));
			rec.setUsername(rs.getString(5));
			rec.setPassword(rs.getString(6));
			recList.add(rec);
		}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return recList;
	}
	public boolean isAuthenticated(String userName,String Password) {
		String actualPass;
		try {
		PreparedStatement st = con.prepareStatement("select password from recorduser where userName = ?");
		st.setString(1, userName);
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			actualPass = rs.getString("password");
			if(actualPass.equals(Password)) {
				return true;
			}
		}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	public boolean adminAuthenticated(String userName,String Password) {
		String actualPass;
		try {
		PreparedStatement st = con.prepareStatement("select password from recordadmin where userName = ?");
		st.setString(1, userName);
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			actualPass = rs.getString("password");
			if(actualPass.equals(Password)) {
				return true;
			}
		}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	public Record getUserRecord(String custId) {
		Record al = new Record();
		try {
			PreparedStatement st = con.prepareStatement("select * from recorduser where customerId = ?");
			st.setString(1, custId);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				al.setCustomerId(rs.getString(1));
				al.setCustomerName(rs.getString(2));
				al.setPlace(rs.getString(3));
				al.setPhoneNo(rs.getString(4));
				al.setUsername(rs.getString(5));
				al.setPassword(rs.getString(6));
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return al;
	}
	public boolean deleteUserRecord(String custId) {
		try {
			PreparedStatement st = con.prepareStatement("delete from recorduser where customerId = ?");
			st.setString(1, custId);
			if(st.executeUpdate()>0) {
				return true;
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
}

