/*******************************************************************************
 * Copyright (c) 2022. BMW Group. All rights reserved.
 ******************************************************************************/
package com.criticaltechworks.dataset.boundary.dto;

import lombok.Data;

import java.util.List;

@Data
public class DatasetDTO {

    private List<ColumnDTO> columns;

    private List<RowDTO> data;
}
