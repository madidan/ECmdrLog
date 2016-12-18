package app.journal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;

import app.game.Mission;
import app.game.StarSystem;

public class ProcessJournal {
	
	// game
	private String gameMode;
	
	// commander
	private String cmdr;
	private String ship;
	private String credit;
	private String loan;
	
	// location
	private String currStation;
	private String currBody;
	private String currBodyType;
	
	//Missions
	List<Mission> missions = new ArrayList<>();
	StarSystem curenntSystem = new StarSystem();
	
	private boolean docked;
	private static final ProcessJournal processJournal = new ProcessJournal();
	
	private ProcessJournal(){}
	
	public static ProcessJournal getInstance(){
		return processJournal;
	}
	
	public void processLog(JSONObject jObject) {
					
		final String event = jObject.get("event").toString();
		System.out.println(jObject);		
		
		switch(event) {
			case "Fileheader":
				// TODO: do something ... or nothing
				break;
			case "LoadGame":
				break;
				
			case "Location":
				updatecurrSystem(jObject);
				docked = (boolean) jObject.get("Docked");
				break;
			case "FSDJump":
				updatecurrSystem(jObject);
				break;
								
				
			case "Docked":
				docked = true;
				currStation = (String) jObject.get("StationName");
				curenntSystem.setName((String) jObject.get("StarSystem"));
				break;
			case "Undocked":
				docked = false;
				break;
			case "Liftoff":
				docked = false;
				break;
			case "TouchDown":
				docked = true;
				break;
				
			case "MissionAccepted":
				Mission mission = new Mission((String) jObject.get("Name"), 
						(String) jObject.get("Faction"), 
						(String) jObject.get("DestinationSystem"), 
						(String) jObject.get("DestinationStation"), 
						(String) jObject.get("Expiry"), 
						(long) jObject.get("MissionID"));
				
				missions.add(mission);
				break;
			case "MissionCompleted":
				long missionId = (long) jObject.get("MissionID");
				missions = missions.stream().filter(m -> missionId != m.getMissionId()).collect(Collectors.toList());
				//TODO ad rewards
				break;
			case "MissionAbandoned":
			case "MissionFailed":
				missionId = (long) jObject.get("MissionID");
				missions = missions.stream().filter(m -> missionId != m.getMissionId()).collect(Collectors.toList());
				break;
				
			default:
		}
//		System.out.println(this.toString());
		
	}

	private void updatecurrSystem(JSONObject jObject) {
		curenntSystem.setName((String) jObject.get("StarSystem"));
		curenntSystem.setSystemAllegiance((String) jObject.get("SystemAllegiance"));
		curenntSystem.setSystemEconomy((String) jObject.get("SystemEconomy_Localised"));
		curenntSystem.setSystemGovernment((String) jObject.get("SystemGovernment_Localised"));
		curenntSystem.setSystemFaction((String) jObject.get("SystemFaction"));
	}

	public String getGameMode() {
		return gameMode;
	}

	public String getCmdr() {
		return cmdr;
	}

	public String getShip() {
		return ship;
	}

	public String getCredit() {
		return credit;
	}

	public String getLoan() {
		return loan;
	}

	public String getCurrStation() {
		return currStation;
	}

	public String getCurrBody() {
		return currBody;
	}

	public StarSystem getCurrSystem() {
		return curenntSystem;
	}

	public List<Mission> getMissions() {
		return missions;
	}
	
	public boolean hasMissions(){
		return !missions.isEmpty();
	}

	public boolean isDocked() {
		return docked;
	}
	
	@Override
	public String toString() {
		return "ProcessJournal ["
				+ ", currStation=" + currStation 
				+ ", currBody=" + currBody 
				+ ", currBodyType=" + currBodyType
				+ ", missions=" + missions 
				+ ", docked=" + docked 
				+ "]";
	}

}
