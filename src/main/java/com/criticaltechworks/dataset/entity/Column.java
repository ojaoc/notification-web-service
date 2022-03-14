/*******************************************************************************
 * Copyright (c) 2022. BMW Group. All rights reserved.
 ******************************************************************************/
package com.criticaltechworks.dataset.entity;

import lombok.*;

import javax.persistence.*;

@Getter

@Entity
@Table(name = "t_column")
public class Column {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String title;

    private boolean checked;

    public Column(String name, String title, boolean checked) {
        this.name = name;
        this.title = title;
        this.checked = checked;
    }

    protected Column() {

    }
}
