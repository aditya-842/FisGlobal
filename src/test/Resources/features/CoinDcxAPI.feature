Feature: Validate Coindesk Current Price API

  Scenario: Verify the currentprice API returns three BPIs with correct GBP description
    Given I send a GET request to "https://api.coindesk.com/v1/bpi/currentprice.json"
    When I receive the response
    Then the response should contain 3 BPIs: "USD", "GBP", "EUR"
    And the GBP description should equal "British Pound Sterling"
