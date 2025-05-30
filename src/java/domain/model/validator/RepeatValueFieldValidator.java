/*
 * @(#)RepeatValueFieldValidator.java
 *
 * Copyright (c) 2016 FAE-USACH
 */
package domain.model.validator;

import com.opensymphony.xwork2.validator.ValidationException;
import com.opensymphony.xwork2.validator.validators.FieldValidatorSupport;

/**
 * Validador de la igualdad de valores de 2 campos ingresados. Por ejemplo
 * confirmación de passwords.
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public class RepeatValueFieldValidator extends FieldValidatorSupport {

    private String otherField;

    /**
     * Validación
     *
     * @param object
     * @throws ValidationException
     */
    @Override
    public void validate(Object object) throws ValidationException {
        String fieldName = getFieldName();
        String val = (String) getFieldValue(fieldName, object);

        if (val == null || val.length() <= 0) {
            return;
        }

        String otherVal = (String) getFieldValue(otherField, object);

        if (!val.equals(otherVal)) {
            addFieldError(fieldName, object);
        }
    }

    /**
     * Method description
     *
     * @return
     */
    public String getOtherField() {
        return otherField;
    }

    /**
     * Method description
     *
     * @param otherField
     */
    public void setOtherField(String otherField) {
        this.otherField = otherField;
    }
}
