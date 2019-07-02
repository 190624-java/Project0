package project0.users;

import java.io.Serializable;
import java.util.Scanner;

public abstract class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public abstract boolean registerAccount(Scanner scanner);
	public abstract boolean login(String userName, short loginPin);

}
