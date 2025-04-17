package com.eric.controller;

import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/ai")
public class OllamaController {

    @Autowired
    private OllamaChatModel ollamaChatModel;

    @RequestMapping("/ollama")
    public Object ollama(String msg) {
        String call = ollamaChatModel.call(msg);
        return call;
    }

    //获得全部内容后再返回
    @PostMapping(value = "/ai/ask")
    public Object ask(String msg) {
        String called = ollamaChatModel.call(msg);
        System.out.println(called);
        return called;
    }

    //一点点获取然后返回
    @PostMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> stream(String msg) {
        return ollamaChatModel.stream(msg).flatMapSequential(Flux::just);
    }
}
