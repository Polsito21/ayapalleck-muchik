package utp.edu.pe.ayapalleckmuchik.model.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Estado {
    PENDIENTE ("pendiente"),
    RESERVADO ("reservado"),
    CANCELADO ("cancelado");

    private final String displayName;
    Estado(String displayName) {
        this.displayName = displayName;
    }
    public String getDisplayName() {
        return displayName;
    }
    public static List<Estado> getEstados() {
        return new ArrayList<>(Arrays.asList(Estado.values()));
    }
}
