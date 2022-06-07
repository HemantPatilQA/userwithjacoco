package com.selflearning.userwithjacoco;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = UserwithjacocoApplication.class)
class UserwithjacocoApplicationTests {

    @Test
    void userwithjacocoApplicationTest(){
        String[] args=
                new String[] {
                        "test", "test"
                };
        UserwithjacocoApplication.main(args);
        Assertions.assertNotNull(args);
    }
}
