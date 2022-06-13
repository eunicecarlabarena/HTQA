package sync_testrail

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import groovy.json.JsonSlurper as JsonSlurper
import com.kms.katalon.core.testobject.TestObjectProperty as TestObjectProperty
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.testobject.impl.HttpTextBodyContent

import internal.GlobalVariable

public class Testrail {
	
	//Makes a GET request to TestRail to get all the test case IDs from the selected TestRail run.
	@Keyword
	def get_tests(String id) {
		def slurper = new JsonSlurper()
		RequestObject ro = new RequestObject('a')
		ro.setRestRequestMethod('GET')
		ro.setRestUrl('https://%YOUR PROJECT%/index.php?/api/v2/get_tests/'+id)
		def httpheader = new ArrayList<TestObjectProperty>()
		httpheader.add(new TestObjectProperty(
				'Content-Type', ConditionType.EQUALS, 'application/json'))
		httpheader.add(new TestObjectProperty(
				'Authorization', ConditionType.EQUALS,
				'Basic %ZGV2QGhvbWV0aW1lLmlvClY3Z3ZuYlhLcmFQNUUzZA==%'))
		ro.setHttpHeaderProperties(httpheader)
		ro.setBodyContent(new HttpTextBodyContent('', 'UTF-8', 'application/json'))
		def response = WSBuiltInKeywords.sendRequest(ro)
		return slurper.parseText(response.getResponseText())
	}
	
	
	@Keyword
	def update_run(String id, String array) {
		def slurper = new JsonSlurper()
		RequestObject ro = new RequestObject('Update TestRail test run')
		ro.setRestRequestMethod('POST')
		ro.setRestUrl('https://%YOUR PROJECT%/index.php?/api/v2/update_run/'+id)
		def httpheader = new ArrayList<TestObjectProperty>()
		httpheader.add(new TestObjectProperty(
				'Content-Type', ConditionType.EQUALS, 'application/json'))
		httpheader.add(new TestObjectProperty(
				'Authorization', ConditionType.EQUALS,
				'Basic %PASTE YOUR ENCODED CREDENTIALS HERE%'))
		ro.setHttpHeaderProperties(httpheader)
		//StringArray array = new String(tc_id.size())
		def body ='{"include_all": false,"case_ids": '+ array +'}'
		WebUI.comment('body='+body)
		ro.setBodyContent(new HttpTextBodyContent(body, 'UTF-8', 'application/json'))
		def response = WSBuiltInKeywords.sendRequest(ro)
		return slurper.parseText(response.getResponseText())
	}
	
	
	//Makes a POST to add the test results to the TestRail run.
	@Keyword
	def add_results(String id, String request) {
		def slurper = new JsonSlurper()
		RequestObject ro = new RequestObject('a')
		ro.setRestRequestMethod('POST')
		ro.setRestUrl('https://%YOUR PROJECT%/index.php?/api/v2/add_results_for_cases/'+id)
		def httpheader = new ArrayList<TestObjectProperty>()
		httpheader.add(new TestObjectProperty(
				'Content-Type', ConditionType.EQUALS, 'application/json'))
		httpheader.add(new TestObjectProperty(
				'Authorization', ConditionType.EQUALS,
				'Basic %PASTE YOUR ENCODED CREDENTIALS HERE%'))
		ro.setHttpHeaderProperties(httpheader)
		WebUI.comment('body='+request)
		ro.setBodyContent(new HttpTextBodyContent(
				request, 'UTF-8', 'application/json'))
		def response = WSBuiltInKeywords.sendRequest(ro)
		def response_array = slurper.parseText(response.getResponseText())
		return slurper.parseText(response.getResponseText())
	}
	
}
