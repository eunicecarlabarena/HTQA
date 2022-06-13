import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import java.util.ArrayList as ArrayList

def response = CustomKeywords.'Testrail.get_tests'(GlobalVariable.G_testrail_run_id)

def tcs_to_add = []

for (def n : (0..response['id'].size)) {
    if (((response['case_id'])[n]) != null) {
        tcs_to_add.add((response['case_id'])[n])
    }
}

response = CustomKeywords.'Testrail.update_run'(GlobalVariable.G_testrail_run_id, tcs_to_add.toString())

def total_tcs_to_update = []

for (def n : (0..GlobalVariable.G_run_testrail_tc_id.size)) {
    if ((GlobalVariable.G_run_testrail_tc_id[n]) != null) {
        total_tcs_to_update.add(GlobalVariable.G_run_testrail_tc_id[n])
    }
}

for (def n : (0..tcs_to_add.size)) {
    if ((tcs_to_add[n]) != null) {
        total_tcs_to_update.add(tcs_to_add[n])
    }
}

def PASSED = '1'

def FAILED = '5'

def status_id

String request = '{"results": ['

// Generate a request for update test results
for (def n : (0..GlobalVariable.G_run_testrail_tc_id.size)) {
    if ((GlobalVariable.G_run_testrail_tc_id[n]) != null) {
        if ((GlobalVariable.G_run_testrail_tc_status[n]) == 'PASSED') {
            status_id = PASSED
        } else {
            status_id = FAILED
        }
        
        request = request.concat(((('{"case_id":' + (GlobalVariable.G_run_testrail_tc_id[n])) + ',"status_id":') + status_id) + 
            ',"comment":"Automation test via Katalon"},')
    }
}

request = request.substring(0, request.length() - 1 //removing last excessive comma from request
    )

request = request.concat(']}')

WebUI.comment('request=' + request.toString())

response = CustomKeywords.'Testrail.add_results'(GlobalVariable.G_testrail_run_id, request)

WebUI.comment('response=' + response.toString())