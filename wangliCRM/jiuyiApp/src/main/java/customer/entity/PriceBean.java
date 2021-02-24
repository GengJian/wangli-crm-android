package customer.entity;

import java.util.List;

public class PriceBean {


    private List<ContentBean> content;

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean {
        public String getKondm() {
            return kondm;
        }

        public void setKondm(String kondm) {
            this.kondm = kondm;
        }

        public double getMin() {
            return min;
        }

        public void setMin(double min) {
            this.min = min;
        }

        public double getMax() {
            return max;
        }

        public void setMax(double max) {
            this.max = max;
        }

        public int getKpein() {
            return kpein;
        }

        public void setKpein(int kpein) {
            this.kpein = kpein;
        }

        public String getKmein() {
            return kmein;
        }

        public void setKmein(String kmein) {
            this.kmein = kmein;
        }

        public String getKonwa() {
            return konwa;
        }

        public void setKonwa(String konwa) {
            this.konwa = konwa;
        }

        public String getDatab() {
            return datab;
        }

        public void setDatab(String datab) {
            this.datab = datab;
        }

        public String getDatbi() {
            return datbi;
        }

        public void setDatbi(String datbi) {
            this.datbi = datbi;
        }

        public String getKondmtext() {
            return kondmtext;
        }

        public void setKondmtext(String kondmtext) {
            this.kondmtext = kondmtext;
        }

        public double getGuidePrice() {
            return guidePrice;
        }

        public void setGuidePrice(double guidePrice) {
            this.guidePrice = guidePrice;
        }

        /**
         * kondm : 01
         * min : 40
         * max : 52
         * kpein : 1
         * kmein : KG
         * konwa : RMB
         * datab : 2018-06-28
         * datbi : 9999-12-31
         * kondmtext : 一等品
         * guidePrice : 42
         */

        private String kondm;
        private double min;
        private double max;
        private int kpein;
        private String kmein;
        private String konwa;
        private String datab;
        private String datbi;
        private String kondmtext;
        private double guidePrice;
    }


}
