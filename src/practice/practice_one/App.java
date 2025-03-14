package practice.practice_one;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        PacientDatabase db = new PacientDatabase();
        String path = "src/practice/practice_one/pacienti.txt";
        File file = new File(path);
        try (Scanner sc = new Scanner(file).useDelimiter(",|\\r\\n")) {
            while (sc.hasNext()) {
                int codPacient = Integer.parseInt(sc.next());
                String numePacient = sc.next();
                String sectiePacient = sc.next();

                Pacient pacientToAdd = new Pacient(codPacient, numePacient, sectiePacient);
                db.addPacientToDatabase(pacientToAdd);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Lista pacienti: \n");
        db.getPacientList().stream()
                .forEach(p -> System.out.println(p.toString()));

        System.out.println("");
        System.out.println("--------------------------------------");
        System.out.println("");

        System.out.println("Numar pacienti: ");
        System.out.println(db.getPacientNumber());

        System.out.println("");
        System.out.println("--------------------------------------");
        System.out.println("");

        System.out.println("Lista sectiilor sortata descrescator dupa numarul pacientilor: \n");
        db.getSectieListSortedByPacientNumber().forEach(System.out::println);

        System.out.println("");
        System.out.println("--------------------------------------");
        System.out.println("");

        System.out.println("Lista sectiilor impreuna cu numarul lor (in scopul verificarii): \n");
        for (Map.Entry<String, Long> entry : db.getSectieListSortedByPacientNumberAsMap().entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("");
        System.out.println("--------------------------------------");
        System.out.println("");

        System.out.println("Generare fisier binar care sa contina lista de pacienti intitulat \"pacientList.txt\".");
        db.generateBinaryFileWithThePacientiListAndReturnContent();

        System.out.println("");
        System.out.println("--------------------------------------");
        System.out.println("");

        System.out.println("Citire din fisierul binar \"pacientList.txt\": ");

        String pathBinary = "src/practice/practice_one/pacientList.txt";
        try {
            FileInputStream fis = new FileInputStream(pathBinary);
            ObjectInputStream ois = new ObjectInputStream(fis);

            List<Pacient> binaryToList = (List<Pacient>)ois.readObject();
            binaryToList.forEach(p -> System.out.println(p.toString()));
            ois.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
