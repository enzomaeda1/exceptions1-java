package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.entities.Reservation;

public class Program {
	public static void main(String[] args) throws ParseException {

		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		System.out.print("Room Number: ");
		int number = sc.nextInt();

		System.out.print("Check-in (dd/MM/yyyy): ");
		Date checkIn = sdf.parse(sc.next());

		System.out.print("Check-out (dd/MM/yyyy): ");
		Date checkOut = sdf.parse(sc.next());

		if (!checkOut.after(checkIn)) {// checa se checkout é depois de checkin
			System.out.println("Error checkout after checkin");
		}

		else {
			Reservation reservation = new Reservation(number, checkIn, checkOut);
			System.out.println("Reservation: " + reservation);

			System.out.println("Enter data to update reservation: ");

			System.out.print("Check-in (dd/MM/yyyy): ");
			checkIn = sdf.parse(sc.next());

			System.out.print("Check-out (dd/MM/yyyy): ");
			checkOut = sdf.parse(sc.next());

			Date now = new Date();

			if (checkIn.before(now) || checkOut.before(now)) {// checa se a data nova é ante da data atual
				System.out.println("Error: Reservation must be future date");
			}

			else if (!checkOut.after(checkIn)) {// checa se checkout é depois de checkin
				System.out.println("Error checkout after checkin");
			}

			else {
				
				reservation.updateDates(checkIn, checkOut);

				System.out.println("Reservation: " + reservation);

			}
	
		}

		sc.close();

	}
}
