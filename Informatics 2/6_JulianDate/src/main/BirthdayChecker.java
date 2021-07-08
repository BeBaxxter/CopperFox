package main;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * This class gives the age of the person in days
 * and tells on which day of the week he was born
 * @author katja
 * @author lucas
 * @version 0.1
 */

public class BirthdayChecker {

	/** variables and fields **/
	private static Scanner in = new Scanner(System.in);


	public static void main (String args []) {

		BirthdayChecker bd = new BirthdayChecker();
		JulianDate jd = new JulianDate();

		System.out.println("Julian time today: " + jd.julianDayNumber);
		JulianDate birthday1 = bd.checkBirthday(jd);
		JulianDate birthday2 = bd.checkBirthday(jd);

		//compares who's older
		if (birthday1.isAfter(birthday2)) {
			System.out.println("\nPerson1 is younger than Person2");
		} else {
			System.out.println("\nPerson1 is older than Person2");
		}

		in.close();
	}

	/**
	 * Calculate the age of the person in days
	 * @return
	 */
	public JulianDate checkBirthday(JulianDate jd) {

		System.out.print("Enter your birthday (according to the format yyyy mm dd): ");

		int input;
		int[] birth = new int[3];

		for (int i = 0; i<birth.length; i++) {
			input = in.nextInt();
			birth[i] += input;
		}

		JulianDate birthday = new JulianDate(birth[0], birth[1], birth[2]);

		Float ageIntoDays = jd.daysBetween(birthday);
		System.out.println("You are " + ageIntoDays + " days old.");

		LocalDate currentDay = LocalDate.now();
		if (currentDay.getMonthValue() == birth[1] && currentDay.getDayOfMonth() == birth[2]) {
			System.out.println("Happy birthday to you!");
		}

		if (ageIntoDays % 100 == 0) {
			System.out.println("Days lived: 100 x " + (int) (ageIntoDays/100) + " times");
		}
		dayOfBorn(birthday.julianDayNumber);

		return birthday;
	}

	/**
	 * Calculates the birthday day of week.
	 * @param birthday
	 */
	public void dayOfBorn(float birthday) {

		int dayOfWeek = (int) birthday % 7;
		String dayOfBorn = null;

		switch(dayOfWeek)
		{
			case 0:
				dayOfBorn = "Tuesday\n" + "Tuesday's child is full of grace";
				break;

			case 1:
				dayOfBorn = "Wednesday\n" + "Wednesday's child is full of woe";
				break;

			case 2:
				dayOfBorn = "Thursday\n" + "Thursday's child has far to go";
				break;

			case 3:
				dayOfBorn = "Friday\n" + "Friday's child is loving and giving";
				break;

			case 4:
				dayOfBorn = "Saturday\n" + "Saturday's child must work hard for a living";
				break;

			case 5:
				dayOfBorn = "Sunday\n" + "The child who was born on the Sabbath day\n"
						+ "Is fair and wise and good and gay";
				break;

			case 6:
				dayOfBorn = "Monday\n" + "Monday's child is fair of face";
				break;

		}
		String output ="\nYou were born on a " + dayOfBorn;
		System.out.println(output);
	}

}
