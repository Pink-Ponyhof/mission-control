package de.qaware.theo.mc;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * @author andreas.janning
 */
public class ConfigReader {

    private Properties properties;

    public ConfigReader(String file) {
        properties = new Properties();
        Path path = Paths.get(file);
        InputStream is = Files.newInputStream(path);
        new InputStreamReader(is, Charset.forName())
    }
}
