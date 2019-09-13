package testRunner;

import cucumber.api.CucumberOptions;
import testcases.TestBases;


@CucumberOptions(features = ".//feature/Search.feature",
glue = {"stepdefinition"},
plugin= {"pretty","html:target/cucumber-html-report"})
public class TestRunner extends TestBases {

	
	
}
