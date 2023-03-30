package com.company.cashwise;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(AppProfiles.IN_MEMORY_SECURITY_SOURCE)
class BudgetOAppApplicationTests {

    @Test
    void contextLoads() {
    }

}
