package com.tobeto.feedback_system.services.concretes;

import com.tobeto.feedback_system.core.constants.MessageConstants;
import com.tobeto.feedback_system.core.exceptions.AlreadyExistsException;
import com.tobeto.feedback_system.core.exceptions.NotFoundException;
import com.tobeto.feedback_system.core.mappers.ModelMapperService;
import com.tobeto.feedback_system.core.results.DataResult;
import com.tobeto.feedback_system.core.results.Result;
import com.tobeto.feedback_system.core.results.SuccessDataResult;
import com.tobeto.feedback_system.core.results.SuccessResult;
import com.tobeto.feedback_system.models.concretes.Category;
import com.tobeto.feedback_system.payload.requests.AddCategoryRequest;
import com.tobeto.feedback_system.payload.requests.UpdateCategoryRequest;
import com.tobeto.feedback_system.payload.responses.GetCategoryResponse;
import com.tobeto.feedback_system.repository.CategoryRepository;
import com.tobeto.feedback_system.services.abstracts.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryManager implements ICategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapperService mapperService;


    @Override
    public DataResult<List<GetCategoryResponse>> getCategoryList() {

      List<GetCategoryResponse> responses=  this.categoryRepository.
              findAll()
              .stream()
              .map(category -> this.mapperService.forResponse()
                      .map(category,GetCategoryResponse.class)).toList();


        return new SuccessDataResult<>(responses, MessageConstants.GET_ALL.getMessage());
    }

    @Override
    public DataResult<GetCategoryResponse> getCategoryById(Long id) {
        Category category = this.categoryRepository.findById(id).orElseThrow(()-> new NotFoundException(MessageConstants.NOT_FOUND.getMessage()));
       GetCategoryResponse response = this.mapperService.forResponse().map(category,GetCategoryResponse.class);
        return new SuccessDataResult<>(response, MessageConstants.GET.getMessage());
    }

    @Override
    public Result addCategory(AddCategoryRequest addCategoryRequest) {
        if(this.categoryRepository.existsByName(addCategoryRequest.getName())){
            throw new AlreadyExistsException(MessageConstants.ALREADY_EXISTS.getMessage());
        }
        Category category = this.mapperService.forRequest().map(addCategoryRequest, Category.class);
        this.categoryRepository.save(category);
        return new SuccessResult( MessageConstants.ADD.getMessage());
    }

    @Override
    public Result updateCategory(UpdateCategoryRequest updateCategoryRequest) {
        Category category = this.categoryRepository.
                findById(updateCategoryRequest.getId())
                .orElseThrow(()-> new NotFoundException(MessageConstants.NOT_FOUND.getMessage()));
        category.setName(updateCategoryRequest.getName());
        this.categoryRepository.save(category);
        return new SuccessResult( MessageConstants.UPDATE.getMessage());
    }

    @Override
    public Result deleteCategory(Long id) {
        Category category = this.categoryRepository.
                findById(id)
                .orElseThrow(()-> new NotFoundException(MessageConstants.NOT_FOUND.getMessage()));
        this.categoryRepository.delete(category);
        return new SuccessResult( MessageConstants.DELETE.getMessage());
    }
}
