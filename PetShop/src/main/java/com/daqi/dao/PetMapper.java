package com.daqi.dao;

import com.daqi.entity.Pet;
import java.util.List;

public interface PetMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Pet record);

    Pet selectByPrimaryKey(Integer id);

    List<Pet> selectAll();

    int updateByPrimaryKey(Pet record);

    int add(List<Pet> list);
}