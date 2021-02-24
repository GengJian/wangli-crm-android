package commonlyused.bean;

import java.util.List;

/**
 * ****************************************************************
 * 文件名称:PlanDailySalesBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/7/17 13:58
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/7/17 1.00 初始版本
 * ****************************************************************
 */
public class PlanDailySalesBean {

    private List<DailySalesBean> dailySales;

    public List<DailySalesBean> getDailySales() {
        return dailySales;
    }

    public void setDailySales(List<DailySalesBean> dailySales) {
        this.dailySales = dailySales;
    }

    public static class DailySalesBean {
        private List<RETURNBean> RETURN;

        public List<RETURNBean> getRETURN() {
            return RETURN;
        }

        public void setRETURN(List<RETURNBean> RETURN) {
            this.RETURN = RETURN;
        }

        public static class RETURNBean {
            /**
             * LFIMG_THISYEAR_LS : 350.000
             * LFIMG_MAY_LS : 18.000
             * LFIMG_APR_GC : 0.000
             * LFIMG_DEC : 142.000
             * LFIMG_FEB : 0.000
             * LFIMG_JAN_GC : 0.000
             * LSDZKC : 0.000
             * LFIMG_JUNE_LS : 14.000
             * BEZEI : 江西
             * KUNNR : 0002000074
             * LFIMG_THISYEAR : 460.000
             * LFIMG_DAY : 0.000
             * LFIMG_AUG_LS : 10.000
             * LFIMG_JAN : 10.000
             * TELF2 : 18970286488
             * LFIMG_JUNE : 14.000
             * LFIMG_NOV_LS : 52.000
             * LFIMG_APR : 22.000
             * LFIMG_OCT : 78.000
             * BRAND : 王力
             * REGIO : 140
             * LFIMG_AUG : 10.000
             * LFIMG_DEC_LS : 32.000
             * LFIMG_FEB_GC : 0.000
             * GJAHR : 2018
             * LFIMG_OCT_GC : 0.000
             * BRTXT : 零售经销商
             * KUNNR_NAME : 飞陈
             * LFIMG_JAN_LS : 10.000
             * LFIMG_DEC_GC : 110.000
             * LFIMG_SEPT_GC : 0.000
             * TELFX : 0792-4236988
             * LFIMG_JULY_GC : 0.000
             * LFIMG_JULY : 28.000
             * ERDAT : 2017-02-23
             * LFIMG_NOV : 52.000
             * LFIMG_MAR_GC : 0.000
             * BRSCH : 0002
             * HTDZKC : 0.000
             * LFIMG_OCT_LS : 78.000
             * LFIMG_MAY : 18.000
             * ORT02 :
             * LFIMG_MAR : 38.000
             * ORT01 :
             * NAME1 : 瑞昌市瑞飞建材经营部
             * LFIMG_SEPT : 48.000
             * LFIMG_APR_LS : 22.000
             * LFIMG_MAR_LS : 38.000
             * LFIMG_AUG_GC : 0.000
             * LFIMG_JUNE_GC : 0.000
             * LFIMG_FEB_LS : 0.000
             * LFIMG_NOV_GC : 0.000
             * LFIMG_JULY_LS : 28.000
             * LFIMG_THISYEAR_GC : 110.000
             * LFIMG_SEPT_LS : 48.000
             * LFIMG_MAY_GC : 0.000
             */

            private String LFIMG_THISYEAR_LS;
            private String LFIMG_MAY_LS;
            private String LFIMG_APR_GC;
            private String LFIMG_DEC;
            private String LFIMG_FEB;
            private String LFIMG_JAN_GC;
            private String LSDZKC;
            private String LFIMG_JUNE_LS;
            private String BEZEI;
            private String KUNNR;
            private String LFIMG_THISYEAR;
            private String LFIMG_DAY;
            private String LFIMG_AUG_LS;
            private String LFIMG_JAN;
            private String TELF2;
            private String LFIMG_JUNE;
            private String LFIMG_NOV_LS;
            private String LFIMG_APR;
            private String LFIMG_OCT;
            private String BRAND;
            private String REGIO;
            private String LFIMG_AUG;
            private String LFIMG_DEC_LS;
            private String LFIMG_FEB_GC;
            private String GJAHR;
            private String LFIMG_OCT_GC;
            private String BRTXT;
            private String KUNNR_NAME;
            private String LFIMG_JAN_LS;
            private String LFIMG_DEC_GC;
            private String LFIMG_SEPT_GC;
            private String TELFX;
            private String LFIMG_JULY_GC;
            private String LFIMG_JULY;
            private String ERDAT;
            private String LFIMG_NOV;
            private String LFIMG_MAR_GC;
            private String BRSCH;
            private String HTDZKC;
            private String LFIMG_OCT_LS;
            private String LFIMG_MAY;
            private String ORT02;
            private String LFIMG_MAR;
            private String ORT01;
            private String NAME1;
            private String LFIMG_SEPT;
            private String LFIMG_APR_LS;
            private String LFIMG_MAR_LS;
            private String LFIMG_AUG_GC;
            private String LFIMG_JUNE_GC;
            private String LFIMG_FEB_LS;
            private String LFIMG_NOV_GC;
            private String LFIMG_JULY_LS;
            private String LFIMG_THISYEAR_GC;
            private String LFIMG_SEPT_LS;
            private String LFIMG_MAY_GC;

