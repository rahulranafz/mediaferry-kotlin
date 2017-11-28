package org.clavis

import org.clavis.basepack.Driver
import org.clavis.steps.LoginSteps
import java.io.IOException
import org.openqa.selenium.WebDriver
import org.testng.annotations.AfterTest
import org.testng.annotations.BeforeTest
import org.testng.annotations.Test
import org.testng.asserts.SoftAssert

class LoginTest {

    internal var driverObj = Driver()
    internal var driver: WebDriver? = null
    internal var loginSteps = LoginSteps()
    internal var softAssert = SoftAssert()

    @BeforeTest
    @Throws(IOException::class)
    fun start() {
        driver = driverObj.createDriver()
        driver!!.get(driverObj.getUrl())
    }

    @Test(priority = 0)
    @Throws(InterruptedException::class)
    fun loginWithValidUserDetails() {
        loginSteps.login((driver as WebDriver), softAssert, driverObj.getUsername(), driverObj.getPassword())
        loginSteps.logout((driver as WebDriver), softAssert)
        softAssert.assertAll()
    }

    @Test(priority = 1)
    fun loginWithInvalidUserDeatils() {
        loginSteps.invalidLogin((driver as WebDriver), softAssert, driverObj.getInvalidUsername(), driverObj.getPassword())
    }

    @AfterTest
    fun tearDown() {
        driver!!.quit()
        driver = null
    }
}
