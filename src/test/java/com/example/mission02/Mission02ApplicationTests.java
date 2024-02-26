package com.example.mission02;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Mission02ApplicationTests {

    @Test
    @DisplayName("테스트")
    void test() throws Exception {
        Assertions.assertEquals("1", "1");
    }
}
