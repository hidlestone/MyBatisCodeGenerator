package com.payn.generator;

import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * MyBatis 代码生成器
 * @author: payn
 * @date: 2020/9/6 0:21
 */
public class MyBatisGenerator {

	public void generator() throws Exception{
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		File configFile = new File("src/main/resources/MyBatisGeneratorConfig.xml");
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		org.mybatis.generator.api.MyBatisGenerator myBatisGenerator = new org.mybatis.generator.api.MyBatisGenerator(config,
				callback, warnings);
		myBatisGenerator.generate(null);
	}

	public static void main(String[] args) throws Exception {
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator();
		myBatisGenerator.generator();
	}
}
