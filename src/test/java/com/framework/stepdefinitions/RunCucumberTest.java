/**
 * 
 */
package com.framework.stepdefinitions;

/**
 * @author surendrane
 *
 */
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = "com.framework.stepdefinitions", plugin = {"pretty" , "json:target/report.json","de.monochromata.cucumber.report.PrettyReports:target/cucumber"}, strict = true, monochrome = true)
public class RunCucumberTest {
}