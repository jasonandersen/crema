Feature:
    I want to define a directory on disk as media library
    So that I can track where my media files are stored

Scenario: add a new media library
    Given I have a directory
    And the directory exists
    And the directory can be read
    When I choose the directory as a media library named "Library 1"
    Then I have a media library named "Library 1"
    And the directory for media library "Library 1" matches the directory
    
Scenario: add a new media library with a duplicate name
    Given I have an existing media library named "Library 1"
    And I have a directory
    And the directory exists
    And the directory can be read
    When I choose the directory as a media library named "Library 1"
    Then I get a duplicate media library error

Scenario: add a new media library for a directory that doesn't exist
    Given I have a directory
    And the directory does not exist
    When I choose the directory as a media library named "Library 1"
    Then I get a invalid directory media library error
    
Scenario: add a new media library for a directory that can't be read
    Given I have a directory
    And the directory cannot be read
    When I choose the directory as a media library named "Library 1"
    Then I get a invalid directory media library error
