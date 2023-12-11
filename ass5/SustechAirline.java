import java.util.*;

public class SustechAirline implements Airline{

    public ArrayList<Flight> Fli = new ArrayList<>();

    @Override
    public void addFlight(String flightInfo) {
        Flight flight = new Flight(flightInfo);
        boolean tf = true;
        for (Flight f : Fli) {
            if (f.getFlightNo().compareTo(flight.getFlightNo()) == 0) tf = false;
        }
        if (tf) Fli.add(flight);
    }

    @Override
    public String getFlightInfo(String flightNo) {
        boolean tf = false;
        String a = null;
        for (Flight f : Fli) {
            if (f.getFlightNo().compareTo(flightNo) == 0) {
                tf = true;
                a = f.toString();
                break;
            }
        }
        if (tf) return a;
        else return null;
    }

    @Override
    public List<String> getFlightRoute() {
        ArrayList<String> list = new ArrayList<>();
        String[] departs = new String[10000];//record all the departports
        int length = 0;//Record the number of departure points
        for(Flight flight : Fli){
            if(!Arrays.asList(departs).contains(flight.getDepartPort())){
                departs[length] = flight.getDepartPort();
                length++;
            }
        }
        //add into departs
        Arrays.sort(departs,0,length);
        for(int i = 0; i <length; i++){
            String[] arrives = new String[10000];
            int length1 = 0;
            //record arriveports
            for(Flight flight : Fli){
                if(flight.getDepartPort().equals(departs[i]) && (!Arrays.asList(arrives).contains(flight.getArrivePort()))){
                    arrives[length1] = flight.getArrivePort();
                    length1++;
                }
            }
            //when the same departport,add arriveport into arrives
            Arrays.sort(arrives,0,length1);
            String result = departs[i] + "->";
            for(int j = 0; j < length1; j++){
                result += arrives[j] + " ";
            }
            list.add(result.trim());
        }
        return list;
    }

    @Override
     public boolean removeFlight(String flightNo) {
        boolean tf = false;
        for (Flight f : Fli) {
            if (f.getFlightNo().compareTo(flightNo) == 0){
                Fli.remove(f);
                tf = true;
                break;
            }
        }
        return tf;
    }

    @Override
    public List<Flight> getAllFlightsAboutDepartPort(String departPort) {
        ArrayList<Flight> finalList = new ArrayList<>();
        for (Flight f : Fli){
            if (f.getDepartPort().equals(departPort)){
                finalList.add(f);
            }
        }
        //sort
        Collections.sort(finalList, new Comparator<Flight>() {
            @Override
            public int compare(Flight o1, Flight o2) {
                return o1.getFlightNo().compareTo(o2.getFlightNo());
            }
        });
        return finalList;
    }


    @Override
    public boolean isContainsFlight(String departPort, String arrivePort) {
        for (Flight f : Fli) {
            if (f.getDepartPort().compareTo(departPort) == 0 && f.getArrivePort().compareTo(arrivePort) == 0) return true;
        }
        return false;
    }

    @Override
    public boolean isRoundTrip(String port1, String port2) {
        boolean a = false;
        boolean b = false;
        for (Flight f : Fli) {
            if (f.getDepartPort().equals(port1) && f.getArrivePort().equals(port2)){
                a = true;
            }
            if (f.getDepartPort().equals(port2) && f.getArrivePort().equals(port1)){
                b = true;
            }
        }
        if (a && b)return true;
        return false;
    }


    public List<String> searchAllRoutes(String departPort, String arrivePort, Search search) {
        search.sustechAirline = this;
        return search.searchRoutes(departPort, arrivePort);
    }

    public String searchBestRoute(String departPort, String arrivePort, Search search, SearchPlan searchPlan) {
//        String finalString = null;
//        return finalString;
        search.sustechAirline = this;
        return search.searchBestRoute(departPort,arrivePort,searchPlan);
    }

}

