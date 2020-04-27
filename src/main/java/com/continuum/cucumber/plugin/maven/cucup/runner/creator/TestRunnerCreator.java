package com.continuum.cucumber.plugin.maven.cucup.runner.creator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.continuum.cucumber.plugin.maven.cucup.feature.CucumberFeatureFolder;
import com.continuum.cucumber.plugin.maven.cucup.runner.TestRunner;
import com.continuum.cucumber.plugin.maven.cucup.util.Constants;

public class TestRunnerCreator {

    private TestRunnerCreator() {
        throw new AssertionError("Cannot create TestRunnerCreator instance");
    }

    public static List<TestRunner> createRunnersForFeatureFiles(TestRunnerBuilder builder) throws IOException {

        Path projectBasePath = Paths.get(builder.getProjectBaseDir().toString());

        final String runnerLocationToStore = builder.getRunnerLocationToStore();
        Path runnerTemplatePath = projectBasePath.resolve(builder.getRunnerTemplatePath());
        Path runnerToStorePath = projectBasePath.resolve(runnerLocationToStore);

        final String packageDefinition = runnerLocationToStore
                .substring(runnerLocationToStore.indexOf("java/") + 5, runnerLocationToStore.length())
                .replace('/', '.');

        List<TestRunner> runners = new ArrayList<>();
        for (CucumberFeatureFolder featureFolder : builder.getCucumberFeatureFolders()) {

            TestRunner runner = new TestRunner(featureFolder, packageDefinition, builder.getStepDefinitionGlue(),
                    builder.getTagsToExecute());
            copyTestRunnerToProject(runnerTemplatePath, runnerToStorePath, runner);
            runners.add(runner);

        }

        return runners;
    }

    private static void copyTestRunnerToProject(Path templateTestRunner, Path runnerLocationToCopy, TestRunner runner)
            throws IOException {

        try (Stream<String> lines = Files.lines(templateTestRunner);) {
            List<String> replaced = lines
                    .map(line -> line.replace(Constants.RUNNER_PACKAGE, runner.getPackageDefinition())
                            .replace(Constants.CUCUMBER_FEATURES, runner.getCucumberFeature().getFolderBasePath())
                            .replace(Constants.CUCUMBER_STEPDEFS, runner.getGlueDefinition())
                            .replace(Constants.CUCUMBER_REPORT, runner.getReportName())
                            .replace(Constants.CUCUMBER_TAGS, runner.getTagsToExecute())
                            .replace(Constants.CUCUMBER_RUNNER, runner.getCucumberRunnerClassName()))
                    .collect(Collectors.toList());
            Path runnerFileFinalLocation = runnerLocationToCopy
                    .resolve(runner.getCucumberRunnerClassName() + Constants.JAVA_FILE_EXTENSION);
            Files.write(runnerFileFinalLocation, replaced);
        }

    }

    public static class TestRunnerBuilder {

        private File projectBaseDir;
        private List<CucumberFeatureFolder> cucumberFeatureFolders;
        private String runnerTemplatePath;
        private String runnerLocationToStore;
        private String stepDefinitionGlue;
        private String tagsToExecute;

        public void setProjectBaseDir(File projectBaseDir) {
            this.projectBaseDir = projectBaseDir;
        }

        public void setCucumberFeatureFolders(List<CucumberFeatureFolder> cucumberFeatureFolders) {
            this.cucumberFeatureFolders = cucumberFeatureFolders;
        }

        public void setRunnerTemplatePath(String runnerTemplatePath) {
            this.runnerTemplatePath = runnerTemplatePath;
        }

        public void setTestRunnerLocationtoStore(String runnerLocationToStore) {
            this.runnerLocationToStore = runnerLocationToStore;
        }

        public void setStepDefinitionsPackage(String stepDefinitionPackage) {
            this.stepDefinitionGlue = stepDefinitionPackage;
        }

        public void setTagsToExecute(String tagsToExecute) {
            this.tagsToExecute = tagsToExecute;
        }

        public String getTagsToExecute() {
            return tagsToExecute;
        }

        private File getProjectBaseDir() {
            return projectBaseDir;
        }

        private List<CucumberFeatureFolder> getCucumberFeatureFolders() {
            return cucumberFeatureFolders;
        }

        private String getRunnerTemplatePath() {
            return runnerTemplatePath;
        }

        private String getRunnerLocationToStore() {
            return runnerLocationToStore;
        }

        private String getStepDefinitionGlue() {
            return stepDefinitionGlue;
        }

    }

}