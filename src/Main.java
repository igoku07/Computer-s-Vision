/**
 * Inicia a aplicação na thread de eventos do Swing.
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
        SwingUtilities.invokeLater(() -> { //Evitar problema de thred para o Swing ficar na mesma que o Main
            new Tela();
        });
    }
}
