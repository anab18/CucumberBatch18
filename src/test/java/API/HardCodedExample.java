package API;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HardCodedExample {

    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    static String employee_id;
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE3MTA3MzE5OTgsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTcxMDc3NTE5OCwidXNlcklkIjoiNjU2OSJ9.8ktCfAWFeYJqYqlH9J26vPO4dN2XdcohXHdtkMRvXUA";

    @Test
    public void acreateEmployee() {
        //it will create the request
        RequestSpecification request = given().
                header("Content-Type", "application/json").
                header("Authorization", token).
                body("{\n" +
                        "  \"emp_firstname\": \"Tom\",\n" +
                        "  \"emp_lastname\": \"Smith\",\n" +
                        "  \"emp_middle_name\": \"T\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"2089-03-10\",\n" +
                        "  \"emp_status\": \"progress\",\n" +
                        "  \"emp_job_title\": \"Qa\"\n" +
                        "}");
        // it will give us the response after hitting the endpoint
        Response response = request.when().post("/createEmployee.php");

        //assert that is the method we use to validate the response
        response.then().assertThat().statusCode(201);

        //this method is used to print the response in console
        response.prettyPrint();
        //hamcrest matchers
        response.then().assertThat().body("Message", equalTo("Employee Created"));
        response.then().assertThat().body("Employee.emp_firstname", equalTo("Tom"));
        response.then().assertThat().body("Employee.emp_lastname", equalTo("Smith"));
        // response.then().assertThat().body("Employee.emp_middle_name",equalTo("T"));
        response.then().assertThat().header("Connection", equalTo("Keep-Alive"));
        // to fetch the employee is from response body, we need reponse variable
        employee_id = response.jsonPath().getString("Employee.employee_id");
    }

    @Test
    public void bgetCreateEmployee() {
        // prepare the request
        RequestSpecification request = given().
                header("Content-Type", "application/json").
                header("Authorization", token).queryParam("employee_id", employee_id);
        //hitting the endpoint
        Response response = request.when().get("/getOneEmployee.php");
        //validate the response
        response.then().assertThat().statusCode(200);
        response.prettyPrint();
        String temporaryEmpId =
                response.jsonPath().getString("employee.employee_id");
        //here we are comparing both emp id's from get and post call
        Assert.assertEquals(temporaryEmpId, employee_id);
        //validate the body from get call
        response.then().assertThat().
                body("employee.emp_lastname", equalTo("Smith"));
        response.then().assertThat().
                body("employee.emp_firstname", equalTo("Tom"));
    }

    @Test
    public void cUpdateEmployee() {
        //prepare the request
        RequestSpecification request = given().header("Content-Type", "application/json").
                header("Authorization", token).
                body("{\n" +
                        "  \"employee_id\": \"" + employee_id + "\",\n" +
                        "  \"emp_firstname\": \"Misa\",\n" +
                        "  \"emp_lastname\": \"Smith\",\n" +
                        "  \"emp_middle_name\": \"Mr\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"2089-03-10\",\n" +
                        "  \"emp_status\": \"progress\",\n" +
                        "  \"emp_job_title\":\"Tester\"\n" +
                        "}");

        //hitting the endpoint
        Response response = request.when().put("/updateEmployee.php");
        //validation of response
        response.then().assertThat().statusCode(200);
        response.then().assertThat().
                body("Message", equalTo("Employee record Updated"));
        response.prettyPrint();

    }
    @Test
    public void dGetUpdatedEmployee(){
        //prepare the request
        RequestSpecification request=given().
                header("Content-Type","application/json").
                header("Authorization",token).queryParam("employee_id",employee_id);
                        //hitting the endpoint
                Response response=request.when().get("/getOneEmployee.php");
                //validation
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }
    @Test
    public void eGetAllEmployee(){
        RequestSpecification request=given().
                header("Content-Type","application/json").header("Authorization",token).queryParam("employee_id",employee_id);
        Response response=request.when().get("/getAllEmployees.php");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);

    }
    @Test
    public void fJobTitle(){
        RequestSpecification request=given().
                header("Content-Type","application/json").header("Authorization",token);
        Response response=request.when().get("/jobTitle.php");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void gUpdatePartialEmployeeDetails(){
        RequestSpecification request=given().header("Content-Type","application/json").header("Authorization",token).
                body("{\n" +
                        "  \"employee_id\": \""+employee_id+"\",\n" +
                        "  \"emp_lastname\": \"Smith\",\n" +
                        "  \"emp_middle_name\": \"Mr\",\n" +
                        "  \"emp_status\": \"completed\",\n" +
                        "  \"emp_job_title\": \"Teacher\"\n" +
                        "}");
        Response response=request.when().patch("/updatePartialEmplyeesDetails.php");
        response.then().assertThat().statusCode(201);
        response.then().assertThat().body("Message", equalTo("Employee record updated successfully"));
        response.then().assertThat().body("employee.emp_lastname", equalTo("Smith"));
        response.then().assertThat().body("employee.emp_middle_name", equalTo("Mr"));

        response.then().assertThat().body("employee.emp_job_title", equalTo("Teacher"));
        response.prettyPrint();


    }
}
