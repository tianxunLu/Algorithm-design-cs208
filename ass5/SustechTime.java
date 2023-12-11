public class SustechTime {
    private int hour;
    private int minute;

    //todo: you can add any fields or methods you like
    //
    public SustechTime(String timeInfo) {
        int num = timeInfo.indexOf(":");
        hour = Integer.parseInt(timeInfo.substring(0,num));
       minute = Integer.parseInt(timeInfo.substring(num+1));
    }

    public int timeDifference(SustechTime targetTime) {
        int time1 = targetTime.hour*60 + targetTime.minute;
        int time2 = hour*60 + minute;
        int dtime = 0;
        if (time1 > time2){
            dtime = time1 - time2;
        }else{
            dtime = time2 - time1;
        }
        return dtime;
    }

    public int timeDifference2(SustechTime targetTime) {
        int time1 = targetTime.hour*60 + targetTime.minute;
        int time2 = hour*60 + minute;
        return time1-time2;
    }



    @Override
    public String toString() {
        return String.format("%02d:%02d",hour,minute);
    }

}
