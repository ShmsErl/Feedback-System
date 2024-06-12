package com.tobeto.feedback_system.services.abstracts;

import com.tobeto.feedback_system.core.results.DataResult;
import com.tobeto.feedback_system.core.results.Result;
import com.tobeto.feedback_system.models.concretes.Feedback;
import com.tobeto.feedback_system.payload.requests.AddFeedbackRequest;
import com.tobeto.feedback_system.payload.requests.UpdateFeedbackRequest;
import com.tobeto.feedback_system.payload.responses.GetFeedbackResponse;

import java.util.List;

public interface IFeedbackService {

    DataResult<List<GetFeedbackResponse>> getFeedbacks();

    DataResult<List<GetFeedbackResponse>> getFeedbacks(Long userId);

    DataResult<GetFeedbackResponse> getFeedbackById(Long id);

    Result giveFeedback(AddFeedbackRequest addFeedbackRequest);

    Result updateFeedback(UpdateFeedbackRequest updateFeedbackRequest);

    Result updateFeedbackCheck(Long id, boolean isActive);


    Result deleteFeedback(Long id);

}
