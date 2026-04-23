package com.askattest.interview.controllers;

import java.util.List;

import com.askattest.interview.models.Survey;
import com.askattest.interview.models.Response;
import com.askattest.interview.repository.ResponseRepo;

public class SurveysController {
  public Survey survey;
  public List<Response> responses;

  public SurveysController(Survey survey, ResponseRepo responseRepo) {
      this.survey = survey;
      this.responses = responseRepo.responsesByQuestionList(survey.question_ids());;
  }
}
