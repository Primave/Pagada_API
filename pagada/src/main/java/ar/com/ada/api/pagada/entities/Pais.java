package ar.com.ada.api.pagada.entities;

public class Pais {

    public enum TipoIdImpositivoEnum {
        CUIT, CUIL, NIT, RUT, DNI, RFC
    }

    public enum PaisEnum {
        ARGENTINA(32), ESTADOS_UNIDOS(840), VENEZUELA(862);

        private final Integer value;

        // NOTE: Enum constructor tiene que estar en privado
        private PaisEnum(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public static PaisEnum parse(Integer id) {
            PaisEnum status = null; // Default
            for (PaisEnum item : PaisEnum.values()) {
                if (item.getValue().equals(id)) {
                    status = item;
                    break;
                }
            }
            return status;
        }
    }

}