package com.continuum.cucumber.plugin.maven.cucup.feature;

import java.nio.file.Path;

public class CucumberFeature {

    private final String featureFileName;
    private final Path featureFilePath;
    private final String featureName;
    private final String featureRelativePath;

    public CucumberFeature(Path filePath, String featureRelativePath) {
        this.featureFilePath = filePath;
        featureFileName = featureFilePath.toFile().getName();
        featureName = featureFileName.substring(0, featureFileName.indexOf('.'));
        this.featureRelativePath = featureRelativePath;
    }

    public String getFeatureFileName() {
        return this.featureFileName;
    }

    public Path getFeatureFilePath() {
        return this.featureFilePath;
    }

    public String getFeatureName() {
    	return featureName;
    }

    public String getFeatureFileRelativePath() {
    	return featureRelativePath;
    }

}