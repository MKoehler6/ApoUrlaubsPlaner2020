package apoPlaner2020;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class JahrEingebenFenster extends JFrame{
	
	DataModel dataModel;
	Controller controller;
	private static final int ANZAHL_STELLEN_JAHRESZAHL = 4;
	private static final int ASCII_CODE_0 = 48;
	private static final int ASCII_CODE_9 = 57;
	
	
	public JahrEingebenFenster(DataModel dataModel, Controller controller) {
		super("Jahr");
		this.dataModel = dataModel;
		this.controller = controller;
		
		setSize(250, 100);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		
		JPanel panelNorth = new JPanel();
		JPanel panelSouth = new JPanel();
		panelNorth.setLayout(new GridLayout(1,2));
		panelSouth.setLayout(new GridLayout(1,1));
		
		JLabel label = new JLabel("Jahr eingeben:");
		panelNorth.add(label);
		
		JTextField textField = new JTextField();
		panelNorth.add(textField);
		
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String jahrString = textField.getText();
				if (jahrString.length() != ANZAHL_STELLEN_JAHRESZAHL || jahrString.chars()
						.filter(c -> c >= ASCII_CODE_0 & c <= ASCII_CODE_9)
						.count() != ANZAHL_STELLEN_JAHRESZAHL) {
					textField.setText("");
					return;
				}
				int jahr = Integer.parseInt(jahrString);
				dataModel.setJahr(jahr-2000);
				dispose();
				controller.setJahrOnLabel(jahr-2000);
				controller.updateView();
			}
		});
		JPanel panelButton = new JPanel();
		panelButton.add(ok);
		panelSouth.add(panelButton);
		add(panelNorth, BorderLayout.CENTER);
		add(panelSouth, BorderLayout.SOUTH);
		setVisible(true);
	}
	
	

}
