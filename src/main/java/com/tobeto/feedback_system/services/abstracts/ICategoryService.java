package com.tobeto.feedback_system.services.abstracts;

import com.tobeto.feedback_system.core.results.DataResult;
import com.tobeto.feedback_system.core.results.Result;
import com.tobeto.feedback_system.payload.requests.AddCategoryRequest;
import com.tobeto.feedback_system.payload.requests.UpdateCategoryRequest;
import com.tobeto.feedback_system.payload.responses.GetCategoryResponse;

import java.util.List;


public interface ICategoryService {

    DataResult<List<GetCategoryResponse>> getCategoryList();

    DataResult<GetCategoryResponse> getCategoryById(Long id);

    Result addCategory(AddCategoryRequest addCategoryRequest);

    Result updateCategory(UpdateCategoryRequest updateCategoryRequest);

    Result deleteCategory(Long id);
}
