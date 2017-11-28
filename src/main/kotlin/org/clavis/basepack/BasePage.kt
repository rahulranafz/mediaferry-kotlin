package org.clavis.basepack

import java.text.SimpleDateFormat
import java.util.Date

import org.openqa.selenium.*
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.Assert

open class BasePage {

    val workingDirectoryPath: String
        get() = System.getProperty("user.dir")

    val currentDate: String
        get() {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd")
            val date = Date()
            return dateFormat.format(date)
        }

    fun waitForElementVisibility(driver: WebDriver, elem: By) {
        val webDriverWait = WebDriverWait(driver, 180)
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(elem))
    }

    fun javaScriptClick(driver: WebDriver, element: By) {
        scrollToElement(driver, element)
        (driver as JavascriptExecutor).executeScript("arguments[0].click();", driver.findElement(element))
    }

    fun waitForElementPresence(driver: WebDriver, element: By) {
        val webDriverWait = WebDriverWait(driver, 120)
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(element))
    }

    fun scrollToElementWithoutWait(driver: WebDriver, elem: By) {
        (driver as JavascriptExecutor).executeScript("arguments[0].scrollIntoView();", driver.findElement(elem))
    }

    fun waitForElementClickable(driver: WebDriver, elem: By) {
        val webDriverWait = WebDriverWait(driver, 180)
        webDriverWait.until(ExpectedConditions.elementToBeClickable(elem))
    }

    fun scrollToElement(driver: WebDriver, elem: By) {
        waitForElementVisibility(driver, elem)
        (driver as JavascriptExecutor).executeScript("arguments[0].scrollIntoView();", driver.findElement(elem))
    }

    fun waitForElementInvisibility(driver: WebDriver, elem: By) {
        val webDriverWait = WebDriverWait(driver, 120)
        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(elem))
    }

    fun scrollDownOfElement(driver: WebDriver, elmentId: String) {
        waitForElementVisibility(driver, By.id(elmentId))
        val myScript = ("document.getElementById('" + elmentId + "').scrollTop = document.getElementById('" + elmentId
                + "').scrollHeight;")
        (driver as JavascriptExecutor).executeScript(myScript)
    }

    fun scrollTop(driver: WebDriver) {

        (driver as JavascriptExecutor).executeScript("window.scrollTo(0, 0)")
    }

    fun selectDropDownElement(driver: WebDriver, elementId: By, TextValueorIndex: String, selectionType: String) {
        waitForElementVisibility(driver, elementId)
        val selectElement = driver.findElement(elementId)
        val selectValue = Select(selectElement)
        if (selectionType == "Text")
            selectValue.selectByVisibleText(TextValueorIndex)
        else if (selectionType == "Value")
            selectValue.selectByValue(TextValueorIndex)
        else
            selectValue.selectByIndex(Integer.parseInt(TextValueorIndex))
    }

    fun executeJSCommand(driver: WebDriver, command: String) {
        (driver as JavascriptExecutor).executeScript(command)
    }

    fun scrollDownToPage(driver: WebDriver) {
        (driver as JavascriptExecutor).executeScript("window.scrollTo(0, document.body.scrollHeight)")
    }

    companion object {

        @Throws(InterruptedException::class)
        fun chromeBugClick(driver: WebDriver, clickThis: By) {
            //https://code.google.com/p/selenium/issues/detail?id=2766
            val n = 10
            var errorMessage: String
            for (i in 1..n) {
                try {
                    driver.findElement(clickThis).click()
                    break
                } catch (driverException: WebDriverException) {
                    Thread.sleep(1000)
                    errorMessage = driverException.toString()
                }

                if (i == n) {
                    Assert.fail("Failed to click $n times \n$errorMessage")
                }
            }
        }
    }

}
