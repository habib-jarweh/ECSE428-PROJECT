Feature: Delete User Account
As a user, I wish to permanently delete my account and personal information from the application

  Scenario Outline: User deletes their account successfully
    Given An account with "<username>" and "<password>" exists
    Given the user with "<username>" and "<password>" is logged in
    When the user attempts to delete their account
    Then they are asked to confirm the action by providing their password
    Then the user with "<username>"  inputs their "<password>" correctly
    Then the account with "<username>" will no longer exist in the system

    Examples: 
      | username   | password  |
      | john123    | xuz24cdr  |
      | micheal344 | s22002sdd |
      | kim222     | dasafsf   |

  Scenario Outline: User deletes their account unsuccesfully
    Given An account with "<username>" and "<password>" exists
    Given the user with "<username>" and "<password>" is logged in
    When the user attempts to delete their account
    Then they are asked to confirm the action by providing their password
    Then the user with "<username>"  inputs their "<password>" incorrectly
    Then the account with "<username>" will still exist in the system

    Examples: 
      | username   | password |
      | john123    |          |
      | micheal344 | s22002   |
      | kim222     | DASAFSF  |
