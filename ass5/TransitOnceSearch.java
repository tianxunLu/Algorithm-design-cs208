import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransitOnceSearch extends Search{

    @Override
    public List<String> searchRoutes(String departPort, String arrivePort){
        ArrayList<String> finalFlight = new ArrayList<>();
        for (Flight f : sustechAirline.Fli){
            if(f.getDepartPort().equals(departPort)){
                for (Flight ff : sustechAirline.Fli){
                    if (ff.getDepartPort().equals(f.getArrivePort()) && ff.getArrivePort().equals(arrivePort) && f.getArriveTime().timeDifference2(ff.getDepartTime()) > 30){
                        finalFlight.add(f.toString() + "\t" + ff.toString());
                    }
                }
            }
        }
        return finalFlight;
    }

    public String searchBestRoute(String departPort, String arrivePort, SearchPlan searchPlan) {
        ArrayList<Flight> flightsA = new ArrayList<>();
        ArrayList<Flight> flightsB = new ArrayList<>();
        for (Flight f : sustechAirline.Fli) {
            if (f.getDepartPort().equals(departPort)) {
                for (Flight ff : sustechAirline.Fli) {
                    if (ff.getDepartPort().equals(f.getArrivePort()) && ff.getArrivePort().equals(arrivePort) && f.getArriveTime().timeDifference2(ff.getDepartTime()) > 30) {
                        flightsA.add(f);
                        flightsB.add(ff);
                    }
                }
            }
        }

        //less time
        if (searchPlan.equals(SearchPlan.LESS_TIME)) {
            int minTime = 1000000;
            for (int i = 0; i < flightsA.size(); i++) {
                if (flightsA.get(i).getDepartTime().timeDifference(flightsB.get(i).getArriveTime()) < minTime) {
                    minTime = flightsA.get(i).getDepartTime().timeDifference(flightsB.get(i).getArriveTime());
                }
            }
            int[] j = new int[10000];
            int length = 0;
            for (int i = 0; i < flightsA.size(); i++) {
                if (flightsA.get(i).getDepartTime().timeDifference(flightsB.get(i).getArriveTime()) == minTime) {
                    j[length] = i;
                    length++;
                }
            }
            if (flightsA.size() == 0 || flightsB.size() == 0) {
                return null;
            }

            ArrayList<String> helper = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                helper.add(flightsA.get(j[i]).toString() + "\t" + flightsB.get(j[i]).toString());
            }
            Collections.sort(helper);
            return helper.get(0);
        }

        //less price
        if (searchPlan.equals(SearchPlan.LESS_PRICE)) {
            int minPrice = 1000000;
            for (int i = 0; i < flightsA.size(); i++) {
                if (flightsA.get(i).getPrice() + flightsB.get(i).getPrice() < minPrice) {
                    minPrice = flightsA.get(i).getPrice() + flightsB.get(i).getPrice();
                }
            }
            int[] j = new int[10000];
            int length = 0;
            for (int i = 0; i < flightsA.size(); i++) {
                if (flightsA.get(i).getPrice() + flightsB.get(i).getPrice() == minPrice) {
                    j[length] = i;
                    length++;
                }
            }
            if (flightsA.size() == 0 || flightsB.size() == 0) {
                return null;
            }

            ArrayList<String> helper = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                helper.add(flightsA.get(j[i]).toString() + "\t" + flightsB.get(j[i]).toString());
            }
            Collections.sort(helper);
            return helper.get(0);
        }
        return null;
    }
}
