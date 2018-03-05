package com.mindpool.easymed.sapiappointment.controller;

import ma.glasnost.orika.MapperFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class AbstractController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected MapperFacade orikaMapperFacade;
}
