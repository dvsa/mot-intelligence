package uk.gov.dvsa.mot.intelligence.views;

import io.dropwizard.views.View;

public class PersonView extends View {
    private final String person;

    public PersonView(String person) {
<<<<<<< HEAD
        super("person.ftl");
=======
        super("person.hbs");
>>>>>>> b31e0e7... BL-4492 replaced free marker with handlebars
        this.person = person;
    }

    public String getPerson() {
        return person;
    }
}