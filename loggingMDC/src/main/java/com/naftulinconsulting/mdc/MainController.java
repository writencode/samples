package com.naftulinconsulting.mdc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class MainController {
    Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @RequestMapping(value ="/", method= RequestMethod.GET)
    @ResponseBody
    public String sayHello() {
        LOGGER.info("in Hello World");
        return "Hello world";
    }
}
