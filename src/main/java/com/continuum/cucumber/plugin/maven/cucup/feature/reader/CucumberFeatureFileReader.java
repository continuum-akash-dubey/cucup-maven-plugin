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
import com.continuum.cucumber.plugin.maven.cucup.feature.CucumberFeatureFolder;
import com.continuum.cucumber.plugin.maven.cucup.util.Constants;

public class CucumberFeatureFileReader {

    private CucumberFeatureFileReader() {
        throw new AssertionError("Cant initialize CucumberFeatureFileReader class");
    }

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
    public static List<CucumberFeatureFolder> getAllFeatureFilesInPath(File baseDir, String featureFilepath,
            List<String> featureFolders) throws IOException {
        List<CucumberFeatureFolder> featureFileFolders = new ArrayList<>();
        for (String featureFolder : featureFolders) {
            Path folderFeaturePath = Paths.get(baseDir.toString(), featureFilepath,featureFolder);
            FeatureFileVisitor fileVisitor = new FeatureFileVisitor();
            Files.walkFileTree(folderFeaturePath, fileVisitor);
            featureFileFolders.add(new CucumberFeatureFolder(featureFolder, featureFilepath,
                    fileVisitor.getCucumberFeaturesforPath()));
        }

        return featureFileFolders;
    }

    private static class FeatureFileVisitor extends SimpleFileVisitor<Path> {

        private final List<CucumberFeature> cucumberFeatures;

        private FeatureFileVisitor() {
            cucumberFeatures = new ArrayList<>();
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if (file.toFile().getName().contains(Constants.FEATURE_FILE_EXTENSION)) {
                cucumberFeatures.add(new CucumberFeature(file));
            }
            return super.visitFile(file, attrs);
        }

        public List<CucumberFeature> getCucumberFeaturesforPath() {
            return cucumberFeatures;
        }

    }

}
