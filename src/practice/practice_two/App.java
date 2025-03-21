package practice.practice_two;

import java.time.LocalDate;
import java.util.ArrayList;

public class App {

    public static void main(String[] args) {
        System.out.println("Testare Instrument:\n");

        ArrayList<Instrument.Operatiune> operationList = new ArrayList<>();
        operationList.add(new Instrument.Operatiune(TipOperatiune.CUMPARARE, LocalDate.now(), 490.99, 7));
        operationList.add(new Instrument.Operatiune(TipOperatiune.VANZARE, LocalDate.of(2021, 1, 7), 390.99, 5));

        Instrument firstInstrument = new Instrument("A", operationList);
        System.out.println(firstInstrument.toString());

        System.out.println("\n\n-----------------------------------------------\n\n");

        System.out.printf("Valoare: %.2f\n", firstInstrument.valoare());

        System.out.println("\n\n-----------------------------------------------\n\n");

        System.out.println("Testare Actiune:\n");

        Actiune firstActiune = new Actiune("B", operationList, 10);

        System.out.println(firstActiune.toString());

        System.out.println("\n\n-----------------------------------------------\n\n");

        System.out.println("Testare PortofoliuGenerics:\n");

        PortofoliuGenerics portofoliuGenerics = new PortofoliuGenerics<>();
        portofoliuGenerics.adaugaInstrument(firstInstrument);
        portofoliuGenerics.adaugaInstrument(firstActiune);

        System.out.println(portofoliuGenerics.toString());

        System.out.println("\n\n-----------------------------------------------\n\n");

        System.out.printf("Valoare portofoliu: %.2f\n", portofoliuGenerics.getValoareInstrumente());

        System.out.println("\n\n-----------------------------------------------\n\n");

        System.out.println("Data primei operatiuni a fiecarui instrument financiar: \n");
        portofoliuGenerics.printFirstDateForEachInstrumentOperation();

        System.out.println("\n\n-----------------------------------------------\n\n");

        System.out.println("Write file in \"insturrmente.txt\".");
        PortofoliuGenerics.salvarePortofoliu("src/practice/practice_two/instrumenteWrite.txt", portofoliuGenerics);

        System.out.println("\n\n-----------------------------------------------\n\n");

        System.out.println("Read portofolio from file: \n");

        PortofoliuGenerics<Instrument> newPortofoliu = PortofoliuGenerics.incarcarePortofoliu("src/practice/practice_two/instrumente.txt");
        System.out.println(newPortofoliu.toString());
    }
}
