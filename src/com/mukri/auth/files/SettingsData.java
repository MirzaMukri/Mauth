package com.mukri.auth.files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.mukri.auth.Mauth;


/**
 * CopyRighted by DoomGary / Mukri
 * Please do not edit or copy without permissions.
 * Made on: 12:09:17 PM 
 */

public class SettingsData {
	
	File file;
	FileConfiguration config;
	
	public SettingsData() {
		file = new File(Mauth.getIns().getDataFolder() + "settings.yml");
		config = YamlConfiguration.loadConfiguration(file);
	}
	
	public boolean isExists() {
		return file.exists();
	}
	
	public FileConfiguration getConfig() {
		return config;
	}
	
	public void save() {
		try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		};
	}
	
	public void addDefaullt() {
		try {
			file.createNewFile();
			
			config.addDefault("Type", "YAML");
			config.addDefault("Secure-Inventory", false);
			config.addDefault("Add-Blindness", true);
			config.addDefault("mySQL.Host", "127.0.0.1");
			config.addDefault("mySQL.Port", "3306");
			config.addDefault("mySQL.Username", "mauth");
			config.addDefault("mySQL.Password", "mauthpassword");
			config.addDefault("mySQL.Table", "mauthtable");
			config.addDefault("Gamemode.Force-Survival", true);
			config.addDefault("Gamemode.Clear-If-Creative", false);
			config.addDefault("Register.MinimumPasswordLength", 5);
			config.addDefault("Register.Kick-If-Time-Reach", true);
			config.addDefault("Register.Time-Kicking", 15);
			config.addDefault("Register.Kick-After-Registering", true);
			config.addDefault("Login.Time-Login", 10);
			config.addDefault("Login.Kick-If-Pass-Wrong", true);
			config.addDefault("Login.Commands-Allow", new ArrayList<>());	
			config.addDefault("Login.Allow-Chat", false);
			config.addDefault("Message.Login", "PLEASE LOGIN /LOGIN");
			config.addDefault("Message.Register", "PLEASE REGISTER /REGISTER");
			config.addDefault("Message.Kicked-Too-Much-Attempt", "YOU HAVE BEEN KICKED REASON: TOO MUCH ATTEMPT");
			config.addDefault("Message.Kicked-Time-Limit", "YOU HAVE BEEN KICKED REASON: TIME ENDS!");
			config.addDefault("Message.Successfull-Login", new ArrayList<>());
			
			save();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
