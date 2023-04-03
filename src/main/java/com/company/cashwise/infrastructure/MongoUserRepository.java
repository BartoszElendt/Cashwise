package com.company.cashwise.infrastructure;

import com.company.cashwise.domain.users.BudgetAppUser;
import com.company.cashwise.domain.users.UserId;
import com.company.cashwise.AppProfiles;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;


@Profile({AppProfiles.MONGO_SECURITY_SOURCE, AppProfiles.PROD})
interface MongoUserRepository extends MongoRepository<BudgetAppUser, UserId>, UserRepository {
}
