import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys


class LoginSteps {
	
	@Given("User navigates to login page")
	def navigateToLoginPage() {
		println("\nI am inside navigateToLoginPage ")
		
		WebUI.openBrowser('')
		WebUI.navigateToUrl('https://auth.koala.hometime.io/login')
		
	}
	
	@When("User enters valid (.*) and (.*)")
	def enterCredentials(String username, String password) {
		println("\nI am inside enterCredentials")
		println("Username:"+ username)
		println("Password:"+ password)
		
		WebUI.setText(findTestObject('Object Repository/Page_Login  Hometime/input_Email address_email'), username)
		
		WebUI.setEncryptedText(findTestObject('Object Repository/Page_Login  Hometime/input_Password_password'), password)
		
	}
	
	@And("Clicks on login button")
	def clickLogin() {
		println("\nI am inside clickLogin")
		
		WebUI.click(findTestObject('Object Repository/Page_Login  Hometime/button_Login'))
		
	}
	
	@Then("User should be redirected to homepage")
	def redirectToHomePage() {
		println("\nI am inside redirectToHomePage")
		WebUI.closeBrowser()
	}
	
}