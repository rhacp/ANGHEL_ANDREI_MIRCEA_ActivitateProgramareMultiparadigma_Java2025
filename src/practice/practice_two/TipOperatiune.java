package practice.practice_two;

public enum TipOperatiune {
    CUMPARARE("CUMPARARE"),
    VANZARE("VANZARE");

    private final String label;
    private int pozitie;

    TipOperatiune(String label) {
        this.label = label;

        if (this.label.equalsIgnoreCase("CUMPARARE")) {
            this.pozitie = 1;
        }

        if (this.label.equalsIgnoreCase("VANZARE")) {
            this.pozitie = -1;
        }
    }

    public String getLabel() {
        return this.label;
    }

    public int getPozitie() {
        return this.pozitie;
    }
}
