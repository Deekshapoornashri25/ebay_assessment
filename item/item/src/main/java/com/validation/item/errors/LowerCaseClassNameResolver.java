package com.validation.item.errors;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;

/**
 * The Class LowerCaseClassNameResolver.
 *
 */
public class LowerCaseClassNameResolver extends TypeIdResolverBase {

    /**
     * Id from value.
     *
     * @param value the value
     * @return the string
     */
    @Override
    public String idFromValue(Object value) {
        return value.getClass().getSimpleName().toLowerCase();
    }

    /**
     * Id from value and type.
     *
     * @param value the value
     * @param suggestedType the suggested type
     * @return the string
     */
    @Override
    public String idFromValueAndType(Object value, Class<?> suggestedType) {
        return idFromValue(value);
    }

    /**
     * Gets the mechanism.
     *
     * @return the mechanism
     */
    @Override
    public JsonTypeInfo.Id getMechanism() {
        return JsonTypeInfo.Id.CUSTOM;
    }
}
