/**
 * Copyright (c) 2020, RTE (http://www.rte-france.com)
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.powsybl.network.store.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Nicolas Noir <nicolas.noir at rte-france.com>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel("Load detail attributes")
public class LoadDetailAttributes {

    @ApiModelProperty("Fixed active power in MW")
    private float fixedActivePower;

    @ApiModelProperty("Fixed reactive power in MW")
    private float fixedReactivePower;

    @ApiModelProperty("Variable active power in MW")
    private float variableActivePower;

    @ApiModelProperty("Variable reactive power in MW")
    private float variableReactivePower;
}
