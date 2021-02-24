package com.control.setting;
/**
 * ****************************************************************
 * 文件名称 : JiuyiHqAttrSetSpeech.java
 * 作 者 :   zhengss
 * 创建时间:2018/4/2 17:01
 * 文件描述 : 语音模块
 *****************************************************************
 */
public class JiuyiHqAttrSetSpeech {

    /**
	 * 是否有语音合成模块-资讯朗读
	 */
	private boolean mTztText2Speech = false;
	
	/**
	 * 标题是否显示切换语音朗读按钮
	 * true 显示 （默认）
	 * false 不显示
	 */
    private boolean mIsTitleShowSpeak =false;
	
	/**
	 * 是否有语音听写模块-语音搜索
	 */
    private boolean mHaveTztSpeech2Text = false;


    /**
     * 是否有“技术支持”项,不要去掉，否则侵权
     */
    private boolean mHaveJiShuZhiChiNote = true;



    public void setTztText2Speech(boolean bTztText2Speech) {
        this.mTztText2Speech = bTztText2Speech;
    }

    public void setTitleShowSpeak(boolean bTitleShowSpeak) {
        this.mIsTitleShowSpeak = bTitleShowSpeak;
    }

    public void setTztSpeech2Text(boolean bTztSpeech2Text) {
        this.mHaveTztSpeech2Text = bTztSpeech2Text;
    }

    public boolean IsTztText2Speech(){
		return mTztText2Speech;
	}	
	
	public boolean isTitleShowSpeak(){
		return mIsTitleShowSpeak;
	}
	
	public boolean IsTztSpeech2Text(){
		return mHaveTztSpeech2Text;
	}

    public boolean isHaveJiShuZhiChiNote() {
        return mHaveJiShuZhiChiNote;
    }

    public void setHaveJiShuZhiChiNote(boolean mHaveJiShuZhiChiNote) {
        this.mHaveJiShuZhiChiNote = mHaveJiShuZhiChiNote;
    }
}
