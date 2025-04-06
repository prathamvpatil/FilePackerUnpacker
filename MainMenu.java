import javax.swing.*;
import java.awt.*;

class MainMenu extends JFrame {
    MainMenu() {
        setTitle("File Packer Unpacker - Menu");
        setSize(300, 150);
        setLayout(new FlowLayout());

        JButton packButton = new JButton("Pack Files");
        JButton unpackButton = new JButton("Unpack Files");

        packButton.addActionListener(e -> {
            dispose();
            new PackWindow();
        });

        unpackButton.addActionListener(e -> {
            dispose();
            new UnpackWindow();
        });

        add(packButton);
        add(unpackButton);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}