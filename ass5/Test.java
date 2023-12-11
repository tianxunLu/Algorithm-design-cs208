public class Test {
    public static void main(String[] args) {
        Airline airline = new SustechAirline();
        Search search = new TransitOnceSearch() ;
//        airline.addFlight("S101 A B 12:00 13:00 3000");
        airline.addFlight("S102 B C 14:00 15:00 3000");
        airline.addFlight("S103 A B 12:00 18:00 30");
        airline.addFlight("S101 A B 12:00 13:00 3000");
        airline.addFlight("S104 E C 19:00 21:20 30");
        airline.addFlight("S105 A F 12:00 13:00 3000");
        airline.addFlight("S106 F C 14:00 15:00 3000");
        airline.addFlight("S107 A G 12:00 18:00 30");
        airline.addFlight("S108 G C 19:00 21:20 30");

        airline.getFlightRoute().forEach(System.out::println);


        System.out.println(airline.searchAllRoutes("A","B",search));
        System.out.println(airline.searchBestRoute("A","B",search,SearchPlan.LESS_PRICE));
        System.out.println(airline.searchBestRoute("A","B",search,SearchPlan.LESS_TIME));
    }
}
