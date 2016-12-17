package com.mukri.auth.files;

import java.io.File;
import java.io.IOException;

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
		
	}

}
