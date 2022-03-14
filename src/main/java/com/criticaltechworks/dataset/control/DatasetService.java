/*******************************************************************************
 * Copyright (c) 2022. BMW Group. All rights reserved.
 ******************************************************************************/
package com.criticaltechworks.dataset.control;

import com.criticaltechworks.dataset.boundary.dto.DatasetDTO;

public interface DatasetService {

    DatasetDTO getCurrentDataset();

    void updateDataSet(DatasetDTO datasetDTO);
}
