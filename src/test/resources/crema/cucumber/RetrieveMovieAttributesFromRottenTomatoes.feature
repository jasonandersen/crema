Feature:
    I want to search the rotten tomatoes API for each movie to find a matching record
    So that I can learn more about the movies I have
    
    Scenario:
        Given that I have a movie registered in RottenTomatoes.com named "Toy Story 3"
        When I retrieve RottenTomatoes.com attributes for that movie
        Then the movie's release year is 2010
        And the movie's MPAA rating is G
        And the movie's runtime is 103 minutes
        And the movie's critics' consensus is "Deftly blending comedy, adventure, and honest emotion, Toy Story 3 is a rare second sequel that really works."
        And the movie's critics' score is 99
        And the movie's audience score is 91
        And the movie's synopsis is "Pixar returns to their first success with Toy Story 3. The movie begins with Andy leaving for college and donating his beloved toys -- including Woody (Tom Hanks) and Buzz (Tim Allen) -- to a daycare. While the crew meets new friends, including Ken (Michael Keaton), they soon grow to hate their new surroundings and plan an escape. The film was directed by Lee Unkrich from a script co-authored by Little Miss Sunshine scribe Michael Arndt. ~ Perry Seibert, Rovi"
        And the movie's cast is 
            |Tom Hanks (Woody)                  |
            |Tim Allen (Buzz Lightyear)         |
            |Joan Cusack (Jessie the Cowgirl)   |
            |Don Rickles (Mr. Potato Head)      |
            |Wallace Shawn (Rex)                |
            