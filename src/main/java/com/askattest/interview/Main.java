package com.askattest.interview;

import com.askattest.interview.repository.ResponseRepo;
import com.askattest.interview.repository.SurveyRepo;
import com.askattest.interview.controllers.SurveysController;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        SurveyRepo surveys;
        ResponseRepo responses;
        SurveysController controller;
        int SURVEY_ID = 200;

        try {
            surveys = new SurveyRepo();
            responses = new ResponseRepo();
            controller = new SurveysController(surveys.surveyById(SURVEY_ID), responses);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Here is the data related to the respondants and their survey responses."); 
        System.out.println(String.format("This data is related to Survey ID: %1$s.", SURVEY_ID)); 
        System.out.println("Question response count by respondant.");
        for (var entry : controller.questionCountGroupedByRespondant().entrySet()) {
            System.out.println(String.format("Respondant: %1$s, question count: %2$s", entry.getKey(), entry.getValue()));
        }
    
        Logger.getGlobal().log(Level.INFO, controller.questionCountGroupedByRespondant().toString());
        Logger.getGlobal().log(Level.INFO, controller.totalPayoutGroupedByRespondant().toString());
    }
}
