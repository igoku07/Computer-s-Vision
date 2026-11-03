import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> { //Evitar problema de thred para o Swing ficar na mesma que o Main
            new Tela();
        });
    }
}
