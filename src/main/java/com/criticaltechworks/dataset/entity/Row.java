/*******************************************************************************
 * Copyright (c) 2022. BMW Group. All rights reserved.
 ******************************************************************************/
package com.criticaltechworks.dataset.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Getter

@Entity
@Table(name = "t_row")
public class Row {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ElementCollection
    private List<RowValue> values;

    public Row(List<RowValue> values) {
        this.values = values;
    }

    protected Row() {
    }
}
