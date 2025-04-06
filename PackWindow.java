import javax.swing.*;
import java.awt.*;

class PackWindow extends JFrame {
    JTextField dirField, outputField;

    PackWindow() {
        setTitle("Pack Files");
        setSize(400, 200);
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Directory Path:"));
        dirField = new JTextField();
        add(dirField);

        add(new JLabel("Output File Name:"));
        outputField = new JTextField();
        add(outputField);

        JButton submit = new JButton("Submit");
        submit.addActionListener(e -> {
            Packer.pack(dirField.getText(), outputField.getText());
            JOptionPane.showMessageDialog(this, "Packing Completed");
            dispose();
            new MainMenu();
        });
        add(submit);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}