package uk.gov.dvsa.moti.web.bundle;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Options;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import io.codemonastery.dropwizard.views.handlebars.HandlebarsHelperBundle;
import io.dropwizard.Configuration;
import com.github.jknack.handlebars.Helper;
import io.dropwizard.views.View;
import uk.gov.dvsa.moti.web.core.honeypot.HoneypotFilter;
import uk.gov.dvsa.moti.web.form.element.AbstractFormElement;

import java.io.IOException;

/**
 * Form view helpers bundle
 */
public class FormHelpersBundle extends HandlebarsHelperBundle {

    private Handlebars handlebars;

    @Override
    protected void configureHandlebars(Configuration configuration) {

        registerHelper("display_form_element", new Helper<AbstractFormElement>() {
            @Override
            public String apply(AbstractFormElement formElement, Options options) throws IOException {

                String partial = formElement.getType().name().toLowerCase() + "_element";

                return getHandlebars().compile(partial).apply(formElement);
            }
        });

        registerHelper("honey_pot", new Helper<View>() {
            @Override
            public String apply(View context, Options options) throws IOException {
                return getHandlebars().compile("honey_pot").apply(HoneypotFilter.FIELD_NAME);
            }
        });
    }

    /**
     * Returns or creates new Handlebars if it doesn't exist
     * @return Handlebars
     */
    private Handlebars getHandlebars() {
        if (handlebars == null) {
            handlebars = createHandlebars();
        }

        return handlebars;
    }

    /**
     * Creates new Handlebars instance and sets prefix path
     * @return Handlebars
     */
    private Handlebars createHandlebars() {
        TemplateLoader loader = new ClassPathTemplateLoader();
        loader.setPrefix("/uk/gov/dvsa/moti/web/views/partials/form/");

        return new Handlebars(loader);
    }

}