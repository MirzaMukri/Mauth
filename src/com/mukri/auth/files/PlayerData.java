package com.mukri.auth.files;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.mukri.auth.Mauth;
import com.mukri.auth.security.Base64Con;


/**
 * CopyRighted by DoomGary / Mukri
 * Please do not edit or copy without permissions.
 * Made on: 1:34:49 AM 
 */

public class PlayerData {

	File file;
	FileConfiguration config;
	String uuid;

	public PlayerData(String uuid) {
		this.uuid = uuid;
		file = new File(Mauth.getIns().getDataFolder() + "/Data/" + uuid + ".yml");
		config = YamlConfiguration.loadConfiguration(file);
	}

	public boolean isExists() {
		return file.exists();
	}

	public void save() {
		try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createPlayer(Player p) {
		try {

			if(!file.getParentFile().exists()) {	
				file.getParentFile().mkdirs();
			}

			file.createNewFile();

			config.set("Name", p.getName());
			config.set("Password", "none");
			config.set("Ip-Registered", p.getAddress().toString());
			config.set("Ip-Logged", p.getAddress().toString());

			save();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setPassword(String password) throws UnsupportedEncodingException {
		String encoded = Base64Con.stringToBase(password);

		config.set("Password", encoded);
	}
	
	public String getPassword() {
		return config.getString("Password");
	}

	public String getLastIp() {
		return config.getString("Ip-Logged");
	}

	public void setLastIp(String ip) {
		config.set("Ip-Logged", ip);
	}
}
