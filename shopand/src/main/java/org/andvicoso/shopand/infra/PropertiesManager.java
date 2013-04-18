package org.andvicoso.shopand.infra;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesManager {

	public static Properties read(String propsfilename) {
		Properties properties = new Properties();
		InputStream in = PropertiesManager.class.getResourceAsStream("/"
				+ propsfilename + ".properties");

		try {
			properties.load(in);
			in.close();
		} catch (IOException e) {
		}

		return properties;
	}

}
