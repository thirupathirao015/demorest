package com.telusko.demorest;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class AlienRepository {

	Connection con = null;

	public AlienRepository() {
		
		String url = "jdbc:mysql://localhost:3306/restdb";
		String username = "root";
		String password = "system";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url,username,password);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public List<Alien> getAliens() {

		List<Alien> aliens = new ArrayList<>();
		String s = "select * from alien";
		Statement stmt;
		try {

			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(s);

			while (rs.next()) {
				Alien a = new Alien();
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setPoints(rs.getInt(3));

				aliens.add(a);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return aliens;

	}

	public Alien getAlien(int id) {
		String s = "select * from alien where id=" + id;
		Statement stmt;
		Alien a = new Alien();
		try {

			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(s);

			if (rs.next()) {

				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setPoints(rs.getInt(3));

			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return a;
	}

	public void create(Alien a1) {
		
		String sql = "insert into alien values(?,?,?)";
						 
		try {
			
			PreparedStatement st = con.prepareStatement(sql);			
			 st.setInt(1, a1.getId());
			 st.setString(2, a1.getName());
			 st.setInt(3, a1.getPoints());
			 st.executeUpdate();
			 
		} catch (Exception e) {

			e.printStackTrace();
		}

	}
	
      public void update(Alien a1) {
		
		String sql = "update alien set name=?,points=? where id=?";
						 
		try {
			
			PreparedStatement st = con.prepareStatement(sql);			
			
			 st.setString(1, a1.getName());
			 st.setInt(2, a1.getPoints());
			 st.setInt(3, a1.getId());
			 st.executeUpdate();
			 
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public void delete(int id) {
		 
		String sql = "delete from alien where id=?";
		
		try {
			
			PreparedStatement st = con.prepareStatement(sql);  
			 st.setInt(1, id);
			 st.executeUpdate();
			 
		} catch (Exception e) {

			e.printStackTrace();
		}
		 
	}

}
