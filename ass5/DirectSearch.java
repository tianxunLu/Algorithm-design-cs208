import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DirectSearch extends Search{

    @Override
    public  List<String> searchRoutes(String departPort, String arrivePort){
        ArrayList<String> finalString = new ArrayList<>();
        for (Flight f : sustechAirline.Fli){
            if (f.getDepartPort().equals(departPort) && f.getArrivePort().equals(arrivePort)){
                finalString.add(f.toString());
            }
        }
        return finalString;
    }

    public String searchBestRoute(String departPort, String arrivePort, SearchPlan searchPlan){
        ArrayList<Flight> finalFlights = new ArrayList<>();
        for (Flight f : sustechAirline.Fli){
            if (f.getDepartPort().equals(departPort) && f.getArrivePort().equals(arrivePort)){
                finalFlights.add(f);
            }
        }
        if (searchPlan.equals(SearchPlan.LESS_TIME)){
            Collections.sort(finalFlights,new Comparator<Flight>() {
                @Override
                public int compare(Flight o1, Flight o2) {
                    return o2.getDepartTime().timeDifference(o2.getArriveTime()) - o1.getDepartTime().timeDifference(o1.getArriveTime());
                }
            });

            if (finalFlights.size() == 0){
                return null;
            }
            return finalFlights.get(0).toString();
        }
        if (searchPlan.equals(SearchPlan.LESS_PRICE)){
            Collections.sort(finalFlights,new Comparator<Flight>() {
                @Override
                public int compare(Flight o1, Flight o2) {
                    return o2.getPrice() - o1.getPrice();
                }
            });
            if (finalFlights.size() == 0){
                return null;
            }
            return finalFlights.get(0).toString();
        }
       return null;
    }
}
