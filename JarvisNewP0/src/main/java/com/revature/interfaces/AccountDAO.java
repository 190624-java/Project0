package com.revature.interfaces;

/**
 * TODO
 * 
 * @param <T>
 */
public interface AccountDAO<T> {

//	public void create(T t);
//	public void read(); //get
//	public void update();
//	public void delete();
	
	public void saveCollection();
	public void loadCollection();
	
	public void add();	
	public void remove();

	public T find();
	
	public void displayOne();
	public void displayAll();
	
	
	
//	/**
//	 * Save all the information to storage 
//	 */
//	public void save();	
//	
//	public void saveCars();
//	public void savePayments();
//	public void saveContracts();
//	public void saveOffers();
//	public void saveCredentials();
//	public void saveLots();
//	
//	
//
//	/**
//	 * Load all the information of an Account from storage
//	 */
//	public void load();
//
//	public void loadCars();
//	public void loadPayments();
//	public void loadContracts();
//	public void loadOffers();
//	public void loadCredentials();
//	public void loadLots();
	
}
