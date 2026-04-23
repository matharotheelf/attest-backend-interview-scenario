package com.askattest.interview.controllers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

  public Map<Integer, Long> questionCountGroupedByRespondant() {
      return this.responses.stream().collect(Collectors.groupingBy(response -> response.respondent, Collectors.counting()));
  }
}
