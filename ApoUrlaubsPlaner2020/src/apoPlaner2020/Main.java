package apoPlaner2020;

public class Main
{
	public static void main(String[] args) 
	{
		DataModel dataModel = new DataModel();
		KalenderPanel kalenderPanel = new KalenderPanel(dataModel);
		Hauptfenster hauptfenster = new Hauptfenster(dataModel, kalenderPanel);
		Controller controller = new Controller(dataModel, kalenderPanel, hauptfenster);
		
	}

}
