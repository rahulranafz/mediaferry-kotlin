package org.clavis.steps

import org.clavis.basepack.BasePage
import org.clavis.webpages.LoginPage
import org.openqa.selenium.WebDriver
import org.testng.asserts.SoftAssert


class LoginSteps : BasePage() {

    internal var loginPage = LoginPage()

    fun login(driver: WebDriver, softAssert: SoftAssert, username: String, password: String) {
        loginPage.enterUsername(driver, username)
        loginPage.enterPassword(driver, password)
        loginPage.clickOnLoginButton(driver)
        loginPage.verifyLogin(driver, softAssert)
    }

    @Throws(InterruptedException::class)
    fun logout(driver: WebDriver, softAssert: SoftAssert) {
        Thread.sleep(2000)
        loginPage.mouseHoverOnProfileName(driver)
        loginPage.clickOnLogout(driver)
        loginPage.verifyLogout(driver, softAssert)
    }

    fun verifyValidation(driver: WebDriver, softAssert: SoftAssert) {
        loginPage.checkValidationOfSession(driver, softAssert)
    }

    fun invalidLogin(driver: WebDriver, softAssert: SoftAssert, invalidUsername: String, password: String) {
        loginPage.enterUsername(driver, invalidUsername)
        loginPage.enterPassword(driver, password)
        loginPage.clickOnLoginButton(driver)
        loginPage.verifyInvalidLogin(driver, softAssert)
    }

}
