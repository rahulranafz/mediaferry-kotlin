package org.clavis.basepack

import org.apache.commons.io.FileUtils
import org.clavis.constants.Constants
import org.clavis.utilities.FileOperations
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.edge.EdgeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.ie.InternetExplorerDriver
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class Driver{

    private var detectedOS: String? = null
    var webdriver: WebDriver? = null
    val USERNAME = ""
    val AUTOMATE_KEY = ""
    private val URL = "https://$USERNAME:$AUTOMATE_KEY@hub-cloud.browserstack.com/wd/hub"

    fun getOperatingSystemType() :String {

        var OS = System.getProperty("os.name", "generic").toLowerCase()
        if (OS.indexOf("mac")>=0){
            detectedOS = "MacOS"
        }else if (OS.indexOf("win")>=0){
            detectedOS = "Windows"
        }else if (OS.indexOf("nux")>=0){
            detectedOS = "Linux"
        }else{
            detectedOS = "Other"
        }

        return detectedOS.toString()
    }

    fun getProjectName(): String {
        val fileOperations = FileOperations()
        val constants = Constants()
        return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "projectName").toString()
    }

    fun getCampaign(): String {
        val fileOperations = FileOperations()
        val constants = Constants()
        return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "campaign").toString()
    }

    fun getBrandName(): String {
        val fileOperations = FileOperations()
        val constants = Constants()
        return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "brandname").toString()
    }

    fun getCreativeLevel(): String {
        val fileOperations = FileOperations()
        val constants = Constants()
        return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "creativeLevel").toString()
    }

    fun getFilePath(): String {
        val fileOperations = FileOperations()
        val constants = Constants()
        return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "filePath").toString()
    }

    fun getLogoPath(): String {
        val fileOperations = FileOperations()
        val constants = Constants()
        return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "logoPath").toString()
    }

    fun getMyProfilePath(): String {
        val fileOperations = FileOperations()
        val constants = Constants()
        return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "myProfilePath").toString()
    }

    fun getPriority(): String {
        val fileOperations = FileOperations()
        val constants = Constants()
        return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "priority").toString()
    }

    fun getProjectOwner(): String {
        val fileOperations = FileOperations()
        val constants = Constants()
        return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "projectOwner").toString()
    }

    fun getfpStyle(): String {
        val fileOperations = FileOperations()
        val constants = Constants()
        return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "fpStyle").toString()
    }

    fun getTarget(): String {
        val fileOperations = FileOperations()
        val constants = Constants()
        return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "target").toString()
    }

    fun getClassificationNew(): String {
        val fileOperations = FileOperations()
        val constants = Constants()
        return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "classificationNew").toString()
    }

    fun getOrderValue(): String {
        val fileOperations = FileOperations()
        val constants = Constants()
        return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "orderValue").toString()
    }

    fun getBrowserType(): String {
        val fileOperations = FileOperations()
        val constants = Constants()
        return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "browserName").toString()
    }

    fun getUrl(): String {
        val fileOperations = FileOperations()
        val constants = Constants()
        return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "url").toString()
    }

    fun getEmail(): String {
        val fileOperations = FileOperations()
        val constants = Constants()
        return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "mother_email").toString()
    }

    fun getSharedWithMailOne(): String {
        val fileOperations = FileOperations()
        val constants = Constants()
        return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "sharingMailOne").toString()

    }

    fun getSharedWithMailTwo(): String {
        val fileOperations = FileOperations()
        val constants = Constants()
        return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "sharingMailTwo").toString()

    }

    @Throws(IOException::class)
    fun createDriver(): WebDriver? {
        val osType = getOperatingSystemType()
        val browserName = getBrowserType()

        if (browserName.equals("firefox", ignoreCase = true) || browserName.equals("ff", ignoreCase = true)) {
            System.setProperty("webdriver.gecko.driver", "src/../libs/geckodriver")
            if (osType == "Linux") {
                System.setProperty("webdriver.gecko.driver", "src/../libs/geckodriver")
            } else if (osType == "MacOS") {
                System.setProperty("webdriver.gecko.driver", "src/../libs/geckodrivermac")
            } else {
                System.setProperty("webdriver.gecko.driver", "src/../libs/geckodriver.exe")
            }
            webdriver = FirefoxDriver()
            (webdriver as FirefoxDriver).manage().window().maximize()
        } else if (browserName.equals("chrome", ignoreCase = true) || browserName.equals("ch", ignoreCase = true)) {
            if (osType == "Linux") {
                System.setProperty("webdriver.chrome.driver", "src/../libs/chromedriver")
            } else if (osType == "MacOS") {
                System.setProperty("webdriver.chrome.driver", "src/../libs/chromedrivermac")
            } else {
                System.setProperty("webdriver.chrome.driver", "src/../libs/chromedriver.exe")
            }
            webdriver = ChromeDriver()
            (webdriver as ChromeDriver).manage().window().maximize()
        } else if (browserName.equals("internet explorer", ignoreCase = true) || browserName.equals("ie", ignoreCase = true)) {
            System.setProperty("webdriver.ie.driver", "src/../libs/IEDriverServer.exe")
            webdriver = InternetExplorerDriver()
            (webdriver as InternetExplorerDriver).manage().window().maximize()
        } else if (browserName.equals("BrowserStack", ignoreCase = true) || browserName.equals("bs", ignoreCase = true)) {
            val caps = DesiredCapabilities()
            caps.setCapability("browser", "Chrome")
            caps.setCapability("browser_version", "54")
            caps.setCapability("os", "WINDOWS")
            caps.setCapability("os_version", "8")
            caps.setCapability("browserstack.debug", "true")
            webdriver = RemoteWebDriver(java.net.URL(URL), caps)
        } else if (browserName.equals("edge", ignoreCase = true) || browserName.equals("Microsoft edge", ignoreCase = true)) {
            System.setProperty("webdriver.edge.driver", "src/../libs/MicrosoftWebDriver.exe")
            webdriver = EdgeDriver()
            (webdriver as EdgeDriver).manage().window().maximize()
        }
        return webdriver
    }

    fun getSignupPassword(): String {
        val fileOperations = FileOperations()
        val constants = Constants()
        return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "signuppasswword").toString()
    }

    fun takeScreenshot(driver: WebDriver, name: String) {
        val scrFile = (driver as TakesScreenshot).getScreenshotAs(OutputType.FILE)

        val currDate = Date()
        val dateFormat = SimpleDateFormat("MM_dd_yy_HH_mm_ss")
        val dateAndTime = dateFormat.format(currDate)

        try {
            FileUtils.copyFile(scrFile, File("screenshots/" + name + "_" + dateAndTime + ".png"))

        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    fun getUsername(): String {
        val fileOperations = FileOperations()
        val constants = Constants()
        return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "username").toString()
    }

    fun getProfileName(): String {
        val fileOperations = FileOperations()
        val constants = Constants()
        return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "profileName").toString()
    }

    fun getPassword(): String {
        val fileOperations = FileOperations()
        val constants = Constants()
        return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "password").toString()
    }

    fun getChangePassword(): String {
        val fileOperations = FileOperations()
        val constants = Constants()
        return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "changePassword").toString()
    }

    fun getInvalidUsername(): String {
        val fileOperations = FileOperations()
        val constants = Constants()
        return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "invalidUsername").toString()
    }

    fun getInstructions(): String {
        val fileOperations = FileOperations()
        val constants = Constants()
        return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "instructions").toString()
    }

    fun getTeam(): String {
        val fileOperations = FileOperations()
        val constants = Constants()
        return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "team").toString()
    }

    fun getHeight(): String {
        val fileOperations = FileOperations()
        val constants = Constants()
        return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "height").toString()
    }

    fun getWidth(): String {
        val fileOperations = FileOperations()
        val constants = Constants()
        return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "width").toString()
    }

    fun getProjectOwnerNew(): String {
        val fileOperations = FileOperations()
        val constants = Constants()
        return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "projectOwnerNew").toString()
    }

    fun getPosition(): String {
        val fileOperations = FileOperations()
        val constants = Constants()
        return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "position").toString()
    }

    fun getUFilePath(): String {
        val fileOperations = FileOperations()
        val constants = Constants()
        return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "dropFilePath").toString()
    }


}