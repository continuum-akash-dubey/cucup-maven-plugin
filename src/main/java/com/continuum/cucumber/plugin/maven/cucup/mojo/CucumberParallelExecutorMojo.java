package com.continuum.cucumber.plugin.maven.cucup.mojo;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import com.continuum.cucumber.plugin.maven.cucup.feature.CucumberFeatureFolder;
import com.continuum.cucumber.plugin.maven.cucup.feature.reader.CucumberFeatureFileReader;
import com.continuum.cucumber.plugin.maven.cucup.runner.TestRunner;
import com.continuum.cucumber.plugin.maven.cucup.runner.creator.TestRunnerCreator;
import com.continuum.cucumber.plugin.maven.cucup.testng.TestNGXMLCreator;

/**
 * Goal which executes Cucumber Files in Parallel.
 *
 * @goal cucumber-parallel
 * 
 * @phase process-sources
 */
@Mojo(name = "parallel", defaultPhase = LifecyclePhase.PROCESS_SOURCES)
public class CucumberParallelExecutorMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;

    @Parameter(required = true)
    private String featureFilePath;

    @Parameter(required = false, defaultValue = "/")
    private String featureFolders;

    @Parameter(required = true)
    private String runnerTemplatePath;

    @Parameter(required = true)
    private String stepDefinitionPackage;

    @Parameter(required = true)
    private String tagsToExecute;

    @Parameter(required = true)
    private String pathToPlaceRunnerFiles;

    @Parameter(required = false, defaultValue = "18")
    private int maxThreadToSpawn;

    @Parameter(required = false, defaultValue = "testng_parallel.xml")
    private String testngForExecutionName;

    @Parameter(required = false, defaultValue = "")
    private String featureFilesToIgnore;

    public void execute() throws MojoExecutionException {
        List<CucumberFeatureFolder> cucumberFeatureFileFolders = null;
        try {
            cucumberFeatureFileFolders = CucumberFeatureFileReader.getAllFeatureFilesInPath(project.getBasedir(),
                    featureFilePath, Arrays.asList(featureFolders.split(",")));
        } catch (IOException e) {
            getLog().error("Error occuered while processing Feature Files " + e);
            throw new MojoExecutionException(
                    "Unable to read the Feature files in the Project " + project.getName() + " " + e);
        }

        TestRunnerCreator.TestRunnerBuilder runnerBuilder = new TestRunnerCreator.TestRunnerBuilder();
        runnerBuilder.setCucumberFeatureFolders(cucumberFeatureFileFolders);
        runnerBuilder.setProjectBaseDir(project.getBasedir());
        runnerBuilder.setRunnerTemplatePath(runnerTemplatePath);
        runnerBuilder.setTestRunnerLocationtoStore(pathToPlaceRunnerFiles);
        runnerBuilder.setStepDefinitionsPackage(stepDefinitionPackage);
        runnerBuilder.setTagsToExecute(tagsToExecute);

        List<TestRunner> runners = null;
        try {
            runners = TestRunnerCreator.createRunnersForFeatureFiles(runnerBuilder);
            getLog().info("TestRunners created in the Project");
        } catch (IOException e) {
            getLog().error("Error occuered while preparing TestRunner Files " + e);
            throw new MojoExecutionException(
                    "Unable to create the Test Runner in the project " + project.getName() + " " + e);
        }

        try {
            TestNGXMLCreator.createTestNGXMLFileForRunners(project.getBasedir(), testngForExecutionName, runners);
            getLog().info("Created TestNG xml file " + testngForExecutionName);
        } catch (ParserConfigurationException | TransformerException e) {
            getLog().error("Error occuered while preparing " + testngForExecutionName + " File " + e);
            throw new MojoExecutionException("Unable to create the TestNG file " + testngForExecutionName
                    + " in the project " + project.getName() + " " + e);
        }

    }
}