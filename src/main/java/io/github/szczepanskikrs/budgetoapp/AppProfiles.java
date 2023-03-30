package io.github.szczepanskikrs.budgetoapp;

/**
 * It's a good idea to store information about profiles in some data-holders.
 * This enum is Singleton and is used only as holder for static final strings used as profile names.
 */
public enum AppProfiles {
    INSTANCE;
    public static final String MONGO_SECURITY_SOURCE = "MONGO_SECURITY_SOURCE";
    public static final String IN_MEMORY_SECURITY_SOURCE = "IN_MEMORY_SECURITY_SOURCE";
    public static final String LOCAL = "LOCAL";
    public static final String PROD = "PROD";
}
