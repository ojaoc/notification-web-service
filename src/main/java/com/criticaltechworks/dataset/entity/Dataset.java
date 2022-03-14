/*******************************************************************************
 * Copyright (c) 2022. BMW Group. All rights reserved.
 ******************************************************************************/
package com.criticaltechworks.dataset.entity;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.Column;
import java.util.List;

@Data
@NamedQueries({
        @NamedQuery(name = Dataset.NamedQueries.FIND_CURRENT,
        query = "select dataset from Dataset dataset order by dataset.id desc")
})
@Entity
@Table(name = "t_dataset")
public class Dataset {

    public static class NamedQueries {
        public static final String FIND_CURRENT = "Dataset.FindCurrent";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(
            targetEntity = Row.class,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "dataset_id", referencedColumnName = "id")
    private List<Row> rows;
}
