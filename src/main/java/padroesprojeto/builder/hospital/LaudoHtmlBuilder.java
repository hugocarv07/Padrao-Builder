package padroesprojeto.builder.hospital;

import java.util.ArrayList;
import java.util.List;

public class LaudoHtmlBuilder implements ConstrutorLaudo {

    private List<String> partes = new ArrayList<>();

    @Override
    public void construirCabecalho(String paciente, String medico) {
        partes.add("<h1>Laudo Médico</h1>");
        partes.add("<p><b>Paciente:</b> " + paciente + " | <b>Médico:</b> " + medico + "</p>");
    }

    @Override
    public void construirDiagnostico(String diagnostico) {
        partes.add("<p><b>Diagnóstico:</b> " + diagnostico + "</p>");
    }

    @Override
    public void construirPrescricao(String prescricao) {
        partes.add("<p><b>Prescrição:</b> " + prescricao + "</p>");
    }

    @Override
    public void construirRodape() {
        partes.add("<hr/><small>Documento emitido pelo hospital</small>");
    }

    @Override
    public Laudo obterLaudo() {
        Laudo laudo = new Laudo(String.join("\n", partes));
        partes = new ArrayList<>();
        return laudo;
    }
}
