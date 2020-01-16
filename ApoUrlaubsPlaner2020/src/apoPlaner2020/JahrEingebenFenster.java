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
				String j = textField.getText();
				if (j.length() != 4 || j.chars()
						.filter(c -> c > 47 & c < 58)
						.count() != 4) {
					textField.setText("");
					return;
				}
				int jahr = Integer.parseInt(j);
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
