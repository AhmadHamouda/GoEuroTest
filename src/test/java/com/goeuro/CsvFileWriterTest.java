/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goeuro;

import com.goeuro.model.Country;
import com.goeuro.model.GeoPosition;
import com.goeuro.util.CsvFileWriter;
import com.goeuro.util.CvsFileReader;
import com.goeuro.util.LocationApi;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.springframework.test.util.AssertionErrors.fail;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;

/**
 *
 * @author ahmad
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = com.goeuro.Application.class)
@ContextConfiguration(classes = Application.class,
        initializers = ConfigFileApplicationContextInitializer.class)
public class CsvFileWriterTest {

    private static final Logger LOG = Logger.getLogger(CsvFileWriterTest.class);
    @Autowired
    @InjectMocks
    CsvFileWriter csvFileWriter;
    @Mock
    LocationApi licationApi;
    @Autowired
    CvsFileReader cvsFileReader;
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    private File createdFile;
    private Country[] countries;

    @Before
    public void setup() throws IOException {
        // Process mock annotations
        createdFile = folder.newFile("countries.cvs");
        countries = new Country[1];
        countries[0] = new Country(377078, "Potsda1m", "location", new GeoPosition("52.39886", "13.06566"));
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testWriteCsvFile() {
        try {
            LOG.info("Enter testWriteCsvFile method");
            when(licationApi.getLocations(any())).thenReturn(countries);
            csvFileWriter.writeCsvFile(countries, createdFile.getPath());
            assertTrue(createdFile.exists());
            assertTrue(createdFile.length() > 0);
            ArrayList<String> readedData = cvsFileReader.readCvsFile(createdFile.getPath());
            assertTrue(readedData.size() == 2);
            assertTrue(readedData.get(0).equals("\"_id\",name,type,latitude,longitude"));
            assertTrue(readedData.get(1).equals(countries[0].toString()));
            LOG.info("Finished testWriteCsvFile method successfully");
        } catch (Exception ex) {
            LOG.error("Error in testWriteCsvFile due to: " + ex);
            fail(ex.getMessage());
        }
    }
}
