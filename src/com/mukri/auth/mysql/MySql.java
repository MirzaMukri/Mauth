package com.mukri.auth.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
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
		
	}
	
	public void isPlayerExists() {
		
	}
	
	public void createPlayer() {
		
	}
	
	public void changePlayerPassword() {
		
	}
	
	public void removePlayer() {
		
	}
	
	public void getPlayerPassword() {
		
	}
}
