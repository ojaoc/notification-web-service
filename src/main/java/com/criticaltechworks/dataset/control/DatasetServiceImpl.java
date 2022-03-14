/*******************************************************************************
 * Copyright (c) 2022. BMW Group. All rights reserved.
 ******************************************************************************/
package com.criticaltechworks.dataset.control;

import com.criticaltechworks.dataset.boundary.DatasetWebSocket;
import com.criticaltechworks.dataset.boundary.dto.DatasetDTO;
import com.criticaltechworks.dataset.control.mapper.DatasetMapper;
import com.criticaltechworks.dataset.entity.Dataset;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Dependent
@RequiredArgsConstructor
public class DatasetServiceImpl implements DatasetService {

    @PersistenceContext
    private final EntityManager entityManager;

    private final DatasetMapper mapper;

    private final DatasetWebSocket webSocket;

    @Override
    public DatasetDTO getCurrentDataset() {
        Dataset dataset = entityManager.createNamedQuery(Dataset.NamedQueries.FIND_CURRENT, Dataset.class).getSingleResult();
        return mapper.map(dataset);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void updateDataSet(DatasetDTO datasetDTO) {
        Dataset dataset = mapper.map(datasetDTO);
        entityManager.persist(dataset);

        webSocket.updateDataset(datasetDTO);
    }
}
