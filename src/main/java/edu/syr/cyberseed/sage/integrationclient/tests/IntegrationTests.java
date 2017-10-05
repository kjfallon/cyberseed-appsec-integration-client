package edu.syr.cyberseed.sage.integrationclient.tests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import edu.syr.cyberseed.sage.integrationclient.entities.MedicalRecord;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Component
public class IntegrationTests {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private Environment env;

    // Read API user details from property file
    private String initialSysadminUsername;
    private String initialSysadminPassword;
    private String sysadminPassword;
    private String doctorPassword;
    private String nursePassword;
    private String patientPassword;
    private String insadminPassword;
    private String medadminPassword;

    // create logger
    private static final Logger log = LoggerFactory.getLogger(IntegrationTests.class);

    private String smirkHost = "";
    private String url = "";
    private String smirkService = "";
    private String requestUsername = "";
    private String requestPassword = "";
    private String returnedDataFromAPI = "";
    private String[] recordSummaryList = null;
    private String recordId = "";

    @PostConstruct
    public void init() {

        // Read API details from property file
        smirkHost = env.getProperty("smirk.api.protocol")
                + env.getProperty("smirk.api.host")
                + ":" + env.getProperty("smirk.api.port");

        // Read API user details from property file
        initialSysadminUsername = env.getProperty("smirk.api.initial.sysadmin.username");
        initialSysadminPassword = env.getProperty("smirk.api.initial.sysadmin.password");
        sysadminPassword = env.getProperty("smirk.api.integration.test.sysadmin.password");
        doctorPassword = env.getProperty("smirk.api.integration.test.doctor.password");
        nursePassword = env.getProperty("smirk.api.integration.test.nurse.password");
        patientPassword = env.getProperty("smirk.api.integration.test.patient.password");
        insadminPassword = env.getProperty("smirk.api.integration.test.insadmin.password");
        medadminPassword = env.getProperty("smirk.api.integration.test.medadmin.password");
        ArrayList<String> recordSummaryList = new ArrayList<String>();

        System.out.println("");
        System.out.println("Starting Integration Tests");
        System.out.println("");

        integrationTest1();
        integrationTest2();
        integrationTest3();
        integrationTest4();
        integrationTest5();
        integrationTest6();
        //integrationTest7();
        //integrationTest8();
        //integrationTest9();
        //integrationTest10();
        //integrationTest11();
        //integrationTest12();
        //integrationTest13();
        //integrationTest14();
        //integrationTest15();
        //integrationTest16();
        //integrationTest17();
        //integrationTest18();
        //integrationTest19();
        //integrationTest20();
        //integrationTest21();
        //integrationTest22();
        //integrationTest23();
        //integrationTest24();
        //integrationTest25();
        //integrationTest26();
        //integrationTest27();
        //integrationTest28();

        System.out.println("");
        System.out.println("Integration Tests Complete");
        System.out.println("");

        return;

    }

    private void integrationTest1 () {

        smirkService = "/createSysAdmin";
        url = smirkHost + smirkService;
        requestUsername = initialSysadminUsername;
        requestPassword = initialSysadminPassword;

        // Create HTTP headers that specify the auth for this request and the content type
        HttpHeaders httpHeaders = new HttpHeaders();
        String auth = requestUsername + ":" + requestPassword;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")) );
        String authHeader = "Basic " + new String( encodedAuth );
        httpHeaders.set("Authorization", authHeader);
        httpHeaders.set("Content-Type", "application/json");

        // Define the data we are submitting to the API
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("username", "DMurphy");
        objectNode.put("password", sysadminPassword);
        objectNode.put("fname", "Dade");
        objectNode.put("lname", "Murphy");
        String postData = objectNode.toString();

        // create full request with data and http headers
        HttpEntity<String> postDataWithHeaders = new HttpEntity <String> (postData, httpHeaders);

        // actually post the API and get a string back
        log.debug("POST url: " + url);
        log.debug("POST credentials: " + requestUsername + ":" + requestPassword);
        log.debug("POST data: " + postData);
        try {
            returnedDataFromAPI = restTemplate.postForObject(url, postDataWithHeaders, String.class);
        }
        catch (HttpClientErrorException e)
        {

            log.error("Error message from SMIRK API:  " + e.getResponseBodyAsString());
            return;
        }
        catch(Exception e)
        {
            log.error("error:  " + e.getMessage());
            return;
        }

        log.debug("API Returned: " + returnedDataFromAPI);

        // Print the API result data in the format specified by the integration test requirements
        // System Requirements Specification section 6.2 Expected printed results does not list
        // any output for this service
        //System.out.println("");

        return;
    }

