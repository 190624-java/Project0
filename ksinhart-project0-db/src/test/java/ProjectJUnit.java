import static org.junit.Assert.*;

import java.io.File;
import java.util.Scanner;

import org.junit.*;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.project0.*;

public class ProjectJUnit {
	SystemConsole sc;
	Scanner scan;
	static String str1;
	static String str2;
	static String str3;
	
	@BeforeClass
	public static void setUp() throws Exception {
		str1="1/sinhky01/qwerty/1/3/4/2/1/33000/5";
		str2="1/sinhky02/qwerty/1/Ford/F150/2005/25000/2/2/3/1/4/7/5/6";
		str3="2/Cap/Captain/cappy/qwerty/1/5";
		
		//
	}
	
	@Before
	public  void preTest() {
		new WriteObjects();
		scan=new Scanner(str1);
		scan.useDelimiter("/");
		sc = new SystemConsole(scan);
		sc.start();
	}
	
	@After
	public void afterTest() {
		sc.finish();
	}
	
	@Test
	public void customerTest() {
		
		Customer temp = (Customer) new User();
		Customer c1=new Customer(1,"Carl","Sinhart","sinhky03", "qwerty");
		c1.viewLot(sc);
		fail("Not yet implemented");
	}
	@Test
	public void employeeTest() {
		Employee e1=new Employee(2,"Kyle","Sinhart","sinhky02", "qwerty");
	}@Test
	public void carTest() {
		fail("Not yet implemented");
	}
	
	@AfterClass
	public static void teardown() {
		File f1=new File("user-data.ser");
		File f2=new File("car-data.ser");
		File f3=new File("offer-data.ser");
		f1.delete();
		f2.delete();
		f3.delete();
	}
}
