package com.askattest.interview.controllers;

import com.askattest.interview.repository.SurveyRepo;

public class SurveysController {
  public SurveyRepo surveys;

  public SurveysController(SurveyRepo surveys) {
      this.surveys = surveys;
  }
}
