package practice.practice_one;

import java.io.Serializable;
import java.util.Objects;

final class Pacient implements Serializable {

    private final int codPacient;

    private final String numePacient;

    private final String sectiePacient;

    public Pacient (int codPacient, String numePacient, String sectiePacient) {
        this.codPacient = codPacient;
        this.numePacient = numePacient;
        this.sectiePacient = sectiePacient;
    }

    public int getCodPacient() {
        return this.codPacient;
    }

    public String getNumePacient() {
        return this.numePacient;
    }

    public String getSectiePacient() {
        return this.sectiePacient;
    }

    @Override
    public String toString() {
        return "Pacient{" +
                "codPacient=" + codPacient +
                ", numePacient='" + numePacient + '\'' +
                ", sectiePacient='" + sectiePacient + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pacient pacient = (Pacient) o;
        return codPacient == pacient.codPacient && Objects.equals(numePacient, pacient.numePacient) && Objects.equals(sectiePacient, pacient.sectiePacient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codPacient, numePacient, sectiePacient);
    }
}
