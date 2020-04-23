package com.continuum.cucumber.plugin.maven.cucup.feature.reader;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import com.continuum.cucumber.plugin.maven.cucup.feature.CucumberFeature;

public class CucumberFeatureFileReader {

    private CucumberFeatureFileReader() {
        throw new AssertionError("Cant initialize CucumberFeatureFileReader class");
    }

    private static final List<CucumberFeature> CUCUMBER_FEATURE_FILES = new ArrayList<>();

    /**
     * Get All the Feature Files present in the Path.
     *
     * @param baseDir
     *            Project Base Directory
     * @param path
     *            - Feature file location inside baseDir.
     * @return List of all Cucumber Feature Files.
     * @throws IOException
     *             - In case of IO error in reading feature files.
     */
    public static List<CucumberFeature> getAllFeatureFilesInPath(File baseDir, String path) throws IOException {
        Path featureFilePath = Paths.get(baseDir.toString(), path);
        Files.walkFileTree(featureFilePath, new FeatureFileVisitor(path));
        return CUCUMBER_FEATURE_FILES;
    }

	private static class FeatureFileVisitor extends SimpleFileVisitor<Path> {

		private String relativeFeatureFilePath;

		private FeatureFileVisitor(String relativeFeatureFilePath) {
			this.relativeFeatureFilePath = relativeFeatureFilePath;
		}

		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			if (file.toFile().getName().contains(".feature")) {
				CUCUMBER_FEATURE_FILES.add(new CucumberFeature(file, relativeFeatureFilePath));
			}
			return super.visitFile(file, attrs);
		}

	}

}
