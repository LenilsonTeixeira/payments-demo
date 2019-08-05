package com.lteixeira.msorders.service;

import com.lteixeira.msorders.model.Message;

import java.io.IOException;

@FunctionalInterface
public interface ProcessorService {
    void process(final Message message) throws IOException;
}
