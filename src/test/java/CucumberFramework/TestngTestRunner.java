package CucumberFramework;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src\\test\\java\\CucumberFramework", glue="StepDefinitionPack",
monochrome=true, tags="@Regression", plugin= {"html:target\\cucumber.html"})
public class TestngTestRunner extends AbstractTestNGCucumberTests{

	
	
}
