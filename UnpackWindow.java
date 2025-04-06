import javax.swing.*;
import java.awt.*;

class UnpackWindow extends JFrame {
    JTextField fileField;

    UnpackWindow() {
        setTitle("Unpack Files");
        setSize(400, 150);
        setLayout(new GridLayout(2, 2));

        add(new JLabel("Packed File Name:"));
        fileField = new JTextField();
        add(fileField);

        JButton extract = new JButton("Extract Here");
        extract.addActionListener(e -> {
            Unpacker.unpack(fileField.getText());
            JOptionPane.showMessageDialog(this, "Unpacking Completed");
            dispose();
            new MainMenu();
        });
        add(extract);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}