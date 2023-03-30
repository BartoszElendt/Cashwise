package io.github.szczepanskikrs.budgetoapp.infrastructure;

import io.github.szczepanskikrs.budgetoapp.AppProfiles;
import io.github.szczepanskikrs.budgetoapp.domain.users.BudgetAppUser;
import io.github.szczepanskikrs.budgetoapp.domain.users.UserId;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Mongo implementation of UserRepository
 * Used only when MONGO_SECURITY_SOURCE profile is active.
 * Stores and retrieves user information from mongo
 * It extends MongoRepository so its just normal JPA repository, check {@link UserRepository}
 * to see what real methods are used.
 */
@Profile({AppProfiles.MONGO_SECURITY_SOURCE, AppProfiles.PROD})
interface MongoUserRepository extends MongoRepository<BudgetAppUser, UserId>, UserRepository {
}
