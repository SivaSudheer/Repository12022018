package challenge;
//Heello this is a locl copy dont do anything there
import java.io.File;
import java.util.concurrent.TimeUnit;
//this in my 2nd bracnh and want to cmit to master remote
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class DriverSetup{
	public static void main(String[] args) {
	WebDriver driver;
	try {
		System.setProperty("webdriver.chrome.driver", "C:\\Eclipse\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
		driver.manage().window().maximize();
		driver.get("https://survey.gpssapp.com/");
		System.out.println("Navigated to the url");
		driver.findElement(By.id("loginid1")).sendKeys("HERRPWEPBM");
		System.out.println("Entered the ID");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		System.out.println("Clicked on Login");
		/*driver.findElement(By.cssSelector("input[class='btn']")).click();
		System.out.println("Clicked on Start");*/
		//clear the response before entering
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("errase_respose")));
		String pageNum = driver.findElement(By.xpath("//*[@class='pagenumbercenter']")).getText().trim();
		String page[] = pageNum.split(" ");
		Integer x = Integer.parseInt(page[1]);
		System.out.println("Currently on page "+ x);
		while(x<2)  {
			wait.until(ExpectedConditions.elementToBeClickable(By.id("BPSubmit")));
		}
		//driver.findElement(By.id("errase_respose")).click();
		System.out.println("Cleared the responses on the page 1");
		Thread.sleep(500);
		for(int i=1;i<16;i++) {//fills the values 1-15 in page 1
			driver.findElement(By.xpath("//table[@class='table table-bordered table-custom-hover table-fixed statement-table']/tbody/tr["+i+"]/td[2]/label[@data-original-title='Sometimes untrue/sometimes true']")).click();
			System.out.println("Clicked medium for work group# "+i);
			driver.findElement(By.xpath("//table[@class='table table-bordered table-custom-hover table-fixed statement-table']/tbody/tr["+i+"]/td[3]/label[@data-original-title='Sometimes untrue/sometimes true']")).click();
			System.out.println("Clicked medium for org# "+i);
		}
		//driver.findElement(By.id("NPSubmit")).click();
        //JavascriptExecutor executor= (JavascriptExecutor)driver;
        //executor.executeScript("document.getElementById('NPSubmit').style.display='block';");
		
		WebElement nextBtn = driver.findElement(By.id("NPSubmit"));
		
		
		//wait.until(ExpectedConditions.elementToBeClickable(nextBtn));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("NPSubmit")));
		//click on next to move to next page.
		Actions builder = new Actions(driver);
		Action mouseoverNext = builder.moveToElement(nextBtn).click().build();
		mouseoverNext.perform();
		//System.out.println("Before direct click");
		//wait.until(ExpectedConditions.elementToBeClickable(nextBtn));
		//System.out.println("Clicked on Next to move to page 2");
		
		
		
	}
	catch(Exception e){
		e.printStackTrace();
	}
}
}
