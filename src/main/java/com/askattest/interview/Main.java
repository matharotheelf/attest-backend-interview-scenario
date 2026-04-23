package com.askattest.interview;

import com.askattest.interview.aggregators.ResponseCountAggregator;
import com.askattest.interview.aggregators.PayoutAggregator;
import com.askattest.interview.repository.ResponseRepo;
import com.askattest.interview.repository.SurveyRepo;
import java.io.IOException;

public class Main {
  public static void main(String[] args) {
    SurveyRepo surveys;
    ResponseRepo responses;
    ResponseCountAggregator responseCountAggregator;
    PayoutAggregator payoutAggregator;
    int SURVEY_ID = 200;
    String surveyName;

    try {
      surveys = new SurveyRepo();
      responses = new ResponseRepo();
      responseCountAggregator = new ResponseCountAggregator(surveys.surveyById(SURVEY_ID), responses);
      payoutAggregator = new PayoutAggregator(surveys.surveyById(SURVEY_ID), responses);
      surveyName = surveys.surveyById(SURVEY_ID).name;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    System.out.println("Here is the data related to the respondants and their survey responses.");
    System.out.println(String.format("This data is related to Survey: %1$s.", surveyName));
    System.out.println();
    System.out.println();

    System.out.println("Question response count by respondant.");
    for (var entry : responseCountAggregator.questionCountGroupedByRespondant().entrySet()) {
      System.out.println(
          String.format(
              "Respondant: %1$s, question count: %2$s", entry.getKey(), entry.getValue()));
    }
    System.out.println();
    System.out.println();

    System.out.println("Total payout by respondant.");
    for (var entry : payoutAggregator.totalPayoutGroupedByRespondant().entrySet()) {
      System.out.println(
          String.format("Respondant: %1$s, total payout: %2$s", entry.getKey(), entry.getValue()));
    }
  }
}
