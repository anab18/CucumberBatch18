package Utils;

import netscape.javascript.JSException;
import netscape.javascript.JSObject;
import org.json.JSONObject;

public class APIPayloadConstants {
    public static String createEmployeePayload(){
        String createEmployeePayload = "{\n" +
                "  \"emp_firstname\": \"Tom\",\n" +
                "  \"emp_lastname\": \"Smith\",\n" +
                "  \"emp_middle_name\": \"T\",\n" +
                "  \"emp_gender\": \"M\",\n" +
                "  \"emp_birthday\": \"2089-03-10\",\n" +
                "  \"emp_status\": \"progress\",\n" +
                "  \"emp_job_title\": \"QA\"\n" +
                "}";
        return createEmployeePayload;
    }
    public static String createEmployeeJsonPayload() {
        JSONObject obj = new JSONObject();
        obj.put("emp_firstname", "Tom");
        obj.put("emp_lastname", "Smith");
        obj.put("emp_middle_name", "T");
        obj.put("emp_gender", "M");
        obj.put("emp_birthday", "2089-03-10");
        obj.put("emp_status", "progress");
        obj.put("emp_job_title", "QA");

        return obj.toString();

    }
    public static String createEmployeeJsonPayloadDynamic
            (String emp_firstname, String emp_lastname,
             String emp_middle_name,String emp_gender,
             String emp_birthday, String emp_status, String emp_job_title){

        JSONObject obj = new JSONObject();
        obj.put("emp_firstname", emp_firstname);
        obj.put("emp_lastname", emp_lastname);
        obj.put("emp_middle_name",emp_middle_name);
        obj.put("emp_gender",emp_gender);
        obj.put("emp_birthday",emp_birthday);
        obj.put("emp_status",emp_status);
        obj.put("emp_job_title",emp_job_title);

        return obj.toString();
    }
    public static String updateEmployeeJsonPayload() {
        JSONObject obj = new JSONObject();
        obj.put("employee_id","106869A");
        obj.put("emp_firstname", "Tom");
        obj.put("emp_lastname", "Smith");
        obj.put("emp_middle_name", "T");
        obj.put("emp_gender", "M");
        obj.put("emp_birthday", "2089-03-10");
        obj.put("emp_status", "progress");
        obj.put("emp_job_title", "QA");

        return obj.toString();

    }
    }






