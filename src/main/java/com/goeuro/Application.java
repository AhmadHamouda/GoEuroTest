/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goeuro;

import com.goeuro.exception.NotFoundParamater;
import com.goeuro.util.CsvFileWriter;
import com.goeuro.model.Country;
import com.goeuro.util.Helper;
import com.goeuro.util.LocationApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author ahmad
 */
@SpringBootApplication
public class Application {

    @Autowired
    LocationApi locationAPi;
    @Autowired
    CsvFileWriter csvFileWriter;
    @Autowired
    Helper helper;

    private static String CITYNAME;

    public static void main(String args[]) throws Exception {
//        if (args.length == 0 || args[0].equals("")) {
//            throw new NotFoundParamater();
//        } else {
//            CITYNAME = args[0];
            CITYNAME = "Berlin";
//        }
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {
            Country[] countries = locationAPi.getLocations("http://api.goeuro.com/api/v2/position/suggest/en/" + CITYNAME);
            if (countries.length > 0) {
                csvFileWriter.writeCsvFile(countries, helper.getCurrentPAth() + "/countries.csv");
            }
        };
    }
}
