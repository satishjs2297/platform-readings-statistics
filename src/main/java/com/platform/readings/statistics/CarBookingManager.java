package com.platform.readings.statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class CarBookingManager {

	private List<Car> cars = new ArrayList<>();
	private Integer noOfCars;
	private volatile Integer totalCompletedRides = 0;

	protected boolean[] used;

	private Semaphore carSemaphore;
	private Semaphore passengerSemaphore;

	public CarBookingManager(Integer noOfCars, Integer noOfPassengers) {
		this.noOfCars = noOfCars;
		this.carSemaphore = new Semaphore(noOfCars);
		this.passengerSemaphore = new Semaphore(noOfPassengers);

		this.used = new boolean[noOfCars];
		for (int i = 1; i <= noOfCars; i++) {
			cars.add(new Car("Safari-" + i, 1, "AP 05 " + i));
		}
	}

	public Car getCar() throws InterruptedException {
		carSemaphore.acquire();		
		return getNextAvailableCar();
	}

	public void putCar(Car car) throws InterruptedException {
		if (markAsUnused(car)) {
			carSemaphore.release();
		}
	}

	protected synchronized Car getNextAvailableCar() {
		for (int i = 0; i < noOfCars; ++i) {
			if (!used[i]) {
				used[i] = true;
				return cars.get(i);
			}
		}
		return null; // not reached
	}

	protected synchronized boolean markAsUnused(Car car) throws InterruptedException {
		Thread.sleep(1000);
		for (int i = 0; i < noOfCars; ++i) {
			if (cars.get(i).getCarName().equals(car.getCarName())) {
				if (used[i]) {
					used[i] = false;
					return true;
				} else
					return false;
			}
		}
		return false;
	}

	public void beginRide() throws InterruptedException {
		System.out.println("Waiting passenger to acquire slot");
		passengerSemaphore.acquire();
		System.out.println("Passenger acquired slot waiting for Safari for ride");
		Car car = getCar();
		System.out.println("Passenger got the Safari for ride");
		putCar(car);
		passengerSemaphore.release();
		System.out.println("Passenger Completed Ride on Car :: " + car);
		System.out.println("Ride Finished ...Total Finished Rides :: "+ (++totalCompletedRides));
	}

	public static void main(String[] args) throws Exception {

		Integer totalPassenger = 40;
		CarBookingManager bookingManager = new CarBookingManager(10, 20);
		for (int i = 1; i <= totalPassenger; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						bookingManager.beginRide();
					} catch (Exception e) {
						System.err.println(e);
					}
				}
			}).start();
			Thread.sleep(100);
		}
	}

}

class Car {

	private String carName;
	private int seats;
	private String number;

	public Car(String carName, int seats, String number) {
		this.carName = carName;
		this.seats = seats;
		this.number = number;
	}

	public String getCarName() {
		return carName;
	}

	@Override
	public String toString() {
		return "Car [carName=" + carName + ", seats=" + seats + ", number=" + number + "]";
	}

}
