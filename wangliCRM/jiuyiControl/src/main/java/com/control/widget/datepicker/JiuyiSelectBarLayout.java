package com.control.widget.datepicker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.canvas.CRect;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.JiuyiButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;


@SuppressLint("NewApi")
public class JiuyiSelectBarLayout extends LinearLayout {
    Animation animation = null;
    LinkedList<View> listPager = null;
    private int CommonHqMenuBarPaddingLeftorRight = 0;
    private int position_one;
    private int oneBarHalf;
    boolean bhaveBarHalf = true;// 是否有oneBarHalf
    private int position_two;
    private int position_three;
    private int position_four;
    private int position_five;
    private int currIndex = 0;
    String[][] strMenuData;
    ImageView imageview_hqMenuBarSelect;
    LinearLayout CommonHqMenuBars, CommonHqMenuBar;
    CRect pTableRect;
    // TableBase pTable, pTableNext;
    boolean nHaveSanJiao = true;
    private tztSelectDateActivityCallBack pCallBack;
    CRect pBodyRect;

    protected int m_nTabTextColor;//Pub.BgColor;
    protected int m_nTabSelTextColor;//Pub.BgColor;
    protected int m_nTabBgColor;//Pub.BgColor;
    protected int m_nTabSelBgColor;//mTabBgColor;

    private int m_nInputColor;//Rc.cfg.pPageAttrSet.bIsGeneralEdition() ? Pub.fontColor : 0xFF006fc4;
    private int m_nConfirmColor;//Rc.cfg.pPageAttrSet.bIsGeneralEdition() ? Pub.fontColor : Pub.BgColor;
    // =========================================
    private TextView m_StartDateTextView, m_EndDateTextView;// 开始和结束日期

    int m_bYear;
    int m_bMonth;
    int m_bDay;
    int m_eYear;
    int m_eMonth;
    int m_eDay;
    int m_Year;
    int m_Month;
    int m_Day;
    long m_tmpYestoday;//昨天日期，即结束日期不能大于昨天

    long m_beginDay = 0;
    long m_endDay = 0;
    boolean m_bTimeLiandong = false;
    boolean m_bIncludeToday = false;

    int DefaultDays = 7;// 默认的时间跨度
    int[] mLiandongDays = null;
    boolean bHaveTab = true;

    int m_nMaxDays = 365;//查询时间跨度做固定限制天数,默认

    int m_MaxYear;
    int m_MaxMonth;
    int m_MaxDay;

    public JiuyiSelectBarLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public JiuyiSelectBarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public JiuyiSelectBarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public JiuyiSelectBarLayout(Context context) {
        super(context);
    }

    public boolean isIncludeToday() {
        return m_bIncludeToday;
    }

    public void setSelectBarLayout(CRect pRect, tztSelectDateActivityCallBack callBack, String strData) {
        pCallBack = callBack;
        this.pBodyRect = pRect;

        //是否包含当天,1包含，0不包含，其他采用设置的值
        int nInclude = pCallBack.getIncludeToday();
        m_bIncludeToday = (nInclude == 1) ? true : (nInclude == 0 ? false : m_bIncludeToday);

        if (!Func.IsStringEmpty(strData)) {
            strMenuData = Func.SplitStr2Array(strData);
        } else {
            bHaveTab = false;
        }
        mLiandongDays = new int[]{2, 6, getCurrentMonthDays()};
        currIndex = 1;// 默认为一周
        m_nMaxDays = mLiandongDays[currIndex] + 1;
        DefaultDays = mLiandongDays[1];// 默认的时间跨度

        m_bTimeLiandong = true;

        m_nMaxDays = 60;

        initColor();
        onInit();
    }

    /**
     * 切换皮肤
     */
    public void changeSkinType(){
        initColor();
        onInit();
    }

