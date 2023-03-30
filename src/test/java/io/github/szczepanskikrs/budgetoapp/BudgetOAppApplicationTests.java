package io.github.szczepanskikrs.budgetoapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(AppProfiles.IN_MEMORY_SECURITY_SOURCE)
class BudgetOAppApplicationTests {

    @Test
    void contextLoads() {
    }

}
