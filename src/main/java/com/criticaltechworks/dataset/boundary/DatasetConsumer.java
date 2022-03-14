/*******************************************************************************
 * Copyright (c) 2022. BMW Group. All rights reserved.
 ******************************************************************************/
package com.criticaltechworks.dataset.boundary;

import com.criticaltechworks.dataset.boundary.dto.DatasetDTO;
import com.criticaltechworks.dataset.control.DatasetService;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
@RequiredArgsConstructor
public class DatasetConsumer {

    private final DatasetService datasetService;

    @Incoming("dataset")
    public CompletionStage<Void> consume(Message<DatasetDTO> message) {
        return CompletableFuture.supplyAsync(() -> {
            datasetService.updateDataSet(message.getPayload());
            return null;
        });
    }
}
