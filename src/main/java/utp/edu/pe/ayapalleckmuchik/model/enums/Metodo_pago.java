package utp.edu.pe.ayapalleckmuchik.model.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Metodo_pago {
    EFECTIVO ("Efectivo"),
    TRANSFERENCIA ("Transferencia"),
    YAPE ("Yape");

    private final String displayName;
    Metodo_pago(String displayName) {
        this.displayName = displayName;
    }
    public String getDisplayName() {
        return displayName;
    }
    public static List<Metodo_pago> getMetodos_pago() {
        return new ArrayList<>(Arrays.asList(Metodo_pago.values()));
    }
}
