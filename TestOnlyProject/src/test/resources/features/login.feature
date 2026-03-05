Feature: Login Page Automation Testing

  Scenario: Check user unable to login by giving wrong credentials
    Given User lands on Facebook Login Page
    When User Enters incorrect "Username" and "password"
    And User Clicks on Login Button
    Then User Unable to login the Facebook
