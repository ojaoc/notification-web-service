/*******************************************************************************
 * Copyright (c) 2022. BMW Group. All rights reserved.
 ******************************************************************************/
package com.criticaltechworks.dataset.boundary.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ColumnDTO {

    private String id;

    private String name;

    private String title;

    private boolean checked;
}
