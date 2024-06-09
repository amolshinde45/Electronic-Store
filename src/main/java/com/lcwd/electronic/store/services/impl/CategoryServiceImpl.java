package com.lcwd.electronic.store.services.impl;

import com.lcwd.electronic.store.dtos.CategotyDto;
import com.lcwd.electronic.store.dtos.PegeableResponse;
import com.lcwd.electronic.store.dtos.ProjectionDTO;
import com.lcwd.electronic.store.entities.Category;
import com.lcwd.electronic.store.exception.ResourceNotFoundException;
import com.lcwd.electronic.store.helper.Helper;
import com.lcwd.electronic.store.repositories.CategoryRepository;
import com.lcwd.electronic.store.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public CategotyDto create(CategotyDto categotyDto) {

        //create Id

        String cid = UUID.randomUUID().toString();
        categotyDto.setCategoryId(cid);
        Category category = mapper.map(categotyDto, Category.class);

        Category savedCategory = categoryRepository.save(category);

        return mapper.map(savedCategory, CategotyDto.class);
    }

    @Override
    public CategotyDto update(CategotyDto categotyDto, String categoryId) {


        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category Not found Exception"));
        //

        category.setCategoryId(categotyDto.getCategoryId());
        category.setTitle(categotyDto.getTitle());
        category.setDescription(categotyDto.getDescription());
        category.setCoverImage(categotyDto.getCoverImage());


        Category updatedCategory = categoryRepository.save(category);


        return mapper.map(updatedCategory, CategotyDto.class);
    }

    @Override
    public void delete(String categoryId) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category Not found Exception"));

        categoryRepository.delete(category);

    }

    @Override
    public PegeableResponse<CategotyDto> getAll(int pageNumber, int pageSize, String sortyBy, String sortDir) {

        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortyBy).descending()) : (Sort.by(sortyBy).ascending());


        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);


        Page<Category> page = categoryRepository.findAll(pageable);

        PegeableResponse<CategotyDto> pegeableResponse = Helper.getPegeableResponse(page, CategotyDto.class);


        return pegeableResponse;
    }

    @Override
    public CategotyDto get(String categoryId) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Categoty Id not found"));


        return mapper.map(category, CategotyDto.class);

    }

    @Override
    public int getCount() {

        int count = (int) categoryRepository.count();
        return count;
    }

    @Override
    public List<ProjectionDTO> getListCategories() {
        List<ProjectionDTO> categories=categoryRepository.getCategoryTitle();


        return categories;
    }
}