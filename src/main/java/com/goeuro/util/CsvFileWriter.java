package com.goeuro.util;

import com.goeuro.exception.CreationError;
import com.goeuro.exception.ResourceError;
import com.goeuro.model.Country;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ahmad
 *
 */
@Service
public class CsvFileWriter {

    private static final Logger LOG = Logger.getLogger(CsvFileWriter.class);

    @Autowired
    Helper helper;
    //Delimiter used in CSV file
    private static final String NEW_LINE_SEPARATOR = "\n";

    //CSV file header
    private static final Object[] FILE_HEADER = {"_id", "name", "type", "latitude", "longitude"};

    public void writeCsvFile(Country[] countries, String filePath) {
        LOG.info("Enter writeCsvFile Method.");

        FileWriter fileWriter = null;

        CSVPrinter csvFilePrinter = null;

        //Create the CSVFormat object with "\n" as a record delimiter
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);

        try {

            //initialize FileWriter object
            fileWriter = new FileWriter(filePath);

            //initialize CSVPrinter object 
            csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);

            //Create CSV file header
            csvFilePrinter.printRecord(FILE_HEADER);

            //Write a new country object list to the CSV file
            for (Country country : countries) {
                List countryRecord = new ArrayList();
                countryRecord.add(helper.sanitizeForCsv(String.valueOf(country.getId())));
                countryRecord.add(helper.sanitizeForCsv(country.getName()));
                countryRecord.add(helper.sanitizeForCsv(country.getType()));
                countryRecord.add(helper.sanitizeForCsv(country.getGeoPosition().getLatitude()));
                countryRecord.add(helper.sanitizeForCsv(country.getGeoPosition().getLongitude()));
                csvFilePrinter.printRecord(countryRecord);
            }
            LOG.info("Finished writeCsvFile Successfully.");

        } catch (Exception ex) {
            LOG.error("Error while writeCsvFile due to: " + ex);
            throw new CreationError(ex);
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.flush();
                    fileWriter.close();
                }
                if (csvFilePrinter != null) {
                    csvFilePrinter.close();
                }
            } catch (IOException ex) {
                LOG.error("Error while writeCsvFile due to: " + ex);
                throw new ResourceError(ex);
            }
        }
    }
}
