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

        boolean exited = false;
        while (!exited) {

            System.out.println("1. repülőgépet felvenni az adatbázisba");
            System.out.println("2. repülőgépet keresni az adatbázisból id alapján");
            System.out.println("3. kilistázni az összes repülőgépet");
            System.out.println("4. várost felvenni az adatbázisba");
            System.out.println("5. várost keresni id alapján");
            System.out.println("6. kilistázni az összes várost");
            System.out.println("9. Kilépés");

//            Minden azonos változó kijelölése ALT +J
            int number = Integer.parseInt(inputConsole.nextLine());

            switch (number) {
                case 1:
                    addPlane();
                    break;
                case 2:
                    findPlane();
                    break;
                case 3:
                    listAllPlane();
                    break;
                case 4:
                    addCity();
                    break;
                case 5:
                    findCity();
                    break;
                case 6:
                    listAllCity();
                    break;
                case 9:
                    exited = true;
            }
        }
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
