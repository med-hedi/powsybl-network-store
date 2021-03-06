/**
 * Copyright (c) 2019, RTE (http://www.rte-france.com)
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.powsybl.network.store.iidm.impl;

import com.powsybl.commons.extensions.Extension;
import com.powsybl.entsoe.util.EntsoeArea;
import com.powsybl.entsoe.util.EntsoeAreaImpl;
import com.powsybl.entsoe.util.EntsoeGeographicalCode;
import com.powsybl.iidm.network.*;
import com.powsybl.network.store.model.EntsoeAreaAttributes;
import com.powsybl.network.store.model.Resource;
import com.powsybl.network.store.model.SubstationAttributes;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Geoffroy Jamgotchian <geoffroy.jamgotchian at rte-france.com>
 */
public class SubstationImpl extends AbstractIdentifiableImpl<Substation, SubstationAttributes> implements Substation {

    public SubstationImpl(NetworkObjectIndex index, Resource<SubstationAttributes> resource) {
        super(index, resource);
    }

    static SubstationImpl create(NetworkObjectIndex index, Resource<SubstationAttributes> resource) {
        return new SubstationImpl(index, resource);
    }

    @Override
    public ContainerType getContainerType() {
        return ContainerType.SUBSTATION;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public NetworkImpl getNetwork() {
        return index.getNetwork();
    }

    @Override
    public Optional<Country> getCountry() {
        return Optional.ofNullable(resource.getAttributes().getCountry());
    }

    @Override
    public Country getNullableCountry() {
        return resource.getAttributes().getCountry();
    }

    @Override
    public Substation setCountry(Country country) {
        resource.getAttributes().setCountry(country);
        return this;
    }

    @Override
    public String getTso() {
        return resource.getAttributes().getTso();
    }

    @Override
    public Substation setTso(String tso) {
        resource.getAttributes().setTso(tso);
        return this;
    }

    @Override
    public VoltageLevelAdder newVoltageLevel() {
        return new VoltageLevelAdderImpl(index, resource);
    }

    @Override
    public Stream<VoltageLevel> getVoltageLevelStream() {
        return index.getVoltageLevels(resource.getId()).stream();
    }

    @Override
    public Iterable<VoltageLevel> getVoltageLevels() {
        return getVoltageLevelStream().collect(Collectors.toList());
    }

    @Override
    public Substation addGeographicalTag(String tag) {
        // TODO
        return this;
    }

    @Override
    public TwoWindingsTransformerAdder newTwoWindingsTransformer() {
        return new TwoWindingsTransformerAdderImpl(index, this);
    }

    @Override
    public List<TwoWindingsTransformer> getTwoWindingsTransformers() {
        Set<TwoWindingsTransformer> twoWindingsTransformers = new LinkedHashSet<>();
        for (VoltageLevel vl : getVoltageLevels()) {
            twoWindingsTransformers.addAll(index.getTwoWindingsTransformers(vl.getId()));
        }
        return new ArrayList<>(twoWindingsTransformers);
    }

    @Override
    public Stream<TwoWindingsTransformer> getTwoWindingsTransformerStream() {
        return getTwoWindingsTransformers().stream();
    }

    @Override
    public int getTwoWindingsTransformerCount() {
        return getTwoWindingsTransformers().size();
    }

    @Override
    public ThreeWindingsTransformerAdder newThreeWindingsTransformer() {
        return new ThreeWindingsTransformerAdderImpl(index, this);
    }

    @Override
    public List<ThreeWindingsTransformer> getThreeWindingsTransformers() {
        Set<ThreeWindingsTransformer> threeWindingsTransformers = new LinkedHashSet<>();
        for (VoltageLevel vl : getVoltageLevels()) {
            threeWindingsTransformers.addAll(index.getThreeWindingsTransformers(vl.getId()));
        }
        return new ArrayList<>(threeWindingsTransformers);

    }

    @Override
    public Stream<ThreeWindingsTransformer> getThreeWindingsTransformerStream() {
        return getThreeWindingsTransformers().stream();
    }

    @Override
    public int getThreeWindingsTransformerCount() {
        return getThreeWindingsTransformers().size();
    }

    @Override
    public Set<String> getGeographicalTags() {
        return Collections.emptySet();
    }

    @Override
    public <E extends Extension<Substation>> void addExtension(Class<? super E> type, E extension) {
        if (type == EntsoeArea.class) {
            EntsoeArea entsoeArea = (EntsoeArea) extension;
            resource.getAttributes().setEntsoeArea(
                    EntsoeAreaAttributes.builder()
                            .code(entsoeArea.getCode().toString())
                    .build());
        }
        super.addExtension(type, extension);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E extends Extension<Substation>> E getExtension(Class<? super E> type) {
        if (type == EntsoeArea.class) {
            return (E) createEntsoeArea();
        }
        return super.getExtension(type);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E extends Extension<Substation>> E getExtensionByName(String name) {
        if (name.equals("entsoeArea")) {
            return (E) createEntsoeArea();
        }
        return super.getExtensionByName(name);
    }

    private EntsoeArea createEntsoeArea() {

        if (resource.getAttributes().getEntsoeArea() != null) {
            return new EntsoeAreaImpl(this,
                    EntsoeGeographicalCode.valueOf(resource.getAttributes().getEntsoeArea().getCode()));
        }
        return null;
    }

    @Override
    protected String getTypeDescription() {
        return "Substation";
    }
}
