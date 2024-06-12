package com.tobeto.feedback_system.controller;

import com.tobeto.feedback_system.core.results.DataResult;
import com.tobeto.feedback_system.core.results.Result;
import com.tobeto.feedback_system.payload.requests.AddCategoryRequest;
import com.tobeto.feedback_system.payload.requests.AddFeedbackRequest;
import com.tobeto.feedback_system.payload.requests.UpdateCategoryRequest;
import com.tobeto.feedback_system.payload.requests.UpdateFeedbackRequest;
import com.tobeto.feedback_system.payload.responses.GetCategoryResponse;
import com.tobeto.feedback_system.payload.responses.GetFeedbackResponse;
import com.tobeto.feedback_system.services.abstracts.IFeedbackService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/feedbacks")
@RequiredArgsConstructor
public class FeedbackController {

    private final IFeedbackService feedbackService;

    @GetMapping()
    public DataResult<List<GetFeedbackResponse>> getFeedbackList() {

        return this.feedbackService.getFeedbacks();
    }

    @GetMapping("/getall/{id}")
    public DataResult<List<GetFeedbackResponse>> getFeedbackList(@PathVariable Long id) {

        return this.feedbackService.getFeedbacks(id);
    }


    @GetMapping("/{id}")
    public DataResult<GetFeedbackResponse> getFeedbackById(@PathVariable Long id) {

        return this.feedbackService.getFeedbackById(id);
    }

    @PostMapping
    public Result giveFeedback(@Valid @RequestBody AddFeedbackRequest addFeedbackRequest) {

        return this.feedbackService.giveFeedback(addFeedbackRequest);
    }

    @PutMapping
    public Result updateFeedback(@Valid @RequestBody UpdateFeedbackRequest feedbackRequest) {

        return this.feedbackService.updateFeedback(feedbackRequest);
    }

    @PatchMapping("/isactive/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Result updateFeedbackIsActive(@PathVariable("id") Long id, @RequestParam("active") boolean isActive) {

        return this.feedbackService.updateFeedbackCheck(id,isActive);
    }

    @DeleteMapping("/{id}")
    public Result deleteFeedback(@PathVariable Long id) {

        return this.feedbackService.deleteFeedback(id);
    }



}
