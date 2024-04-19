package com.mona.catalogservice;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;

/** base class for all tests running catalog testcontainers */
/** Random port added so it does not interfere with jenkins or other devops build tools */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import({ContainerConfig.class})
public class AbstractIT {

    /** random port number initialization*/
    @LocalServerPort
    int port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
    }
}
