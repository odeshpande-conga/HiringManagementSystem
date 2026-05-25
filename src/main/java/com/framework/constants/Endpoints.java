package com.framework.constants;

/**
 * API endpoint constants
 */
public class Endpoints {
    // Auth
    public static final String REGISTER = "/api/auth/register";
    public static final String LOGIN = "/api/auth/login";

    // Users
    public static final String USER_PROFILE = "/api/users/profile";

    // Jobs
    public static final String JOBS = "/api/jobs";
    public static final String JOB_BY_ID = "/api/jobs/{id}";
    public static final String SEARCH_JOBS = "/api/jobs/search";
    public static final String FILTER_JOBS = "/api/jobs/filter";

    // Applications
    public static final String APPLICATIONS = "/api/applications";
    public static final String MY_APPLICATIONS = "/api/applications/my";
    public static final String APPLICANTS_FOR_JOB = "/api/applications/job/{jobId}";
    public static final String UPDATE_APP_STATUS = "/api/applications/{id}/status";

    // Admin
    public static final String ADMIN_USERS = "/api/admin/users";
    public static final String ADMIN_USER_BY_ID = "/api/admin/users/{id}";

    // Upload
    public static final String UPLOAD_RESUME = "/api/upload/resume";
}

