Feature:
    I want to identify multiple media files that can represent a single movie
    So that I can deal with movie files that are parted out into separate files
    
    Scenario: handle multiple files labeled with "part"
        Given I have a directory that contains these files:
            |Braveheart part1.avi   |
            |Braveheart part2.avi   |
            |Wall E part 1.avi      |
            |Wall E part 2.avi      |
            |SNATCH PART 1.AVI      |
            |SNATCH PART2.AVI       |
            |Toy Story - part1.avi  |
            |Toy Story - part2.avi  |
            |tron-part1.avi         |
            |tron-part2.avi         |
            |tron-part3.avi         |
        When I add that directory as a new media library
        Then these multi-file movies are added once to the media library:
            |Braveheart |
            |Wall E     |
            |Snatch     |
            |Toy Story  |
            |Tron       |

     Scenario: handle multiple files labeled with "CD"
        Given I have a directory that contains these files:
            |Braveheart CD1.avi   |
            |Braveheart CD2.avi   |
            |Wall E CD 1.avi      |
            |Wall E CD 2.avi      |
            |SNATCH cd 1.AVI      |
            |SNATCH cd2.AVI       |
            |Toy Story - cd1.avi  |
            |Toy Story - cd2.avi  |
            |tron-cd1.avi         |
            |tron-cd2.avi         |
            |tron-cd3.avi         |
        When I add that directory as a new media library
        Then these multi-file movies are added once to the media library:
            |Braveheart |
            |Wall E     |
            |Snatch     |
            |Toy Story  |
            |Tron       |
                
     Scenario: handle multiple files labeled with "disc"
        Given I have a directory that contains these files:
            |Braveheart Disc1.avi   |
            |Braveheart Disc2.avi   |
            |Wall E disc 1.avi      |
            |Wall E disc 2.avi      |
            |SNATCH DISC 1.AVI      |
            |SNATCH DISC2.AVI       |
            |Toy Story - disc1.avi  |
            |Toy Story - disc2.avi  |
            |tron-disc1.avi         |
            |tron-disc2.avi         |
            |tron-disc3.avi         |
        When I add that directory as a new media library
        Then these multi-file movies are added once to the media library:
            |Braveheart |
            |Wall E     |
            |Snatch     |
            |Toy Story  |
            |Tron       |

    Scenario: handle a single movie made of 9 files
        Given I have a directory that contains these files:
            |Gladiator  part 1.avi|
            |Gladiator  part 2.avi|
            |Gladiator  part 3.avi|
            |Gladiator  part 4.avi|
            |Gladiator  part 5.avi|
            |Gladiator  part 6.avi|
            |Gladiator  part 7.avi|
            |Gladiator  part 8.avi|
            |Gladiator  part 9.avi|
        When I add that directory as a new media library
        Then these multi-file movies are added once to the media library:
            |Gladiator|