    private void integrationTest2 () {

        smirkService = "/createDoctor";
        url = smirkHost + smirkService;
        requestUsername = "DMurphy";
        requestPassword = sysadminPassword;

        // Create HTTP headers that specify the auth for this request and the content type
        HttpHeaders httpHeaders = new HttpHeaders();
        String auth = requestUsername + ":" + requestPassword;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")) );
        String authHeader = "Basic " + new String( encodedAuth );
        httpHeaders.set("Authorization", authHeader);
        httpHeaders.set("Content-Type", "application/json");

        // Define the data we are submitting to the API
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("username", "KLibby");
        objectNode.put("password", doctorPassword);
        objectNode.put("fname", "Kate");
        objectNode.put("lname", "Libby");
        objectNode.put("practiceName", "The Gibson Associates");
        objectNode.put("practiceAddress", "1995 Aburn Road, Chicago, IL 60007");
        objectNode.put("recoveryPhrase", "You are not in my class");
        String postData = objectNode.toString();

        // create full request with data and http headers
        HttpEntity<String> postDataWithHeaders = new HttpEntity <String> (postData, httpHeaders);

        // actually post the API and get a string back
        log.debug("POST url: " + url);
        log.debug("POST credentials: " + requestUsername + ":" + requestPassword);
        log.debug("POST data: " + postData);
        try {
            returnedDataFromAPI = restTemplate.postForObject(url, postDataWithHeaders, String.class);
        }
        catch (HttpClientErrorException e)
        {

            log.error("Error message from SMIRK API:  " + e.getResponseBodyAsString());
            return;
        }
        catch(Exception e)
        {
            log.error("error:  " + e.getMessage());
            return;
        }

        log.debug("API Returned: " + returnedDataFromAPI);

        // Print the API result data in the format specified by the integration test requirements
        // System Requirements Specification section 6.2 Expected printed results does not list
        // any output for this service
        //System.out.println("");

        return;
    }

    private void integrationTest3 () {

        smirkService = "/createPatient";
        url = smirkHost + smirkService;
        requestUsername = "KLibby";
        requestPassword = doctorPassword;

        // Create HTTP headers that specify the auth for this request and the content type
        HttpHeaders httpHeaders = new HttpHeaders();
        String auth = requestUsername + ":" + requestPassword;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")) );
        String authHeader = "Basic " + new String( encodedAuth );
        httpHeaders.set("Authorization", authHeader);
        httpHeaders.set("Content-Type", "application/json");

        // Define the data we are submitting to the API
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("username", "MBishop");
        objectNode.put("password", patientPassword);
        objectNode.put("fname", "Martin");
        objectNode.put("lname", "Bishop");
        objectNode.put("dob", "1936-08-18T00:00:00.000Z");
        objectNode.put("ssn", "312124253");
        objectNode.put("address", "145 Redford Drive, Baltimore, MD 21234");
        String postData = objectNode.toString();

        // create full request with data and http headers
        HttpEntity<String> postDataWithHeaders = new HttpEntity <String> (postData, httpHeaders);

        // actually post the API and get a string back
        log.debug("POST url: " + url);
        log.debug("POST credentials: " + requestUsername + ":" + requestPassword);
        log.debug("POST data: " + postData);
        try {
            returnedDataFromAPI = restTemplate.postForObject(url, postDataWithHeaders, String.class);
        }
        catch (HttpClientErrorException e)
        {

            log.error("Error message from SMIRK API:  " + e.getResponseBodyAsString());
            return;
        }
        catch(Exception e)
        {
            log.error("error:  " + e.getMessage());
            return;
        }

        log.debug("API Returned: " + returnedDataFromAPI);

        // Print the API result data in the format specified by the integration test requirements
        // System Requirements Specification section 6.2 Expected printed results does not list
        // any output for this service
        //System.out.println("");

        return;
    }

