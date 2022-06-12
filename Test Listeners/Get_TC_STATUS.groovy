import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

import internal.GlobalVariable as GlobalVariable

import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext

class Get_TC_STATUS {
	/**
	 * Executes after every test case ends.
	 * @param testCaseContext related information of the executed test case.
	 */
//	@AfterTestCase
//	def sampleAfterTestCase(TestCaseContext testCaseContext) {
//		println testCaseContext.getTestCaseId()
//		println testCaseContext.getTestCaseStatus()
//	}
	
	@AfterTestCase
	def afterTestCase(TestCaseContext testCaseContext) {
		try {
			def tc_ids = testCaseContext.getTestCaseVariables()['testrail_tc_id'].split(",")
			for (def n : (0 .. tc_ids.length - 1)) {
				GlobalVariable.G_run_testrail_tc_id.add(tc_ids[n])
				GlobalVariable.G_run_testrail_tc_status.add(testCaseContext.getTestCaseStatus())
			}
		} catch (NullPointerException ex) {
		}
		
	}
//	start of removed from original @AfterTestCase
	def sampleAfterTestCase(TestCaseContext testCaseContext) {
		println testCaseContext.getTestCaseId()
		println testCaseContext.getTestCaseStatus()
//	end of removed from original @AfterTestCase
		
	}
	
	/**
	 * Executes after every test suite ends.
	 * @param testSuiteContext: related information of the executed test suite.
	 */
	@AfterTestSuite
	def sampleAfterTestSuite(TestSuiteContext testSuiteContext) {
		println testSuiteContext.getTestSuiteId()
	}
	
	def afterTestSuite() {
		'push results to testrail'
		WebUI.callTestCase(findTestCase('%Test Cases/Login%'), [:], FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.callTestCase(findTestCase('%Test Cases/TestRail_Update_Run_With_Results%'), [:], FailureHandling.CONTINUE_ON_FAILURE)
		
		
	}
}