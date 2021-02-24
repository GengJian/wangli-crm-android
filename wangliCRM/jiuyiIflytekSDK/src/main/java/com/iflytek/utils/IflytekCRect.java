package com.iflytek.utils;

import android.graphics.Rect;

/**
 * ****************************************************************
 * 文件名称 : IflytekCRect
 * ****************************************************************
 */
public class IflytekCRect
{
    public int left = 0;// = super.x;
    public int right = 0;// = super.width + super.x;
    public int top = 0;// = super.y;
    public int bottom = 0;// = super.height + super.y;px
    public int xPos = 0;// x的原点
    public int yPos = 0;// y的原点
    public int nAction = 0;// 本IflytekIflytekCRect的功能
    public Rect m_rect = new Rect(0, 0, 0, 0);

    public IflytekCRect() {

    }

    public IflytekCRect(Rect rect) {
        if (rect == null)
            return;

        left = rect.left;
        right = rect.right;
        top = rect.top;
        bottom = rect.bottom;
        m_rect.set(left, top, right, bottom);

    }

    public IflytekCRect(IflytekCRect rect) {
        if (rect == null)
            return;

        left = rect.left;
        right = rect.right;
        top = rect.top;
        bottom = rect.bottom;
        m_rect.set(left, top, right, bottom);
    }

    public IflytekCRect(int left_, int top_, int right_, int bottom_) {
        left = left_;
        right = right_;
        top = top_;
        bottom = bottom_;
        m_rect.set(left, top, right, bottom);
    }

    public IflytekCRect(int left_, int top_, int right_, int bottom_, int action) {
        this(left_, top_, right_, bottom_);
        nAction = action;
    }

    public IflytekCRect(int left_, int top_, int right_, int bottom_, int xPos, int yPos) {
        this(left_, top_, right_, bottom_);
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public int Width() {
        return (right - left);
    }

    public int Height() {//包括title
        return (bottom - top);
    }

    public boolean PtInRect(int x, int y) {
        return (x >= left && x <= right && y >= top && y <= bottom);
    }

    public void NormalizeRect() {
        int nTemp;
        if (left > right) {
            nTemp = left;
            left = right;
            right = nTemp;
        }
        if (top > bottom) {
            nTemp = top;
            top = bottom;
            bottom = nTemp;
        }
    }

    public void SetRect(IflytekCRect rect) {
        if (rect == null)
            return;

        left = rect.left;
        right = rect.right;
        top = rect.top;
        bottom = rect.bottom;
        m_rect.set(left, top, right, bottom);
    }

    public static boolean IsRectEmpty(IflytekCRect rect) {
        return rect == null || rect.Height() == 0 || rect.Width() == 0;
    }

    public void Empty() {
        left = 0;
        right = 0;
        top = 0;
        bottom = 0;
        m_rect.set(left, top, right, bottom);
    }

    //
    public void DeflateRect(int l, int t, int r, int b) {
        left += l;
        right -= r;
        top += t;
        bottom -= b;
        m_rect.set(left, top, right, bottom);
    }

    public void InflateRect(int w, int h) {
        left -= w;
        right += w;
        top -= h;
        bottom += h;
        m_rect.set(left, top, right, bottom);
    }
}