    private void integrationTest4 () {

        smirkService = "/addDoctorExamRecord";
        url = smirkHost + smirkService;
        requestUsername = "KLibby";
        requestPassword = doctorPassword;

        // Create HTTP headers that specify the auth for this request and the content type
        HttpHeaders httpHeaders = new HttpHeaders();
        String auth = requestUsername + ":" + requestPassword;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")) );
        String authHeader = "Basic " + new String( encodedAuth );
        httpHeaders.set("Authorization", authHeader);
        httpHeaders.set("Content-Type", "application/json");

        // Define the data we are submitting to the API
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("id", "9123245");
        objectNode.put("examDate", "2017-09-03T00:00:00.000Z");
        objectNode.put("patientUsername", "MBishop");
        objectNode.put("doctorUsername", "KLibby");
        objectNode.put("notes", "Looks great for his age");
        String postData = objectNode.toString();

        // create full request with data and http headers
        HttpEntity<String> postDataWithHeaders = new HttpEntity <String> (postData, httpHeaders);

        // actually post the API and get a string back
        log.debug("POST url: " + url);
        log.debug("POST credentials: " + requestUsername + ":" + requestPassword);
        log.debug("POST data: " + postData);
        try {
            returnedDataFromAPI = restTemplate.postForObject(url, postDataWithHeaders, String.class);
        }
        catch (HttpClientErrorException e)
        {

            log.error("Error message from SMIRK API:  " + e.getResponseBodyAsString());
            return;
        }
        catch(Exception e)
        {
            log.error("error:  " + e.getMessage());
            return;
        }

        log.debug("API Returned: " + returnedDataFromAPI);

        // Print the API result data in the format specified by the integration test requirements
        // System Requirements Specification section 6.2 Expected printed results does not list
        // any output for this service
        //System.out.println("");

        return;
    }

    private void integrationTest5 () {

        smirkService = "/listRecords";
        url = smirkHost + smirkService;
        requestUsername = "MBishop";
        requestPassword = patientPassword;

        // Create HTTP headers that specify the auth for this request and the content type
        HttpHeaders httpHeaders = new HttpHeaders();
        String auth = requestUsername + ":" + requestPassword;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")) );
        String authHeader = "Basic " + new String( encodedAuth );
        httpHeaders.set("Authorization", authHeader);
        httpHeaders.set("Content-Type", "application/json");

        // create request with http headers
        HttpEntity<String> postHeaders = new HttpEntity <String> (httpHeaders);

        // actually GET the API and get a string array back
        log.debug("GET url: " + url);
        log.debug("GET credentials: " + requestUsername + ":" + requestPassword);

        try {
            ResponseEntity<String[]> httpEntityResponse = restTemplate.exchange(url, HttpMethod.GET, postHeaders, String[].class);
            recordSummaryList = httpEntityResponse.getBody();
        }
        catch (HttpClientErrorException e)
        {

            log.error("Error message from SMIRK API:  " + e.getResponseBodyAsString());
            return;
        }
        catch(Exception e)
        {
            log.error("error:  " + e.getMessage());
            return;
        }

        for (String  recordSummary : recordSummaryList) {
            log.debug("Recieved record from API: " + recordSummary);
        }
        // Print the API result data in the format specified by the integration test requirements
        if (recordSummaryList.length > 0) {
            recordId = recordSummaryList[0].split(",")[0];
        }
        // print first record id
        System.out.println(recordId);

        return;
    }

    private void integrationTest6 () {

        smirkService = "/viewRecord";
        url = smirkHost + smirkService;
        requestUsername = "MBishop";
        requestPassword = patientPassword;

        // Create HTTP headers that specify the auth for this request and the content type
        HttpHeaders httpHeaders = new HttpHeaders();
        String auth = requestUsername + ":" + requestPassword;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")) );
        String authHeader = "Basic " + new String( encodedAuth );
        httpHeaders.set("Authorization", authHeader);
        httpHeaders.set("Content-Type", "application/json");

        // create request with http headers
        HttpEntity<String> postHeaders = new HttpEntity <String> (httpHeaders);

        // actually GET the API and get a string array back
        log.debug("GET url: " + url);
        log.debug("GET credentials: " + requestUsername + ":" + requestPassword);
        MedicalRecord medRecord = null;
        try {
            ResponseEntity<MedicalRecord> httpEntityResponse = restTemplate.exchange(url + "/" + recordId,
                    HttpMethod.GET,
                    postHeaders,
                    MedicalRecord.class);
            medRecord = httpEntityResponse.getBody();
        }
        catch (HttpClientErrorException e)
        {

            log.error("Error message from SMIRK API:  " + e.getResponseBodyAsString());
            return;
        }
        catch(Exception e)
        {
            log.error("error:  " + e.getMessage());
            return;
        }

        // Print the API result data in the format specified by the integration test requirements
        // print record, the MedicalRecord.toString() method specifies printing in the required way.
        System.out.println("Record ID : " + medRecord.getId());
        System.out.println("Record Type : " + medRecord.getRecord_type());
        System.out.println("Record Date : " + medRecord.getDate());
        System.out.println("Owner : " + medRecord.getOwner());
        System.out.println("Patient : " + medRecord.getPatient());
        System.out.println("Edit Permissions : " + medRecord.getEdit());
        System.out.println("View Permissions : " +medRecord.getView());
        System.out.println("Date : " ); //todo need subtype data
        System.out.println("Doctor : "); //todo need subtype data
        System.out.println("Notes : "); //todo need subtype data

        return;
    }

}