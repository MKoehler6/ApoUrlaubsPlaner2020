package apoPlaner2020;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
	JRadioButton gv1, gv2, gv3, gv4, gv5;
	JRadioButton gn1, gn2, gn3, gn4, gn5;
	JRadioButton uv1, uv2, uv3, uv4, uv5;
	JRadioButton un1, un2, un3, un4, un5;
	
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
		gv1 = new JRadioButton("Mo vorm");
		gv1.setName("!gerade:0:0!");
		gv1.addActionListener(new RadioButtonListener());
		panelGeradevormittag.add(gv1);
		gv2 = new JRadioButton("Di vorm");
		gv2.setName("!gerade:1:0!");
		gv2.addActionListener(new RadioButtonListener());
		panelGeradevormittag.add(gv2);
		gv3 = new JRadioButton("Mi vorm");
		gv3.setName("!gerade:2:0!");
		gv3.addActionListener(new RadioButtonListener());
		panelGeradevormittag.add(gv3);
		gv4 = new JRadioButton("Do vorm");
		gv4.setName("!gerade:3:0!");
		gv4.addActionListener(new RadioButtonListener());
		panelGeradevormittag.add(gv4);
		gv5 = new JRadioButton("Fr vorm");
		gv5.setName("!gerade:4:0!");
		gv5.addActionListener(new RadioButtonListener());
		panelGeradevormittag.add(gv5);
		
		JPanel panelGeradenachmittag = new JPanel();
		panelGeradenachmittag.setLayout(new GridLayout(5,0));
		gn1 = new JRadioButton("Mo nachm");
		gn1.setName("!gerade:0:1!");
		gn1.addActionListener(new RadioButtonListener());
		panelGeradenachmittag.add(gn1);
		gn2 = new JRadioButton("Di nachm");
		gn2.setName("!gerade:1:1!");
		gn2.addActionListener(new RadioButtonListener());
		panelGeradenachmittag.add(gn2);
		gn3 = new JRadioButton("Mi nachm");
		gn3.setName("!gerade:2:1!");
		gn3.addActionListener(new RadioButtonListener());
		panelGeradenachmittag.add(gn3);
		gn4 = new JRadioButton("Do nachm");
		gn4.setName("!gerade:3:1!");
		gn4.addActionListener(new RadioButtonListener());
		panelGeradenachmittag.add(gn4);
		gn5 = new JRadioButton("Fr nachm");
		gn5.setName("!gerade:4:1!");
		gn5.addActionListener(new RadioButtonListener());
		panelGeradenachmittag.add(gn5);
		
		JPanel panelUngeradevormittag = new JPanel();
		panelUngeradevormittag.setLayout(new GridLayout(5,0));
		uv1 = new JRadioButton("Mo vorm");
		uv1.setName("!ungerade:0:0!");
		uv1.addActionListener(new RadioButtonListener());
		panelUngeradevormittag.add(uv1);
		uv2 = new JRadioButton("Di vorm");
		uv2.setName("!ungerade:1:0!");
		uv2.addActionListener(new RadioButtonListener());
		panelUngeradevormittag.add(uv2);
		uv3 = new JRadioButton("Mi vorm");
		uv3.setName("!ungerade:2:0!");
		uv3.addActionListener(new RadioButtonListener());
		panelUngeradevormittag.add(uv3);
		uv4 = new JRadioButton("Do vorm");
		uv4.setName("!ungerade:3:0!");
		uv4.addActionListener(new RadioButtonListener());
		panelUngeradevormittag.add(uv4);
		uv5 = new JRadioButton("Fr vorm");
		uv5.setName("!ungerade:4:0!");
		uv5.addActionListener(new RadioButtonListener());
		panelUngeradevormittag.add(uv5);
		
		
		JPanel panelUngeradenachmittag = new JPanel();
		panelUngeradenachmittag.setLayout(new GridLayout(5,0));
		un1 = new JRadioButton("Mo nachm");
		un1.setName("!ungerade:0:1!");
		un1.addActionListener(new RadioButtonListener());
		panelUngeradenachmittag.add(un1);
		un2 = new JRadioButton("Di nachm");
		un2.setName("!ungerade:1:1!");
		un2.addActionListener(new RadioButtonListener());
		panelUngeradenachmittag.add(un2);
		un3 = new JRadioButton("Mi nachm");
		un3.setName("!ungerade:2:1!");
		un3.addActionListener(new RadioButtonListener());
		panelUngeradenachmittag.add(un3);
		un4 = new JRadioButton("Do nachm");
		un4.setName("!ungerade:3:1!");
		un4.addActionListener(new RadioButtonListener());
		panelUngeradenachmittag.add(un4);
		un5 = new JRadioButton("Fr nachm");
		un5.setName("!ungerade:4:1!");
		un5.addActionListener(new RadioButtonListener());
		panelUngeradenachmittag.add(un5);
		
		setzeRadioButtonsNeu();
		
		
		panelDienstplan.add(panelGeradevormittag);
		panelDienstplan.add(panelGeradenachmittag);
		panelDienstplan.add(panelUngeradevormittag);
		panelDienstplan.add(panelUngeradenachmittag);
		panelDienstplan.setBorder(new EmptyBorder(0, 40, 0, 0));;
		
		panelSouth.add(panelDienstplan);
		
		JPanel panelOK = new JPanel();
        JButton buttonOK = new JButton("OK");
        panelOK.add(buttonOK);
        buttonOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				controller.updateView();
			}
		});
        panelSouth.add(panelOK);
		
		add(panelSouth);
		
		setVisible(true);
	}
	
	public void setzeRadioButtonsNeu() {
		if (mitarbeiter.dienstplanArrayList.get(mitarbeiter.dienstplanArrayList.size()-1).getGeradeWoche()[0][0] == 1)
			gv1.setSelected(true);
		else gv1.setSelected(false);
		if (mitarbeiter.dienstplanArrayList.get(mitarbeiter.dienstplanArrayList.size()-1).getGeradeWoche()[1][0] == 1)
			gv2.setSelected(true);
		else gv2.setSelected(false);
		if (mitarbeiter.dienstplanArrayList.get(mitarbeiter.dienstplanArrayList.size()-1).getGeradeWoche()[2][0] == 1)
			gv3.setSelected(true);
		else gv3.setSelected(false);
		if (mitarbeiter.dienstplanArrayList.get(mitarbeiter.dienstplanArrayList.size()-1).getGeradeWoche()[3][0] == 1)
			gv4.setSelected(true);
		else gv4.setSelected(false);
		if (mitarbeiter.dienstplanArrayList.get(mitarbeiter.dienstplanArrayList.size()-1).getGeradeWoche()[4][0] == 1)
			gv5.setSelected(true);
		else gv5.setSelected(false);
		
		if (mitarbeiter.dienstplanArrayList.get(mitarbeiter.dienstplanArrayList.size()-1).getGeradeWoche()[0][1] == 1)
			gn1.setSelected(true);
		else gn1.setSelected(false);
		if (mitarbeiter.dienstplanArrayList.get(mitarbeiter.dienstplanArrayList.size()-1).getGeradeWoche()[1][1] == 1)
			gn2.setSelected(true);
		else gn2.setSelected(false);
		if (mitarbeiter.dienstplanArrayList.get(mitarbeiter.dienstplanArrayList.size()-1).getGeradeWoche()[2][1] == 1)
			gn3.setSelected(true);
		else gn3.setSelected(false);
		if (mitarbeiter.dienstplanArrayList.get(mitarbeiter.dienstplanArrayList.size()-1).getGeradeWoche()[3][1] == 1)
			gn4.setSelected(true);
		else gn4.setSelected(false);
		if (mitarbeiter.dienstplanArrayList.get(mitarbeiter.dienstplanArrayList.size()-1).getGeradeWoche()[4][1] == 1)
			gn5.setSelected(true);
		else gn5.setSelected(false);
		
		if (mitarbeiter.dienstplanArrayList.get(mitarbeiter.dienstplanArrayList.size()-1).getUngeradeWoche()[0][0] == 1)
			uv1.setSelected(true);
		else uv1.setSelected(false);
		if (mitarbeiter.dienstplanArrayList.get(mitarbeiter.dienstplanArrayList.size()-1).getUngeradeWoche()[1][0] == 1)
			uv2.setSelected(true);
		else uv2.setSelected(false);
		if (mitarbeiter.dienstplanArrayList.get(mitarbeiter.dienstplanArrayList.size()-1).getUngeradeWoche()[2][0] == 1)
			uv3.setSelected(true);
		else uv3.setSelected(false);
		if (mitarbeiter.dienstplanArrayList.get(mitarbeiter.dienstplanArrayList.size()-1).getUngeradeWoche()[3][0] == 1)
			uv4.setSelected(true);
		else uv4.setSelected(false);
		if (mitarbeiter.dienstplanArrayList.get(mitarbeiter.dienstplanArrayList.size()-1).getUngeradeWoche()[4][0] == 1)
			uv5.setSelected(true);
		else uv5.setSelected(false);
		
		if (mitarbeiter.dienstplanArrayList.get(mitarbeiter.dienstplanArrayList.size()-1).getUngeradeWoche()[0][1] == 1)
			un1.setSelected(true);
		else un1.setSelected(false);
		if (mitarbeiter.dienstplanArrayList.get(mitarbeiter.dienstplanArrayList.size()-1).getUngeradeWoche()[1][1] == 1)
			un2.setSelected(true);
		else un2.setSelected(false);
		if (mitarbeiter.dienstplanArrayList.get(mitarbeiter.dienstplanArrayList.size()-1).getUngeradeWoche()[2][1] == 1)
			un3.setSelected(true);
		else un3.setSelected(false);
		if (mitarbeiter.dienstplanArrayList.get(mitarbeiter.dienstplanArrayList.size()-1).getUngeradeWoche()[3][1] == 1)
			un4.setSelected(true);
		else un4.setSelected(false);
		if (mitarbeiter.dienstplanArrayList.get(mitarbeiter.dienstplanArrayList.size()-1).getUngeradeWoche()[4][1] == 1)
			un5.setSelected(true);
		else un5.setSelected(false);
	}
	
	class RadioButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String geradeUngerade = e.getSource().toString().split("!")[1].split(":")[0];
			int index1 = Integer.parseInt(e.getSource().toString().split("!")[1].split(":")[1]);
			int index2 = Integer.parseInt(e.getSource().toString().split("!")[1].split(":")[2]);
			if (geradeUngerade.equals("gerade"))
				if (mitarbeiter.dienstplanArrayList.get(mitarbeiter.dienstplanArrayList.size()-1).getGeradeWoche()[index1][index2] == 1) 
					mitarbeiter.dienstplanArrayList.get(mitarbeiter.dienstplanArrayList.size()-1).getGeradeWoche()[index1][index2] = 0;
				else
					mitarbeiter.dienstplanArrayList.get(mitarbeiter.dienstplanArrayList.size()-1).getGeradeWoche()[index1][index2] = 1;
			if (geradeUngerade.equals("ungerade"))
				if (mitarbeiter.dienstplanArrayList.get(mitarbeiter.dienstplanArrayList.size()-1).getUngeradeWoche()[index1][index2] == 1) 
					mitarbeiter.dienstplanArrayList.get(mitarbeiter.dienstplanArrayList.size()-1).getUngeradeWoche()[index1][index2] = 0;
				else
					mitarbeiter.dienstplanArrayList.get(mitarbeiter.dienstplanArrayList.size()-1).getUngeradeWoche()[index1][index2] = 1;
		}
		
	}

}
