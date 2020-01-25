/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("sv.edu.udb.util.NameValidator")
public class NameValidator implements Validator {

    private static final String CUSTOM_PATTERN = "^([A-Z][a-z]+([ ]?[a-z]?['-]?[A-Z][a-z]+)*)$";
    private Pattern pattern;
    private Matcher matcher;

    public NameValidator() {
        pattern = Pattern.compile(CUSTOM_PATTERN);
    }

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
           matcher = pattern.matcher(o.toString());
        if (!matcher.matches()) {
            FacesMessage msg = new FacesMessage("Validación de nombre falló.", "Formato incorrecto.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

}