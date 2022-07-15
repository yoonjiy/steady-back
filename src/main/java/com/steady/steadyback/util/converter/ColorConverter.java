package com.steady.steadyback.util.converter;

import com.steady.steadyback.domain.Color;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ColorConverter implements AttributeConverter<Color, String> {

    @Override
    public String convertToDatabaseColumn(Color attribute) {
        return attribute.getValue();
    }

    @Override
    public Color convertToEntityAttribute(String dbData) {
        if(dbData==null){
            return null;
        }
        try{
            return Color.valueOf(dbData);
        }
        catch(IllegalArgumentException e){
            throw e;
        }
    }
}
