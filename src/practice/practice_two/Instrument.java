package practice.practice_two;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Instrument implements Evaluabil{

    private String symbol;
    private List<Operatiune> operatiuni = new ArrayList<>();

    public Instrument() {}

    public Instrument(String symbol, ArrayList<Operatiune> operatiuni) {
        this.symbol = symbol;
        this.operatiuni = operatiuni;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public List<Operatiune> getOperatiuni() {
        return operatiuni;
    }

    public void setOperatiuni(List<Operatiune> operatiuni) {
        this.operatiuni = operatiuni;
    }

    public void addOperatiune(Operatiune operatiune) {
        operatiuni.add(operatiune);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instrument that = (Instrument) o;
        return Objects.equals(symbol, that.symbol) && Objects.equals(operatiuni, that.operatiuni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, operatiuni);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        String separator = "\",\n";

        sb.append("{\n");
        sb.append("    \"Symbol\": \"" + symbol); sb.append(separator);
        sb.append("    \"Operatiuni\": [\n");
        for (Operatiune operatiune : operatiuni) {
            sb.append(operatiune.toString() + ",\n");
        }
        sb.delete(sb.length() - 2, sb.length() - 1);
        sb.append("    ]\n}");

        return sb.toString();
    }

    @Override
    public double valoare() {
        double result = 0;
        for(Operatiune operatiune : this.operatiuni) {
            result += operatiune.price * operatiune.cantitate * operatiune.tip.getPozitie();
        }

        return result;
    }

    public static final class Operatiune implements Comparable<Operatiune> {

        private final TipOperatiune tip;
        private final LocalDate data;
        private final double price;
        private final int cantitate;

        public Operatiune(TipOperatiune tip, LocalDate data, double price, int cantitate) {
            this.tip = tip;
            this.data = data;
            this.price = price;
            this.cantitate = cantitate;
        }

        public TipOperatiune getTip() {
            return tip;
        }

        public LocalDate getData() {
            return data;
        }

        public double getPrice() {
            return price;
        }

        public int getCantitate() {
            return cantitate;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Operatiune that = (Operatiune) o;
            return Double.compare(price, that.price) == 0 && cantitate == that.cantitate && tip == that.tip && Objects.equals(data, that.data);
        }

        @Override
        public int hashCode() {
            return Objects.hash(tip, data, price, cantitate);
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            String separator = "\",\n";

            sb.append("        {\n");
            sb.append("            \"Tip\": \"" + tip.getLabel()); sb.append(separator);
            sb.append("            \"Data\": \"" + data.getYear() + "." + data.getMonthValue() + "." + data.getDayOfMonth()); sb.append(separator);
            sb.append("            \"Pret\": \"" + price); sb.append(separator);
            sb.append("            \"Cantitate\": \"" + cantitate + "\"\n");
            sb.append("        }");

            return sb.toString();
        }

        @Override
        public int compareTo(Operatiune o) {
            return getData().compareTo(o.getData());
        }
    }
}
