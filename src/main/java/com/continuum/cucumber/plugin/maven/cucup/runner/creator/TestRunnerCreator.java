package com.continuum.cucumber.plugin.maven.cucup.runner.creator;

import java.io.File;
import java.util.List;

import com.continuum.cucumber.plugin.maven.cucup.feature.CucumberFeature;

public class TestRunnerCreator {

	private TestRunnerCreator() {
		throw new AssertionError("Cannot create TestRunnerCreator instance");
	}

	public static void createRunnersForFeatureFiles(TestRunnerBuilder builder) {
		// TODO
	}

	public static class TestRunnerBuilder {

		private File projectBaseDir;
		private List<CucumberFeature> cucumberfeatures;
		private String runnerTemplatePath;
		private String runnerLocationToStore;
		private String stepDefinitionGlue;

		public void setProjectBaseDir(File projectBaseDir) {
			this.projectBaseDir = projectBaseDir;
		}

		public void setCucumberFeatures(List<CucumberFeature> cucumberfeatures) {
			this.cucumberfeatures = cucumberfeatures;
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

		public File getProjectBaseDir() {
			return projectBaseDir;
		}

		public List<CucumberFeature> getCucumberfeatures() {
			return cucumberfeatures;
		}

		public String getRunnerTemplatePath() {
			return runnerTemplatePath;
		}

		public String getRunnerLocationToStore() {
			return runnerLocationToStore;
		}

		public String getStepDefinitionGlue() {
			return stepDefinitionGlue;
		}

	}

}