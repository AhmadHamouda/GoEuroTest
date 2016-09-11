/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goeuro.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author ahmad
 */
@Service
public class CvsFileReader {

    private static final Logger LOG = Logger.getLogger(CvsFileReader.class);

    public ArrayList<String> readCvsFile(String path) {
        LOG.info("Enter readCvsFile method.");
        String line;
        ArrayList<String> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while ((line = br.readLine()) != null) {

                data.add(line);
            }
            LOG.info("Finish readCvsFile method successfully.");
        } catch (IOException ex) {
            LOG.error("Finished readCvsFile method with error due to: " + ex);
        }
        return data;

    }
}
