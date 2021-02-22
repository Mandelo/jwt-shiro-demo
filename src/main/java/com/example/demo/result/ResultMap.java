package com.example.demo.result;

import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @description:
 * @author: luox
 * @date： 2021/2/22
 */

@Component
public class ResultMap extends HashMap<String, Object> {
    public ResultMap() {
    }

    public ResultMap success() {
        this.put("result", "success");
        return this;
    }

    public ResultMap fail() {
        this.put("result", "fail");
        return this;
    }

    public ResultMap code(int code) {
        this.put("code", code);
        return this;
    }

    public ResultMap message(Object message) {
        this.put("message", message);
        return this;
    }
}