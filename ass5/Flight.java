public class Flight {
    // fields must be declared
    private String flightNo;
    private SustechTime departTime;
    private SustechTime arriveTime;
    private int price;
    private String departPort;
    private String arrivePort;

    //todo: you can add any fields that you think is necessary


    /**
     * example of flightInfo:
     * S101 A B 8:00 11:20 960
     * S102 A C 9:20 14:20 1210
     *
     * @param flightInfo format: [flightNo] [departPort] [arrivePort] [departTime] [arriveTime]
     */
    public Flight(String flightInfo) {
        //todo: complete the constructor
        String[] str = flightInfo.split(" ");
        flightNo = str[0];
        departPort = str[1];
        arrivePort = str[2];
        departTime  = new SustechTime(str[3]);
        arriveTime = new SustechTime(str[4]);
        price = Integer.parseInt(str[5]);
    }

    /**
     * @return a format like: [flightNo] [[departPort] -> [arrivePort]] [departTime] -> [arriveTime] ([price])
     * for example:
     * S103 [A -> E] 03:00 -> 05:30 (900)
     */
    @Override
    public String toString() {
//      todo: complete the return format:  return String.format("%s [%s -> %s] %s -> %s (%d)", );
        return String.format("%s [%s -> %s] %s -> %s (%d)",flightNo,departPort,arrivePort,departTime,arriveTime,price);
    }

    public String getFlightNo(){
        return flightNo;
    }

    public  int getPrice() {
        return price;
    }
    public SustechTime getDepartTime(){
        return departTime;
    }
    public SustechTime getArriveTime(){
        return arriveTime;
    }

    public String getDepartPort(){
        return departPort;
    }

    public String getArrivePort(){
        return arrivePort;
    }

    //todo: you can add any method that you think necessary

}
