package practice.practice_one;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class PacientDatabase {

    private List<Pacient> pacientList = new ArrayList<>();

    public PacientDatabase() {}

    public void addPacientToDatabase(Pacient pacient) {
        this.pacientList.add(pacient);
    }

    public List<Pacient> getPacientList() {
        return pacientList;
    }

    public int getPacientNumber() {
        return pacientList.size();
    }

    public List<String> getSectieListSortedByPacientNumber() {
        List<String> result = new ArrayList();

        Map<String, Long> resultMap = this.pacientList.stream()
                .collect(Collectors.groupingBy(Pacient::getSectiePacient, Collectors.counting()));

        result = resultMap.entrySet().stream()
                        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                        .map(Map.Entry::getKey)
                        .toList();

        return result;
    }

    public Map<String, Long> getSectieListSortedByPacientNumberAsMap() {
        Map<String, Long> resultMap = this.pacientList.stream()
                .collect(Collectors.groupingBy(Pacient::getSectiePacient, Collectors.counting()));

        return resultMap;
    }

    public void generateBinaryFileWithThePacientiListAndReturnContent() {
        try {
            FileOutputStream fos = new FileOutputStream("src/practice/practice_one/pacientList.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this.pacientList);
            oos.flush();
            oos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
