package com.continuum.cucumber.plugin.maven.cucup.testng;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.continuum.cucumber.plugin.maven.cucup.runner.TestRunner;

public class TestNGXMLCreator {

    private TestNGXMLCreator() {
        throw new AssertionError("Cannot create TestNGXMLCreator instance");
    }

    public static void createTestNGXMLFileForRunners(File baseDirectoryForXml, String testNGFileName,
            List<TestRunner> testRunners) throws ParserConfigurationException, TransformerException {

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.newDocument();

        Element suiteNode = doc.createElement("suite");

        Attr suiteName = doc.createAttribute("name");
        suiteName.setValue("Parallel-Suite");
        Attr parallel = doc.createAttribute("parallel");
        parallel.setValue("tests");
        suiteNode.setAttributeNode(suiteName);
        suiteNode.setAttributeNode(parallel);

        doc.appendChild(suiteNode);

        for (TestRunner testRunner : testRunners) {

            Element testNode = doc.createElement("test");
            Attr testName = doc.createAttribute("name");
            testName.setValue(testRunner.getCucumberRunnerClassName());
            testNode.setAttributeNode(testName);

            Element classesNode = doc.createElement("classes");

            Element classNode = doc.createElement("class");
            Attr className = doc.createAttribute("name");
            className.setValue(testRunner.getPackageDefinition() + "." + testRunner.getCucumberRunnerClassName());
            classNode.setAttributeNode(className);

            classesNode.appendChild(classNode);
            testNode.appendChild(classesNode);

            suiteNode.appendChild(testNode);

        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "http://testng.org/testng-1.0.dtd");
        DOMSource source = new DOMSource(doc);

        File testNGLocationToStore = new File(baseDirectoryForXml, testNGFileName);
        StreamResult result = new StreamResult(testNGLocationToStore);
        transformer.transform(source, result);

    }

}