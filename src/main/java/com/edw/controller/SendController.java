package com.edw.controller;

import com.edw.simulate.ProduceMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 *     com.edw.controller.SendController
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 13 Jan 2023 16:16
 */
@RestController
public class SendController {

    @Autowired
    private ProduceMessages produceMessages;

    @GetMapping("/")
    public String sendMessage() throws Exception {
        produceMessages.sendMessage();
        return "";
    }

}
