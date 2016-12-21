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
		file = new File(Mauth.getIns().getDataFolder(), "settings.yml");
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
			
			if(!file.getParentFile().exists()) {	
				file.getParentFile().mkdirs();
			}
			
			file.createNewFile();
			
			config.set("Type", "YAML");
			config.set("Secure-Inventory", false);
			config.set("Add-Blindness", true);
			
			config.set("mySQL.Host", "127.0.0.1");
			config.set("mySQL.Port", "3306");
			config.set("mySQL.Username", "mauth");
			config.set("mySQL.Password", "mauthpassword");
			config.set("mySQL.Table", "mauthtable");
			
			config.set("Gamemode.Force-Survival", true);
			config.set("Gamemode.Clear-If-Creative", false);
			
			config.set("Register.MinimumPasswordLength", 5);
			config.set("Register.Kick-After-Registering", true);
			
			config.set("Login.Time-Login", 10);
			config.set("Login.Kick-If-Pass-Wrong", true);
			config.set("Login.Commands-Allow", new ArrayList<>());	
			config.set("Login.Allow-Chat", false);
			
			config.set("Message.Login", "&a&lMAUTH &7PLEASE LOGIN /LOGIN");
			config.set("Message.Register", "&a&lMAUTH &7PLEASE REGISTER /REGISTER");
			config.set("Message.Kicked-Too-Much-Attempt", "&a&lMAUTH &7YOU HAVE BEEN KICKED\n &cREASON: &7TOO MUCH ATTEMPT");
			config.set("Message.Kicked-Time-Limit", "&a&lMAUTH &7YOU HAVE BEEN KICKED\n &cREASON: &7TIME ENDS!");
			config.set("Message.Successfull-Login", "&a&lMAUTH &7You successfully logged in!");
			config.set("Message.Successfull-Register", "&a&lMAUTH &7You set your password to &a{PASSWORD}");
			config.set("Message.Error-Lenght", "&a&lMAUTH &7Your password have to be longer than 5!");
			config.set("Message.Error-Invalid", "&a&lMAUTH &7Your are not allowed to use that password!");
			
			save();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
