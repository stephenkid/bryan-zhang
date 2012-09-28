package org.poseidon.mapper;

import java.util.Map;

import org.poseidon.pojo.Person;

public interface PersonMapper {
    public Person getPerson(Map<String, Object> map);
}