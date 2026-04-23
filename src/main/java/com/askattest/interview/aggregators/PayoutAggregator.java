package com.askattest.interview.aggregators;

import com.askattest.interview.models.Question;
import com.askattest.interview.models.Response;
import com.askattest.interview.models.Survey;
import com.askattest.interview.repository.ResponseRepo;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PayoutAggregator {
  public Survey survey;
  public List<Response> responses;

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
