/*******************************************************************************
 * Copyright (c) 2022. BMW Group. All rights reserved.
 ******************************************************************************/
package com.criticaltechworks.dataset.entity;

import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Getter

@Embeddable
public class RowValue {

    @ManyToOne
    private Column column;

    private String value;

    public RowValue(Column column, String value) {
        this.column = column;
        this.value = value;
    }

    protected RowValue() {
    }
}
