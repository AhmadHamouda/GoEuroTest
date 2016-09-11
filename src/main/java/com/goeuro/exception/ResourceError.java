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
public class ResourceError extends RuntimeException {

    private static final Logger LOG = Logger.getLogger(CreationError.class);
    private final String error;

    public ResourceError(Exception ex) {
        error = ex.getMessage();
    }

    @Override
    public String getMessage() {
        LOG.error("Error while creation file due to: " + error);
        return "Error while creation file due to: " + error;
    }

}
