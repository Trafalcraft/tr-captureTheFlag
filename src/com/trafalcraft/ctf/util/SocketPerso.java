package com.trafalcraft.ctf.util;

import com.trafalcraft.client.api.SocketPlugin;
import com.trafalcraft.ctf.RandowmTeam;
import com.trafalcraft.ctf.Controler.ArenaControle;
import com.trafalcraft.ctf.Controler.PlayerControle;
import com.trafalcraft.ctf.file.FileControler;

public class SocketPerso implements SocketPlugin {

	@Override
	public void playerJoin(String arg0, String arg1, String arg2) {
		if(!arg2.equalsIgnoreCase("red") || arg2.equalsIgnoreCase("blue")){
			PlayerControle.addPlayer(arg0, arg1, RandowmTeam.chooseTeam(arg1));
		}else{
			PlayerControle.addPlayer(arg0, arg1, arg2);
		}
		
	}

	@Override
	public boolean checkJoin(String arg0, String arg1) {
		
		
		if(ArenaControle.getArena(arg0).getStatus().equalsIgnoreCase("lobby")){
			
			if(arg1.equalsIgnoreCase("red") || arg1.equalsIgnoreCase("blue")){
				
				if(ArenaControle.getArena(arg0).getTeam(arg1).getPlayerList().size() < FileControler.getArena(arg0).getInt("maxplayer")){
					
					return true;
				}else{
					
					return false;
				}
			}else{
				
				if(ArenaControle.getArena(arg0).getTeam("blue").getPlayerList().size() < FileControler.getArena(arg0).getInt("maxplayer") || ArenaControle.getArena(arg0).getTeam("red").getPlayerList().size() < FileControler.getArena(arg0).getInt("maxplayer")){
					
					return true;
				}else{
					
					return false;
				}
			}
		}else{

			return false;
		}
	}

}
