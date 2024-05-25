package runner;

import lombok.extern.slf4j.Slf4j;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import testexecution.configuration.GlobalConfiguration;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CustomSuiteRunner {

    private static String getPlatform() {
        return GlobalConfiguration.FRAMEWORK_CONFIG.platform();
    }

    private XmlSuite getSuite() {
        XmlSuite suite = new XmlSuite();
        /*
        Listners
         */
        suite.addListener("listeners.ConfigListener");
        /*
        Execution mode
         */
        suite.setParallel(XmlSuite.ParallelMode.TESTS);
        suite.setDataProviderThreadCount(Integer.parseInt(GlobalConfiguration.FRAMEWORK_CONFIG.threadCount()));
        suite.setThreadCount(Integer.parseInt(GlobalConfiguration.FRAMEWORK_CONFIG.threadCount()));
        suite.setVerbose(1);
        suite.setName("Cucumber Suite runner");
        XmlTest xmlTest = new XmlTest(suite);
        xmlTest.setName("Test running on:" + getPlatform().toUpperCase());
        xmlTest.addParameter("Platform", getPlatform().toUpperCase());
        xmlTest.setParallel(XmlSuite.ParallelMode.TESTS);
        List<XmlClass> myClasses = new ArrayList<>();
        myClasses.add(new XmlClass("runner.CucumberTestNGTests"));
        xmlTest.setXmlClasses(myClasses);
        return suite;
    }

    public static void main(String[] arg) {
        //ConfigurationFactory.setConfigurationFactory(new CustomizedConfigFactory());
        List<XmlSuite> suites = new ArrayList<>();
        CustomSuiteRunner suiteRunner = new CustomSuiteRunner();
        suites.add(suiteRunner.getSuite());
        log.info("\n<------ TestNG Xml Generated is ------>\n" + suites.get(0).toXml());
        TestNG tng = new TestNG();
        tng.setXmlSuites(suites);
        tng.run();
    }
}
