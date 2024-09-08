package com.qa.stepdefs;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventHandler;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.TestStepStarted;

public class StepDetails implements ConcurrentEventListener {

    public static String stepName;

    public EventHandler<TestStepStarted> stepHandler=new EventHandler<TestStepStarted>() {
        @Override
        public void receive(TestStepStarted testStepStarted) {

        }
    };

    @Override
    public void setEventPublisher(EventPublisher eventPublisher) {
        eventPublisher.registerHandlerFor(TestStepStarted.class,stepHandler);
    }

    private void testStepStartedOn(TestStepStarted event){
        if(event.getTestStep() instanceof PickleStepTestStep){
            PickleStepTestStep testStep= (PickleStepTestStep) event.getTestStep();
            stepName=testStep.getStep().getText();
        }
    }
}
