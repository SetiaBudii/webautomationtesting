// package io.cucumber.swaglabs.runners;

// import org.junit.platform.suite.api.ConfigurationParameter;
// import org.junit.platform.suite.api.ConfigurationParameters;
// import org.junit.platform.suite.api.IncludeEngines;
// import org.junit.platform.suite.api.SelectPackages;
// import org.junit.platform.suite.api.Suite;

// import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;
// import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

// @Suite
// @IncludeEngines("cucumber")
// @SelectPackages("io.cucumber.swaglabs")
// @ConfigurationParameters(value = {
//   // @ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty"),
//   @ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "html:target/a6-reports"),
//   @ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "io.cucumber.swaglabs")
// })
// public class RunCucumberTest {
// }

package io.cucumber.swaglabs.runners;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.ConfigurationParameters;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectPackages("io.cucumber.swaglabs")
@ConfigurationParameters(value = {
  @ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "html:result/a6-reports"),
  @ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "json:result/cucumber-report.json"),
  @ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "io.cucumber.swaglabs")
})
public class RunCucumberTest {
}

