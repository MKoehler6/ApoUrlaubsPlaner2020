package apoPlaner2020;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ButtonListenerMAButton implements ActionListener{
	
	JButton button;
	
	public ButtonListenerMAButton(JButton button)
	{
		this.button = button;
	}

	@Override
	public void actionPerformed(ActionEvent event) 
	{
		
	}
	
	public void setzeGanzeWocheUrlaub(JButton b)
	{
		
	}
	
	public void setzeGanzeWocheDienst(JButton b)
	{
		
	}
	
	public void setzeTagUrlaub(JButton b)
	{
		
	}
	
	public void setzeTagDienst(JButton b)
	{
		
	}
	
	public boolean istWocheRot(JButton b)
	{
		return false;
	}
	public boolean istTagRot(JButton b)
	{
		
		return false;
	}
	
	public int tageUrlaubInDieserWoche(JButton b)
	{
		return 0;
	}
}
