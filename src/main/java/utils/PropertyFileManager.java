package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileManager {

	private static Properties props;
	private static FileInputStream fis;

	public static Properties getProperty(String env) throws IOException {
		props = new Properties();// first object create
		if (env.equalsIgnoreCase("qa")) {
			fis = new FileInputStream("./src/main/resources/propertyFiles/qa.properties");
		} else if (env.equalsIgnoreCase("uat")) {
			fis = new FileInputStream("./src/main/resources/propertyFiles/uat.properties");
		} else if (env.equalsIgnoreCase("prod")) {
			// TODO
		}
		props.load(fis);
		return props;

	}

}
