package apoPlaner2020;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MAbearbeitenFenster extends JFrame {
	
	Mitarbeiter mitarbeiter;
	Controller controller;
	String[] tageString = {"Mo","Di","Mi","Do","Fr"};
	String[] vormNachmString = {"vorm","nachm"};
	String[] geradeUngeradeString = {"gerade","ungerade"};
	JRadioButton[][][] radioButtons = new JRadioButton[5][2][2]; // Tag, vormNachm, geradeUngerade
	JPanel[][] panelArray  = new JPanel[2][2]; // vormNachm, geradeUngerade
	boolean dienstplanGeaendert = false;
	
	public MAbearbeitenFenster(ArrayList<Mitarbeiter> mitarbeiterArrayList, Controller controller) {
		super("Mitarbeiter bearbeiten");
		this.controller = controller;
		mitarbeiter = mitarbeiterArrayList.get(0);
		
		JPanel panelNorth = new JPanel();
		JPanel panelSouth = new JPanel();
		panelNorth.setLayout(new GridLayout(4,1));
		panelSouth.setLayout(new GridLayout(3,1));
		
		setSize(600, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(2,0));
		
		JLabel labelLeer = new JLabel(" ");
		panelNorth.add(labelLeer);
		JLabel labelMAauswaehlen = new JLabel("Mitarbeiter auswählen", SwingConstants.CENTER);
		panelNorth.add(labelMAauswaehlen);
		
		JPanel panelComboBox = new JPanel();
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		for (int i = 0; i < mitarbeiterArrayList.size(); i++) {
			model.addElement(mitarbeiterArrayList.get(i).getName());
		}
        JComboBox<String> comboBox = new JComboBox<String>(model);
        comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (dienstplanGeaendert) {
					speicherRadioButtonsInNeuemDienstplan();
					dienstplanGeaendert = false;
				}
				mitarbeiter = mitarbeiterArrayList.get(comboBox.getSelectedIndex());
				setzeRadioButtonsNeu();
			}
		});
        panelComboBox.add(comboBox);
        panelNorth.add(panelComboBox);
        
        JPanel panelButtonNeuerMA = new JPanel();
        JButton buttonNeuerMA = new JButton("Neuen Mitarbeiter anlegen");
        panelButtonNeuerMA.add(buttonNeuerMA);
        panelNorth.add(panelButtonNeuerMA);
		add(panelNorth);
		
		JLabel labelDienstplan = new JLabel("Gerade Woche            ***  DIENSTPLAN  ***            Ungerade Woche", SwingConstants.CENTER);
		panelSouth.add(labelDienstplan);
		
		JPanel panelDienstplan = new JPanel();
		panelDienstplan.setLayout(new GridLayout(1,4));
		
		JPanel panelGeradevormittag = new JPanel();
		panelGeradevormittag.setLayout(new GridLayout(5,0));
		panelArray[0][0] = panelGeradevormittag;
		JPanel panelGeradenachmittag = new JPanel();
		panelGeradenachmittag.setLayout(new GridLayout(5,0));
		panelArray[1][0] = panelGeradenachmittag;
		JPanel panelUngeradevormittag = new JPanel();
		panelUngeradevormittag.setLayout(new GridLayout(5,0));
		panelArray[0][1] = panelUngeradevormittag;
		JPanel panelUngeradenachmittag = new JPanel();
		panelUngeradenachmittag.setLayout(new GridLayout(5,0));
		panelArray[1][1] = panelUngeradenachmittag;
		
		for (int tag = 0; tag < 5; tag++) {
			for (int vormNachm = 0; vormNachm < 2; vormNachm++) {
				for (int geradeUngerade = 0; geradeUngerade < 2; geradeUngerade++) {
					radioButtons[tag][vormNachm][geradeUngerade] = 
							new JRadioButton(tageString[tag] + " " + vormNachmString[vormNachm]);
					radioButtons[tag][vormNachm][geradeUngerade].setName("!" + 
							geradeUngeradeString[geradeUngerade] + ":" + 
							tag + ":" + vormNachm + "!");
					radioButtons[tag][vormNachm][geradeUngerade].addActionListener(new RadioButtonListener());
					panelArray[vormNachm][geradeUngerade].add(radioButtons[tag][vormNachm][geradeUngerade]);
				}
			}
		}
		
		setzeRadioButtonsNeu();
		
		panelDienstplan.add(panelGeradevormittag);
		panelDienstplan.add(panelGeradenachmittag);
		panelDienstplan.add(panelUngeradevormittag);
		panelDienstplan.add(panelUngeradenachmittag);
		panelDienstplan.setBorder(new EmptyBorder(0, 40, 0, 0));;
		
		panelSouth.add(panelDienstplan);
		
		JPanel panelButtons = new JPanel();
		JPanel panelOK = new JPanel();
        JButton buttonOK = new JButton("OK");
        panelOK.add(buttonOK);
        buttonOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				if (dienstplanGeaendert) {
					speicherRadioButtonsInNeuemDienstplan();
				}
				controller.updateView();
			}
		});
        JPanel panelAbbrechen = new JPanel();
        JButton buttonAbbrechen = new JButton("Abbrechen");
        panelAbbrechen.add(buttonAbbrechen);
        buttonAbbrechen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
        panelButtons.add(panelOK);
        panelButtons.add(panelAbbrechen);
        panelSouth.add(panelButtons);
		
		add(panelSouth);
		
		setVisible(true);
	}
	
	public void setzeRadioButtonsNeu() {
		for (int tag = 0; tag < 5; tag++) {
			for (int vormNachm = 0; vormNachm < 2; vormNachm++) {
				if (mitarbeiter.dienstplanArrayList
						.get(mitarbeiter.dienstplanArrayList.size()-1).getGeradeWoche()[tag][vormNachm] == 1)
					radioButtons[tag][vormNachm][0].setSelected(true);
				else radioButtons[tag][vormNachm][0].setSelected(false);
			}
		}
		for (int tag = 0; tag < 5; tag++) {
			for (int vormNachm = 0; vormNachm < 2; vormNachm++) {
				if (mitarbeiter.dienstplanArrayList
						.get(mitarbeiter.dienstplanArrayList.size()-1).getUngeradeWoche()[tag][vormNachm] == 1)
					radioButtons[tag][vormNachm][1].setSelected(true);
				else radioButtons[tag][vormNachm][1].setSelected(false);
			}
		}
	}
	
	public void speicherRadioButtonsInNeuemDienstplan() {
		Dienstplan dienstplan  = new Dienstplan();
		mitarbeiter.dienstplanArrayList.add(dienstplan);
		for (int tag = 0; tag < 5; tag++) {
			for (int vormNachm = 0; vormNachm < 2; vormNachm++) {
				if (radioButtons[tag][vormNachm][0].isSelected())
					dienstplan.getGeradeWoche()[tag][vormNachm] = 1;
				else 
					dienstplan.getGeradeWoche()[tag][vormNachm] = 0;
			}
		}
		for (int tag = 0; tag < 5; tag++) {
			for (int vormNachm = 0; vormNachm < 2; vormNachm++) {
				if (radioButtons[tag][vormNachm][1].isSelected())
					dienstplan.getUngeradeWoche()[tag][vormNachm] = 1;
				else 
					dienstplan.getUngeradeWoche()[tag][vormNachm] = 0;
			}
		}
		Calendar calendar = new GregorianCalendar();
		dienstplan.setGueltigAb(calendar.get(Calendar.WEEK_OF_YEAR));
	}
	class RadioButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dienstplanGeaendert = true;
		}
		
	}
}
