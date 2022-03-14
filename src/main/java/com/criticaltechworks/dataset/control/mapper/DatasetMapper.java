/*******************************************************************************
 * Copyright (c) 2022. BMW Group. All rights reserved.
 ******************************************************************************/
package com.criticaltechworks.dataset.control.mapper;

import com.criticaltechworks.dataset.boundary.dto.ColumnDTO;
import com.criticaltechworks.dataset.boundary.dto.DatasetDTO;
import com.criticaltechworks.dataset.boundary.dto.RowDTO;
import com.criticaltechworks.dataset.entity.Column;
import com.criticaltechworks.dataset.entity.Dataset;
import com.criticaltechworks.dataset.entity.Row;
import com.criticaltechworks.dataset.entity.RowValue;

import javax.enterprise.context.Dependent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Dependent
public class DatasetMapper {

    public DatasetDTO map(Dataset dataset) {

        List<ColumnDTO> columnDTOS = new ArrayList<>();
        List<RowDTO> rowDTOS = new ArrayList<>();

        for (Row row : dataset.getRows()) {
            HashMap<String, Object> rowData = new HashMap<>();
            for (RowValue value : row.getValues()) {
                Column column = value.getColumn();
                if (columnDTOS.stream().noneMatch(columnDTO -> columnDTO.getName().equals(column.getName()))) {
                    columnDTOS.add(mapColumn(column));
                }
                rowData.put(column.getName(), value.getValue());
            }
            rowDTOS.add(new RowDTO(rowData));
        }

        DatasetDTO dto = new DatasetDTO();
        dto.setColumns(columnDTOS);
        dto.setData(rowDTOS);
        return dto;
    }

    public Dataset map(DatasetDTO dto) {
        Dataset dataset = new Dataset();
        List<Row> rows = new ArrayList<>();

        for (RowDTO rowDTO : dto.getData()) {
            ArrayList<RowValue> rowValues = new ArrayList<>();
            rowDTO.getData().forEach((key, value) ->
                dto.getColumns().stream().filter(columnDTO -> columnDTO.getName().equals(key)).findAny()
                        .map(columnDTO ->
                            new Column(columnDTO.getName(), columnDTO.getTitle(), columnDTO.isChecked())
                        ).map(column -> new RowValue(column, value.toString()))
            );
            rows.add(new Row(rowValues));
        }

        dataset.setRows(rows);
        return dataset;
    }

    private ColumnDTO mapColumn(Column column) {
        return new ColumnDTO(column.getName(), column.getName(), column.getTitle(), column.isChecked());
    }
}
