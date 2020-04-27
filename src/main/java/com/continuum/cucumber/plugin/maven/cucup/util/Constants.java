package com.continuum.cucumber.plugin.maven.cucup.util;

public class Constants {

    private Constants() {
        throw new AssertionError("Cant intantiate Constants Class");
    }

    public static final String RUNNER_PACKAGE = "RUNNER_PACKAGE";
    public static final String CUCUMBER_FEATURES = "CUCUMBER_FEATURES";
    public static final String CUCUMBER_STEPDEFS = "CUCUMBER_STEPDEFS";
    public static final String CUCUMBER_REPORT = "CUCUMBER_REPORT";
    public static final String CUCUMBER_TAGS = "CUCUMBER_TAGS";
    public static final String CUCUMBER_RUNNER = "CUCUMBER_RUNNER";

    public static final String JAVA_FILE_EXTENSION = ".java";
    public static final String FEATURE_FILE_EXTENSION = ".feature";

    public static final String TEST_RUNNER_CLASS_NAME_PREFIX = "PTestRunner_";
    public static final String TEST_REPORT_PREFIX = "Report_";
}