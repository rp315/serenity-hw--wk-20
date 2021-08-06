Feature: Testing  request on video game application

  Scenario: Check if the video game application can be accessed by users
    When User sends a get request to list endpoint, they must get back a valid status code 200

  Scenario Outline: Check if the video game application can be accessed by users
    When User sends a get request to id "<videoGameId>" endpoint, they must get back a valid status code 200
    Examples:
      | videoGameId |
      | 1           |
      | 8           |

  Scenario Outline: Create new video game and verify video game added
    When I create a new video game by providing the information id"<id>" name"<name>",releaseDate"<releaseDate>",reviewScore"<reviewScore>",category"<category>",rating"<rating>"
    Then I verify that the video game with name "<name>" is created

    Examples:
      | id | name  | releaseDate | reviewScore | category       | rating    |
      | 10 | PS4   | 2000-10-01  | 87          | Shooter        | Universal |
      | 30 | Mario | 2001-10-01  | 97          | Platform games | Universal |

  Scenario Outline: Update new video game and verify video game updated
    When I update a new video game by providing the information id"<id>" name"<name>",releaseDate"<releaseDate>",reviewScore"<reviewScore>",category"<category>",rating"<rating>"
    Then I verify that the video game with name "<name>" is updated
    Examples:
      | id | name | releaseDate | reviewScore | category | rating |
      | 9 | PS4   | 2000-10-01  | 89          | Shooter        | Universal |
      | 5 | Mario | 2001-10-01  | 97          | Platform games | Universal |

  Scenario Outline: Delete video game and verify video game deleted
    When I delete a video game by providing the information id"<id>"
    Then I verify that the video game with id"<id>" is deleted
    Examples:
      | id |
      |7   |
      |8   |
