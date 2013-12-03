Feature:
    I want to be able to default to a directory to store crema data
    So that each time I run crema, my data will be available
    
Scenario:
    When I open Crema
    Then I have a crema directory to store data in
    And I can read from the crema directory
    And I can write to the crema directory