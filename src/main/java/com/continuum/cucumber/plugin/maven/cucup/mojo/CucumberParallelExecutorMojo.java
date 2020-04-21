package com.continuum.cucumber.plugin.maven.cucup.mojo;

import java.io.IOException;
import java.util.List;

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

import com.continuum.cucumber.plugin.maven.cucup.feature.CucumberFeature;
import com.continuum.cucumber.plugin.maven.cucup.feature.reader.CucumberFeatureFileReader;

/**
 * Goal which executes Cucumber Files in Parallel.
 *
 * @goal cucumber-parallel
 * 
 * @phase process-sources
 */
@Mojo(name = "cucumber-parallel", defaultPhase = LifecyclePhase.PROCESS_SOURCES)
public class CucumberParallelExecutorMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;

    @Parameter(required = true)
    private String featureFilePath;

    @Parameter(required = true)
    private String runnerTemplatePath;

    @Parameter(required = true)
    private String tagsToExecute;

    @Parameter(required = true)
    private String pathToPlaceRunnerFiles;

    @Parameter(required = false)
    private int totalThreads;

    @Parameter(required = false, defaultValue = "testng_parallel.xml")
    private String testngForExecutionName;

    public void execute() throws MojoExecutionException {
        try {
            List<CucumberFeature> cucumberFeatures = CucumberFeatureFileReader.getAllFeatureFilesInPath(project.getBasedir(), featureFilePath);
        } catch (IOException e) {
            throw new MojoExecutionException("Unable to read the Feature files in the Project " + project.getName());
        }
    }
}