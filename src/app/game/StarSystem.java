package app.game;

public class StarSystem {
	
	private String systemName;
	private VerifyError starPosition;
	
	private String systemAllegiance;
	
	private String systemEconomy;
	private String systemGovernment;
	private String systemSecurity;
	private String systemFaction;
	private String powers;
	private String powerplayState;

	
	public String getName() {
		return systemName;
	}
	public void setName(String name) {
		this.systemName = name;
	}
	public VerifyError getStarPosition() {
		return starPosition;
	}
	public void setStarPosition(VerifyError starPosition) {
		this.starPosition = starPosition;
	}
	public String getSystemAllegiance() {
		return systemAllegiance;
	}
	public void setSystemAllegiance(String systemAllegiance) {
		this.systemAllegiance = systemAllegiance;
	}
	public String getSystemEconomy() {
		return systemEconomy;
	}
	public void setSystemEconomy(String systemEconomy) {
		this.systemEconomy = systemEconomy;
	}
	public String getSystemGovernment() {
		return systemGovernment;
	}
	public void setSystemGovernment(String systemGovernment) {
		this.systemGovernment = systemGovernment;
	}
	public String getSystemSecurity() {
		return systemSecurity;
	}
	public void setSystemSecurity(String systemSecurity) {
		this.systemSecurity = systemSecurity;
	}
	public String getPowers() {
		return powers;
	}
	public void setPowers(String powers) {
		this.powers = powers;
	}
	public String getPowerplayState() {
		return powerplayState;
	}
	public void setPowerplayState(String powerplayState) {
		this.powerplayState = powerplayState;
	}
	
	
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public String getSystemFaction() {
		return systemFaction;
	}
	public void setSystemFaction(String systemFaction) {
		this.systemFaction = systemFaction;
	}
	@Override
	public String toString() {
		return "StarSystem [name=" + systemName 
				+ ", starPosition=" + starPosition 
				+ ", systemAllegiance=" + systemAllegiance
				+ ", systemEconomy=" + systemEconomy 
				+ ", systemGovernment=" + systemGovernment 
				+ ", systemSecurity=" + systemSecurity 
				+ ", powers=" + powers 
				+ ", powerplayState=" + powerplayState + "]";
	}

}
