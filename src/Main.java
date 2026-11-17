/**
 * Ponto de entrada da aplicação Visão Computacional.
 *
 * A classe Main inicializa a interface gráfica usando a thread de eventos
 * do Swing (Event Dispatch Thread), que é a thread apropriada para criar e
 * atualizar componentes Swing.
 *
 * @author Felipe Estima Correia Urzi
 * @author Igor Dias da Silva
 * @author Pedro Henrique Freire
 * @author Thierry Nadjarian
 * @version 20261102
 */
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        // Agenda a criação da janela na thread de eventos do Swing.
        SwingUtilities.invokeLater(() -> { //Evitar problema de thred para o Swing ficar na mesma que o Main
            new Tela();
        });
    }
}
