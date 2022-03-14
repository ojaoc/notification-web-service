/*******************************************************************************
 * Copyright (c) 2022. BMW Group. All rights reserved.
 ******************************************************************************/
package com.criticaltechworks.dataset.boundary.encoding;

import com.criticaltechworks.dataset.boundary.dto.DatasetDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class DatasetEncoder implements Encoder.Text<DatasetDTO> {

    private static final Logger LOG = LoggerFactory.getLogger(DatasetEncoder.class);

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String encode(DatasetDTO datasetDTO) throws EncodeException {
        try {
            return objectMapper.writeValueAsString(datasetDTO);
        } catch (JsonProcessingException e) {
            throw new EncodeException(datasetDTO, "Error encoding Dataset Object", e);
        }
    }

    @Override
    public void init(EndpointConfig config) {
        // empty
    }

    @Override
    public void destroy() {
        // empty
    }
}
