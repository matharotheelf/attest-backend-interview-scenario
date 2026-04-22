package com.askattest.interview.controllers;

import com.askattest.interview.models.Survey;
import com.askattest.interview.repository.ResponseRepo;

public class SurveysController {
  public Survey survey;
  public ResponseRepo responses;

  public SurveysController(Survey survey, ResponseRepo responses) {
      this.survey = survey;
      this.responses = responses;
  }
}