    private void initColor(){
        m_nTabTextColor = Res.getColor(getContext(), "tzt_searchdate_toptab_color");
        m_nTabSelTextColor = Res.getColor(getContext(), "tzt_searchdate_toptab_color");
        m_nTabBgColor = Res.getColor(getContext(), "tzt_searchdate_toptab_bgcolor");
        m_nTabSelBgColor = Res.getColor(getContext(), "tzt_searchdate_toptab_bgcolor");

        m_nInputColor = Res.getColor(getContext(), "tzt_SearchSelDate_edittext_color");//Rc.cfg.pPageAttrSet.bIsGeneralEdition() ? Pub.fontColor : 0xFF006fc4;
        m_nConfirmColor = Res.getColor(getContext(), "tzt_SearchSelDate_confirm_color");//Rc.cfg.pPageAttrSet.bIsGeneralEdition() ? Pub.fontColor : Pub.BgColor;

    }

    public void onInit() {
        removeAllViews();

        setOrientation(LinearLayout.VERTICAL);

        if (strMenuData != null) {
            InitWidth();
            initTitleBar();
        }
        initDateSelect();

        m_MaxYear = m_eYear;
        m_MaxMonth = m_eMonth;
        m_MaxDay = m_eDay;
    }

    public void OnConfirm() {
        if (m_beginDay > m_endDay) {
            pCallBack.startDialog(DialogID.DialogDoNothing, "", "开始日期不能大于结束日期！\r\n请重新选择日期！", JiuyiDialogBase.Dialog_Type_No);
        }
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            Date de = df.parse(m_eYear + "-" + (m_eMonth + 1) + "-" + m_eDay);
            Date db = df.parse(m_bYear + "-" + (m_bMonth + 1) + "-" + m_bDay);

            long diff = de.getTime() - db.getTime();
            long days = diff / (1000 * 60 * 60 * 24);
            if ((days + 1) > m_nMaxDays) {
                pCallBack.startDialog(DialogID.DialogDoNothing, "", "查询时间跨度不大于" + m_nMaxDays + "天!", JiuyiDialogBase.ActionDlg_Cancle);
                //				showErrorMessage("查询时间跨度不大于"+mMaxDays+"天!", 0);
                return;
            }
        } catch (Exception e) {
        }
        pCallBack.setConfirmSelectDate(m_beginDay, m_endDay);
    }

    private void initTitleBar() {
        nHaveSanJiao = Res.getDrawabelID(getContext(), "tzt_shichang_radioviewpage_sanjiao") > 0;
        // 横线
        LayoutParams commonHqMenuBarParams = new LayoutParams(LayoutParams.FILL_PARENT, 1);

        int biaoshi = nHaveSanJiao ? getResources().getDrawable(Res.getDrawabelID(getContext(), "tzt_shichang_radioviewpage_sanjiao")).getIntrinsicHeight() : 0;// 一行的高度

        // 行情五大功能textview
        CommonHqMenuBars = new LinearLayout(getContext());
        commonHqMenuBarParams = new LayoutParams(pBodyRect.Width(), Rc.getIns().getTitleHeight());
        CommonHqMenuBars.setLayoutParams(commonHqMenuBarParams);
        CommonHqMenuBars.setOrientation(LinearLayout.VERTICAL);
        CommonHqMenuBars.setBackgroundColor(m_nTabBgColor);
        CommonHqMenuBar = new LinearLayout(getContext());
        CommonHqMenuBar.setGravity(Gravity.CENTER);
        CommonHqMenuBar.setPadding(CommonHqMenuBarPaddingLeftorRight, 0, CommonHqMenuBarPaddingLeftorRight, 0);
        commonHqMenuBarParams = new LayoutParams(LayoutParams.FILL_PARENT, Rc.getIns().getTitleHeight() - biaoshi);
        commonHqMenuBarParams.gravity = Gravity.CENTER;
        commonHqMenuBarParams.setMargins(0, 0, 0, 0);
        CommonHqMenuBar.setLayoutParams(commonHqMenuBarParams);
        CommonHqMenuBar.setBackgroundColor(m_nTabBgColor);

        if (strMenuData == null || strMenuData.length <= 0) {
            return;
        }
        //
        LayoutParams textview_HqMenuBarParams = new LayoutParams(strMenuData.length, LayoutParams.FILL_PARENT);
        textview_HqMenuBarParams.gravity = Gravity.CENTER;

        //
        int i = 0;
        TextView textview_hs = new TextView(getContext());
        setTextview(textview_hs, strMenuData[i][0], Rc.getIns().getCanvasMainFont(), textview_HqMenuBarParams);
        textview_hs.setOnClickListener(new MyOnClickListener(i));
        textview_hs.setTag(getClass().getSimpleName()+"_bar"+i);
        textview_hs.setId(i);
        textview_hs.setTextColor(m_nTabSelTextColor);
        textview_hs.setBackgroundColor(m_nTabBgColor);
        CommonHqMenuBar.addView(textview_hs);
        for (i = 1; i < strMenuData.length; i++) {
            TextView textview_zixuan = new TextView(getContext());
            setTextview(textview_zixuan, strMenuData[i][0], Rc.getIns().getCanvasMainFont(), textview_HqMenuBarParams);

            textview_zixuan.setOnClickListener(new MyOnClickListener(i));
            textview_zixuan.setTag(getClass().getSimpleName()+"_bar"+i);
            textview_zixuan.setId(i);
            if (i == 0) {
                textview_zixuan.setTextColor(m_nTabSelTextColor);
                textview_zixuan.setBackgroundColor(m_nTabSelBgColor);
            } else {
                textview_zixuan.setTextColor(m_nTabTextColor);
                textview_zixuan.setBackgroundColor(m_nTabSelBgColor);
            }

            CommonHqMenuBar.addView(textview_zixuan);
        }
        CommonHqMenuBars.addView(CommonHqMenuBar);

        if (nHaveSanJiao) {
            imageview_hqMenuBarSelect = new ImageView(getContext());
            LayoutParams BarSelectPar = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

            BarSelectPar.setMargins(position_one, 0, 0, 0);
            //通过这个动画确定默认选中（不然影响切换动画）
            currIndex = 0;
            setAnimation(1);

            imageview_hqMenuBarSelect.setLayoutParams(BarSelectPar);
            imageview_hqMenuBarSelect.setImageResource(Res.getDrawabelID(getContext(), "tzt_shichang_radioviewpage_sanjiao"));
            int width = getResources().getDrawable(Res.getDrawabelID(getContext(), "tzt_shichang_radioviewpage_sanjiao")).getIntrinsicWidth();
            if (width > Res.Dip2Pix(20)) {// 不是三角，是横线
                bhaveBarHalf = false;
            }
            if (!bhaveBarHalf)
                imageview_hqMenuBarSelect.setMaxWidth(pBodyRect.Width() / (strMenuData.length));
            CommonHqMenuBars.addView(imageview_hqMenuBarSelect);
        }
        addView(CommonHqMenuBars);

    }

    public void initDateSelect() {
        int padding = Res.Dip2Pix(5);
        LinearLayout Datalayout = new LinearLayout(getContext());
        Datalayout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        Datalayout.setOrientation(LinearLayout.HORIZONTAL);
        if (bHaveTab) {
            Datalayout.setBackgroundColor(Res.getColor(getContext(), "tzt_SearchSelDate_havetab_bgcolor"));
        } else {
            Datalayout.setBackgroundColor(Res.getColor(getContext(), "tzt_SearchSelDate_notab_bgcolor"));
        }
        Datalayout.setGravity(Gravity.CENTER);
        LayoutParams lpEdit = new LinearLayout.LayoutParams(pBodyRect.Width() / 3 - padding * 2, LayoutParams.FILL_PARENT);
        lpEdit.setMargins(padding, padding, padding, padding);
        m_StartDateTextView = new TextView(getContext());
        m_StartDateTextView.setLayoutParams(lpEdit);
        m_StartDateTextView.setTextSize(Rc.getIns().getCanvasMainFont());
        m_StartDateTextView.setHint("开始日期");
        m_StartDateTextView.setBackgroundResource(Res.getDrawabelID(getContext(), "tzt_searchdate_bg"));
        m_StartDateTextView.setTextColor(m_nInputColor);
        m_StartDateTextView.setGravity(Gravity.CENTER);
        m_StartDateTextView.setOnClickListener(pStartDateClickListener);
        m_StartDateTextView.setTag(getClass().getSimpleName()+"_begin");

        LayoutParams lpArrow = new LinearLayout.LayoutParams(pBodyRect.Width() / (3 * 4), LayoutParams.FILL_PARENT);
        lpArrow.setMargins(0, padding, 0, padding);
        TextView txtjt = new TextView(getContext());
        txtjt.setLayoutParams(lpArrow);
        txtjt.setTextSize(Rc.getIns().getCanvasMainFont());
        txtjt.setText(" ↔ ");

//		txtjt.setTextColor(Pub.fontColor);
        txtjt.setTextColor(Res.getColor(getContext(), "tzt_SearchSelDate_arrow_color"));
        txtjt.setGravity(Gravity.CENTER);

        m_EndDateTextView = new TextView(getContext());
        m_EndDateTextView.setLayoutParams(lpEdit);
        m_EndDateTextView.setTextSize(Rc.getIns().getCanvasMainFont());
        m_EndDateTextView.setHint("结束日期");
        m_EndDateTextView.setBackgroundResource(Res.getDrawabelID(getContext(), "tzt_searchdate_bg"));
        m_EndDateTextView.setTextColor(m_nInputColor);
        m_EndDateTextView.setGravity(Gravity.CENTER);
        m_EndDateTextView.setOnClickListener(pEndDateClickListener);
        m_EndDateTextView.setTag(getClass().getSimpleName()+"_end");

        final JiuyiButton btnConfirm = new JiuyiButton(getContext());
        btnConfirm.setCheckFastDoubleClick(true);
        btnConfirm.setDelay(1000);
        LayoutParams lpConfirm = new LinearLayout.LayoutParams(pBodyRect.Width() / (4) - padding, LayoutParams.FILL_PARENT);
        lpConfirm.setMargins(0, padding, padding, padding);
        btnConfirm.setLayoutParams(lpConfirm);
        // btnConfirm.setTextSize(Rc.getIns().getCanvasMainFont());
        btnConfirm.setText("确定");
        btnConfirm.setTextColor(m_nConfirmColor);
        btnConfirm.setBackgroundResource(Res.getDrawabelID(getContext(), "tzt_v23_button_bg_selector"));
        btnConfirm.setGravity(Gravity.CENTER);
        btnConfirm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                OnConfirm();
            }
        });
        btnConfirm.setTag(getClass().getSimpleName()+"_confirm");

        Datalayout.addView(m_StartDateTextView);
        Datalayout.addView(txtjt);
        Datalayout.addView(m_EndDateTextView);
        Datalayout.addView(btnConfirm);
        addView(Datalayout);

        initData(DefaultDays);
    }

    public void initData(int nCircle) {
        int subday = m_bIncludeToday ? 0 : -1;

        //设置时间
        Calendar m_Curdate = Calendar.getInstance();
        Calendar m_Prdate = Calendar.getInstance();
        m_Curdate.add(Calendar.DAY_OF_MONTH, subday);//默认周
        m_Prdate.add(Calendar.DAY_OF_MONTH, -nCircle + subday);//默认周

        m_eYear = m_Curdate.get(Calendar.YEAR);// 获取当前年份
        m_eMonth = m_Curdate.get(Calendar.MONTH);// 获取当前月份
        m_eDay = m_Curdate.get(Calendar.DAY_OF_MONTH);// 获取当前月份的日期号码
        m_endDay = m_eYear * 10000 + (m_eMonth + 1) * 100 + m_eDay;
        m_tmpYestoday = m_endDay;

        m_Year = m_eYear;
        m_Month = m_eMonth;
        m_Day = m_eDay;
        updateEndDisplay(m_EndDateTextView);

        m_bYear = m_Prdate.get(Calendar.YEAR);
        m_bMonth = m_Prdate.get(Calendar.MONTH);
        m_bDay = m_Prdate.get(Calendar.DAY_OF_MONTH);
        m_beginDay = m_bYear * 10000 + (m_bMonth + 1) * 100 + m_bDay;

        m_Year = m_bYear;
        m_Month = m_bMonth;
        m_Day = m_bDay;
        updateBeginDisplay(m_StartDateTextView);

        pCallBack.setConfirmSelectDate(m_beginDay, m_endDay);
    }

    public void setDatetpickerValue(int index, int mYear, int mMonth, int mDay) {
        if (index == 2) {
            m_eYear = mYear;
            m_eMonth = mMonth;
            m_eDay = mDay;
            m_endDay = m_eYear * 10000 + (m_eMonth + 1) * 100 + m_eDay;
            updateEndDisplay(m_EndDateTextView);

            if (m_bTimeLiandong && mLiandongDays[currIndex] > 0) {
                Calendar m_Curdate = Calendar.getInstance();
                m_Curdate.set(m_eYear, m_eMonth, m_eDay);
                m_Curdate.add(Calendar.DAY_OF_MONTH, -mLiandongDays[currIndex]);
                m_bYear = m_Curdate.get(Calendar.YEAR);
                m_bMonth = m_Curdate.get(Calendar.MONTH);
                m_bDay = m_Curdate.get(Calendar.DATE);
                m_beginDay = m_bYear * 10000 + (m_bMonth + 1) * 100 + m_bDay;
                updateBeginDisplay(m_StartDateTextView);
            }
        } else if (index == 1) {
            m_bYear = mYear;
            m_bMonth = mMonth;
            m_bDay = mDay;
            m_beginDay = m_bYear * 10000 + (m_bMonth + 1) * 100 + m_bDay;
            updateBeginDisplay(m_StartDateTextView);

            if (m_bTimeLiandong && mLiandongDays[currIndex] > 0) {
                Calendar m_Curdate = Calendar.getInstance();
                m_Curdate.set(m_bYear, m_bMonth, m_bDay);
                m_Curdate.add(Calendar.DAY_OF_MONTH, +mLiandongDays[currIndex]);
                m_eYear = m_Curdate.get(Calendar.YEAR);
                m_eMonth = m_Curdate.get(Calendar.MONTH);
                m_eDay = m_Curdate.get(Calendar.DATE);

                Calendar m_MaxCurdate = Calendar.getInstance();
                m_MaxCurdate.set(m_MaxYear, m_MaxMonth, m_MaxDay);
                if (m_Curdate.getTime().after(m_MaxCurdate.getTime())) {
                    m_eYear = m_MaxYear;
                    m_eMonth = m_MaxMonth;
                    m_eDay = m_MaxDay;
                }

                m_endDay = m_eYear * 10000 + (m_eMonth + 1) * 100 + m_eDay;
                updateEndDisplay(m_EndDateTextView);
            }
        }
    }

    /**
     * 监听和处理
     */
    OnClickListener pStartDateClickListener = new OnClickListener() {
        public void onClick(View v) {
            pCallBack.showDatepicker(1, m_bYear, (m_bMonth + 1), m_bDay);
        }
    };

    /**
     * 监听和处理
     */
    OnClickListener pEndDateClickListener = new OnClickListener() {
        public void onClick(View v) {
            pCallBack.showDatepicker(2, m_eYear, (m_eMonth + 1), m_eDay);
        }
    };
