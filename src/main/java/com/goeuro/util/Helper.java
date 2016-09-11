/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goeuro.util;

import java.io.File;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 *
 * @author ahmad
 */
@Component
public class Helper {

    private static final Logger LOG = Logger.getLogger(Helper.class);

    public String sanitizeForCsv(String cellData) {
        LOG.info("Enter sanitizeForCsv method.");
        StringBuilder resultBuilder = new StringBuilder(cellData);

        // Look for doublequotes, escape as necessary.
        int lastIndex = 0;
        while (resultBuilder.indexOf("\"", lastIndex) >= 0) {
            int quoteIndex = resultBuilder.indexOf("\"", lastIndex);
            resultBuilder.replace(quoteIndex, quoteIndex + 1, "\"\"");
            lastIndex = quoteIndex + 2;
        }

        char firstChar = cellData.charAt(0);
        char lastChar = cellData.charAt(cellData.length() - 1);

        if (cellData.contains(",")
                || // Check for commas
                cellData.contains("\n")
                || // Check for line breaks
                Character.isWhitespace(firstChar)
                || // Check for leading whitespace.
                Character.isWhitespace(lastChar)) { // Check for trailing whitespace
            resultBuilder.insert(0, "\"").append("\""); // Wrap in doublequotes.
        }
        LOG.info("Finished sanitizeForCsv successfully.");
        return resultBuilder.toString();
    }

    public String getCurrentPAth() throws IOException {
        LOG.info("Enter getCurrentPAth method.");
        File file = new File("").getCanonicalFile();
        String path = file.getPath();
        LOG.info("Finished getCurrentPAth method successfully.");
        return path;
    }
}
