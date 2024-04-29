package listeners;

import org.testng.IAlterSuiteListener;
import org.testng.xml.XmlSuite;
import testexecution.configuration.GlobalConfiguration;

import java.util.List;

public class ConfigListener implements IAlterSuiteListener {

    @Override
    public void alter(List<XmlSuite> suites) {
        int thread = Integer.parseInt(GlobalConfiguration.FRAMEWORK_CONFIG.threadCount());
        XmlSuite suite = suites.get(0);
        suite.setDataProviderThreadCount(thread);
        suite.setThreadCount(thread);
    }

}
