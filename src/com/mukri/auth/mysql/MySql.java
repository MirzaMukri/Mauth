package com.mukri.auth.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mukri.auth.Mauth;


/**
 * CopyRighted by DoomGary / Mukri
 * Please do not edit or copy without permissions.
 * Made on: 8:30:46 PM 
 */

public class MySql {
	
	public Connection con;
	
	public MySql(String ip, String port, String database, String user, String password) {
		if(!isConnected()) {
			String storageType = Mauth.getIns().settings.getConfig().getString("Storage-Type").toLowerCase();
			
			if(storageType.equals("mysql")) {
				try {
					con = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + database + "?autoReconnect=true", user, password);
					System.out.println("[Mauth] Connected to mySQL!");
				} catch (SQLException e) {
					System.out.println("[Mauth] Could not connect to the mySQL! Reason: " + e.getMessage());
				}
			}
		}
	}
	
	public void close() {
		if(isConnected()) {
			try {
				con.close();
				System.out.println("[Mauth] Disconnected from the mySQL!");
			} catch (SQLException e) {
				System.out.println("[Mauth] Could not disconnect from mySQL?! Reason: " + e.getMessage());
			}
		}
	}
	
	public boolean isConnected() {
		return con != null;
	}

	public void createTable() {
		if(isConnected()) {
			try {
				con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS Mauth (username VARCHAR(16), password VARCHAR(100), PRIMARY KEY (username))");
				System.out.println("[Mauth] Creating mysql table if not exists.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean isPlayerExists(String user) {
		if(isConnected()) {
			try {
				PreparedStatement ps = con.prepareStatement("SELECT * FROM Mauth WHERE username=?");
				ps.setString(1, user);
				
				ResultSet rs = ps.executeQuery();
				
				if(rs.next()) {
					ps.close();
					rs.close();
					return true;
				}
				
				ps.close();
				rs.close();
				return false;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public void createPlayer(String user, String password) {
		if(isConnected()) {
			if(!isPlayerExists(user)) {
				try {
					PreparedStatement ps = con.prepareStatement("INSERT INTO Mauth values(?,?)");
					ps.setString(1, user);
					ps.setString(2, password);
					
					ps.execute();
					ps.close();
					
					System.out.println("[Mauth] Added player " + user + " to the database!");
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void changePlayerPassword() {
		
	}
	
	public void removePlayer(String user) {
		if(isConnected()) {
			if(isPlayerExists(user)) {
				try {
					PreparedStatement ps = con.prepareStatement("DELETE FROM Mauth WHERE username=?");
					ps.setString(1, user);
					
					ps.execute();
					ps.close();
					
					System.out.println("[Mauth] Removed player " + user + " from the database!");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public String getPlayerPassword(String user) {
		if(isConnected()) {
			try {
				PreparedStatement ps = con.prepareStatement("SELECT * FROM Mauth WHERE username=?");
				ps.setString(1, user);
				
				ResultSet rs = ps.executeQuery();
				
				if(rs.next()) {
					return rs.getString("password");
				}
				
				return null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
