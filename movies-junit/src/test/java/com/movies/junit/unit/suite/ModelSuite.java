package com.movies.junit.unit.suite;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.ExcludeTags;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectPackages("com.movies.junit.unit.model")
@IncludeTags("primary|nullpointer")
@ExcludeTags("illegalargument")
public class ModelSuite {
}