            public String getLFIMG_THISYEAR_LS() {
                return LFIMG_THISYEAR_LS;
            }

            public void setLFIMG_THISYEAR_LS(String LFIMG_THISYEAR_LS) {
                this.LFIMG_THISYEAR_LS = LFIMG_THISYEAR_LS;
            }

            public String getLFIMG_MAY_LS() {
                return LFIMG_MAY_LS;
            }

            public void setLFIMG_MAY_LS(String LFIMG_MAY_LS) {
                this.LFIMG_MAY_LS = LFIMG_MAY_LS;
            }

            public String getLFIMG_APR_GC() {
                return LFIMG_APR_GC;
            }

            public void setLFIMG_APR_GC(String LFIMG_APR_GC) {
                this.LFIMG_APR_GC = LFIMG_APR_GC;
            }

            public String getLFIMG_DEC() {
                return LFIMG_DEC;
            }

            public void setLFIMG_DEC(String LFIMG_DEC) {
                this.LFIMG_DEC = LFIMG_DEC;
            }

            public String getLFIMG_FEB() {
                return LFIMG_FEB;
            }

            public void setLFIMG_FEB(String LFIMG_FEB) {
                this.LFIMG_FEB = LFIMG_FEB;
            }

            public String getLFIMG_JAN_GC() {
                return LFIMG_JAN_GC;
            }

            public void setLFIMG_JAN_GC(String LFIMG_JAN_GC) {
                this.LFIMG_JAN_GC = LFIMG_JAN_GC;
            }

            public String getLSDZKC() {
                return LSDZKC;
            }

            public void setLSDZKC(String LSDZKC) {
                this.LSDZKC = LSDZKC;
            }

            public String getLFIMG_JUNE_LS() {
                return LFIMG_JUNE_LS;
            }

            public void setLFIMG_JUNE_LS(String LFIMG_JUNE_LS) {
                this.LFIMG_JUNE_LS = LFIMG_JUNE_LS;
            }

            public String getBEZEI() {
                return BEZEI;
            }

            public void setBEZEI(String BEZEI) {
                this.BEZEI = BEZEI;
            }

            public String getKUNNR() {
                return KUNNR;
            }

            public void setKUNNR(String KUNNR) {
                this.KUNNR = KUNNR;
            }

            public String getLFIMG_THISYEAR() {
                return LFIMG_THISYEAR;
            }

            public void setLFIMG_THISYEAR(String LFIMG_THISYEAR) {
                this.LFIMG_THISYEAR = LFIMG_THISYEAR;
            }

            public String getLFIMG_DAY() {
                return LFIMG_DAY;
            }

            public void setLFIMG_DAY(String LFIMG_DAY) {
                this.LFIMG_DAY = LFIMG_DAY;
            }

            public String getLFIMG_AUG_LS() {
                return LFIMG_AUG_LS;
            }

            public void setLFIMG_AUG_LS(String LFIMG_AUG_LS) {
                this.LFIMG_AUG_LS = LFIMG_AUG_LS;
            }

            public String getLFIMG_JAN() {
                return LFIMG_JAN;
            }

            public void setLFIMG_JAN(String LFIMG_JAN) {
                this.LFIMG_JAN = LFIMG_JAN;
            }

            public String getTELF2() {
                return TELF2;
            }

            public void setTELF2(String TELF2) {
                this.TELF2 = TELF2;
            }

            public String getLFIMG_JUNE() {
                return LFIMG_JUNE;
            }

            public void setLFIMG_JUNE(String LFIMG_JUNE) {
                this.LFIMG_JUNE = LFIMG_JUNE;
            }

