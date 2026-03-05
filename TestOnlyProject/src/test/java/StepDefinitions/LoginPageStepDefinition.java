package StepDefinitions;

import java.util.Properties;

import org.testng.Assert;

import com.page.LoginPage;
import com.qa.factory.DriverFactory;
import com.utils.ConfigReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginPageStepDefinition {

    private final LoginPage loginPage;
    private final Properties prop;

    public LoginPageStepDefinition() {
        this.loginPage = new LoginPage(DriverFactory.getDriver());
        this.prop = new ConfigReader().init_prop();
    }

    @Given("User lands on Facebook Login Page")
    public void user_lands_on_facebook_login_page() {
        loginPage.openLoginPage(prop.getProperty("url"));
    }

    @When("User Enters incorrect {string} and {string}")
    public void user_enters_incorrect_and(String username, String password) {
        String finalUserName = "Username".equalsIgnoreCase(username)
                ? prop.getProperty("invalid.username")
                : username;
        String finalPassword = "password".equalsIgnoreCase(password)
                ? prop.getProperty("invalid.password")
                : password;

        loginPage.enterUsername(finalUserName);
        loginPage.enterPassword(finalPassword);
    }

    @When("User Clicks on Login Button")
    public void user_clicks_on_login_button() {
        loginPage.clickLogin();
    }

    @Then("User Unable to login the Facebook")
    public void user_unable_to_login_the_facebook() {
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Expected login error message was not displayed.");
        Assert.assertEquals(
                loginPage.getErrorMessage(),
                prop.getProperty("expected.error.message"),
                "Error message text is not matching with expected text."
        );
    }
}
