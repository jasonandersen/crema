Feature:
    I want to be able to rename a movie when the movie name was not properly deciphered
    So that web service calls will be more accurate
    
    Scenario: rename a movie
        Given I have a media library that contains a movie named "I Like Monkeys"
        When I rename a movie named "I Like Monkeys" to "A Monkey Punched Me in the Groin"
        Then the movie's name is now "A Monkey Punched Me in the Groin"
        
    Scenario: make sure the renamed value does not change case
        Given I have a media library that contains a movie named "Blah"
        When I rename a movie named "Blah" to "BLAH"
        Then the movie's name is now "BLAH"