            public String getLFIMG_NOV_LS() {
                return LFIMG_NOV_LS;
            }

            public void setLFIMG_NOV_LS(String LFIMG_NOV_LS) {
                this.LFIMG_NOV_LS = LFIMG_NOV_LS;
            }

            public String getLFIMG_APR() {
                return LFIMG_APR;
            }

            public void setLFIMG_APR(String LFIMG_APR) {
                this.LFIMG_APR = LFIMG_APR;
            }

            public String getLFIMG_OCT() {
                return LFIMG_OCT;
            }

            public void setLFIMG_OCT(String LFIMG_OCT) {
                this.LFIMG_OCT = LFIMG_OCT;
            }

            public String getBRAND() {
                return BRAND;
            }

            public void setBRAND(String BRAND) {
                this.BRAND = BRAND;
            }

            public String getREGIO() {
                return REGIO;
            }

            public void setREGIO(String REGIO) {
                this.REGIO = REGIO;
            }

            public String getLFIMG_AUG() {
                return LFIMG_AUG;
            }

            public void setLFIMG_AUG(String LFIMG_AUG) {
                this.LFIMG_AUG = LFIMG_AUG;
            }

            public String getLFIMG_DEC_LS() {
                return LFIMG_DEC_LS;
            }

            public void setLFIMG_DEC_LS(String LFIMG_DEC_LS) {
                this.LFIMG_DEC_LS = LFIMG_DEC_LS;
            }

            public String getLFIMG_FEB_GC() {
                return LFIMG_FEB_GC;
            }

            public void setLFIMG_FEB_GC(String LFIMG_FEB_GC) {
                this.LFIMG_FEB_GC = LFIMG_FEB_GC;
            }

            public String getGJAHR() {
                return GJAHR;
            }

            public void setGJAHR(String GJAHR) {
                this.GJAHR = GJAHR;
            }

            public String getLFIMG_OCT_GC() {
                return LFIMG_OCT_GC;
            }

            public void setLFIMG_OCT_GC(String LFIMG_OCT_GC) {
                this.LFIMG_OCT_GC = LFIMG_OCT_GC;
            }

            public String getBRTXT() {
                return BRTXT;
            }

            public void setBRTXT(String BRTXT) {
                this.BRTXT = BRTXT;
            }

            public String getKUNNR_NAME() {
                return KUNNR_NAME;
            }

            public void setKUNNR_NAME(String KUNNR_NAME) {
                this.KUNNR_NAME = KUNNR_NAME;
            }

            public String getLFIMG_JAN_LS() {
                return LFIMG_JAN_LS;
            }

            public void setLFIMG_JAN_LS(String LFIMG_JAN_LS) {
                this.LFIMG_JAN_LS = LFIMG_JAN_LS;
            }

            public String getLFIMG_DEC_GC() {
                return LFIMG_DEC_GC;
            }

            public void setLFIMG_DEC_GC(String LFIMG_DEC_GC) {
                this.LFIMG_DEC_GC = LFIMG_DEC_GC;
            }

            public String getLFIMG_SEPT_GC() {
                return LFIMG_SEPT_GC;
            }

            public void setLFIMG_SEPT_GC(String LFIMG_SEPT_GC) {
                this.LFIMG_SEPT_GC = LFIMG_SEPT_GC;
            }

            public String getTELFX() {
                return TELFX;
            }

            public void setTELFX(String TELFX) {
                this.TELFX = TELFX;
            }

            public String getLFIMG_JULY_GC() {
                return LFIMG_JULY_GC;
            }

            public void setLFIMG_JULY_GC(String LFIMG_JULY_GC) {
                this.LFIMG_JULY_GC = LFIMG_JULY_GC;
            }

            public String getLFIMG_JULY() {
                return LFIMG_JULY;
            }

            public void setLFIMG_JULY(String LFIMG_JULY) {
                this.LFIMG_JULY = LFIMG_JULY;
            }

            public String getERDAT() {
                return ERDAT;
            }

            public void setERDAT(String ERDAT) {
                this.ERDAT = ERDAT;
            }

            public String getLFIMG_NOV() {
                return LFIMG_NOV;
            }

            public void setLFIMG_NOV(String LFIMG_NOV) {
                this.LFIMG_NOV = LFIMG_NOV;
            }

            public String getLFIMG_MAR_GC() {
                return LFIMG_MAR_GC;
            }

