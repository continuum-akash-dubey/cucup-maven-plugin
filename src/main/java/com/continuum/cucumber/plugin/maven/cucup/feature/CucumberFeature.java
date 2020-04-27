package com.continuum.cucumber.plugin.maven.cucup.feature;

import java.nio.file.Path;

public class CucumberFeature {

	private final String featureFileName;
	private final Path featureFilePath;
	private final String featureName;

	public CucumberFeature(Path filePath) {
		this.featureFilePath = filePath;
		featureFileName = featureFilePath.toFile().getName();
		featureName = featureFileName.substring(0, featureFileName.indexOf('.'));
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

	@Override
	public String toString() {
		return "Featue file is " + featureFileName;
	}
}