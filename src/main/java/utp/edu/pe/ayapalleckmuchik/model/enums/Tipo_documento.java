package utp.edu.pe.ayapalleckmuchik.model.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Tipo_documento {
    DNI ("DNI"),
    PASAPORTE ("Pasaporte"),
    CARNET_EXTRANJERIA ("Carnet de extranjeria");

    private final String displayName;
    Tipo_documento(String displayName) {
        this.displayName = displayName;
    }
    public String getDisplayName() {
        return displayName;
    }
    public static List<Tipo_documento> getTipos_documento() {
        return new ArrayList<>(Arrays.asList(Tipo_documento.values()));
    }
}
