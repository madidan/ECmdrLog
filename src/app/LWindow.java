package app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class LWindow {

	private JFrame frame;
	
	private JLabel lblCurrSys;
	private JLabel lblCurrStatus;
	private JTextPane txtpnMissions;
	private JTextPane txtpnBalance;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LWindow window = new LWindow();
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
	public LWindow() {
		Runnable journalProcessor = () -> {
			new JSONReader(this);
		};
		new Thread(journalProcessor).start();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panelSys = new JPanel();
		panelSys.setBounds(0, 11, 434, 70);
		frame.getContentPane().add(panelSys);
		
		JLabel lblSystemholder = new JLabel("CRNT SYS:     ");
		lblSystemholder.setHorizontalAlignment(SwingConstants.LEFT);
		panelSys.add(lblSystemholder);
		
		lblCurrSys = new JLabel("--");
		lblCurrSys.setFont(new Font("Trebuchet MS", Font.PLAIN, 30));
		panelSys.add(lblCurrSys);
		
		JPanel panelStatus = new JPanel();
		panelStatus.setBounds(10, 92, 424, 101);
		frame.getContentPane().add(panelStatus);
		GridBagLayout gbl_panelStatus = new GridBagLayout();
		gbl_panelStatus.columnWidths = new int[]{0, 0};
		gbl_panelStatus.rowHeights = new int[]{0, 0, 0};
		gbl_panelStatus.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelStatus.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panelStatus.setLayout(gbl_panelStatus);
		
		JLabel lblStatus = new JLabel("STATUS:");
		GridBagConstraints gbc_lblStatus = new GridBagConstraints();
		gbc_lblStatus.insets = new Insets(0, 0, 5, 0);
		gbc_lblStatus.anchor = GridBagConstraints.WEST;
		gbc_lblStatus.gridx = 0;
		gbc_lblStatus.gridy = 0;
		panelStatus.add(lblStatus, gbc_lblStatus);
		
		lblCurrStatus = new JLabel("--");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		panelStatus.add(lblCurrStatus, gbc_lblNewLabel);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 204, 424, 346);
		frame.getContentPane().add(tabbedPane);
		
		txtpnMissions = new JTextPane();
		tabbedPane.addTab("Missions", null, txtpnMissions, null);
		
		txtpnBalance = new JTextPane();
		txtpnBalance.setText("bilanz");
		tabbedPane.addTab("Balance", null, txtpnBalance, null);
		
	}
	
	public JTextPane getTxtpnBalance() {
		return txtpnBalance;
	}
	public JLabel getLblCurrStatus() {
		return lblCurrStatus;
	}
	public JTextPane getTxtpnMissions() {
		return txtpnMissions;
	}
	public JLabel getLblCurrSys() {
		return lblCurrSys;
	}
}
