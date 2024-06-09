package com.lcwd.electronic.store.controllers;


import com.lcwd.electronic.store.dtos.ApiResponseMessage;
import com.lcwd.electronic.store.dtos.CategotyDto;
import com.lcwd.electronic.store.dtos.PegeableResponse;
import com.lcwd.electronic.store.dtos.ProjectionDTO;
import com.lcwd.electronic.store.entities.Category;
import com.lcwd.electronic.store.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {


    //create
    @Autowired
    private CategoryService categoryService;


    @PostMapping
    public ResponseEntity<CategotyDto> createCategory(@Valid
            @RequestBody CategotyDto categotyDto){

        CategotyDto categotyDto1 = categoryService.create(categotyDto);


        return  new ResponseEntity<>(categotyDto1, HttpStatus.CREATED);
    }

    //update

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategotyDto> updateCategorty(@Valid
            @RequestBody CategotyDto categotyDto,
            @PathVariable String categoryId
    ){

        CategotyDto updatedCategory =categoryService.update(categotyDto,categoryId);


        return new ResponseEntity<>(updatedCategory,HttpStatus.CREATED);
    }


    //delete

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponseMessage> deleteCategory(
            @PathVariable String categoryId

    ){

        categoryService.delete(categoryId);
       ApiResponseMessage responseMessage= ApiResponseMessage.builder().message("Category deleted Sucessfull")
                .success(true)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(responseMessage,HttpStatus.OK);
    }



    //get all


    @GetMapping
    public ResponseEntity<PegeableResponse<CategotyDto>> getAll(
        @RequestParam(value = "pageNumber", defaultValue = "0",required = false) int pageNumber,
        @RequestParam(value = "pageSize", defaultValue = "10",required = false) int pageSize,
        @RequestParam(value = "sortBy",defaultValue = "title",required = false) String sortBy,
        @RequestParam(value = "sortDir", defaultValue = "asc",required = false) String sortDir
    ){

        PegeableResponse<CategotyDto> all = categoryService.getAll(pageNumber, pageSize, sortBy, sortDir);


        return new ResponseEntity(all,HttpStatus.OK);

    }


    //get single
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategotyDto> getSingleCategory(
            @PathVariable String categoryId
    )
    {

        CategotyDto categotyDto = categoryService.get(categoryId);

//        return new ResponseEntity<>(categotyDto,HttpStatus.OK);\

        return ResponseEntity.ok(categotyDto);
    }

    //get Count

    @GetMapping("/count")
    public ResponseEntity<ApiResponseMessage> getcount(){
        Integer count=categoryService.getCount();

        String count1 =count.toString();
        ApiResponseMessage build = ApiResponseMessage.builder()
                .status(HttpStatus.OK).success(true).message("Count of categories " + count1).build();


        return new ResponseEntity<ApiResponseMessage>(build,HttpStatus.OK);
    }

    @GetMapping("/listCategories")

    public ResponseEntity<List<ProjectionDTO>> getList(){

        List<ProjectionDTO> categories=categoryService.getListCategories();
        System.out.println(categories);

        return  new ResponseEntity<>(categories,HttpStatus.OK);

    }
}
