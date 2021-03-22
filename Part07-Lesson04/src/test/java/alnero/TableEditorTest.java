package alnero;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class TableEditorTest {
    /** Test table name. */
    private static final String TEST_TABLE_NAME = "table_editor_test_table";
    /** Test column name. */
    private static final String TEST_COLUMN_NAME = "test_column";
    /** Test new column name. */
    private static final String NEW_TEST_COLUMN_NAME = "new_test_column";
    /** Test column type. */
    private static final String TEST_COLUMN_TYPE = "VARCHAR";
    /** DB connection properties. */
    private Properties properties;
    /** Common table editor for tests. */
    private TableEditor tableEditor;

    /**
     * Load properties and initialize common table editor object.
     */
    @Before
    public void beforeTest() {
        try (InputStream is = TableEditorTest.class.getClassLoader().getResourceAsStream("app.properties")) {
            this.properties = new Properties();
            this.properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.tableEditor = new TableEditor(this.properties);
    }

    /**
     * Drop test table after each test.
     */
    @After
    public void dropTableFromDBAfterEachTest() {
        this.tableEditor.dropTable(TEST_TABLE_NAME);
    }

    @Test
    public void whenCreateTableThenTableExistsInDB() throws SQLException {
        this.tableEditor.createTable(TEST_TABLE_NAME);
        DatabaseMetaData metaData = this.tableEditor.getDatabaseMetaData();
        ResultSet tables = metaData.getTables(null, null, TEST_TABLE_NAME, new String[] {"TABLE"});
        while (tables.next()) {
            assertEquals(TEST_TABLE_NAME, tables.getString("TABLE_NAME"));
        }
    }

    @Test
    public void whenDropTableThenTableDoesNotExistInDB() throws SQLException {
        this.tableEditor.createTable(TEST_TABLE_NAME);
        this.tableEditor.dropTable(TEST_TABLE_NAME);
        DatabaseMetaData metaData = this.tableEditor.getDatabaseMetaData();
        ResultSet tables = metaData.getTables(null, null, TEST_TABLE_NAME, new String[] {"TABLE"});
        if (tables.next()) {
            fail();
        } else {
            assertTrue(true);
        }
    }

    @Test
    public void whenAddColumnToTableThenColumnExistsInTableInDB() throws SQLException {
        this.tableEditor.createTable(TEST_TABLE_NAME);
        this.tableEditor.addColumn(TEST_TABLE_NAME, TEST_COLUMN_NAME, TEST_COLUMN_TYPE);
        assertTrue(this.tableEditor.getScheme(TEST_TABLE_NAME).contains(TEST_COLUMN_NAME));
        assertTrue(this.tableEditor.getScheme(TEST_TABLE_NAME).contains(TEST_COLUMN_TYPE.toLowerCase()));
    }

    @Test
    public void whenDropColumnFromTableThenColumnDoesNotExistInTableInDB() throws SQLException {
        this.tableEditor.createTable(TEST_TABLE_NAME);
        this.tableEditor.addColumn(TEST_TABLE_NAME, TEST_COLUMN_NAME, TEST_COLUMN_TYPE);
        this.tableEditor.dropColumn(TEST_TABLE_NAME, TEST_COLUMN_NAME);
        assertFalse(this.tableEditor.getScheme(TEST_TABLE_NAME).contains(TEST_COLUMN_NAME));
        assertFalse(this.tableEditor.getScheme(TEST_TABLE_NAME).contains(TEST_COLUMN_TYPE.toLowerCase()));
    }

    @Test
    public void whenRenameColumnInTableThenRenamedColumnExistsInTableInDB() throws SQLException {
        this.tableEditor.createTable(TEST_TABLE_NAME);
        this.tableEditor.addColumn(TEST_TABLE_NAME, TEST_COLUMN_NAME, TEST_COLUMN_TYPE);
        this.tableEditor.renameColumn(TEST_TABLE_NAME, TEST_COLUMN_NAME, NEW_TEST_COLUMN_NAME);
        assertTrue(this.tableEditor.getScheme(TEST_TABLE_NAME).contains(NEW_TEST_COLUMN_NAME));
        assertTrue(this.tableEditor.getScheme(TEST_TABLE_NAME).contains(TEST_COLUMN_TYPE.toLowerCase()));
    }
}
