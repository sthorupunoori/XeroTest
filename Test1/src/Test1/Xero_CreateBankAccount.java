package Test1;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/*Launch Xero Application
  Verify the page title
  Login to Application
  Navigate to Accounts Menu
  Select Bank Accounts option from list
  Click on Add new bank Accocunts
  Type Country name in Search box
  Click on Search Result
  Enter Account details (Name, Number, Type)
  Click on Continue
  Verify the success message
  Logout from Application
  
*/

public class Xero_CreateBankAccount {
	
	static WebDriver driver;
	
	static WebDriverWait wait;
	
	static String url="https://login.xero.com/";
	
	static String email="sthorupunoori@gmail.com";
	
	static String pwd="Rakshi@2411";
	
	static String pageActualTitle="Login | Xero Accounting Software";
	
	static String searchcountry="ANZ";


	public static void main(String[] args) throws InterruptedException {
		
		
		
		//Chrome Browser Execution
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\prasad\\Desktop\\Selenium Setup\\chromedriver_win32\\chromedriver.exe");
		
		driver = new ChromeDriver();
		
		driver.manage().deleteAllCookies();
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		driver.get(url);
		
		Assert.assertEquals(driver.getTitle(), pageActualTitle);
		
		WebElement emailaddress=driver.findElement(By.id("email"));
		
		emailaddress.clear();
		
		emailaddress.sendKeys(email);
		
		WebElement password=driver.findElement(By.id("password"));
		
		password.clear();
		
		password.sendKeys(pwd);
		
		//WebElement cookies=driver.findElement(By.xpath("//*[contains(text(), \"OK\")]"));
		
		//cookies.click();
		
		WebElement submit=driver.findElement(By.id("submitButton"));
		
		submit.click();
		
		wait= new WebDriverWait(driver, 20);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Accounts")));
		
		WebElement Accounts=driver.findElement(By.linkText("Accounts"));
		
		Actions mousehover= new Actions(driver);
		
		mousehover.moveToElement(Accounts).click().build().perform();
		
		WebElement BankAcconts=driver.findElement(By.linkText("Bank Accounts"));
		
		BankAcconts.click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-automationid='Add Bank Account-button']")));
		
		driver.findElement(By.xpath("//*[@data-automationid='Add Bank Account-button']")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@type='search']")));
		
		
		driver.findElement(By.xpath("//*[@type='search']")).sendKeys(searchcountry);
		

		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[@data-automationid=\"searchBanksList\"]/ul")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), \"Continue\")]")));
		
		
		String AccountName="BusinessAccount"+ UUID.randomUUID().toString();
		
		driver.findElement(By.xpath(".//*[@placeholder='e.g Business Account']")).sendKeys(AccountName);
		
		driver.findElement(By.xpath(".//*[@placeholder='Please select one']")).click();
		
		
		List<WebElement> pleaseselectoneoptions=driver.findElements(By.className("x-list-plain"));
		
		for(int i=0; i<pleaseselectoneoptions.size();i++)
		{
			System.out.println(pleaseselectoneoptions.get(i).getText());
			
			if(pleaseselectoneoptions.get(i).getText().contains("Term Deposit"));
			{
				driver.findElement(By.xpath("//*[contains(text(), \"Term Deposit\")]")).click();
				break;
			}
		
		}
		
		driver.findElement(By.id("accountnumber-1068-inputEl")).sendKeys("1234567");
		
		driver.findElement(By.xpath("//*[contains(text(), \"Continue\")]")).click();
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='notify01']/div/p")));
		
		String text=driver.findElement(By.xpath("//div[@id='notify01']/div/p")).getText();
		
		System.out.println(text);
		
		Assert.assertEquals(text, text);
		
		driver.findElement(By.xpath("//*[@class=\"xn-h-user\"]/a")).click();
		
		driver.findElement(By.xpath(".//*[@data-js='logout']")).click();
		
		driver.quit();
		
		
				

	}

}

