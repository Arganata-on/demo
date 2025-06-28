import javax.swing.*;
import com.formdev.flatlaf.intellijthemes.FlatGradiantoDeepOceanIJTheme;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatGradiantoDeepOceanIJTheme());
            UIManager.put("Button.arc", 30);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new ProductFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}