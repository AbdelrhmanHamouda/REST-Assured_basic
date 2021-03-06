package simpleGetRequest;


import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class SimpleGetRequest {

    @Test
    public void requestUsZipCode90210_checkPlaceNameInResponseBody_expectBeverlyHills() {

        given().when().
                get("http://zippopotam.us/us/90210").
                then().
                assertThat().body("places[0].'place name'", equalTo("Beverly Hills"));

    }

    @Test
    public void statusCode() {

        given().
                log().all().
                when().
                get("http://zippopotam.us/us/90210").
                then().
                log().body().
                assertThat().statusCode(200);

    }

    @Test
    public void header_contenttypes() {

        given().when().
                get("http://zippopotam.us/us/90210").
                then().
                assertThat().contentType(ContentType.JSON);

    }

    @Test
    public void bodyChecks() {

        given().when().
                get("http://zippopotam.us/us/90210").
                then().
                assertThat().body("places[0].'place name'", equalTo("Beverly Hills"));

    }

    @Test
    public void bodyChecksWithHasItem() {

        given().when().
                get("http://zippopotam.us/us/90210").
                then().
                assertThat().body("places.'place name'", hasItem("Beverly Hills"));

    }

    @Test
    public void bodyChecksWithHasItemWithNot() {

        given().when().
                get("http://zippopotam.us/us/90210").
                then().
                assertThat().body("places.'place name'", not(hasItem("NY")));

    }

    @Test
    public void bodyChecksWithHasIsize() {

        given().when().
                get("http://zippopotam.us/us/90210").
                then().
                assertThat().body("places.'place name'", hasSize(1));

    }
}
