@P2

Feature: [Squad-2] [Products Feature] [TestCaseID-001] Verify Products Feature

  Scenario Outline: Verify products information on Products screen
    Given I am logged in to the app
    Then the product is listed with title "<title>" and price "<price>"
    And I should be able to log out of the app

    Examples:
      | title                   |price      |
      | Suace Labs Bagpack      |   $29.99  |



  Scenario Outline: Verify products information on Products Details screen
    Given I am logged in to the app
    And I tap on product title "<title>"
    Then I should be navigated to product details screen with title "<title>", price "<price>" and description "<description>"
    And I should be able to log out of the app

    Examples:
      | title           |price|description                                                                                                                                                    |
      |Sauce Labs Onesie|$7.99|Rib snap infant onesie for the junior automation engineer in development. Reinforced 3-snap bottom closure, two-needle hemmed sleeved and bottom won't unravel.|
