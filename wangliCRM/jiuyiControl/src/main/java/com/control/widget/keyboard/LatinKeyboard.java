/*
 * Copyright (C) 2008-2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.control.widget.keyboard;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.inputmethodservice.Keyboard;

import com.control.utils.Rc;
import com.control.utils.Res;


public class LatinKeyboard extends Keyboard {
    public LatinKeyboard(Context context, int xmlLayoutResId) {
        super(context, xmlLayoutResId);
    }

    public LatinKeyboard(Context context, int layoutTemplateResId, 
            CharSequence characters, int columns, int horizontalPadding) {
        super(context, layoutTemplateResId, characters, columns, horizontalPadding);
    }
    

    @Override
    protected Key createKeyFromXml(Resources res, Row parent, int x, int y, 
            XmlResourceParser parser) {
    	LatinKey key = new LatinKey(res, parent, x, y, parser);
    	
    	
    	//zhengss
    	switch(key.codes[0]){
	    	case 66607://确定
	    		key.background_blue = true;//蓝色
	    		break;
	    	case 66605://数字切换到字母
	    	case 66621://字母切换到数字
	    	case 66606://切换系统键盘
	    	case 66622://切换到大写
	    	case 66608://切换到小写
	    	case 66610://删除 
	    	case 66609://隐藏
	    	case 66595://全部
	    	case 66596://1/2
	    	case 66597://1/3
	    	case 66598://1/4
	    		key.background_gray = true;//
	    		break;
	    	default:
	    		key.background_white = true;
	    		break;
    	}

        return key;
    }
    
   

    static class LatinKey extends Keyboard.Key {
    	public boolean background_blue = false;
    	public boolean background_white = false;
    	public boolean background_gray = false;
    	
    	 private final static int[] KEY_STATE_NORMAL_ON = { 
             android.R.attr.state_checkable, 
             android.R.attr.state_checked
         };

         private final static int[] KEY_STATE_PRESSED_ON = { 
             android.R.attr.state_pressed, 
             android.R.attr.state_checkable, 
             android.R.attr.state_checked 
         };

         private final static int[] KEY_STATE_NORMAL_OFF = { 
             android.R.attr.state_checkable 
         };

         private final static int[] KEY_STATE_PRESSED_OFF = { 
             android.R.attr.state_pressed, 
             android.R.attr.state_checkable 
         };

         private final static int[] KEY_STATE_NORMAL = {   
         };                                                
//         private final static int[] KEY_STATE_NORMAL_CUSTOM_BACKGROUD_BLUE = {   
//        	 R.attr.background_blue
//         };                                                
//         private final static int[] KEY_STATE_NORMAL_CUSTOM_BACKGROUD_GRAY = {   
//        	 R.attr.background_gray
//         };     
//         private final static int[] KEY_STATE_NORMAL_CUSTOM_BACKGROUD_WHITE = {   
//        	 R.attr.background_white
//         }; 
         private final static int[] KEY_STATE_NORMAL_CUSTOM_BACKGROUD_BLUE = {   
        	 Res.getAttrID(Rc.getApplication(), "background_blue")
         };                                                
         private final static int[] KEY_STATE_NORMAL_CUSTOM_BACKGROUD_GRAY = {
             Res.getAttrID(Rc.getApplication(), "background_gray")
         };     
         private final static int[] KEY_STATE_NORMAL_CUSTOM_BACKGROUD_WHITE = {
              Res.getAttrID(Rc.getApplication(), "background_white")
         };
         private final static int[] KEY_STATE_PRESSED = {  
             android.R.attr.state_pressed                  
         };                                                

         
    	public LatinKey(Resources res, Keyboard.Row parent, int x, int y, XmlResourceParser parser) {
            super(res, parent, x, y, parser);
        }
        
        /**
         * Overriding this method so that we can reduce the target area for the key that
         * closes the keyboard. 
         */
        @Override
        public boolean isInside(int x, int y) {
            return super.isInside(x, codes[0] == KEYCODE_CANCEL ? y - 10 : y);
        }
        
        @Override
        public int[] getCurrentDrawableState() {        
            int[] states = KEY_STATE_NORMAL; 
            if (background_blue) {
            	states = KEY_STATE_NORMAL_CUSTOM_BACKGROUD_BLUE;
            } else if (background_gray) {
            	states = KEY_STATE_NORMAL_CUSTOM_BACKGROUD_GRAY;
            } else if (background_white) {
            	states = KEY_STATE_NORMAL_CUSTOM_BACKGROUD_WHITE;
            }

            if (on) {                                   
                if (pressed) {                          
                    states = KEY_STATE_PRESSED_ON;      
                } else {
                    states = KEY_STATE_NORMAL_ON;       
                }                                       
            } else {                                    
                if (sticky) {                           
                    if (pressed) {                      
                        states = KEY_STATE_PRESSED_OFF; 
                    } else {                            
                        states = KEY_STATE_NORMAL_OFF;  
                    }                                   
                } else {                                
                    if (pressed) {                      
                        states = KEY_STATE_PRESSED;     
                    }                                   
                }                                       
            }                                           
            return states;                              
        }  

    }

}
