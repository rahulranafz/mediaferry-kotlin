package org.clavis.webpages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.interactions.Actions
import org.testng.asserts.SoftAssert
import org.clavis.basepack.BasePage

class LoginPage : BasePage() {

    private val usernameTextFieldLocator = By.id("username")
    private val PasswordTextFieldLocator = By.name("password")
    private val loginBtnLocator = By.id("SbmtButton")
    private val homeTitleLocaotr = By.className("page-title")
    private val profileFieldLocator = By.id("profile")
    private val profileAdjacentDownIconLocator = By.xpath("//i[@class='fa fa-angle-down']")
    private val logoutFieldLocator = By.xpath("//a[text()=' Log Out']")
    private val invalidLoginMessageFieldLocator = By.xpath("//div[@id='alertuperr']/span")
    private val createAnAccountLinkFieldLocator = By.id("register-btn")
    private val createNewProjectBtnLocator = By.xpath("//div[contains(@class, 'dashboard-stat') and contains(., ' Create')]")
    private val activeProjectsLocator = By.xpath("//div[contains(@class, 'dashboard-stat') and contains(., 'Active projects')]")
    private val sessionValidationLocator = By.xpath("//span[contains(text(),'Session expired. Please login again.')]")

    fun enterUsername(driver: WebDriver, username: String) {
        waitForElementVisibility(driver, usernameTextFieldLocator)
        driver.findElement(usernameTextFieldLocator).sendKeys(username)
    }

    fun enterPassword(driver: WebDriver, password: String) {
        driver.findElement(PasswordTextFieldLocator).sendKeys(password)
    }

    fun clickOnLoginButton(driver: WebDriver) {
        driver.findElement(loginBtnLocator).click()
    }

    fun verifyLogin(driver: WebDriver, softAssert: SoftAssert) {
        waitForElementVisibility(driver, homeTitleLocaotr)
        softAssert.assertTrue(driver.findElement(homeTitleLocaotr).isDisplayed)
    }

    fun mouseHoverOnProfileName(driver: WebDriver) {
        waitForElementVisibility(driver, profileFieldLocator)
        val action = Actions(driver)
        action.moveToElement(driver.findElement(profileFieldLocator)).build().perform()
    }

    @Throws(InterruptedException::class)
    fun clickOnLogout(driver: WebDriver) {
        waitForElementVisibility(driver, logoutFieldLocator)
        driver.findElement(logoutFieldLocator).click()
    }

    fun verifyInvalidLogin(driver: WebDriver, softAssert: SoftAssert) {
        waitForElementVisibility(driver, invalidLoginMessageFieldLocator)
        softAssert.assertEquals("Either username or password is invalid", driver.findElement(invalidLoginMessageFieldLocator).text)
    }

    fun verifyLogout(driver: WebDriver, softAssert: SoftAssert) {
        waitForElementVisibility(driver, usernameTextFieldLocator)
        softAssert.assertTrue(driver.findElement(usernameTextFieldLocator).isDisplayed)
    }

    fun checkValidationOfSession(driver: WebDriver, softAssert: SoftAssert) {
        waitForElementVisibility(driver, sessionValidationLocator)
        softAssert.assertTrue(driver.findElement(sessionValidationLocator).isDisplayed)
    }

    fun clickOnTheCreateAnAccountLink(driver: WebDriver) {
        driver.findElement(createAnAccountLinkFieldLocator).click()
    }

    fun verifyCreateProjectBtn(driver: WebDriver, softAssert: SoftAssert) {
        waitForElementVisibility(driver, createNewProjectBtnLocator)
        softAssert.assertTrue(driver.findElement(createNewProjectBtnLocator).isDisplayed)
    }

    fun verifyActiveProjectLocator(driver: WebDriver, softAssert: SoftAssert) {
        waitForElementVisibility(driver, activeProjectsLocator)
        softAssert.assertTrue(driver.findElement(activeProjectsLocator).isDisplayed)
    }

    fun clickOnProfileAdjacentDownIcon(driver: WebDriver) {
        waitForElementVisibility(driver, profileAdjacentDownIconLocator)
        driver.findElement(profileAdjacentDownIconLocator).click()
    }

}