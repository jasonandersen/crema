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

    Scenario Outline: adding files to a media library by extension in upper case
        Given I have a directory that contains a file named <fileName>
        When I add that directory as a new media library
        Then <fileName> is <result> to the media library
        Examples:
            |fileName   |result     |
            |file.AVI   |added      |
            |file.ASF   |added      |
            |file.FLV   |added      |
            |file.M4V   |added      |
            |file.MKV   |added      |
            |file.MOV   |added      |
            |file.MPEG  |added      |
            |file.MPG   |added      |
            |file.MPE   |added      |
            |file.WMV   |added      |
            |file.DOC   |not added  |
            |file.XML   |not added  |
            |file       |not added  |
            |file.TXT   |not added  |
            |.FILE      |not added  |
            
    Scenario: adding a directory with multiple media files
        Given I have a directory that contains these files:
            |file.avi   |
            |file.mkv   |
            |file.mpeg  |
            |file       |
            |file.txt   |
            |file.xls   |
        When I add that directory as a new media library
        Then these files have been added to the media library:
            |file.avi   |
            |file.mkv   |
            |file.mpeg  |
        And these files have not been added to the media library:
            |file       |
            |file.txt   |
            |file.mpeg  |
            
    Scenario: adding files nested several directories deep
        Given I have a directory that contains these files:
            |sub-dir-1/sub-dir-1-a/sub-dir-1-a-i/file.mkv     |
            |sub-dir-1/sub-dir-1-b/file.mpe                   |
            |sub-dir-2/sub-dir-2-a/sub-dir-2-a-i/file.avi     |
            |sub-dir-2/sub-dir-2-a/sub-dir-2-a-i/file.txt     |
            |file.mpeg                                        |
            |file.xls                                         |
        When I add that directory as a new media library
        Then these files have been added to the media library:
            |file.mkv   |
            |file.mpe   |
            |file.avi   |
            |file.mpeg  |
        And these files have not been added to the media library:
            |file.txt   |
            |file.xls   |
            