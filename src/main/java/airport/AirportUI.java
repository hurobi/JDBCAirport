package airport;

import java.util.List;
import java.util.Scanner;

public class AirportUI {

    private Scanner inputConsole = new Scanner(System.in);

    private PlaneRepository planeRepository;
    private CityRepository cityRepository;

    public AirportUI(PlaneRepository planeRepository, CityRepository cityRepository) {
        this.planeRepository = planeRepository;
        this.cityRepository = cityRepository;
    }

    public void menu() {
        System.out.println("1. repülőgépet felvenni az adatbázisba");
        System.out.println("2. repülőgépet keresni az adatbázisból id alapján");
        System.out.println("3. kilistázni az összes repülőgépet");
        System.out.println("4. várost felvenni az adatbázisba");
        System.out.println("5. várost keresni id alapján");
        System.out.println("6. kilistázni az összes várost");
//            Minden azonos változó kijelölése ALT +J
        while (!inputConsole.hasNextInt()) {
//                System.out.println("Input is not a number.");
            inputConsole.nextLine();
        }
        int number = inputConsole.nextInt();

        switch (inputConsole)
        case 1:
        addPlane();
    }

    public void addPlane() {
        System.out.println("Írd be az új repülőgép serial number-jét!");
        String serialNumber = inputConsole.nextLine();

        System.out.println("Írd be az új repülőgép ülésszámát!");
        int numberOfSeats = Integer.parseInt(inputConsole.nextLine());

        Plane plane = new Plane(serialNumber, numberOfSeats);
        planeRepository.createPlane(plane);
    }

    public void findPlane() {
        System.out.println("Kérem a repülőgép id-jét!");
        int id = Integer.parseInt(inputConsole.nextLine());
        Plane plane = planeRepository.findPlaneById(id);
        System.out.println(plane);
    }

    public void listAllPlane() {
        List<Plane> allPlane = planeRepository.listAllPlanes();
        allPlane.forEach(System.out::println);

    }


    public void addCity() {
        System.out.println("Írd be az új város nevét!");
        String name = inputConsole.nextLine();

        System.out.println("Írd be az új város hosszósági koordinátáját!");
        double longitude = Double.parseDouble(inputConsole.nextLine());

        System.out.println("Írd be az új város hosszósági koordinátáját!");
        double latitude = Double.parseDouble(inputConsole.nextLine());

        City city = new City(name, longitude, latitude);
        cityRepository.createCity(city);
    }

    public void findCity() {
        System.out.println("Kérem a repülőgép id-jét!");
        int id = Integer.parseInt(inputConsole.nextLine());
        City city = cityRepository.findCityById(id);
        System.out.println(city);
    }

    public void listAllCity() {
        List<City> allCity = cityRepository.listAllCities();
        allCity.forEach(System.out::println);

    }
}
