package com.simon.example.layout.skin;

import android.util.TypedValue;

/**
 * TypedValue是Android解析Xml之后保存Xml节点属性的一种类型
 * @see TypedValue
 */
class TypedValueParser {

    /**
     * 颜色字面值
     * @param color the literal color string
     * @return null if parse error occurred
     */
    static TypedValue parseLiteralColor(String color) {
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

    /**
     * 浮点数字面值
     * @param f 浮点数
     * @return null if parse error occurred
     */
    static TypedValue parseFloat(String f) {
        try {
            TypedValue value = new TypedValue();
            value.type = TypedValue.TYPE_FLOAT;
            value.data = Float.floatToIntBits(Float.parseFloat(f));
            return value;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * 尺寸字面值
     * @param dimension 尺寸
     * @return null if parse error occurred
     */
    static TypedValue parseDimension(String dimension) {
        TypedValue value = new TypedValue();
        value.type = TypedValue.TYPE_DIMENSION;
        value.density = TypedValue.DENSITY_DEFAULT;

        return value;
    }
}
