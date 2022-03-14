package com.criticaltechworks.dataset.boundary;

import com.criticaltechworks.dataset.boundary.dto.DatasetDTO;
import com.criticaltechworks.dataset.control.DatasetService;
import lombok.RequiredArgsConstructor;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/dataset")
@RequiredArgsConstructor
public class DatasetResource {

    private final DatasetService datasetService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public DatasetDTO dataset() {
        return datasetService.getCurrentDataset();
    }
}