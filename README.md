# cucup-maven-plugin
This Maven Plugin will allow Cucumber parallel execution feasible

Add the following plugin to your project pom

 <plugin>
				<groupId>com.continuum.cucumber</groupId>
				<artifactId>cucup-maven-plugin</artifactId>
				<version>0.0.1-SNAPSHOT</version>
				<configuration>
					<featureFilePath>src/test/resources/features</featureFilePath>
					<runnerTemplatePath>src/test/resources/template/TestRunner.txt</runnerTemplatePath>
					<tagsToExecute>@e2e</tagsToExecute>
					<pathToPlaceRunnerFiles>src/main/test/com/continnum/cucumber/runner</pathToPlaceRunnerFiles>
          <totalThreads>20</totalThreads>
          <testngForExecutionName></testngForExecutionName>
				</configuration>
				<executions>
					<execution>
						<phase>process-sources</phase>
						<goals>
							<goal>cucumber-parallel</goal>
						</goals>
					</execution>
				</executions>
</plugin>

Once this added when you try to run your maven test, Runner Files will be created for each file in the path specified and also this plugin will create testng
in the project root directory based on the name of the file passed (by default testng_parallel.xml will be created)

By default this plugin will execute in process-sources package but can be customised to run in defined phase.

Right now this plugin supports only feature file parallel execution. Scenario level execution is in development.

Each feature file runner will generate a indvidual report file in json format. To Merge all the reports use masterthought plugin at verify or package
phase of maven to get the individual report of execution
