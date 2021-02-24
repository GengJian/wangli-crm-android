package com.control.widget.canvas;

import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

public class Font {
    static int FACE_SYSTEM;
    static int FACE_MONOSPACE;

    static int STYLE_PLAIN;
    static int STYLE_BOLD = Typeface.BOLD;

    static int SIZE_LARGE;
    static int SIZE_SMALL;
    static int SIZE_MEDIUM;
    Typeface mTypeface = null;

    public Font() {
        String familyName = "宋体";
        mTypeface = Typeface.create(familyName, Typeface.NORMAL);
        if (mTypeface == null) {
            mTypeface = Typeface.DEFAULT;
        }
    }

    public Font(String familyName) {
        if (familyName != null && familyName.length() > 0) {
            mTypeface = Typeface.create(familyName, Typeface.NORMAL);
        }
        if (mTypeface == null) {
            mTypeface = Typeface.DEFAULT;
        }
    }

    public static Font getDefaultFont() {
        Font font = new Font();
        return font;
    }

    public static Font getFont(int i, int j, int k) {
        Font font = new Font();
        font.mTypeface = Typeface.defaultFromStyle(k);
        return font;
    }

    public int stringWidth(String str) {
        return GetStringWidth(str);
    }

    public static int getHeight() {
        //return mTypeface.getStyle();
        Paint mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //mTextPaint.setColor(Color);
        // Define the string.
        // Measure the width of the text string.
        Rect bounds = new Rect();
        mTextPaint.getTextBounds("A", 0, 1, bounds);
        return Math.abs(bounds.height()) + 2;
    }

    public int charWidth(char pChar) {
        return mTypeface.getStyle();
    }

    public static int GetStringWidth(String str) {
        Paint mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //mTextPaint.setColor(Color);    
        // Define the string.
        // Measure the width of the text string.
        Rect bounds = new Rect();
        mTextPaint.getTextBounds(str, 0, str.length(), bounds);
        return Math.abs(bounds.width());
    }
}
