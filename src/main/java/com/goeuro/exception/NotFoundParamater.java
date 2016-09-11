/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goeuro.exception;

import org.apache.log4j.Logger;

/**
 *
 * @author ahmad
 */
public class NotFoundParamater extends RuntimeException {

    private static final Logger LOG = Logger.getLogger(NotFoundParamater.class);

    @Override
    public String getMessage() {
        LOG.error("Country name is missing.");
        return "Country name is missing.";
    }

}
