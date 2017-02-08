package uk.gov.dvsa.moti.web.bundle;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Options;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import io.codemonastery.dropwizard.views.handlebars.HandlebarsHelperBundle;
import io.dropwizard.Configuration;
import com.github.jknack.handlebars.Helper;
import uk.gov.dvsa.moti.web.form.element.AbstractFormElement;

import java.io.IOException;

public class DisplayFormElementHelperBundle extends HandlebarsHelperBundle {


    @Override
    protected void configureHandlebars(Configuration configuration) {
        registerHelper("display_form_element", new Helper<AbstractFormElement>() {
            @Override
            public String apply(AbstractFormElement formElement, Options options) throws IOException {
                TemplateLoader loader = new ClassPathTemplateLoader();
                loader.setPrefix("/uk/gov/dvsa/moti/web/views/partials/form/");

                Handlebars handlebars = new Handlebars(loader);

                String partial = formElement.getType().name().toLowerCase() + "_element";

                return handlebars.compile(partial).apply(formElement);
            }
        });


    }


}
