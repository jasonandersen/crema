Feature:
    I want to have movie files discovered in my media library automatically
    So I don't have to manually enter each media file

    Scenario Outline: adding files to a media library by extension
        Given I have a directory that contains a file named <fileName>
        When I add that directory as a new media library
        Then <fileName> is <result> to the media library
        Examples:
            |fileName   |result     |
            |file.avi   |added      |
            |file.asf   |added      |
            |file.flv   |added      |
            |file.m4v   |added      |
            |file.mkv   |added      |
            |file.mov   |added      |
            |file.mpeg  |added      |
            |file.mpg   |added      |
            |file.mpe   |added      |
            |file.wmv   |added      |
            |file.doc   |not added  |
            |file.xml   |not added  |
            |file       |not added  |
            |file.txt   |not added  |
            |.file      |not added  |
