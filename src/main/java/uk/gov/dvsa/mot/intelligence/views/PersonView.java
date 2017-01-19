package uk.gov.dvsa.mot.intelligence.views;

import io.dropwizard.views.View;

public class PersonView extends View {
    private final String person;

    public PersonView(String person) {
        super("person.ftl");
        this.person = person;
    }

    public String getPerson() {
        return person;
    }
}