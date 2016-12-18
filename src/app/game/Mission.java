package app.game;


public class Mission {
	
	private final String name;
	private final String faction;
	private final String destSys;
	private final String destStation;
	private final String expireTime;
	private final long missionId;
	
	public Mission(String name, String faction, String destSys, String sedtStation, String expireTime,
			long missionId) {
		super();
		this.name = name;
		this.faction = faction;
		this.destSys = destSys;
		this.destStation = sedtStation;
		this.expireTime = expireTime;
		this.missionId = missionId;
	}
	
	
	
	public String getName() {
		return name;
	}
 	
	public String getFaction() {
		return faction;
	}

	public String getDestSys() {
		return destSys;
	}

	public String getDestStation() {
		return destStation;
	}

	public String getExpireTime() {
		return expireTime;
	}
	
	public long getMissionId() {
		return missionId;
	}

	
}
