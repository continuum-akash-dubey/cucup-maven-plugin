package com.continuum.cucumber.plugin.maven.cucup.feature;

import java.nio.file.Path;

public class CucumberFeature {

    private final String featureFileName;
    private final Path featureFilePath;

    public CucumberFeature(Path filePath) {
        this.featureFilePath = filePath;
        featureFileName = featureFilePath.toFile().getName();
    }

    public String getFeatureFileName() {
        return this.featureFileName;
    }

    public Path getFeatureFilePath() {
        return this.featureFilePath;
    }

}