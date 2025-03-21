package practice.practice_two;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PortofoliuGenerics<T> {

    private List<T> portofoliu = new ArrayList<>();

    public PortofoliuGenerics() {}

    public PortofoliuGenerics(List<T> portofoliu) {
        this.portofoliu = portofoliu;
    }

    public List<T> getPortofoliu() {
        return portofoliu;
    }

    public void setPortofoliu(List<T> portofoliu) {
        this.portofoliu = portofoliu;
    }

    public void adaugaInstrument(T instrument) {
        portofoliu.add(instrument);
    }

    public T getInstrument(int index) {
        if (index >= 0 && index < portofoliu.size()) {
            return portofoliu.get(index);
        }

        return null;
    }

    public double getValoareInstrumente() {
        double value = 0;
        List<Instrument> listaInstrumente = (List<Instrument>) portofoliu;

        for (Instrument instrument : listaInstrumente) {
            value += instrument.valoare();
        }

        return value;
    }

    public void printFirstDateForEachInstrumentOperation() {
        List<Instrument> listaInstrumente = (List<Instrument>) portofoliu;

        for (int i = 0; i < listaInstrumente.size(); i++) {
            List<Instrument.Operatiune> copieListaOperatiuni = listaInstrumente.get(i).getOperatiuni();
            copieListaOperatiuni.sort(Instrument.Operatiune::compareTo);

            System.out.println(copieListaOperatiuni.get(0).getData());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PortofoliuGenerics<?> that = (PortofoliuGenerics<?>) o;
        return Objects.equals(portofoliu, that.portofoliu);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(portofoliu);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String separator = ",\n";

        sb.append("{\n");
        for (T instrument : portofoliu) {
            sb.append(instrument.toString()).append(separator);
        }

        sb.delete(sb.length() - 2, sb.length() - 1);

        String result = sb.toString().replaceAll("\n", "\n    ");
        result = result.substring(0, result.length() - 4);
        result += "}";

        return result;
    }

    public static void salvarePortofoliu(String caleFisier, PortofoliuGenerics portofoliuGenerics) {
        String toSave = portofoliuGenerics.toString();

//        try {
//            PrintWriter pw = new PrintWriter(caleFisier);
//            pw.write(toSave);
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(caleFisier));
            writer.write(toSave);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
