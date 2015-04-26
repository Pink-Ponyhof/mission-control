package de.qaware.theo.mc.store;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import de.qaware.theo.mc.ConfigurationNotAccessibleException;
import de.qaware.theo.mc.data.PropertiesReader;
import de.qaware.theo.mc.model.Metadata;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.*;


/**
 * The unit test class for {@link PropertiesStore}
 *
 * @author s.wittke
 */
@RunWith(MockitoJUnitRunner.class)
public class PropertiesStoreTest {

    @Mock
    private PropertiesReader propertiesReader;

    private List<String> configKeys;
    private Metadata metadata;
    private PropertiesStore store;

    private Map<String, String> expectedResult;


    @Before
    public void setUp() {
        configKeys = new ArrayList<>();
        configKeys.add("first.key");
        configKeys.add("second.key");
        metadata = new Metadata("config", "fileName", configKeys);
        store = new PropertiesStore(metadata, propertiesReader);

        expectedResult = new HashMap<>();
        expectedResult.put("first.key", "first.value");
        expectedResult.put("second.key", "second.value");
    }

    @Test
    public void testGetConfigValuesSuccessful() throws Exception {

        when(propertiesReader.read()).thenReturn(expectedResult);

        Map<String, String> result = store.getConfigValues();
        assertThat(result.size(), is(equalTo(2)));
        assertThat(result.get("first.key"), is(equalTo("first.value")));
        assertThat(result.get("second.key"), is(equalTo("second.value")));
    }

    @Test(expected = ConfigurationNotAccessibleException.class)
    public void testGetConfigValuesErrorInReader() throws Exception {
        when(propertiesReader.read()).thenThrow(ConfigurationNotAccessibleException.class);
        store.getConfigValues();
    }

    @Test
    public void testGetConfigValueSuccessful() throws Exception {
        when(propertiesReader.read()).thenReturn(expectedResult);

        String key = "first.key";
        String configValue = store.getConfigValue(key);
        assertThat(configValue, is(equalTo("first.value")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetConfigValueForNotAllowedKey() throws Exception{

        String key = "not.contained.key";
        store.getConfigValue(key);
    }

    @Test
    public void testGetConfigValueNotSet() throws Exception {
        configKeys.add("third.key");
        metadata = new Metadata("config", "fileName", configKeys);
        store = new PropertiesStore(metadata, propertiesReader);
        when(propertiesReader.read()).thenReturn(expectedResult);

        String key = "third.key";
        String configValue = store.getConfigValue(key);
        assertThat(configValue, isEmptyString());
    }

    @Test(expected = ConfigurationNotAccessibleException.class)
    public void testGetConfigValueErrorInReader() throws Exception {
        when(propertiesReader.read()).thenThrow(ConfigurationNotAccessibleException.class);
        store.getConfigValue("first.key");
    }


}