package com.lcwd.electronic.store.services;

import com.lcwd.electronic.store.dtos.CategotyDto;
import com.lcwd.electronic.store.dtos.PegeableResponse;
import com.lcwd.electronic.store.dtos.ProjectionDTO;
import com.lcwd.electronic.store.entities.Category;

import java.util.List;

public interface CategoryService {

    //create

    CategotyDto create(CategotyDto categotyDto);


    //update
    CategotyDto update(CategotyDto categotyDto, String categoryId);

    //delete
    void delete(String categoryId);


    //get all

    PegeableResponse<CategotyDto> getAll(int pageNumber, int pageSize , String sortyBy, String sortDir);


    //get single category
    CategotyDto get(String categoryId);



    //Count in table

    int getCount();
    //search


    //get list  for category id
  List<ProjectionDTO> getListCategories();

}
