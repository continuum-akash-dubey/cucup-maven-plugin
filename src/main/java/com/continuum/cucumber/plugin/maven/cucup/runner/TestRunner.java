package com.continuum.cucumber.plugin.maven.cucup.runner;

import com.continuum.cucumber.plugin.maven.cucup.feature.CucumberFeatureFolder;
import com.continuum.cucumber.plugin.maven.cucup.util.Constants;

public class TestRunner {

    private final CucumberFeatureFolder cucumberFeatureFolder;
    private final String packageDefinition;
    private final String glueDefinition;
    private final String tagsToExecute;
    private final String cucumberRunnerClassName;
    private final String reportName;

    public TestRunner(CucumberFeatureFolder cucumberFeatureFolder, String packageDefinition, String glueDefinition,
            String tagsToExecute) {
        this.cucumberFeatureFolder = cucumberFeatureFolder;
        cucumberRunnerClassName = Constants.TEST_RUNNER_CLASS_NAME_PREFIX + cucumberFeatureFolder.getFolderName();
        reportName = Constants.TEST_REPORT_PREFIX + cucumberFeatureFolder.getFolderName();
        this.packageDefinition = packageDefinition;
        this.glueDefinition = glueDefinition;
        this.tagsToExecute = tagsToExecute;
    }

    public CucumberFeatureFolder getCucumberFeature() {
        return cucumberFeatureFolder;
    }

    public String getPackageDefinition() {
        return packageDefinition;
    }

    public String getGlueDefinition() {
        return glueDefinition;
    }

    public String getTagsToExecute() {
        return tagsToExecute;
    }

    public String getCucumberRunnerClassName() {
        return cucumberRunnerClassName;
    }

    public String getReportName() {
        return reportName;
    }

    @Override
    public String toString() {
        return "TestRunner " + cucumberRunnerClassName + " with Report " + reportName + " Glue " + glueDefinition
                + " Package " + packageDefinition;
    }

}