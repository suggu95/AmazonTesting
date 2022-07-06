package Amazon;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AmazonTesting {

	public static void main(String[] args)
			throws InterruptedException, IOException, SQLException, ClassNotFoundException {

		System.setProperty("webdriver.chrome.driver", "E:\\Selinium\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		// CTRL + SHIFT+ O
		// Enter the url
		driver.get("https://www.amazon.in/");
		// maximize
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//div[@class='nav-search-field ']")).click();
		driver.findElement(By.xpath("//div[@class='nav-search-field ']/input")).sendKeys("Mobiles");
		driver.findElement(By.xpath("//input[@id='nav-search-submit-button']")).click();

		String dbUrl = "jdbc:mysql://localhost:3306/amazon";
		String username = "root";
		String password = "root";
		// Query to Execute
		String query = "select *  from products;";
		// Load mysql jdbc driver
		Class.forName("com.mysql.jdbc.Driver");
		// Create Connection to DB
		Connection con = DriverManager.getConnection(dbUrl, username, password);
		// Create Statement Object
		Statement stmt = con.createStatement();

		// Execute the SQL Query. Store results in ResultSet
		ResultSet rs = stmt.executeQuery(query);

		// While Loop to iterate through all data and print results
		while (rs.next()) {
			String BrandName = rs.getString(1);
			String ModelName = rs.getString(2);
			int price = rs.getInt(3);
			System.out.print("Brand :"+BrandName + "\t");
			System.out.print("Model : " + ModelName +"\t ");
			System.out.print("Price :" + price+"     \t");
			System.out.println();
		}
		// closing DB Connection
		con.close();

		driver.close();
		driver.quit();
	}
}
