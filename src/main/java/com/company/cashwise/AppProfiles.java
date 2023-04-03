package com.company.cashwise;


public enum AppProfiles {
    INSTANCE;
    public static final String MONGO_SECURITY_SOURCE = "MONGO_SECURITY_SOURCE";
    public static final String IN_MEMORY_SECURITY_SOURCE = "IN_MEMORY_SECURITY_SOURCE";
    public static final String LOCAL = "LOCAL";
    public static final String PROD = "PROD";
}
