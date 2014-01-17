Feature:
    I want to search the rotten tomatoes API for each movie to find a matching record
    So that I can learn more about the movies I have
    
    Scenario:
        Given that I have a movie that is registered in RottenTomatoes.com named "Toy Story 3"
        When I retrieve RottenTomatoes.com attributes for that movie
        Then the movie's release year is 2010
        And the movie's MPAA rating is "G"
        And the movie's runtime is 103 minutes
        And the movie's critics' score is 99
        And the movie's audience score is 89
