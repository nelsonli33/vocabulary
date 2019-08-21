package com.lee.vocabulary.facades.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public interface GenericConverter<SOURCE, TARGET> extends Converter<SOURCE, TARGET> {

    @Override
    TARGET convert(SOURCE source);

    default List<TARGET> convertAll(Collection<? extends SOURCE> sources) {
        if (CollectionUtils.isEmpty(sources)) {
            return Collections.emptyList();
        } else {
            return sources.stream().map(s -> convert(s)).collect(Collectors.toList());
        }
    }
}
