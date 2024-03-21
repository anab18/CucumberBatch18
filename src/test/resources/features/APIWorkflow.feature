Feature:API workflow
  Background:
    Given a JWT bearer token is created

  @api
  Scenario: create the employee from framework with rest assured
    Given a request is prepared to create an employee using api call
    When a POST call is made to create the employee
    Then the status code for this request should be 201
    And the employee created contains key "Message" and value "Employee Created"
    And the employee id "Employee.employee_id" is stored as global variable

  @api
  Scenario: Get the created employee
    Given a request is prepared to get the created employee
    When a GET call is made to retrieve the employee
    Then the status code for this get request is 200
    And the employee has ID "employee.employee_id" must match with global emp id
    And the data coming from "employee" object should match with the data used in post call
      |emp_firstname|emp_lastname|emp_middle_name|emp_gender|emp_birthday|emp_status|emp_job_title|
      |Tom          |Smith       |T              |Male      |2089-03-10  |progress  |QA           |


  @json
  Scenario: Create employee using json payload
    Given a request is prepared to create an employee using json payload
    When a POST call is made to create the employee
    Then the status code for this request should be 201
    And the employee created contains key "Message" and value "Employee Created"
    And the employee id "Employee.employee_id" is stored as global variable

  @dynamic
  Scenario: Create employee using more dynamic json payload
    Given a request is prepared using data "Tom" , "Smith" , "T" , "M" , "2089-03-10" , "progress" , "Qa"
    When a POST call is made to create the employee
    Then the status code for this request should be 201
    And the employee created contains key "Message" and value "Employee Created"
    And the employee id "Employee.employee_id" is stored as global variable

    @update
    Scenario: Create request for updating the employee
      Given a request is prepared for update the employee
      When a PUT call is made to update the employee
      Then the status code for this get request is 200
      And the employee update contains key "Message" and value "Employee record Updated"
      And the data coming from "Employee" object should match with the data used in PUT call
        |emp_firstname|emp_lastname|emp_middle_name|emp_gender|emp_birthday|emp_status|emp_job_title|
        |Tom          |Smith       |T              |Male      |2089-03-10  |progress  |QA           |

