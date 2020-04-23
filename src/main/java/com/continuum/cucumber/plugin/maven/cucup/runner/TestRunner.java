package com.continuum.cucumber.plugin.maven.cucup.runner;

import com.continuum.cucumber.plugin.maven.cucup.feature.CucumberFeature;

public class TestRunner {

	private final CucumberFeature cucumberFeature;

	public TestRunner(CucumberFeature feature) {
		cucumberFeature = feature;
	}

	public CucumberFeature getCucumberFeature() {
		return cucumberFeature;
	}

}