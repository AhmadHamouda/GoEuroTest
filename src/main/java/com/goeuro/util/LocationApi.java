/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goeuro.util;

import com.goeuro.model.Country;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author ahmad
 */
@Service
public class LocationApi {

    private static final Logger LOG = Logger.getLogger(LocationApi.class);

    public Country[] getLocations(String Url) {
        LOG.info("Enter getLocations method.");
        RestTemplate restTemplate = new RestTemplate();
        Country[] countries = restTemplate.getForObject(Url, Country[].class);
        LOG.info("Finished getLocations method successfully.");
        if (countries.length == 0) {
            LOG.warn("No result found");
        }
        return countries;
    }
}
