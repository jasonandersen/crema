Feature:
    I want to have the names of the media files guessed automatically
    So that I don't have to type the name of each media file
    
    Scenario Outline: guessing the names of the movies based on their file names
        Given I have a directory that contains a file named <fileName>
        When I add that directory as a new media library
        Then <fileName> is added to the media library
        And the name of the movie is <movieName>
        Examples:
            |fileName               |movieName          |
            |YoungGuns.avi          |Young Guns         |
            |young-guns.avi         |Young Guns         |
            |YoungGuns.avi          |Young Guns         |
            |youngGuns.avi          |Young Guns         |   
            |Young Guns.avi         |Young Guns         |
            |young_guns.avi         |Young Guns         |
            |young.guns.avi         |Young Guns         |
            |a-beautiful-mind.avi   |A Beautiful Mind   |
            |12_MONKEYS.AVI         |12 Monkeys         |
            |a_beautiful.mind.avi   |A Beautiful Mind   |
            |a-beautiful_mind.avi   |A Beautiful Mind   |
            |bill_and_ted's_adv.mpg |Bill And Ted's Adv |
            |falling-down(1993).avi |Falling Down       |
            |Godzilla 1000.mov      |Godzilla 1000      |
            |2012.m4v               |2012               |
            
    Scenario Outline: guessing movie names for some common file name patterns
        Given I have a directory that contains a file named <fileName>
        When I add that directory as a new media library
        Then <fileName> is added to the media library
        And the name of the movie is <movieName>
        Examples:
            |fileName                                                       |movieName              |
            |2001.a.space.odyssey.MOV                                       |2001 A Space Odyssey   |
            |Somers.Town.2008.LIMITED.DVDRip.XviD-DMT.avi                   |Somers Town            |
            |Half.Nelson.1080p.LiMiTED.DVDRip.XviD-LMG.avi                  |Half Nelson            |
            |I'm.Not.There.[2007].DvDrip[Eng]-aXXo.avi                      |I'm Not There          |
            |Last Chance Harvey[2008]DvDrip[Eng]-FXG.avi                    |Last Chance Harvey     |
            |The.Dark.Knight.Returns.2013.iNTERNAL.BDRi.avi                 |The Dark Knight Returns|
            |Batman Begins (2008) 1080p.avi                                 |Batman Begins          |
            |Batman Begins (2008) 720p.avi                                  |Batman Begins          |
            |Snatch (2000) 720p BrRip x264 - DagarX.avi                     |Snatch                 |
            |Snatch.2000.1080p.AC3(Dolby).5.1ch.Blu-ray.PS3-TEAM.avi        |Snatch                 |
            |Snatch (2000) - IPOD CLASSIC by empetrojki.avi                 |Snatch                 |
            |Mystery.Men.1999.BDRip.576P.X264.AC3-FaNGDiNG0.avi             |Mystery Men            |
            |Mystery Men (1999) [HDDVDMux720p.Ita-Eng-Spa][Nautilus-BT].avi |Mystery Men            |
            
