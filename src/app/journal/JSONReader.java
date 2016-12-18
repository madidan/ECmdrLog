package app.journal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import app.game.Mission;
import app.game.StarSystem;
import app.gui.LWindow;

public class JSONReader {

	private static final Logger logger = Logger.getLogger(JSONReader.class.getName());

	private JSONObject jObject;
	private JSONParser parser;
	private ProcessJournal processJournal = ProcessJournal.getInstance();

	public JSONReader(LWindow window) {
		boolean reading = true;
		String journalFile = getCurrentJournalFile();
		logger.log(Level.INFO, "reading from: " + journalFile);
		parser = new JSONParser();

		try (BufferedReader br = new BufferedReader(new FileReader(journalFile))) {
			while (reading) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					logger.log(Level.SEVERE, "error waiting for input from file");
					e.printStackTrace();
				}
				String line = br.readLine();
				if (line == null) {
					continue;
				}
				jObject = (JSONObject) parser.parse(line);

				processJournal.processLog(jObject);
				updateWindow(window);

			}

		} catch (FileNotFoundException e) {
			logger.log(Level.SEVERE, "json file not found");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			logger.log(Level.SEVERE, "json parse error");
			e.printStackTrace();
		}
	}

	private void updateWindow(LWindow window) {

		StarSystem currentSytem = processJournal.getCurrSystem();

		window.getLblCurrSys().setText(currentSytem.getName());
		window.getLblCurrStatus().setText(processJournal.isDocked() ? "Docked" : "");
		if (processJournal.hasMissions()) {
			String missionsStr = "";
			for (Mission mission : processJournal.getMissions()) {
				String mFaction = mission.getFaction();
				String mName = mission.getName();

				missionsStr += mFaction + "\n\t" + mName + "\n";
				if (mission.getDestSys() != null) {
					missionsStr += "\t" + "Dest: " + mission.getDestSys() + " - " + mission.getDestStation() + "\n";
				}
			}
			window.getTxtpnMissions().setText(missionsStr);
		} else {
			window.getTxtpnMissions().setText("");
		}

	}

	private String getCurrentJournalFile() {
		String savedGames = System.getenv("USERPROFILE")
				.concat("\\Saved Games\\Frontier Developments\\Elite Dangerous");
		File[] files = new File(savedGames).listFiles();

		return Stream.of(files).reduce((f1, f2) -> f1.lastModified() > f2.lastModified() ? f1 : f2)
				.map(File::getAbsolutePath).get();
	}

}
