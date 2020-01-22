package com.priyanka.demorest;

import java.util.*;
import java.sql.*;
public class alienRepository {
		Connection con;
	public alienRepository() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "");		
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public List<Alien> getAliens(){
		List<Alien> alienList = new ArrayList<Alien>();
		try {
			String query = "select * from aliendata";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				Alien al = new Alien();
				al.setName(rs.getString(1));
				al.setId(rs.getString(2));
				al.setPoints(rs.getInt(3));
				alienList.add(al);
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return alienList;
	}
	public Alien getAlien(int id) {
		Alien al = new Alien();
		try {
			String query = "select * from aliendata where alienid ="+id;
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			if(rs.next()) {
				al.setName(rs.getString(1));
				al.setId(rs.getString(2));
				al.setPoints(rs.getInt(3));
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return al;
	}
	public int create(Alien a) {
		try {
			String query = "insert into aliendata values(?,?,?)";
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, a.getName());
			st.setString(2, a.getId());
			st.setInt(3, a.getPoints());
			st.executeUpdate();
			return 1;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}
		public int delete(String id) {
			try {
				String query = "delete from aliendata where alienid=\""+id+"\"";
				PreparedStatement st = con.prepareStatement(query);
				st.executeUpdate();
				return 1;
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
				return 0;
			}
	}

}
