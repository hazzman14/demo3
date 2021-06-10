Feature: Using Selenium

  Scenario Template: user opens lists
    Given user goes to the site
    When user clicks on "<entity>" button
    Then "<entity>" page opens

    Examples:
      |entity|
      |actors|
      |categories|
      |films|
      |invalid|


  Scenario Template: user edits entity firstname
    Given user goes to the site
    When user clicks on "<entity>" button
    And user clicks edit on id <id>
    And user enters "<name>" into firstname box and submits
    Then firstname for <id> is updated with "<name>"

    Examples:
      |entity|name|id|
      |actors|harry|1 |
      |actors|peedah|2|
