package com.dealership;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;

import com.dealership.DAOinterface.CarAccessor;

public class Lot implements CarAccessor{
	private Hashtable<String, LinkedList<Car>> carList;
	private static final String LOT_FILE = "Lot_Data.txt";
	private static Lot mySelf; 
	
	private Lot() {
		carList = new Hashtable<String, LinkedList<Car>>();
		
	}
	
	
	
	public boolean lotContainsType(String carKey)
	{
		return carList.containsKey(carKey);
	}
	
	public static Lot getInstance() {
		if(mySelf == null) {
			mySelf = new Lot();
			try {
				mySelf.read();
			} catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return mySelf;
	}
	
	public void addCar(Car newCar)
	{
		String tmpKey = newCar.getMake() + " " + newCar.getModel() + " " + newCar.getYear();
		if(!carList.containsKey(tmpKey))
			carList.put(tmpKey, new LinkedList<Car>());
		carList.get(tmpKey).add(newCar);
	}
	
	// TODO: move scanner to a helper function.
	public void removeCar(Car newCar)
	{
		String tmpKey = newCar.getMake() + " " + newCar.getModel() + " " + newCar.getYear();
		if(carList.containsKey(tmpKey))
		{
			LinkedList tmpList = carList.get(tmpKey);
			if(tmpList.size() > 0)
			{
				for(int i = 0; i < tmpList.size(); i++)
				{
					//TODO print car summary
				}
				System.out.println("Insert index of car to remove (beginning at 0)");
				int removalIndex = DealershipDriver.inScan.nextInt();
				DealershipDriver.inScan.nextLine();
				if(removalIndex < tmpList.size())
				{
					tmpList.remove(removalIndex);
				}
			}
		}
	}
	
	public Car returnCar(String carKey, int carIndex)
	{
		return carList.get(carKey).get(carIndex);
	}
	
	public void displayLot()
	{
		carList.forEach((k,v)->{
			int availCount = 0;
			for(Car c: v)
			{
				if(!c.getSold())
					availCount++;
			}
			System.out.println("Make/Model/Year: " + k + ". Number available: " + availCount);
		});
	}
	
	public void displayOfType(String carKey)
	{
		LinkedList<Car> tmpList = carList.get(carKey);
		for(Car c: tmpList)
		{
			System.out.println("Index: " + tmpList.indexOf(c) + " " + c.toString());
			
		}
	}
	
	public int numberOfType(String carKey)
	{
		return carList.get(carKey).size();
	}
	
	
	public void write() {
		
		carList.forEach((k,v) -> {
			for(Car car : v)
			{
				try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(LOT_FILE)))) {
					oos.writeObject(car);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void read() {
		
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(LOT_FILE)))) {
			while(true) {
				Car obj = (Car)ois.readObject();
				addCar(obj);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	@Override
	public HashSet<Integer> getAllIds() {
		HashSet<Integer> tmpHash = new HashSet<>();
		carList.forEach((k,v)->{
			for(Car c : v) {
				tmpHash.add(c.getId());
			}
		});
		return tmpHash;
	}



	@Override
	public ArrayList<Car> retrunCarsOwnedBy(String custId) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Car getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
