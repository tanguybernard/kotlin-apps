Feature: Create an employee

  Scenario Template: : Create an employee

    Given There is no employees
    When I create employees "<name>" with "<address>"

    Then the response will return status 201
    And creation employee body contains "<name>" and "<address>"


    Examples:
      | name       | address     |
      | Tony      | Los Angeles |
      | Gwenaelle | Bretagne    |