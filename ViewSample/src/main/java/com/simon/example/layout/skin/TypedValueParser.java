package com.simon.example.layout.skin;

import android.util.TypedValue;

/**
 *
 */
public class TypedValueParser {

    /**
     * @param color the literal color string
     * @return return null if parse error occurred
     */
    public static TypedValue parseLiteralColor(String color) {
        if (color.startsWith("#")) {
            String substring = color.substring(1, color.length());
            if (substring.length() == 6 || substring.length() == 8) {
                TypedValue value = new TypedValue();
                try {

                    value.data = Integer.valueOf(substring);
                    value.type = TypedValue.TYPE_INT_COLOR_ARGB8;
                } catch (NumberFormatException e) {
                    return null;
                }
            }
        }
        return null;
    }

    public static TypedValue parseFloat(String f) {
        try {
            TypedValue value = new TypedValue();
            value.type = TypedValue.TYPE_FLOAT;
            value.data = Float.floatToIntBits(Float.parseFloat(f));
            return value;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static TypedValue parseDimension(String value) {
    }
}
