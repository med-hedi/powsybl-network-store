/**
 * Copyright (c) 2020, RTE (http://www.rte-france.com)
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.powsybl.network.store.iidm.impl;

import com.powsybl.network.store.model.BusbarSectionAttributes;
import com.powsybl.network.store.model.ConnectablePositionAttributes;
import com.powsybl.network.store.model.InjectionAttributes;

import java.util.Map;

/**
 * @author Geoffroy Jamgotchian <geoffroy.jamgotchian at rte-france.com>
 */
public class BusbarSectionToInjectionAdapter implements InjectionAttributes {

    private final BusbarSectionAttributes attributes;

    public BusbarSectionToInjectionAdapter(BusbarSectionAttributes attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getName() {
        return attributes.getName();
    }

    @Override
    public boolean isFictitious() {
        return attributes.isFictitious();
    }

    @Override
    public void setFictitious(boolean fictitious) {
        attributes.setFictitious(fictitious);
    }

    @Override
    public Map<String, String> getProperties() {
        return attributes.getProperties();
    }

    @Override
    public void setProperties(Map<String, String> properties) {
        attributes.setProperties(properties);
    }

    @Override
    public String getVoltageLevelId() {
        return attributes.getVoltageLevelId();
    }

    @Override
    public Integer getNode() {
        return attributes.getNode();
    }

    @Override
    public String getBus() {
        throw new AssertionError();
    }

    @Override
    public String getConnectableBus() {
        throw new AssertionError();
    }

    @Override
    public double getP() {
        throw new AssertionError();
    }

    @Override
    public void setP(double p) {
        throw new AssertionError();
    }

    @Override
    public double getQ() {
        throw new AssertionError();
    }

    @Override
    public void setQ(double q) {
        throw new AssertionError();
    }

    @Override
    public ConnectablePositionAttributes getPosition() {
        throw new AssertionError();
    }

    @Override
    public void setPosition(ConnectablePositionAttributes position) {
        throw new AssertionError();
    }
}
