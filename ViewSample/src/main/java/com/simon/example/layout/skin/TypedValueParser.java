package com.simon.example.layout.skin;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;

/**
 * TypedValue是Android解析Xml之后保存Xml节点属性的一种类型
 *
 * @see TypedValue
 */
class TypedValueParser {
    private static final String TAG = "TypedValueParser";

    /**
     * 颜色字面值
     *
     * @param color the literal color string
     * @return null if parse error occurred
     */
    static TypedValue parseColor(String color) {
        if (color.startsWith("#")) {
            String substring = color.substring(1, color.length());
            TypedValue value = new TypedValue();
            try {
                value.data = (int) Long.parseLong(substring, 16);
                Log.d(TAG, "parseColor : " + Integer.toHexString(value.data));
            } catch (NumberFormatException e) {
                Loot.logParse("Parse color failed: " + color);
                return null;
            }
            final int length = color.length() - 1;
            if (length == 6) {
                value.type = TypedValue.TYPE_INT_COLOR_RGB8;
                return value;
            } else if (length == 3) {
                value.type = TypedValue.TYPE_INT_COLOR_RGB4;
            } else if (length == 8) {
                value.type = TypedValue.TYPE_INT_COLOR_ARGB8;
            } else if (length == 4) {
                value.type = TypedValue.TYPE_INT_COLOR_ARGB4;
            } else {
                Loot.logParse("Parse color failed: [" + color + "] : wrong TypedValue Type");
                return null;
            }
            return value;
        }
        Loot.logParse("Parse color failed: [" + color + "] : not a color");
        return null;
    }

    /**
     * 浮点数字面值
     *
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
            Loot.logParse("Parse float failed: [" + f + "]");
            return null;
        }
    }

    /**
     * 尺寸字面值
     *
     * @param dimension 尺寸
     * @return null if parse error occurred
     */
    static TypedValue parseDimension(String dimension, DisplayMetrics dm) {
        TypedValue tv = new TypedValue();
        tv.type = TypedValue.TYPE_DIMENSION;
        tv.density = dm.densityDpi;

        String unitStr = dimension.substring(dimension.length() - 2, dimension.length() - 1);
        String dimenStr = dimension.substring(0, dimension.length() - 3);

        int unit;
        if (unitStr.equals("dp") || unitStr.equals("dip")) {
            unit = TypedValue.COMPLEX_UNIT_DIP;
        } else if (unitStr.equals("sp")) {
            unit = TypedValue.COMPLEX_UNIT_SP;
        } else if (unitStr.equals("in")) {
            unit = TypedValue.COMPLEX_UNIT_IN;
        } else if (unitStr.equals("mm")) {
            unit = TypedValue.COMPLEX_UNIT_MM;
        } else if (unitStr.equals("pt")) {
            unit = TypedValue.COMPLEX_UNIT_PT;
        } else if (unitStr.equals("")) {
            unit = TypedValue.COMPLEX_UNIT_PX;
        } else {
            Loot.logParse("Parse dimension failed: [" + dimension + "] : wrong unit type");
            return null;
        }

        try {
            final float value = Float.valueOf(dimenStr);
            tv.data = Float.floatToIntBits(TypedValue.applyDimension(unit, value, dm));
        } catch (NumberFormatException e) {
            Loot.logParse("Parse dimension failed: [" + dimension + "]");
            return null;
        }

        return tv;
    }

    /**
     * 字符串字面值
     * @param string a string
     * @return the TypedValue
     */
    static TypedValue parseString(String string) {
        TypedValue tv = new TypedValue();
        tv.type = TypedValue.TYPE_STRING;
        tv.string = string;
        return tv;
    }

    /**
     * 布尔字面值
     * @param value a boolean
     * @return the TypedValue
     */
    static TypedValue parseBoolean(String value) {
        TypedValue tv = new TypedValue();
        tv.type = TypedValue.TYPE_INT_BOOLEAN;
        tv.data = value.equals("true") ? 1 : 0;
        return tv;
    }

    /**
     * 整型字面值
     * @param integer a integer
     * @return the TypedValue
     */
    static TypedValue parseInt(String integer) {
        TypedValue tv = new TypedValue();
        if (integer.startsWith("0x")) {
            tv.type = TypedValue.TYPE_INT_HEX;
            try {
                tv.data = Integer.parseInt(integer, 16);
            } catch (NumberFormatException e) {
                Loot.logParse("Parse integer failed: [" + integer + "]");
                return null;
            }
            return tv;
        } else {
            tv.type = TypedValue.TYPE_INT_DEC;
            try {
                tv.data = Integer.parseInt(integer);
            } catch (NumberFormatException e) {
                Loot.logParse("Parse integer failed: [" + integer + "]");
                return null;
            }
            return tv;
        }
    }

    static TypedValue parseReference(String ref, Resources res) {
        if (ref.startsWith("@")) {
            TypedValue tv = new TypedValue();
            tv.type = TypedValue.TYPE_REFERENCE;
            tv.data = res.getIdentifier(ref.substring(1, ref.length() - 1), null, null);
            tv.resourceId = tv.data;
            if (tv.data == 0) {
                Loot.logParse("Parse reference failed: [" + ref + "] : resource not found");
                return null;
            }
            return tv;
        } else {
            Loot.logParse("Parse reference failed: [" + ref + "] : not start with @");
            return null;
        }
    }

}
