package customer.entity;

public class NeedPlanBean {
   private String date;
   private String planno;
   private  String place;
   private String plansend;
   private String realsend;

   public String getDate() {
      return date;
   }

   public void setDate(String date) {
      this.date = date;
   }

   public String getPlanno() {
      return planno;
   }

   public void setPlanno(String planno) {
      this.planno = planno;
   }

   public String getPlace() {
      return place;
   }

   public void setPlace(String place) {
      this.place = place;
   }

   public String getPlansend() {
      return plansend;
   }

   public void setPlansend(String plansend) {
      this.plansend = plansend;
   }

   public String getRealsend() {
      return realsend;
   }

   public void setRealsend(String realsend) {
      this.realsend = realsend;
   }

   public String getProgress() {
      return progress;
   }

   public void setProgress(String progress) {
      this.progress = progress;
   }

   private String progress;
}
