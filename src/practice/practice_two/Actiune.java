package practice.practice_two;

import java.util.ArrayList;
import java.util.Objects;

public class Actiune extends Instrument implements Evaluabil {

    private double procentDividend;

    public Actiune() {
        super();
        this.procentDividend = -1;
    }

    public Actiune(String symbol, ArrayList<Operatiune> operatiuni, double procentDividend) {
        super(symbol, operatiuni);
        this.procentDividend = procentDividend;
    }

    public double getProcentDividend() {
        return procentDividend;
    }

    public void setProcentDividend(double procentDividend) {
        this.procentDividend = procentDividend;
    }

    public double valoare() {
        double valoare = super.valoare();
        return valoare += (valoare * this.procentDividend) / 100;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Actiune actiune = (Actiune) o;
        return Double.compare(procentDividend, actiune.procentDividend) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), procentDividend);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        String separator = "\",\n";

        sb.append("{\n");
        sb.append("    \"Symbol\": \"" + this.getSymbol()); sb.append(separator);
        sb.append("    \"Operatiuni\": {\n");
        for (Operatiune operatiune : this.getOperatiuni()) {
            sb.append(operatiune.toString() + ",\n");
        }
        sb.delete(sb.length() - 2, sb.length() - 1);
        sb.append("    },\n");
        sb.append("    \"procentDividente\": \"" + this.procentDividend + "\"");
        sb.append("\n}");

        return sb.toString();
    }
}
