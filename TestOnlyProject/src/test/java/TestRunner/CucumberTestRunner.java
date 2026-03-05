package TestRunner;

import io.cucumber.core.cli.Main;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@SuppressWarnings("unused")
@CucumberOptions(features = "src\\test\\resource\\Features", glue = "src\\test\\java\\StepDefinitions\\LoginPageStepDefinition", plugin = "pretty",monochrome=true)

public class CucumberTestRunner extends AbstractTestNGCucumberTests{

}
