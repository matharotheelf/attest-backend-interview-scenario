package com.askattest.interview.aggregators;

import com.askattest.interview.models.Response;
import com.askattest.interview.models.Survey;
import com.askattest.interview.repository.ResponseRepo;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResponseCountAggregator {
  public Survey survey;
  public List<Response> responses;

  public ResponseCountAggregator(Survey survey, ResponseRepo responseRepo) {
    this.survey = survey;
    this.responses = responseRepo.responsesByQuestionSet(survey.questionIds());
    ;
  }

  public Map<Integer, Long> questionCountGroupedByRespondant() {
    return this.responses.stream()
        .collect(Collectors.groupingBy(response -> response.respondent, Collectors.counting()));
  }
}