            public void setLFIMG_MAR_GC(String LFIMG_MAR_GC) {
                this.LFIMG_MAR_GC = LFIMG_MAR_GC;
            }

            public String getBRSCH() {
                return BRSCH;
            }

            public void setBRSCH(String BRSCH) {
                this.BRSCH = BRSCH;
            }

            public String getHTDZKC() {
                return HTDZKC;
            }

            public void setHTDZKC(String HTDZKC) {
                this.HTDZKC = HTDZKC;
            }

            public String getLFIMG_OCT_LS() {
                return LFIMG_OCT_LS;
            }

            public void setLFIMG_OCT_LS(String LFIMG_OCT_LS) {
                this.LFIMG_OCT_LS = LFIMG_OCT_LS;
            }

            public String getLFIMG_MAY() {
                return LFIMG_MAY;
            }

            public void setLFIMG_MAY(String LFIMG_MAY) {
                this.LFIMG_MAY = LFIMG_MAY;
            }

            public String getORT02() {
                return ORT02;
            }

            public void setORT02(String ORT02) {
                this.ORT02 = ORT02;
            }

            public String getLFIMG_MAR() {
                return LFIMG_MAR;
            }

            public void setLFIMG_MAR(String LFIMG_MAR) {
                this.LFIMG_MAR = LFIMG_MAR;
            }

            public String getORT01() {
                return ORT01;
            }

            public void setORT01(String ORT01) {
                this.ORT01 = ORT01;
            }

            public String getNAME1() {
                return NAME1;
            }

            public void setNAME1(String NAME1) {
                this.NAME1 = NAME1;
            }

            public String getLFIMG_SEPT() {
                return LFIMG_SEPT;
            }

            public void setLFIMG_SEPT(String LFIMG_SEPT) {
                this.LFIMG_SEPT = LFIMG_SEPT;
            }

            public String getLFIMG_APR_LS() {
                return LFIMG_APR_LS;
            }

            public void setLFIMG_APR_LS(String LFIMG_APR_LS) {
                this.LFIMG_APR_LS = LFIMG_APR_LS;
            }

            public String getLFIMG_MAR_LS() {
                return LFIMG_MAR_LS;
            }

            public void setLFIMG_MAR_LS(String LFIMG_MAR_LS) {
                this.LFIMG_MAR_LS = LFIMG_MAR_LS;
            }

            public String getLFIMG_AUG_GC() {
                return LFIMG_AUG_GC;
            }

            public void setLFIMG_AUG_GC(String LFIMG_AUG_GC) {
                this.LFIMG_AUG_GC = LFIMG_AUG_GC;
            }

            public String getLFIMG_JUNE_GC() {
                return LFIMG_JUNE_GC;
            }

            public void setLFIMG_JUNE_GC(String LFIMG_JUNE_GC) {
                this.LFIMG_JUNE_GC = LFIMG_JUNE_GC;
            }

            public String getLFIMG_FEB_LS() {
                return LFIMG_FEB_LS;
            }

            public void setLFIMG_FEB_LS(String LFIMG_FEB_LS) {
                this.LFIMG_FEB_LS = LFIMG_FEB_LS;
            }

            public String getLFIMG_NOV_GC() {
                return LFIMG_NOV_GC;
            }

            public void setLFIMG_NOV_GC(String LFIMG_NOV_GC) {
                this.LFIMG_NOV_GC = LFIMG_NOV_GC;
            }

            public String getLFIMG_JULY_LS() {
                return LFIMG_JULY_LS;
            }

            public void setLFIMG_JULY_LS(String LFIMG_JULY_LS) {
                this.LFIMG_JULY_LS = LFIMG_JULY_LS;
            }

            public String getLFIMG_THISYEAR_GC() {
                return LFIMG_THISYEAR_GC;
            }

            public void setLFIMG_THISYEAR_GC(String LFIMG_THISYEAR_GC) {
                this.LFIMG_THISYEAR_GC = LFIMG_THISYEAR_GC;
            }

            public String getLFIMG_SEPT_LS() {
                return LFIMG_SEPT_LS;
            }

            public void setLFIMG_SEPT_LS(String LFIMG_SEPT_LS) {
                this.LFIMG_SEPT_LS = LFIMG_SEPT_LS;
            }

            public String getLFIMG_MAY_GC() {
                return LFIMG_MAY_GC;
            }

            public void setLFIMG_MAY_GC(String LFIMG_MAY_GC) {
                this.LFIMG_MAY_GC = LFIMG_MAY_GC;
            }
        }
    }
}
