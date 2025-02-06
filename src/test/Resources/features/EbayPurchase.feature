Feature: eBay Book Purchase

  Scenario: Add a book to the cart
    Given I open the firefox Browser
    When I navigate to "https://www.ebay.com"
    And I search for "book"
    And I click on the first book in the list
    And I add the item to the cart
    Then the cart should display the updated item count
