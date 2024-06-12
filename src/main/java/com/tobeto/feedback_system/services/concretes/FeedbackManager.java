package com.tobeto.feedback_system.services.concretes;

import com.tobeto.feedback_system.core.constants.MessageConstants;
import com.tobeto.feedback_system.core.exceptions.NotFoundException;
import com.tobeto.feedback_system.core.mappers.ModelMapperService;
import com.tobeto.feedback_system.core.results.DataResult;
import com.tobeto.feedback_system.core.results.Result;
import com.tobeto.feedback_system.core.results.SuccessDataResult;
import com.tobeto.feedback_system.core.results.SuccessResult;
import com.tobeto.feedback_system.models.concretes.Feedback;
import com.tobeto.feedback_system.payload.requests.AddFeedbackRequest;
import com.tobeto.feedback_system.payload.requests.UpdateFeedbackRequest;
import com.tobeto.feedback_system.payload.responses.GetFeedbackResponse;
import com.tobeto.feedback_system.repository.FeedbackRepository;
import com.tobeto.feedback_system.services.abstracts.IFeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackManager implements IFeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final ModelMapperService mapperService;


    @Override
    public DataResult<List<GetFeedbackResponse>> getFeedbacks() {

        List<GetFeedbackResponse> responses = this.feedbackRepository.
                findAll()
                .stream()
                .map(feedback -> this.mapperService.forResponse()
                        .map(feedback, GetFeedbackResponse.class)).toList();


        return new SuccessDataResult<>(responses, MessageConstants.GET_ALL.getMessage());


    }

    @Override
    public DataResult<List<GetFeedbackResponse>> getFeedbacks(Long userId) {

        List<GetFeedbackResponse> responses = this.feedbackRepository.
        findByUserId(userId)
                .stream()
                .map(feedback -> this.mapperService.forResponse()
                        .map(feedback, GetFeedbackResponse.class)).toList();


        return new SuccessDataResult<>(responses, MessageConstants.GET_ALL.getMessage());


    }

    @Override
    public DataResult<GetFeedbackResponse> getFeedbackById(Long id) {

        Feedback feedback = this.feedbackRepository.findById(id).orElseThrow(() -> new NotFoundException(MessageConstants.NOT_FOUND.getMessage()));
        GetFeedbackResponse response = this.mapperService.forResponse().map(feedback, GetFeedbackResponse.class);
        return new SuccessDataResult<>(response, MessageConstants.GET.getMessage());

    }

    @Override
    public Result giveFeedback(AddFeedbackRequest addFeedbackRequest) {

        Feedback feedback = this.mapperService.forRequest().map(addFeedbackRequest, Feedback.class);
        this.feedbackRepository.save(feedback);
        return new SuccessResult(MessageConstants.ADD.getMessage());
    }

    @Override
    public Result updateFeedback(UpdateFeedbackRequest updateFeedbackRequest) {
        Feedback feedback = this.feedbackRepository.findById(updateFeedbackRequest.getId())
                .orElseThrow(() -> new NotFoundException(MessageConstants.NOT_FOUND.getMessage()));
        this.feedbackRepository.save(this.mapperService.forRequest().map(updateFeedbackRequest, Feedback.class));

        return new SuccessResult(MessageConstants.UPDATE.getMessage());
    }

    @Override
    public Result updateFeedbackCheck(Long id, boolean isActive) {

        Feedback feedback = this.feedbackRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageConstants.NOT_FOUND.getMessage()));
        feedback.setActive(isActive);
        this.feedbackRepository.save(feedback);

        return new SuccessResult(MessageConstants.UPDATE.getMessage());

    }

    @Override
    public Result deleteFeedback(Long id) {
        Feedback feedback = this.feedbackRepository.findById(id).orElseThrow(() -> new NotFoundException(MessageConstants.NOT_FOUND.getMessage()));
        this.feedbackRepository.delete(feedback);
        return new SuccessResult(MessageConstants.DELETE.getMessage());
    }
}
