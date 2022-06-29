package com.betvictor.websocket.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class WebsocketController {

    @Value("${websocket.topic}")
    private String topic;

    @Value("${websocket.endpoint}")
    private String endpoint;

    @RequestMapping("/")
    public String index(Model model) {
        log.info(topic);
        log.info(endpoint);
        model.addAttribute("topic", topic);
        model.addAttribute("endpoint", endpoint);
        return "index";
    }

}
