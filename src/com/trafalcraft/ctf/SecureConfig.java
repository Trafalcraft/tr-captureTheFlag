package com.trafalcraft.ctf;

import com.trafalcraft.ctf.file.FileControler;

public class SecureConfig {

	public static boolean testConfig(String arene){
		if(FileControler.getArena(arene).contains("world") && FileControler.getArena(arene).getString("world") != null){
			if(FileControler.getArena(arene).contains("maxplayer") && FileControler.getArena(arene).getString("maxplayer") != null){
				if(FileControler.getArena(arene).contains("redspawn") && FileControler.getArena(arene).getString("redspawn") != null){
					if(FileControler.getArena(arene).contains("bluespawn") && FileControler.getArena(arene).getString("bluespawn") != null){
						if(FileControler.getArena(arene).contains("temps") && FileControler.getArena(arene).getString("temps") != null){
							if(FileControler.getArena(arene).contains("friendlyfire") && FileControler.getArena(arene).getString("friendlyfire") != null){
								if(FileControler.getArena(arene).contains("lobby.red.x") && FileControler.getArena(arene).getString("lobby.red.x") != null){
									if(FileControler.getArena(arene).contains("lobby.blue.x") && FileControler.getArena(arene).getString("lobby.blue.x") != null){
										if(FileControler.getArena(arene).contains("redflag") && FileControler.getArena(arene).getString("redflag") != null){
											if(FileControler.getArena(arene).contains("blueflag") && FileControler.getArena(arene).getString("blueflag") != null){
												if(FileControler.getArena(arene).contains("maxpoint") && FileControler.getArena(arene).getString("maxpoint") != null){
													if(FileControler.getArena(arene).contains("eventlobby.x") && FileControler.getArena(arene).getString("eventlobby.x") != null){
														return true;
													}else{
														return false;
													}
												}else{
													return false;
												}
											}else{
												return false;
											}
										}else{
											return false;
										}
									}else{
										return false;
									}
								}else{
									return false;
								}
							}else{
								return false;
							}
						}else{
							return false;
						}
					}else{
						return false;
					}
				}else{
					return false;
				}
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
}
