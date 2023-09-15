package com.methodsecurity;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Run Multiple Test classes")
@SelectClasses({MethodSecurityApplicationTests.class, PrivateClassTest.class, ProductTest.class})
public class SuiteTestExample {
}
