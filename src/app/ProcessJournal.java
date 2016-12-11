package app;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;

public class ProcessJournal {
	
	// game
	private String gameMode;
	
	// commander
	private String cmdr;
	private String ship;
	private String credit;
	private String loan;
		
	// Rank
	private String tradeRank;
	private String exploreRank;
	private String combatRank;
	private String cqcRank;
	private String empireRank;
	private String fedRank;
	
	// location
	private String currSystem;
	private String currStation;
	private String currSysAllegiance;
	private String currSysEconomy;
	private String currSysGovernment;
	private String currSysFaction;
	private String currBody;
	private String currBodyType;
	
	//Missions
	List<Mission> missions = new ArrayList<>();
	
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
								
				
			case "Docked":
				docked = true;
				currStation = (String) jObject.get("StationName");
				currSystem = (String) jObject.get("StarSystem");
				break;
			case "Undocked":
				docked = false;
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
				
			case "FSDJump":
				updatecurrSystem(jObject);
				//TODO add jump info if needed
				break;
				
			case "Liftoff":
				docked = false;
				break;
				
			case "TouchDown":
				docked = true;
				break;
				
			default:
		}
		System.out.println(this.toString());
		
	}

	private void updatecurrSystem(JSONObject jObject) {
		// currSystem = (String) jObject.get("StarSystem");
		currSysAllegiance= (String) jObject.get("SystemAllegiance");
		currSysEconomy = (String) jObject.get("SystemEconomy_Localised");
		currSysGovernment = (String) jObject.get("SystemGovernment_Localised");
		currSysFaction= (String) jObject.get("SystemFaction");
		currBody = (String) jObject.get("Body");
		currBodyType= (String) jObject.get("BodyType");

		
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

	public String getTradeRank() {
		return tradeRank;
	}

	public String getExploreRank() {
		return exploreRank;
	}

	public String getCombatRank() {
		return combatRank;
	}

	public String getCqcRank() {
		return cqcRank;
	}

	public String getEmpireRank() {
		return empireRank;
	}

	public String getFedRank() {
		return fedRank;
	}

	public String getCurrSystem() {
		return currSystem;
	}

	public String getCurrStation() {
		return currStation;
	}

	public String getCurrSysAllegiance() {
		return currSysAllegiance;
	}

	public String getCurrSysEconomy() {
		return currSysEconomy;
	}

	public String getCurrSysGovernment() {
		return currSysGovernment;
	}

	public String getCurrSysFaction() {
		return currSysFaction;
	}

	public String getCurrBody() {
		return currBody;
	}

	public String getCurrBodyType() {
		return currBodyType;
	}

	public List<Mission> getMissions() {
		return missions;
	}
	
	public boolean hasMission(){
		return !missions.isEmpty();
	}

	public boolean isDocked() {
		return docked;
	}
	
	@Override
	public String toString() {
		return "ProcessJournal ["
//				+ "gameMode=" + gameMode 
//				+ ", cmdr=" + cmdr 
//				+ ", ship=" + ship 
//				+ ", credit=" + credit
//				+ ", loan=" + loan 
//				+ ", tradeRank=" + tradeRank 
//				+ ", exploreRank=" + exploreRank 
//				+ ", combatRank=" + combatRank 
//				+ ", cqcRank=" + cqcRank 
//				+ ", empireRank=" + empireRank 
//				+ ", fedRank=" + fedRank
				+ ", currSystem=" + currSystem 
				+ ", currStation=" + currStation 
				+ ", currSysAllegiance=" + currSysAllegiance 
				+ ", currSysEconomy=" + currSysEconomy 
				+ ", currSysGovernment=" + currSysGovernment
				+ ", currSysFaction=" + currSysFaction 
				+ ", currBody=" + currBody 
				+ ", currBodyType=" + currBodyType
				+ ", missions=" + missions 
				+ ", docked=" + docked 
				+ "]";
	}

}
