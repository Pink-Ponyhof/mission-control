package de.qaware.theo.mc.data;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

import de.qaware.theo.mc.model.Metadata;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * The unit test class for {@link PropertiesFileOperator}
 *
 * @author s.wittke
 */

public class PropertiesFileOperatorTest {

    private List<String> containedKeys;
    private String configFilePath;

    @Before
    public void setUp() throws Exception {
        containedKeys = new ArrayList<>();
        containedKeys.add("first.value");
        containedKeys.add("second.value");
        containedKeys.add("third.value");
        containedKeys.add("empty.value");
        containedKeys.add("fifth.value");

        URL resource = getClass().getResource("/test.properties");
        configFilePath = resource.getPath();




    }

    @Test
    public void testReadAllPropertiesExistingInFile() throws Exception {
        Metadata metadata = new Metadata("testConfig", configFilePath, containedKeys);
        PropertiesFileOperator reader = new PropertiesFileOperator(metadata);

        Map<String, String> properties = reader.read();
        assertThat(properties.size(), is(equalTo(5)));

        assertThat(properties.get("first.value"), is(equalTo("Erster Wert")));
        assertThat(properties.get("second.value"), is(equalTo("Zweiter Wert")));
        assertThat(properties.get("third.value"), is(equalTo("Dritter Wert")));
        assertThat(properties.get("empty.value"), is(equalTo("")));
        assertThat(properties.get("fifth.value"), is(equalTo("Fünfter Wert")));
    }

    @Test
    public void testReadOnlyPropertiesInKeyList() throws Exception {
        Metadata metadata = new Metadata("testConfig", configFilePath, containedKeys);

        containedKeys.remove("first.value");
        containedKeys.remove("second.value");
        containedKeys.remove("empty.value");
        containedKeys.add("key.not.in.file");

        PropertiesFileOperator reader = new PropertiesFileOperator(metadata);

        Map<String, String> properties = reader.read();
        assertThat(properties.size(), is(equalTo(2)));

        assertThat(properties.get("third.value"), is(equalTo("Dritter Wert")));
        assertThat(properties.get("fifth.value"), is(equalTo("Fünfter Wert")));
    }

    @Test
    public void testFileNotFound() throws Exception {
        TemporaryFolder folder = new TemporaryFolder();
        folder.create();
        File propertiesFolder = folder.newFolder();

        String path = propertiesFolder.getPath();
        path = path + "/newFile.properties";

        Metadata metadata = new Metadata("testConfig", path, containedKeys);

        PropertiesFileOperator reader = new PropertiesFileOperator(metadata);
        Map<String, String> properties = reader.read();

        File file = new File(path);
        assertThat(file.exists(), is(true));

        assertThat(properties.size(), is(equalTo(5)));

        assertThat(properties.containsKey("first.value"), is(true));
        assertThat(properties.containsKey("second.value"), is(true));
        assertThat(properties.containsKey("third.value"), is(true));
        assertThat(properties.containsKey("empty.value"), is(true));
        assertThat(properties.containsKey("fifth.value"), is(true));

        for (String value : properties.values()) {
            assertThat(value, isEmptyString());
        }
    }

}