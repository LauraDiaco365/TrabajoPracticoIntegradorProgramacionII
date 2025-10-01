public class HistoriaClinica {
    private int idHistoria;
    private boolean eliminado;
    private String nroHistoria;
    private String grupoSanguineo;
    private String antecedentes;
    private String medicacionActual;
    private String observaciones;

    // Constructor vacío
    public HistoriaClinica() {
    }

    // Constructor completo
    public HistoriaClinica(int idHistoria, boolean eliminado, String nroHistoria,
            String grupoSanguineo,String antecedentes, String medicacionActual,
            String observaciones) {
        this.idHistoria = idHistoria;
        this.eliminado = eliminado;
        this.nroHistoria = nroHistoria;
        this.grupoSanguineo = grupoSanguineo;
        this.antecedentes = antecedentes;
        this.medicacionActual = medicacionActual;
        this.observaciones = observaciones;
    }

    // Método toString para mostrar la historia completa
    @Override
    public String toString() {
        return "HistoriaClinica{" +
               "idHistoria=" + idHistoria +
               ", eliminado=" + eliminado +
               ", nroHistoria='" + nroHistoria + '\'' +
               ", grupoSanguineo='" + grupoSanguineo + '\'' +
               ", antecedentes='" + antecedentes + '\'' +
               ", medicacionActual='" + medicacionActual + '\'' +
               ", observaciones='" + observaciones + '\'' +
               '}';
    }

    // Getters y setters
    public int getIdHistoria() {
        return idHistoria;
    }

    public void setIdHistoria(int idHistoria) {
        this.idHistoria = idHistoria;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public String getNroHistoria() {
        return nroHistoria;
    }

    public void setNroHistoria(String nroHistoria) {
        this.nroHistoria = nroHistoria;
    }

    public String getGrupoSanguineo() {
        return grupoSanguineo;
    }

    public void setGrupoSanguineo(String grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }

    public String getAntecedentes() {
        return antecedentes;
    }

    public void setAntecedentes(String antecedentes) {
        this.antecedentes = antecedentes;
    }

    public String getMedicacionActual() {
        return medicacionActual;
    }

    public void setMedicacionActual(String medicacionActual) {
        this.medicacionActual = medicacionActual;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}