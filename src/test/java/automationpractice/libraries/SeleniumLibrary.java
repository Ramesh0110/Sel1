package automationpractice.libraries;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumLibrary {

	final static Logger logger = Logger.getLogger(SeleniumLibrary.class);
	private boolean isDemoMode = false;

	public void setDemoMode(boolean isDemoMode) {
		this.isDemoMode = isDemoMode;
	}

	private WebDriver driver;
	public List<String> errorScreenshots;
	private boolean isRemote;

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver _driver) {
		this.driver = _driver;
	}

	public SeleniumLibrary(WebDriver _driver) {
		driver = _driver;
	}

	public SeleniumLibrary(WebDriver _driver, String browserType) {
		driver = _driver;
	}

	
	
	public WebDriver startRemoteBrowser(String hubURL, String browser) {
		DesiredCapabilities cap = null;
		try {
			if (browser.toLowerCase().contains("chrome")) {
				cap = DesiredCapabilities.chrome();
			} else if (browser.toLowerCase().contains("ie")) {
				cap = DesiredCapabilities.internetExplorer();
				cap.setCapability("ignoreProtectedModeSettings", true);
				cap.setCapability("ie.ensureCleanSession", true);
			} else if (browser.toLowerCase().contains("firefox")) {
				cap = DesiredCapabilities.firefox();
			} else {
				logger.info("You choose: '" + browser.toLowerCase() + "', Currently, framework does't support it.");
				logger.info("starting default browser 'Internet Explorer'");
				
				cap = DesiredCapabilities.internetExplorer();
				cap.setCapability("ignoreProtectedModeSettings", true);
				cap.setCapability("ie.ensureCleanSession", true);
			}
			driver = new RemoteWebDriver(new URL(hubURL), cap);
			isRemote = true;
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return driver;
	}
	public void fileUpload(By by, String fileRelativePath) {
		try {
			WebElement fileUploadElem = driver.findElement(by);
			highlightElement(fileUploadElem);
			File tempFile = new File(fileRelativePath);
			String fullPath = tempFile.getAbsolutePath();
			logger.info("file uploading : " + fullPath);
			if (isRemote == true) {
				((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
			}
			fileUploadElem.sendKeys(fullPath);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}
	
	@SuppressWarnings("unlikely-arg-type")
	private WebDriver startChromeBrowser() {
		try {
		
			WebDriverManager.chromedriver().setup();
			ChromeOptions options =new ChromeOptions();
			if(options.equals("Headless")) {//headless
			options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return driver;
	}

	private WebDriver startFirefoxBrowser() {
		try {
			
			WebDriverManager.firefoxdriver().arch64().setup();
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return driver;
	}

	@SuppressWarnings("deprecation")
	private WebDriver startIEBrowser() {
		try {
			
			WebDriverManager.iedriver().arch32().setup();
			DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
			cap.setCapability("ignoreProtectedModeSettings", true);
			cap.setCapability("ie.ensureCleanSession", true);
			driver = new InternetExplorerDriver(cap);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return driver;
	}

	public void handleCheckBox(By by, boolean isCheck) {
		try {
			WebElement checkBoxElem = driver.findElement(by);
			highlightElement(checkBoxElem);
			boolean checkBoxState = checkBoxElem.isSelected();

			if (isCheck == true) {
				if (checkBoxState == true) {
				} else {
					checkBoxElem.click();
				}
			} else {
				if (checkBoxState == true) {
					checkBoxElem.click();
				} else {
				}
			}
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public WebDriver startBrowser(String browserType) {
		try {
			if (browserType.toLowerCase().contains("chrome")) {
				driver = startChromeBrowser();
			} else if (browserType.toLowerCase().contains("firefox")) {
				driver = startFirefoxBrowser();
			} else if (browserType.toLowerCase().contains("ie")) {
				driver = startIEBrowser();
			} else {
				driver = startChromeBrowser();
			}
			driver.manage().deleteAllCookies();
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return driver;
	}

	public void selectDropDown(By by, int index) {
		try {
			WebElement element = driver.findElement(by);
			highlightElement(element);
			Select dropDown = new Select(element);
			dropDown.selectByIndex(index);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public void selectDropDown(By by, String visibleText) {
		try {
			WebElement element = driver.findElement(by);
			highlightElement(element);
			Select dropDown = new Select(element);
			dropDown.selectByVisibleText(visibleText);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public void selectDropDown(String attributeValue, By by) {
		try {
			WebElement element = driver.findElement(by);
			highlightElement(element);
			Select dropDown = new Select(element);
			dropDown.selectByValue(attributeValue);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public void enterText(By by, String textString) {
		try {
			WebElement element = driver.findElement(by);
			highlightElement(element);
			element.clear();
			element.sendKeys(textString);

		} catch (Exception e) {

			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public void clickButton(By by) {
		try {
			WebElement button = driver.findElement(by);
			highlightElement(button);
			button.click();
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public void clickHiddenElement(WebElement element) {
		try {
			highlightElement(element);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public void clickHiddenElement(By by) {
		try {
			WebElement elem = driver.findElement(by);
			highlightElement(elem);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", elem);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public void customWait(double inSecs) {
		try {
			Thread.sleep((long) (inSecs * 1000));
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public void highlightElement(WebElement element) {
		try {
			if (isDemoMode == true) {
				for (int i = 0; i < 3; i++) {
					WrapsDriver wrappedElement = (WrapsDriver) element;
					JavascriptExecutor js = (JavascriptExecutor) wrappedElement.getWrappedDriver();
					js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
							"color: red; border: 2px solid green");
					customWait(0.5);
					js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
					customWait(0.5);
				}
			}
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	
	public String getCurrentTime() {
		String finalTime = null;
		try {
			Date date = new Date();
		
			
			String tempTime = new Timestamp(date.getTime()).toString();
			logger.debug("time: " + tempTime);
			finalTime = tempTime.replace("-", "").replace(" ", "").replace(":", "").replace(".", "");
			logger.debug("updated time: " + finalTime);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return finalTime;
	}

	public String screenCapture(String screenshotFileName) {
		String filePath = null;
		String fileName = null;
		try {
			fileName = screenshotFileName + getCurrentTime() + ".png";
			filePath = "target/screenshots/";
			File tempfile = new File(filePath);
			if (!tempfile.exists()) {
				tempfile.mkdirs();
			}
			filePath = tempfile.getAbsolutePath();
			if (isRemote == true) {
				driver = new Augmenter().augment(driver);
			}

			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			Files.copy(scrFile, new File(filePath + "/" + fileName));

		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		logger.info(filePath + "/" + fileName);
		return filePath + "/" + fileName;
	}

	public WebElement waitForElementPresence(By by) {
		WebElement elem = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			elem = wait.until(ExpectedConditions.presenceOfElementLocated(by));
			highlightElement(elem);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return elem;
	}

	public boolean isElementIsClickable(By by) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(by));
			return true;
		} catch (Exception e) {
			return false;

		}
	}

	public WebElement waitForElementVisibility(By by) {
		WebElement elem = null;
		try {
			elem = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(by));
			highlightElement(elem);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return elem;
	}

	public void clickButton(WebElement element) {
		try {
			highlightElement(element);
			element.click();
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}

	}

	
	public void highlightElement(By by) {
		try {
			if (isDemoMode == true) {
				WebElement element = driver.findElement(by);
				for (int i = 0; i < 3; i++) {
					WrapsDriver wrappedElement = (WrapsDriver) element;
					JavascriptExecutor js = (JavascriptExecutor) wrappedElement.getWrappedDriver();
					js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
							"color: red; border: 2px solid green");
					customWait(0.5);
					js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
					customWait(0.5);
				}
			}
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public void moveMouseToElement(WebElement targetElem) {
		try {
			highlightElement(targetElem);
			Actions action = new Actions(driver);
			action.moveToElement(targetElem).build().perform();
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public void moveMouseToElement(WebElement firstElem, WebElement secElem) {
		try {
			highlightElement(firstElem);
			Actions action = new Actions(driver);
			action.moveToElement(firstElem).build().perform();
			customWait(1);
			highlightElement(secElem);
			action.moveToElement(secElem).build().perform();
			customWait(1);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public List<String> automaticallyAttachErrorImgToEmail() {
		List<String> fileNames = new ArrayList<String>();
		JavaPropertiesManager propertyReader = new JavaPropertiesManager("src/test/resources/dynamicConfig.properties");
		String tempTimeStamp = propertyReader.readProperty("sessionTime");// read time from dynamic config
		String numberTimeStamp = tempTimeStamp.replaceAll("_", "");
		long testStartTime = Long.parseLong(numberTimeStamp);
		File file = new File("target/screenshots");
		if (file.isDirectory()) {
			if (file.list().length > 0) {
				File[] screenshotFiles = file.listFiles();
				for (int i = 0; i < screenshotFiles.length; i++) {

					if (screenshotFiles[i].isFile()) {// if there is folder found then it will skip this step
						String eachFileName = screenshotFiles[i].getName();
						logger.debug("Testing file names: " + eachFileName);
						int indexOf20 = searchSubstringInString("20", eachFileName);
						String timeStampFromScreenshotFile = eachFileName.substring(indexOf20,
								eachFileName.length() - 4);
						logger.debug("Testing file timestamp: " + timeStampFromScreenshotFile);
						String fileNumberStamp = timeStampFromScreenshotFile.replaceAll("_", "");
						long screenshotfileTime = Long.parseLong(fileNumberStamp);

						testStartTime = Long.parseLong(numberTimeStamp.substring(0, 14));
						screenshotfileTime = Long.parseLong(fileNumberStamp.substring(0, 14));
						if (screenshotfileTime > testStartTime) {
							fileNames.add("target/screenshots/" + eachFileName);
							logger.info("Screenshots attaching: " + eachFileName);
						}
					}
				}
			}
		}
		errorScreenshots = fileNames;
		return fileNames;
	}

	public int searchSubstringInString(String target, String message) {
		int targetIndex = 0;
		for (int i = -1; (i = message.indexOf(target, i + 1)) != -1;) {
			targetIndex = i;
			break;
		}
		return targetIndex;
	}

	public String ScreenShot() {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
	}
}
