package com.mukri.auth.files;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.mukri.auth.Mauth;


/**
 * CopyRighted by DoomGary / Mukri
 * Please do not edit or copy without permissions.
 * Made on: 2:57:41 AM 
 */

public class MessageData {
	
	File file;
	FileConfiguration config;
	
	public MessageData() {
		file = new File(Mauth.getIns().getDataFolder(), "settings.yml");
		config = YamlConfiguration.loadConfiguration(file);
	}
	
	public void save() {
		try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isExists() {
		return file.exists();
	}
	
	public FileConfiguration getConfig() {
		return config;
	}
	
	public void addDefault() {
		if(!isExists()) {
			try {
				if(!file.getParentFile().exists()) {	
					file.getParentFile().mkdirs();
				}
				
				file.createNewFile();
				
				config.set("Msg-Login", "&a[Mauth] &7Please login! /login [password]");
				config.set("Msg-Register", "&a[Mauth] &7Please register! /register [password]");
				config.set("Msg-Set-Password", "&a[Mauth] &7You set your password to &c{Password}");
				config.set("Msg-Error-Length", "&a[Mauth] &7Your password lenght have to be more than &c{NumberHighest}");
				config.set("Msg-Error-Invalid-Pass", "&a[Mauth] You are not allowed to use that password!");

				save();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
