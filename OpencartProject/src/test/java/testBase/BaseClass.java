package testBase;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	protected static WebDriver driver;	//static bcz if we create obj for it'll also create driver, so avoid conflict
	public Logger logger;
	public Properties property;
	
	@BeforeClass(groups = {"sanity","regression","master"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException {
		
		//loading config.propeerties file
		FileReader file = new FileReader("./src//test//resources//config.properties");
		property = new Properties();
		property.load(file);
		
		//logging : log4j2
		logger = LogManager.getLogger(this.getClass());
		
		//if remote execution : Selenium Grid
		if(property.getProperty("execution_env").equalsIgnoreCase("remote")){
			DesiredCapabilities capabalities = new DesiredCapabilities();
			//os
			if(os.equalsIgnoreCase("windows")) {
				capabalities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac")) {
				capabalities.setPlatform(Platform.MAC);
			}
			else {
				System.out.println("No matching browser");
			}
			
			//browser
			switch(br.toLowerCase()) {
			case "chrome" : capabalities.setBrowserName("chrome");break;
			case "edge" : capabalities.setBrowserName("MicrosoftEdge");break;
			default : System.out.println("No matching Browser");return;
			}
			
			driver = new RemoteWebDriver(new URL("http://192.168.1.6:4444/wd/hub"),capabalities);
		}
		
		if(property.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(br.toLowerCase()) {
			case "chrome" : driver = new ChromeDriver(); break;
			case "edge" : driver = new EdgeDriver(); break;
			default : System.out.println("Invalid browser name..");
			return;
			}
		}
		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().deleteAllCookies();
		driver.get(property.getProperty("appURL"));	//reading data from properties file
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups = {"sanity","regression","master"})
	public void teardown() {
		driver.quit();
	}
	
	//random string generator
	public String randomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	public String randomNumber() {
		String generatedNumber = RandomStringUtils.randomNumeric(5);
		return generatedNumber;
	}
	
	//pwd
	public String randomAlphaNumeric() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		String generatedNumber = RandomStringUtils.randomNumeric(3);
		return (generatedString+"#@"+generatedNumber);
	}
	
	public String captureScreen(String testName) throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String targetFilePath = System.getProperty("user.dir") + 
                                "\\screenshots\\" + testName + "_" + timeStamp + ".png";

        TakesScreenshot ts = (TakesScreenshot) driver;
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);	//OutputType.FILE → screenshot is returned as a file
        File targetFile = new File(targetFilePath);

        FileUtils.copyFile(sourceFile, targetFile);	//This copies screenshot from temporary → actual location

        return targetFilePath; // return path for Extent Report
    }
}
