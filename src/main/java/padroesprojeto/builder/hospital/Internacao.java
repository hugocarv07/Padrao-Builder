package padroesprojeto.builder.hospital;

/** Variante fluente do Builder (estilo Effective Java): objeto imutável, campos opcionais com padrão. */
public class Internacao {

    private final String paciente;
    private final String leito;
    private final String medico;
    private final String convenio;
    private final boolean urgente;

    private Internacao(Builder builder) {
        this.paciente = builder.paciente;
        this.leito = builder.leito;
        this.medico = builder.medico;
        this.convenio = builder.convenio;
        this.urgente = builder.urgente;
    }

    public String getPaciente() {
        return paciente;
    }

    public String getLeito() {
        return leito;
    }

    public String getMedico() {
        return medico;
    }

    public String getConvenio() {
        return convenio;
    }

    public boolean isUrgente() {
        return urgente;
    }

    public String resumo() {
        return "Internacao{paciente='" + paciente + "', leito='" + leito + "', medico='" + medico
                + "', convenio='" + convenio + "', urgente=" + urgente + "}";
    }

    public static class Builder {

        private final String paciente;
        private String leito = "A definir";
        private String medico = "A definir";
        private String convenio = "Particular";
        private boolean urgente = false;

        public Builder(String paciente) {
            this.paciente = paciente;
        }

        public Builder leito(String leito) {
            this.leito = leito;
            return this;
        }

        public Builder medico(String medico) {
            this.medico = medico;
            return this;
        }

        public Builder convenio(String convenio) {
            this.convenio = convenio;
            return this;
        }

        public Builder urgente(boolean urgente) {
            this.urgente = urgente;
            return this;
        }

        public Internacao build() {
            if (paciente == null || paciente.isBlank()) {
                throw new IllegalStateException("Paciente é obrigatório");
            }
            return new Internacao(this);
        }
    }
}
