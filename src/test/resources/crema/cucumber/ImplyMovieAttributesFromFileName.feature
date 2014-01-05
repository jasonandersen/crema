Feature:
    I want to be able to imply some media details from the file name
    So that I know as much as possible about the movie I'm watching
    
    Scenario Outline: discover the movie year based on the file name
        Given I have a directory that contains a file named <fileName>
        When I add that directory as a new media library
        Then <fileName> is added to the media library
        And the name of the movie is <movieName>
        And the year of the movie is "<year>"
        Examples:
            |fileName                                                       |movieName                  |year   |
            |Somers.Town.2008.LIMITED.DVDRip.XviD-DMT.avi                   |Somers Town                |2008   |
            |I'm.Not.There.[2007].DvDrip[Eng]-aXXo.avi                      |I'm Not There              |2007   |
            |Last Chance Harvey[2008]DvDrip[Eng]-FXG.avi                    |Last Chance Harvey         |2008   |
            |The.Dark.Knight.Returns.2013.iNTERNAL.BDRi.avi                 |The Dark Knight Returns    |2013   |
            |Batman Begins (2008) 1080p.avi                                 |Batman Begins              |2008   |
            |Batman Begins (2008) 720p.avi                                  |Batman Begins              |2008   |
            |2001.a.space.odyssey.MOV                                       |2001 A Space Odyssey       |       |
            |Snatch (2000) 720p BrRip x264 - DagarX.avi                     |Snatch                     |2000   |
            |Snatch.2000.1080p.AC3(Dolby).5.1ch.Bluray.PS3-TEAM.avi         |Snatch                     |2000   |
            |Snatch (2000) - IPOD CLASSIC by empetrojki.avi                 |Snatch                     |2000   |
            |Mystery.Men.1999.BDRip.576P.X264.AC3-FaNGDiNG0.avi             |Mystery Men                |1999   |
            |Mystery Men (1999) [DVD.Mux.720p.Ita-Eng-Spa][NautiluBT].avi   |Mystery Men                |1999   |
        
    Scenario Outline: discover the resolution of the movie based on the file name
        Given I have a directory that contains a file named <fileName>
        When I add that directory as a new media library
        Then <fileName> is added to the media library
        And the name of the movie is <movieName>
        And the resolution of the movie is "<resolution>"
        Examples:
            |fileName                                                       |movieName                  |resolution |
            |Batman Begins (2008) 1080p.avi                                 |Batman Begins              |1080p      |
            |Batman Begins (2008) 720p.avi                                  |Batman Begins              |720p       |
            |Snatch (2000) 720p BrRip x264 - DagarX.avi                     |Snatch                     |720p       |
            |Snatch.2000.1080p.AC3(Dolby).5.1ch.Bluray.PS3-TEAM.avi         |Snatch                     |1080p      |
            |Mystery.Men.1999.BDRip.576P.X264.AC3-FaNGDiNG0.avi             |Mystery Men                |576p       |
            |Mystery Men (1999) [HDDVDMux.720p.Ita-Eng-Spa][NautilusBT].avi |Mystery Men                |720p       |
            
    Scenario Outline: discover the source based on the file name
        Given I have a directory that contains a file named <fileName>
        When I add that directory as a new media library
        Then <fileName> is added to the media library
        And the name of the movie is <movieName>
        And the source of the movie is "<source>"
        Examples:
            |fileName                                                       |movieName                  |source |
            |Somers.Town.2008.LIMITED.DVDRip.XviD-DMT.avi                   |Somers Town                |DVD    |
            |I'm.Not.There.[2007].DvDrip[Eng]-aXXo.avi                      |I'm Not There              |DVD    |
            |Last Chance Harvey[2008]DvDrip[Eng]-FXG.avi                    |Last Chance Harvey         |DVD    |
            |The.Dark.Knight.Returns.2013.iNTERNAL.BRay.avi                 |The Dark Knight Returns    |BlueRay|
            |Snatch (2000) 720p BrRip x264 - DagarX.avi                     |Snatch                     |BlueRay|
            |Snatch.2000.1080p.AC3(Dolby).5.1ch.Bluray.PS3-TEAM.avi         |Snatch                     |BlueRay|
            |Mystery.Men.1999.BDRip.576P.X264.AC3-FaNGDiNG0.avi             |Mystery Men                |BlueRay|
            |Mystery Men (1999) [DVD.Mux.720p.Ita-Eng-Spa][NautiluBT].avi   |Mystery Men                |DVD    |


        