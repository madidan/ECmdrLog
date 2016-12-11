package app;

import java.awt.AWTException;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JTextPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.JButton;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

public class RWindow {

	private JFrame frame;
	private JTextField tagsField;
	private JTextPane notePane;
	private JPanel imagePane;
	
	static final String DATAPATH = "data/";
	static final String IMAGEPATH = DATAPATH+"images/";
	static final String DATAFILE = DATAPATH+"notes.json";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RWindow window = new RWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setForeground(Color.BLACK);
		frame.setUndecorated(true);
//		frame.setOpacity(0.6f);
		frame.setBounds(0, 300, 450, 500);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// exit button
		JButton btnClose = new JButton("");
		btnClose.setIcon(new ImageIcon("resource/icon-close.png"));
		btnClose.addActionListener(e -> System.exit(0));
		btnClose.setBounds(420, 10, 20, 20);
		frame.getContentPane().add(btnClose);
		
		// current System
		JLabel lblSystem = new JLabel("currentSystem");
		lblSystem.setBounds(107, 5, 128, 25);
		lblSystem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.getContentPane().add(lblSystem);
		
		// Tags
		tagsField = new JTextField();
		tagsField.setBounds(29, 40, 345, 28);
		frame.getContentPane().add(tagsField);
		tagsField.setColumns(10);
		
		// take screenshot button
		JButton btnScreenShot = new JButton("");
		btnScreenShot.addActionListener(e -> takeScreenShot() );
		btnScreenShot.setIcon(new ImageIcon("resource/icon-camera.png"));
		btnScreenShot.setBounds(384, 40, 28, 28);
		frame.getContentPane().add(btnScreenShot);

		// Note text
		notePane = new JTextPane();
		JScrollPane scrollPane = new JScrollPane(notePane);
		scrollPane.setBounds(29, 77, 377, 255);
		frame.getContentPane().add(scrollPane);
		
		// imagesPane
		imagePane = new JPanel();
		JScrollPane panel = new JScrollPane(imagePane);
		panel.setBounds(29, 343, 377, 100);
		frame.getContentPane().add(panel);
		
						
		// save/add button
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(29, 454, 75, 23);
		btnSave.addActionListener(e -> saveNote());
		frame.getContentPane().add(btnSave);
		
		// import button
		JButton btnImport = new JButton("Import");
		btnImport.setBounds(255, 454, 65, 23);
		frame.getContentPane().add(btnImport);
		
		// export button
		JButton btnExport = new JButton("Export");
		btnExport.setBounds(329, 454, 75, 23);
		frame.getContentPane().add(btnExport);
	}
	
	
	private void saveNote() {
		String[] tags = tagsField.getText().split(";");
		String textNote = notePane.getText();
		JSONArray jNote = null;

		if (new File(DATAFILE).isFile()) {
			jNote = getDataFromDataFile();
		} else {
			jNote = new JSONArray();
		}
		
		JSONArray jsonTags = new JSONArray();
		JSONObject jsonNote = new JSONObject();
		for (String tag : tags) {
			jsonTags.add(tag);
		}
		jsonNote.put("tags", jsonTags);
		jsonNote.put("note", textNote);

		jNote.add(jsonNote);

		saveJsonTOFile(jNote);

	}

	private void saveJsonTOFile(JSONArray jsonNote) {
		
		FileWriter file;
		try {
			
			file = new FileWriter(DATAFILE);
			file.write(jsonNote.toJSONString());
			file.flush();
			file.close();
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}	
		
	}

	private JSONArray getDataFromDataFile() {
		JSONParser parser = new JSONParser();
		JSONArray jsonArrayNotes = null;
             
        try {
        	jsonArrayNotes = (JSONArray) parser.parse(new FileReader(DATAFILE));
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found. A new file will be created");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return jsonArrayNotes ;
	}

	private void takeScreenShot() {
		BufferedImage image = null;
		try {
			frame.setVisible(false);
			image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd-HHMMss");
			String fileName = sdf.format(new Date()) + ".png";
			ImageIO.write(image, "png", new File(IMAGEPATH+fileName));
		} catch (HeadlessException | AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame.setVisible(true);
		ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(100, 70, Image.SCALE_SMOOTH));
		
		
		imagePane.add(new JLabel(imageIcon));
	}

	private void showNotesWindow(){
		while(frame.getBounds().x <= 0){
			frame.setLocation(frame.getBounds().x + 2, frame.getBounds().y);		
		}
	}
}
