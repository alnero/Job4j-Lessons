package alnero;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotEquals;

public class PrepareStatementExampleTest {
    /** Common prepare statement object for test. */
    private PrepareStatementExample prepareStatementExample;
    /** DB connection properties. */
    private Properties properties;
    /** Table editor. */
    private TableEditor tableEditor;
    /** Test table name. */
    private static final String TEST_TABLE_NAME = "cities";

    /**
     * Load properties, create table in DB, initialize common object for tests.
     */
    @Before
    public void beforeTest() throws Exception {
        try (InputStream is = TableEditorTest.class.getClassLoader().getResourceAsStream("app.properties")) {
            this.properties = new Properties();
            this.properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.tableEditor = new TableEditor(this.properties);
        this.tableEditor.createTable(TEST_TABLE_NAME);
        this.tableEditor.addColumn(TEST_TABLE_NAME, "id", "serial primary key");
        this.tableEditor.addColumn(TEST_TABLE_NAME, "name", "text");
        this.tableEditor.addColumn(TEST_TABLE_NAME, "population", "int");
        this.prepareStatementExample = new PrepareStatementExample();
    }

    /**
     * Drop test table after each test.
     */
    @After
    public void dropTableFromDBAfterEachTest() {
        this.tableEditor.dropTable(TEST_TABLE_NAME);
    }

    @Test
    public void whenInsertCitiesInDBThenCitiesExistInDB() {
        List<City> cityList = Arrays.asList(
                new City(1, "CityOne", 1),
                new City(2, "CityTwo", 2),
                new City(3, "CityThree", 3)
        );
        for (City city : cityList) {
            this.prepareStatementExample.insert(city);
        }
        List<City> result = this.prepareStatementExample.findAll();
        assertThat(result, containsInAnyOrder(cityList.toArray()));
    }

    @Test
    public void whenUpdateCityInDBThenTrueReturnedAndUpdatedCityExistsInDB() {
        City cityToSaveInDB = new City(1, "CityOne", 1);
        this.prepareStatementExample.insert(cityToSaveInDB);
        City updatedCity = new City(1, "NewCityOne", 9);
        boolean updateResult = this.prepareStatementExample.update(updatedCity);
        List<City> cityListFromDb = this.prepareStatementExample.findAll();
        assertTrue(updateResult);
        assertEquals(cityListFromDb.get(0), updatedCity);
    }

    @Test
    public void whenDeleteCityFromDBThenCityDoesNotExistInDB() {
        City cityToSaveInDB = new City(1, "CityOne", 1);
        this.prepareStatementExample.insert(cityToSaveInDB);
        boolean deleteResult = this.prepareStatementExample.delete(cityToSaveInDB.getId());
        List<City> cityListFromDb = this.prepareStatementExample.findAll();
        assertTrue(deleteResult);
        assertEquals(cityListFromDb.size(), 0);
    }

    @Test
    public void whenInsertCityReturningIdInDBThenCityExistInDBAndCorrectIdReturned() {
        City cityToSaveInDB = new City(-99, "CityOne", 1);
        int initialCityId = cityToSaveInDB.getId();
        this.prepareStatementExample.insertReturningId(cityToSaveInDB);
        assertNotEquals(cityToSaveInDB.getId(), initialCityId);
    }
}
