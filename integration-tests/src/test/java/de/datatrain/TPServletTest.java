package de.datatrain;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

import com.sap.cloud.sdk.testutil.MockUtil;

import static io.restassured.RestAssured.when;

@RunWith(Arquillian.class)
public class TPServletTest {
    private static final MockUtil mockUtil = new MockUtil();
    private static final Logger logger = LoggerFactory.getLogger(TPServletTest.class);

    @ArquillianResource
    private URL baseUrl;

    @Deployment
    public static WebArchive createDeployment() {
        return TestUtil.createDeployment(TPServletTest.class);
    }

    @BeforeClass
    public static void beforeClass() {
        mockUtil.mockDefaults();
        mockUtil.mockErpDestination("saperp", "DT1_100");
    }

    @Before
    public void before() {
        //RestAssured.baseURI = baseUrl.toExternalForm();
    }

    @Test
    public void testService() {
//        // JSON schema validation from resource definition
//        final JsonSchemaValidator jsonValidator = JsonSchemaValidator
//                .matchesJsonSchemaInClasspath("tiles-schema.json");
//
//        // HTTP GET response OK, JSON header and valid schema
//        when()
//                .get("/tiles")
//        .then()
//                .statusCode(200)
//                .contentType(ContentType.JSON)
//                .body(jsonValidator);
    }
}