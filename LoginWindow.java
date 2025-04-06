import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class LoginWindow extends JFrame implements ActionListener {
    JTextField usernameField;
    JPasswordField passwordField;
    int attempts = 0;

    LoginWindow() {
        setTitle("Login - File Packer Unpacker");
        setSize(400, 250);
        setLocationRelativeTo(null); // Center on screen
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Custom font and color
        Font font = new Font("Arial", Font.PLAIN, 16);
        Color bgColor = new Color(245, 245, 255);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(bgColor);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(font);
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(userLabel, gbc);

        usernameField = new JTextField(15);
        usernameField.setFont(font);
        gbc.gridx = 1;
        panel.add(usernameField, gbc);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(font);
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(passLabel, gbc);

        passwordField = new JPasswordField(15);
        passwordField.setFont(font);
        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        JButton loginButton = new JButton("Login");
        loginButton.setFont(font);
        loginButton.setBackground(new Color(100, 149, 237));
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(this);
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        panel.add(loginButton, gbc);

        add(panel);
        setVisible(true);

        usernameField.requestFocusInWindow(); // Set focus to username

        // Tooltip logic
        new Thread(() -> {
            while (true) {
                String pass = new String(passwordField.getPassword());
                if (pass.length() > 0 && pass.length() < 8) {
                    passwordField.setToolTipText("Password must be at least 8 characters");
                }
                if (Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK)) {
                    passwordField.setToolTipText("Warning: Caps Lock is ON");
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {}
            }
        }).start();
    }

    public void actionPerformed(ActionEvent e) {
        String user = usernameField.getText();
        String pass = new String(passwordField.getPassword());

        if (user.equals("MarvellousAdmin") && pass.equals("MarvellousAdmin")) {
            dispose();
            new MainMenu();
        } else {
            attempts++;
            if (attempts >= 3) {
                JOptionPane.showMessageDialog(this, "3 incorrect attempts. Exiting.");
                System.exit(0);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials. Attempt " + attempts);
            }
        }
    }
}