//
//	// 获得当前日期
//	private DatePickerDialog.OnDateSetListener mDateSetListenerStart = new DatePickerDialog.OnDateSetListener() {
//		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//			m_bYear = year;
//			m_bMonth = monthOfYear;
//			m_bDay = dayOfMonth;
//			m_beginDay = m_bYear * 10000 + (m_bMonth + 1) * 100 + m_bDay;
//			updateBeginDisplay(m_StartDateTextView);
//
//			if (m_bTimeLiandong && mLiandongDays[currIndex] > 0) {
//				Calendar m_Curdate = Calendar.getInstance();
//				m_Curdate.set(m_bYear, m_bMonth, m_bDay);
//				m_Curdate.add(Calendar.DAY_OF_MONTH, +mLiandongDays[currIndex]);
//				m_eYear = m_Curdate.get(Calendar.YEAR);
//				m_eMonth = m_Curdate.get(Calendar.MONTH);
//				m_eDay = m_Curdate.get(Calendar.DATE);
//
//				//update by zhengss20160726(判断起始日期有没有超过最大日期,超过了 则使用最大日期)
//				Calendar m_MaxCurdate = Calendar.getInstance();
//				m_MaxCurdate.set(m_MaxYear, m_MaxMonth, m_MaxDay); 
//				if(m_Curdate.getTime().after(m_MaxCurdate.getTime())){
//					m_eYear =m_MaxYear;
//					m_eMonth = m_MaxMonth;
//					m_eDay = m_MaxDay;
//				}
//
//				m_endDay = m_eYear * 10000 + (m_eMonth + 1) * 100 + m_eDay;
//				updateEndDisplay(m_EndDateTextView);
//			}
//		}
//	};
//
//	// 获得当前日期
//	private DatePickerDialog.OnDateSetListener mDateSetListenerEnd = new DatePickerDialog.OnDateSetListener() {
//		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//			m_eYear = year;
//			m_eMonth = monthOfYear;
//			m_eDay = dayOfMonth;
//			m_endDay = m_eYear * 10000 + (m_eMonth + 1) * 100 + m_eDay;
//			updateEndDisplay(m_EndDateTextView);
//
//			if (m_bTimeLiandong && mLiandongDays[currIndex] > 0) {
//				Calendar m_Curdate = Calendar.getInstance();
//				m_Curdate.set(m_eYear, m_eMonth, m_eDay);
//				m_Curdate.add(Calendar.DAY_OF_MONTH, -mLiandongDays[currIndex]);
//				m_bYear = m_Curdate.get(Calendar.YEAR);
//				m_bMonth = m_Curdate.get(Calendar.MONTH);
//				m_bDay = m_Curdate.get(Calendar.DATE);
//				m_beginDay = m_bYear * 10000 + (m_bMonth + 1) * 100 + m_bDay;
//				updateBeginDisplay(m_StartDateTextView);
//			}
//		}
//	};

    // 将时间显示到文本框中
    private void updateBeginDisplay(TextView text) {
        text.setText(new StringBuilder().append(m_bYear).append("-").append(formateTime(m_bMonth + 1)).append("-").append(formateTime(m_bDay)));
    }

    private void updateEndDisplay(TextView text) {
        text.setText(new StringBuilder().append(m_eYear).append("-").append(formateTime(m_eMonth + 1)).append("-").append(formateTime(m_eDay)));
    }

    private String formateTime(int time) {
        String timeStr = "";
        if (time < 10) {
            timeStr = "0" + String.valueOf(time);
        } else {
            timeStr = String.valueOf(time);
        }
        return timeStr;
    }

    public class MyOnClickListener implements View.OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
            int nSelIndex = v.getId();

            m_nMaxDays = mLiandongDays[nSelIndex] + 1;

            setAnimation(nSelIndex);

            if (currIndex == 0) {
                initData(2);
            } else if (currIndex == 1) {
                initData(6);
            } else {
                initData(mLiandongDays[nSelIndex]);
            }
        }
    }

    ;

    public void resetAnimation() {
        if (animation != null && imageview_hqMenuBarSelect != null) {
            animation.setFillAfter(true);
            animation.setDuration(300);
            if (nHaveSanJiao)
                imageview_hqMenuBarSelect.startAnimation(animation);
        }
    }

    public void setAnimation(int nSelIndex) {

        int oneBarHalf = bhaveBarHalf ? JiuyiSelectBarLayout.this.oneBarHalf : 0;
        switch (nSelIndex) {
            case 0:
                if (currIndex == 1) {
                    animation = new TranslateAnimation(position_two, 0, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(position_three
                            - oneBarHalf, 0, 0, 0);
                } else if (currIndex == 3) {
                    animation = new TranslateAnimation(position_four
                            - oneBarHalf, 0, 0, 0);
                } else if (currIndex == 4) {
                    animation = new TranslateAnimation(position_five
                            - oneBarHalf, 0, 0, 0);
                }
                break;
            case 1:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(position_one,
                            position_two - oneBarHalf, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(position_three,
                            position_two - oneBarHalf, 0, 0);
                } else if (currIndex == 3) {
                    animation = new TranslateAnimation(position_four
                            - oneBarHalf, position_two - oneBarHalf, 0, 0);
                } else if (currIndex == 4) {
                    animation = new TranslateAnimation(position_five
                            - oneBarHalf, position_two - oneBarHalf, 0, 0);
                }
                break;
            case 2:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(0, position_three
                            - oneBarHalf, 0, 0);
                } else if (currIndex == 1) {
                    animation = new TranslateAnimation(position_two,
                            position_three - oneBarHalf, 0, 0);
                } else if (currIndex == 3) {
                    animation = new TranslateAnimation(position_four,
                            position_three - oneBarHalf, 0, 0);
                } else if (currIndex == 4) {
                    animation = new TranslateAnimation(position_five,
                            position_three - oneBarHalf, 0, 0);
                }
                break;
            case 3:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(0, position_four
                            - oneBarHalf, 0, 0);
                } else if (currIndex == 1) {
                    animation = new TranslateAnimation(position_two,
                            position_four - oneBarHalf, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(position_three,
                            position_four - oneBarHalf, 0, 0);
                } else if (currIndex == 4) {
                    animation = new TranslateAnimation(position_five,
                            position_four - oneBarHalf, 0, 0);
                }
                break;
            case 4:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(0, position_five
                            - oneBarHalf, 0, 0);
                } else if (currIndex == 1) {
                    animation = new TranslateAnimation(position_two,
                            position_five - oneBarHalf, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(position_three,
                            position_five - oneBarHalf, 0, 0);
                } else if (currIndex == 3) {
                    animation = new TranslateAnimation(position_four,
                            position_five - oneBarHalf, 0, 0);
                }
                break;
        }
        currIndex = nSelIndex;

        if (animation != null) {
            animation.setFillAfter(true);
            animation.setDuration(300);
            if (nHaveSanJiao)
                imageview_hqMenuBarSelect.startAnimation(animation);
        }

        for (int i = 0; i < CommonHqMenuBar.getChildCount(); i++) {
            TextView tmp = (TextView) CommonHqMenuBar.getChildAt(i);
            if (tmp.getId() == currIndex) {
                tmp.setTextColor(m_nTabSelTextColor);
                tmp.setBackgroundColor(m_nTabSelBgColor);
            } else {
                tmp.setTextColor(m_nTabTextColor);
                tmp.setBackgroundColor(m_nTabBgColor);
            }
        }
    }

    private void setTextview(TextView view, String text, int textSize, LayoutParams params) {
        view.setText(text);
        view.setGravity(Gravity.CENTER);
        view.setTextSize(textSize);
        params.weight = 1;
        view.setTextColor(Color.WHITE);
        view.setLayoutParams(params);
    }

    private void InitWidth() {
        if (strMenuData == null)
            return;
        oneBarHalf = (pBodyRect.Width() - CommonHqMenuBarPaddingLeftorRight * 2) / strMenuData.length / 2;
        position_one = oneBarHalf + CommonHqMenuBarPaddingLeftorRight;
        position_two = CommonHqMenuBarPaddingLeftorRight + oneBarHalf * 3;
        position_three = CommonHqMenuBarPaddingLeftorRight + oneBarHalf * 5;
        position_four = CommonHqMenuBarPaddingLeftorRight + oneBarHalf * 7;
        position_five = CommonHqMenuBarPaddingLeftorRight + oneBarHalf * 9;
    }

    /**
     * 取得当月天数
     * */
    public int getCurrentMonthDays()
    {
//        Calendar a = Calendar.getInstance();
//        a.set(Calendar.DATE, 1);//把日期设置为当月第一天
//        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
//        int maxDate = a.get(Calendar.DATE);
//        return maxDate;
        //统一前推30天，与ios一致
        return 30;
    }

    public interface tztSelectDateActivityCallBack {
        public void setConfirmSelectDate(long lBeginDate, long lEndDate);

        public void showDatepicker(int index, int mYear, int mMonth, int mDay);

        public int getIncludeToday();

        public void startDialog(final int nAction, final String sTitle, final String sContent, final int nBtnType);
    }
}
