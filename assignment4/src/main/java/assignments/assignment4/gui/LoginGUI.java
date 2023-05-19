package assignments.assignment4.gui;

import assignments.assignment3.LoginManager;
import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment4.MainFrame;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JPanel {
    public static final String KEY = "LOGIN";
    private JPanel mainPanel;
    private JLabel idLabel;
    private JTextField idTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton backButton;
    private LoginManager loginManager;
    private GridBagConstraints gbc = new GridBagConstraints();

    public LoginGUI(LoginManager loginManager) {
        super(new BorderLayout()); // Setup layout, Feel free to make any changes
        this.loginManager = loginManager;

        // Set up main panel, Feel free to make any changes
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        initGUI();

        add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */
    private void initGUI() {
        // TODO
        mainPanel.setBackground(new Color(198, 224, 225));
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.HORIZONTAL; // supaya text field ikut berubah ukuran kalo frame diresize
        gbc.weightx = 1; // supaya text field menuhin 1 panel
        gbc.gridx = 0;

        // buat label untuk menanyakan id dan tambahkan ke mainPanel
        idLabel = new JLabel("Masukkan ID Anda:");
        idLabel.setFont(new Font("b Bumbu Sushi", Font.PLAIN, 18));
        idLabel.setForeground(new Color(219, 22, 14));
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 10,5, 0);
        mainPanel.add(idLabel, gbc);

        // buat text field untuk input id dan tambahkan ke mainPanel
        idTextField = new JTextField();
        idTextField.setPreferredSize(new Dimension(100, 25));
        idTextField.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 10,0, 10);
        mainPanel.add(idTextField, gbc);

        // buat label untuk menanyakan password dan tambahkan ke mainPanel
        passwordLabel = new JLabel("Masukkan password Anda:");
        passwordLabel.setFont(new Font("b Bumbu Sushi", Font.PLAIN, 18));
        passwordLabel.setForeground(new Color(219, 22, 14));
        gbc.gridy = 2;
        gbc.insets = new Insets(15, 10,5, 10);
        mainPanel.add(passwordLabel, gbc);

        // buat password field untuk input password dan tambahkan ke mainPanel
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(100, 25));
        passwordField.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
        gbc.gridy = 5;
        gbc.insets = new Insets(0,10,0,10);
        mainPanel.add(passwordField, gbc);

        // mengatur agar button berada di tengah-tengah mainPanel
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;

        // membuat button login dan tambahkan ke mainPanel
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Shikamaru", Font.BOLD, 15));
        setButtonDesign(loginButton);
        gbc.gridy = 6;
        gbc.insets = new Insets(50,0,0,0);
        mainPanel.add(loginButton, gbc);

        // membuat button back dan tambahkan ke mainPanel
        backButton = new JButton("Kembali");
        setButtonDesign(backButton);
        gbc.gridy = 7;
        gbc.insets = new Insets(15,0,0,0);
        mainPanel.add(backButton, gbc);

        // action listener untuk passwordField agar saat user menekan enter maka bisa memproses login seperti saat menekan tombol login
        passwordField.addActionListener(e -> {
            handleLogin();
        });

        // action listener untuk button login
        loginButton.addActionListener(e -> {
            handleLogin();
        });

        // action listener untuk button back
        backButton.addActionListener(e -> {
            handleBack();
        });
    }

    /**
     * Method untuk mengatur tampilan button.
     * */
    private void setButtonDesign(JButton button) {
        button.setFont(new Font("Shikamaru", Font.BOLD, 15));
        button.setPreferredSize(new Dimension(150, 30));
        button.setBackground(new Color(23, 36, 110));
        button.setForeground(Color.WHITE);
        button.addMouseListener(new java.awt.event.MouseAdapter() { // agar button berubah tampilan saat cursor hover diatasnya
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(128, 175, 191));
                button.setForeground(new Color(23, 36, 110));
                button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(23, 36, 110));
                button.setForeground(Color.WHITE);;
                button.setCursor(Cursor.getDefaultCursor());
            }
        });
        button.setFocusPainted(false);
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        // kosongkan text field untuk id dan passwordfield, kemudian navigate / tampilkan panel HomeGUI
        idTextField.setText("");
        passwordField.setText("");
        MainFrame.getInstance().navigateTo(HomeGUI.KEY);
    }

    /**
     * Method untuk login pada sistem.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    private void handleLogin() {
        // TODO
        // get id dan password yang telah diinput
        String id = idTextField.getText();
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);

        boolean loginVal = MainFrame.getInstance().login(id, password); // cek apakah bisa login dengan menggunakan id dan password yang ada
        if (loginVal){ // jika berhasil login
            idTextField.setText("");
            passwordField.setText("");
        } else { // jika gagal login
            idTextField.setText("");
            passwordField.setText("");
            JOptionPane.showMessageDialog(null, "ID atau password invalid!",
                    "Login Failed", JOptionPane.ERROR_MESSAGE); // tampilkan message gagal login
        }
    }
}