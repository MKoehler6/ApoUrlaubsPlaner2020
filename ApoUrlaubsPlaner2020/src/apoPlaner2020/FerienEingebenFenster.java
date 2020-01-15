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

public class FerienEingebenFenster extends JFrame {
	
	private DataModel dataModel;
	private Controller controller;
	private JRadioButton[] radioButtonsArray = new JRadioButton[52];
	
	public FerienEingebenFenster(DataModel dataModel, Controller controller) {
		super("Bitte Ferien eingeben");
		this.dataModel = dataModel;
		this.controller = controller;
		
		setSize(600, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		
		JPanel panelNorth = new JPanel();
		JPanel panelSouth = new JPanel();
		panelNorth.setLayout(new GridLayout(4,13));
		panelSouth.setLayout(new GridLayout(1,1));
		
		for (int woche = 0; woche < 52; woche++) {
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(3,0));
			JLabel label = new JLabel("" + (woche+1));
			JRadioButton radioButton = new JRadioButton();
			radioButtonsArray[woche] = radioButton;
			panel.add(new JLabel(" "));
			panel.add(label);
			panel.add(radioButton);
			panelNorth.add(panel);
		}
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int woche = 0; woche < 52; woche++) {
					if (radioButtonsArray[woche].isSelected()) {
						dataModel.setFerienInWocheX(woche, 1);
					} else {
						dataModel.setFerienInWocheX(woche, 0);
					}
				}
				for (int woche = 0; woche < 52; woche++) {
					System.out.print(dataModel.getFerienInWocheX(woche)+ ",");
				}
				dispose();
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
