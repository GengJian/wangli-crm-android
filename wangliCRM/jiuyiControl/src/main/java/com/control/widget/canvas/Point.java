package com.control.widget.canvas;

/**
 *
 * @author zhengss
 */
public class Point
{
    public int x = 0;
    public int y = 0;

    /* Creates a new instance of Point */
    public Point() {
    }
    
    public Point(int px,int py) {
    	x = px;
    	y = py;
    }
    
    public void SetPoint(int px,int py)
    {
    	x = px;
    	y = py;
    }
}
