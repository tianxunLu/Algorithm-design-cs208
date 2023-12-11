import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Search {
    SustechAirline sustechAirline = new SustechAirline();
    protected final int AIRPORT_FEE = 80;
    //fields you must be declare;
    protected final int TRANSIT_TIME = 30;


    //todo: you can add other fields and method you like


    public abstract List<String> searchRoutes(String departPort, String arrivePort);

    public abstract String searchBestRoute(String departPort, String arrivePort, SearchPlan searchPlan);

}
