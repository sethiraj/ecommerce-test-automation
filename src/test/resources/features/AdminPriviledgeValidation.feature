@Admin_priviledges_feature
Feature: Validate Admin Priviledges for Admin and User

  @admin_link @admin_validation
  Scenario: Validate Admin Link is present for Admin User
    Given I login to the application with username 'admin'
    When I land on the home screen
    Then I validate the Admin Link is present
    And I clickon the Admin Link

  @admin_link @user_validation
  Scenario: Validate Admin Link is present for Customer
    Given I login to the application with username 'user'
    When I land on the home screen
    Then I validate the Admin Link is present
    And I clickon the Admin Link