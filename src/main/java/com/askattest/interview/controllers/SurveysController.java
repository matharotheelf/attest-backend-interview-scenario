package com.askattest.interview.controllers;

import com.askattest.interview.models.Question;
import com.askattest.interview.models.Response;
import com.askattest.interview.models.Survey;
import com.askattest.interview.repository.ResponseRepo;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SurveysController {
  public Survey survey;
  public List<Response> responses;

  public SurveysController(Survey survey, ResponseRepo responseRepo) {
    this.survey = survey;
    this.responses = responseRepo.responsesByQuestionSet(survey.questionIds());
    ;
  }

  public Map<Integer, Long> questionCountGroupedByRespondant() {
    return this.responses.stream()
        .collect(Collectors.groupingBy(response -> response.respondent, Collectors.counting()));
  }

  private List<Question> questionsFromResponses(List<Response> responseList) {
    return responseList.stream().map(response -> survey.questionById(response.question)).toList();
  }

  private Map<Integer, List<Response>> responsesGroupedByRespondant() {
    return this.responses.stream().collect(Collectors.groupingBy(response -> response.respondent));
  }

  private Integer totalPayoutByQuestions(List<Question> questionList) {
    return questionList.stream().mapToInt(response -> response.payout).sum();
  }

  public Map<Integer, Integer> totalPayoutGroupedByRespondant() {
    return responsesGroupedByRespondant().entrySet().stream()
        .collect(
            Collectors.toMap(
                Map.Entry::getKey, // Unique key with suffix
                responseGroup ->
                    totalPayoutByQuestions(questionsFromResponses(responseGroup.getValue()))));
  }
}
