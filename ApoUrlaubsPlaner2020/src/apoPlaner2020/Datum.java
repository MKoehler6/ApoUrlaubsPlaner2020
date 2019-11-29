package apoPlaner2020;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.IsoFields;

public class Datum {
	
	int year = 2019;
	int week;
	int[] ferien = {1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,1};
	
	public static LocalDate getTodayDate() {
		return LocalDate.now();
	}
	public String gibMontagVonWocheX (int week)
	{
		LocalDate date = LocalDate.of(year, Month.JANUARY, 10);
		LocalDate dayInWeek = date.with(IsoFields.WEEK_OF_WEEK_BASED_YEAR, week);
		LocalDate dateMonday = dayInWeek.with(DayOfWeek.MONDAY);
		String dateStringEnglish = dateMonday.toString();
		String dateStringGerman = dateStringEnglish.split("-")[2] + "."
				+ dateStringEnglish.split("-")[1] + ".";
		return dateStringGerman;
	}
	
	public String gibFreitagVonWocheX (int week)
	{
		LocalDate date = LocalDate.of(year, Month.JANUARY, 10);
		LocalDate dayInWeek = date.with(IsoFields.WEEK_OF_WEEK_BASED_YEAR, week);
		LocalDate dateFriday = dayInWeek.with(DayOfWeek.FRIDAY);
		String dateStringEnglish = dateFriday.toString();
		String dateStringGerman = dateStringEnglish.split("-")[2] + "."
				+ dateStringEnglish.split("-")[1] + ".";
		return dateStringGerman;
	}
	public String gibDienstagVonWocheX (int week)
	{
		LocalDate date = LocalDate.of(year, Month.JANUARY, 10);
		LocalDate dayInWeek = date.with(IsoFields.WEEK_OF_WEEK_BASED_YEAR, week);
		LocalDate dateTuesday = dayInWeek.with(DayOfWeek.TUESDAY);
		String dateStringEnglish = dateTuesday.toString();
		String dateStringGerman = dateStringEnglish.split("-")[2] + "."
				+ dateStringEnglish.split("-")[1] + ".";
		return dateStringGerman;
	}
	public String gibMittwochVonWocheX (int week)
	{
		LocalDate date = LocalDate.of(year, Month.JANUARY, 10);
		LocalDate dayInWeek = date.with(IsoFields.WEEK_OF_WEEK_BASED_YEAR, week);
		LocalDate dateWednesday = dayInWeek.with(DayOfWeek.WEDNESDAY);
		String dateStringEnglish = dateWednesday.toString();
		String dateStringGerman = dateStringEnglish.split("-")[2] + "."
				+ dateStringEnglish.split("-")[1] + ".";
		return dateStringGerman;
	}
	public String gibDonnerstagVonWocheX (int week)
	{
		LocalDate date = LocalDate.of(year, Month.JANUARY, 10);
		LocalDate dayInWeek = date.with(IsoFields.WEEK_OF_WEEK_BASED_YEAR, week);
		LocalDate dateThursday = dayInWeek.with(DayOfWeek.THURSDAY);
		String dateStringEnglish = dateThursday.toString();
		String dateStringGerman = dateStringEnglish.split("-")[2] + "."
				+ dateStringEnglish.split("-")[1] + ".";
		return dateStringGerman;
	}

}
