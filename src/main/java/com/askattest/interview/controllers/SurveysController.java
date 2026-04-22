package com.askattest.interview.controllers;

import com.askattest.interview.repository.SurveyRepo;
import com.askattest.interview.repository.ResponseRepo;

public class SurveysController {
  public SurveyRepo surveys;
  public ResponseRepo responses;

  public SurveysController(SurveyRepo surveys, ResponseRepo responses) {
      this.surveys = surveys;
      this.responses = responses;
  }
}
