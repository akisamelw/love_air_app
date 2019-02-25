package com.example.akisame_lin.love_air2.bean1;


import java.util.List;

public class ResultBean {
   private int resultcode;//数据头
    private String reason;
   private int error_code;

  private List<dataBean> result;


    public class dataBean{
        private String city;
        private String PM2_5;
        private String AQI;
        private String quality;
        private String PM10;
        private String CO;
        private String NO2;
        private String O3;
        private String SO2;
        private String time;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getPM2_5() {
            return PM2_5;
        }

        public void setPM2_5(String PM2_5) {
            this.PM2_5 = PM2_5;
        }


        public String getAQI() {
            return AQI;
        }

        public void setAQI(String AQI) {
            this.AQI = AQI;
        }

        public String getQuality() {
            return quality;
        }

        public void setQuality(String quality) {
            this.quality = quality;
        }

        public String getPM10() {
            return PM10;
        }

        public void setPM10(String PM10) {
            this.PM10 = PM10;
        }

        public String getCO() {
            return CO;
        }

        public void setCO(String CO) {
            this.CO = CO;
        }


        public String getNO2() {
            return NO2;
        }

        public void setNO2(String NO2) {
            this.NO2 = NO2;
        }

        public String getO3() {
            return O3;
        }

        public void setO3(String o3) {
            O3 = o3;
        }

        public String getSO2() {
            return SO2;
        }

        public void setSO2(String SO2) {
            this.SO2 = SO2;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
  }



   public int getResultcode() {
        return resultcode;
    }


    public void setResultcode(int resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

   public int getError_code() {
        return error_code;
    }

   public void setError_code(int error_code) {
        this.error_code = error_code;
    }
    public List<dataBean> getResult()
    { return result; }

   public void setResult(List<dataBean> result) {
      this.result = result;
   }


}
