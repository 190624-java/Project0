package basicpackage;

public class Employee extends User{
	private static final long serialVersionUID = 2347923823847293842L;
	public Employee(String user, String pass)
	{
		super();
		this.username = user;
		this.password = pass;
		this.userType = "employee";
	}
	public void addCar()
	{
		//this.carArray.add(new Car());
	}
	public void viewOffers(Car car)
	{
		for(Offer o : car.offerList)
		{
			System.out.print(o.offerValue + " " + o.offerer + " ");
		}
	}
	public void acceptOffer(Offer o)
	{
		
	}
	public void rejectOffer(Offer o)
	{
		
	}
	public void removeCar(int ID)
	{
		carArray.remove(ID);
	}
	public void viewPayments()
	{
		
	}
}
