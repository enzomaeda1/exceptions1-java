package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

public class Reservation {
	
	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) {
		if (!checkOut.after(checkIn)) {// checa se checkout é depois de checkin
			throw new DomainException("Checkout after checkin");
		}
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}
	
	
	public Integer getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(Integer rootNumber) {
		this.roomNumber = rootNumber;
	}
	public Date getCheckIn() {
		return checkIn;
	}
	
	public Date getCheckOut() {
		return checkOut;
	}
	
	public long duration() {
		long diff = checkOut.getTime() - checkIn.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);//CONVERTE MILISEGUNDOS EM DIAS
	}
	
	public void updateDates(Date checkIn, Date checkOut){//Lancamento de excecoes
		Date now = new Date();
		
		if (checkIn.before(now) || checkOut.before(now)) {// checa se a data nova é ante da data atual
			throw new DomainException("Reservation must be future date");//usa throw para jogar mensagem de excessao
		}

		if (!checkOut.after(checkIn)) {// checa se checkout é depois de checkin
			throw new DomainException("Checkout after checkin");
		}
		
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		
	}
	
	@Override
	public String toString() {
		return "Room "
				+ roomNumber +
				", check-in "+
				sdf.format(checkIn)+
				", check-out "+
				sdf.format(checkOut)+
				", "+
				duration()+
				" nights.";
				
	}
	
	
	
}
