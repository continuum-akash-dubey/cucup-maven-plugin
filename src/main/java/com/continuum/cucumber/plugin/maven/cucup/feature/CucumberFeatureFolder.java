package com.continuum.cucumber.plugin.maven.cucup.feature;

import java.nio.file.Paths;
import java.util.List;

public class CucumberFeatureFolder {

    private final String folderName;
    private final String folderBasePath;

    private final List<CucumberFeature> features;

    public CucumberFeatureFolder(String folderName, String folderBasePath, List<CucumberFeature> featuresInFolder) {
        this.folderName = folderName.equals("/") ? "All" : folderName.trim().replace('.', '_').replace('/', '_');
        String folder = folderName.equals("All") ? folderBasePath : Paths.get(folderBasePath, folderName).toString();
        this.folderBasePath = folder.replace('\\', '/');
        features = featuresInFolder;
    }

    public String getFolderName() {
        return folderName;
    }

    public String getFolderBasePath() {
        return folderBasePath;
    }

    public List<CucumberFeature> getCucumberFeatures() {
        return features;
    }

}