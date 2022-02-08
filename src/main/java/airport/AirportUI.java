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
        inputConsole.nextLine();
        String serialNumber = inputConsole.nextLine();

        System.out.println("Írd be az új repülőgép ülésszámát!");
        inputConsole.nextLine();
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

    public void listAllPlane(String listAllPlane) {
        List<Plane> allPlane = planeRepository.listAllPlanes();
        allPlane.forEach(System.out::println);

    }

    public static void addNewCity(String addNewCity) {
        //TODO
    }

    public static void findId(String addNewCity) {
        //TODO
    }
}
