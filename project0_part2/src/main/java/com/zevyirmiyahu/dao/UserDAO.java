package com.zevyirmiyahu.dao;

import java.util.Scanner;

public interface UserDAO {
	public abstract boolean registerAccount(Scanner scanner);
	public abstract boolean login(String userName, short loginPin);
}
