package org.clavis.utilities

import java.io.FileInputStream
import java.io.IOException
import java.util.Properties

import org.testng.Assert

class FileOperations {

    fun getValueFromPropertyFile(filePath: String, key: String): String? {
        var keyValue: String? = null
        val prop = Properties()
        try {
            prop.load(FileInputStream(filePath))
            keyValue = prop.getProperty(key)
        } catch (e: IOException) {
            e.printStackTrace()
            Assert.fail()
        }

        return keyValue
    }

}
