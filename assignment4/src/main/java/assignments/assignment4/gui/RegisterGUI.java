package assignments.assignment4.gui;

import assignments.assignment3.LoginManager;
import assignments.assignment3.user.Member;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterGUI extends JPanel {
    public static final String KEY = "REGISTER";
    private JPanel mainPanel;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel phoneLabel;
    private JTextField phoneTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton registerButton;
    private LoginManager loginManager;
    private JButton backButton;
    private GridBagConstraints gbc = new GridBagConstraints();

    public RegisterGUI(LoginManager loginManager) {
        super(new BorderLayout()); // Setup layout, Feel free to make any changes
        this.loginManager = loginManager;

        // Set up main panel, Feel free to make any changes
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
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
        // pengaturan tampilan dan letak components
        mainPanel.setBackground(new Color(198, 224, 225));
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1; // agar text field mengisi 1 panel secara horizontal
        gbc.gridx = 0;

        // buat label untuk menanyakan nama dan tambahkan ke mainPanel
        nameLabel = new JLabel("Masukkan nama Anda:");
        nameLabel.setFont(new Font("b Bumbu Sushi", Font.PLAIN, 18));
        nameLabel.setForeground(new Color(219, 22, 14));
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 10,5, 0);
        mainPanel.add(nameLabel, gbc);

        // buat text field untuk input nama dan tambahkan ke mainPanel
        nameTextField = new JTextField();
        nameTextField.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 10,0, 10);
        mainPanel.add(nameTextField, gbc);

        // buat label untuk menanyakan nomor hp dan tambahkan ke mainPanel
        phoneLabel = new JLabel("Masukkan nomor handphone Anda:");
        phoneLabel.setFont(new Font("b Bumbu Sushi", Font.PLAIN, 18));
        phoneLabel.setForeground(new Color(219, 22, 14));
        gbc.gridy = 2;
        gbc.insets = new Insets(15, 10,5, 10);
        mainPanel.add(phoneLabel, gbc);

        // buat text field untuk input nomor hp dan tambahkan ke mainPanel
        phoneTextField = new JTextField();
        phoneTextField.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
        gbc.gridy = 3;
        gbc.insets = new Insets(0,10,0,10);
        mainPanel.add(phoneTextField, gbc);

        // buat label untuk menanyakan password dan tambahkan ke mainPanel
        passwordLabel = new JLabel("Masukkan password Anda:");
        passwordLabel.setFont(new Font("b Bumbu Sushi", Font.PLAIN, 18));
        passwordLabel.setForeground(new Color(219, 22, 14));
        gbc.gridy = 4;
        gbc.insets = new Insets(15,10,5,10);
        mainPanel.add(passwordLabel, gbc);

        // buat password field untuk input password dan tambahkan ke mainPanel
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
        gbc.gridy = 5;
        gbc.insets = new Insets(0,10,0,10);
        mainPanel.add(passwordField, gbc);

        // mengatur agar button berada di tengah-tengah mainPanel
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;

        // buat button register dan add ke mainPanel
        registerButton = new JButton("Register");
        setButtonDesign(registerButton);
        gbc.gridy = 6;
        gbc.insets = new Insets(30,0,0,0);
        mainPanel.add(registerButton, gbc);

        // buat button back dan add ke mainPanel
        backButton = new JButton("Kembali");
        setButtonDesign(backButton);
        gbc.gridy = 7;
        gbc.insets = new Insets(15,0,0,0);
        mainPanel.add(backButton, gbc);

        // action listener untuk passwordField agar saat user menekan enter maka bisa memproses login seperti saat menekan tombol login
        passwordField.addActionListener(e -> {
            handleRegister();
        });

        // action listener untuk button register
        registerButton.addActionListener(e -> {
            handleRegister();
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
        // kosongkan semua text field untuk input, kemudian tampilkan / pergi ke panel HomeGUI
        nameTextField.setText("");
        phoneTextField.setText("");
        passwordField.setText("");
        MainFrame.getInstance().navigateTo(HomeGUI.KEY);
    }

    /**
    * Method untuk mendaftarkan member pada sistem.
    * Akan dipanggil jika pengguna menekan "registerButton"
    * */
    private void handleRegister() {
        // TODO
        // get nama, noHP, dan password dari yang telah diinput pada field masing-masing
        String nama = nameTextField.getText();
        String noHp = phoneTextField.getText();
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);

        if (nama.isEmpty() || noHp.isEmpty() || password.isEmpty()){ // cek apakah nama, noHp, atau password ada yang tidak diisi / kosong
            JOptionPane.showMessageDialog(this, "Semua field di atas wajib diisi!", "Registration Failed",
            JOptionPane.ERROR_MESSAGE);
            return;
        }
        else {
            if (!noHp.matches("[0-9]+")){ // cek apakah nomor hp hanya mengandung digit
                phoneTextField.setText("");
                JOptionPane.showMessageDialog(this, "Nomor handphone harus berisi angka!", "Invalid Phone Number",
                JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        Member registeredMember = loginManager.register(nama, noHp, password); // cek apakah member sudah terdaftar
        if (registeredMember == null) { // jika member yang ingin didaftarkan sudah terdaftar (gagal) dan tampilkan pesannya
            JOptionPane.showMessageDialog(this, "User dengan nama " + nama + " dan nomor hp " + noHp + " sudah ada!",
            "Registration Failed", JOptionPane.ERROR_MESSAGE);
            nameTextField.setText("");
            phoneTextField.setText("");
            passwordField.setText("");
            MainFrame.getInstance().navigateTo(HomeGUI.KEY);
            return;
        }

        // jika member belum terdaftar, maka berhasil mendaftar dan tampilkan pesannya
        JTextField textFieldSuccess = new JTextField("Berhasil membuat user dengan ID " + registeredMember.getId() +  "!");
        textFieldSuccess.setEditable(false);
        JOptionPane.showMessageDialog(this, textFieldSuccess,
        "Registration Successful", JOptionPane.INFORMATION_MESSAGE);
        nameTextField.setText("");
        phoneTextField.setText("");
        passwordField.setText("");
        MainFrame.getInstance().navigateTo(HomeGUI.KEY);
    }
}
