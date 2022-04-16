package com.kenlhk.notekeeper.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CommonMapper {
    private final ModelMapper modelMapper;

    <T, S> S map(T data, Class<S> type) {
        return modelMapper.map(data, type);
    }

    <T, S> List<S> map(List<T> list, Class<S> type) {
        return list.stream()
                .map(item -> map(item, type))
                .collect(Collectors.toList());
    }
}
