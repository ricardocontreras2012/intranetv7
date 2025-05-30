/*
 * @(#)LocalDateFieldValidator.java
 *
 * Copyright (c) 2016 FAE-USACH
 */
package domain.model.validator;

import com.opensymphony.xwork2.validator.ValidationException;
import com.opensymphony.xwork2.validator.validators.FieldValidatorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static infrastructure.util.SystemParametersUtil.DATE_FORMAT;
import static infrastructure.util.ContextUtil.getLocale;

/**
 * Validador de formato fecha de acuerdo a SystemParametersUtil.DATE_FORMAT.
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public class LocalDateFieldValidator extends FieldValidatorSupport {

    /**
     * Validación
     *
     * @param object
     * @throws ValidationException
     */
    @Override
    public void validate(Object object) throws ValidationException {
        String fieldName = getFieldName();
        Object value = this.getFieldValue(fieldName, object);

        if (!(value instanceof String)) {
            addActionError(object);

            return;
        }

        String str = (String) value;

        if (str.isEmpty()) {
            addActionError(object);

            return;
        }

        str = str.trim().replaceAll("-", "/");

        if (str.length() == DATE_FORMAT.length() - 1) {
            str = '0' + str;
        }

        DateFormat df = new SimpleDateFormat(DATE_FORMAT, getLocale());

        try {
            Date fechaDate = df.parse(str);

            if (!df.format(fechaDate).equals(str)) {
                addActionError(object);
            }
        } catch (ParseException e) {
            addActionError(object);
        }
    }
}
