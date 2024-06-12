package com.tobeto.feedback_system.controller;

import com.tobeto.feedback_system.core.results.DataResult;
import com.tobeto.feedback_system.core.results.Result;
import com.tobeto.feedback_system.payload.requests.AddCategoryRequest;
import com.tobeto.feedback_system.payload.requests.UpdateCategoryRequest;
import com.tobeto.feedback_system.payload.responses.GetCategoryResponse;
import com.tobeto.feedback_system.services.abstracts.ICategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final ICategoryService categoryService;

    @GetMapping
    public DataResult<List<GetCategoryResponse>> getCategoryList() {

        return this.categoryService.getCategoryList();
    }

    @GetMapping("/{id}")
    public DataResult<GetCategoryResponse> getCategoryById(@PathVariable Long id) {

        return this.categoryService.getCategoryById(id);
    }

    @PostMapping
    public Result addCategory(@Valid @RequestBody AddCategoryRequest categoryRequest) {

        return this.categoryService.addCategory(categoryRequest);
    }

    @PutMapping
    public Result updateCategory(@Valid @RequestBody UpdateCategoryRequest categoryRequest) {

        return this.categoryService.updateCategory(categoryRequest);
    }

    @DeleteMapping("/{id}")
    public Result deleteCategory(@PathVariable Long id) {

        return this.categoryService.deleteCategory(id);
    }


}
