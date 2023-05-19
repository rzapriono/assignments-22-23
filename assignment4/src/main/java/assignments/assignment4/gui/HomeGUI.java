package assignments.assignment4.gui;

import assignments.assignment3.LoginManager;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static assignments.assignment3.nota.NotaManager.toNextDay;

public class HomeGUI extends JPanel {
    public static final String KEY = "HOME";
    private JLabel titleLabel;
    private JLabel dateLabel;
    private JPanel mainPanel;
    private JButton loginButton;
    private JButton registerButton;
    private JButton toNextDayButton;
    private GridBagConstraints gbc = new GridBagConstraints();
    private JPanel pictPanel;

    public HomeGUI(){
        super(new BorderLayout()); // Setup layout, Feel free to make any changes

        // Set up main panel, Feel free to make any changes
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 10));

        pictPanel = new JPanel(new GridBagLayout());
        pictPanel.setBackground(new Color(198, 224, 225));

        initGUI();

        add(pictPanel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.WEST);

    }

    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */
    private void initGUI() {
        gbc.anchor = GridBagConstraints.NORTHWEST; // BUAT SET KE KIRI
        gbc.fill = GridBagConstraints.HORIZONTAL;

        mainPanel.setBackground(new Color(198, 224, 225));

        // create & add label dan button ke mainPanel serta atur tampilannya
        titleLabel = new JLabel("Selamat Datang di CuciCuci System!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Gang of Three", Font.PLAIN, 40));
        this.setBackground(new Color(229, 174, 78));
        titleLabel.setForeground(new Color(219, 22, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 50, 0);
        add(titleLabel, BorderLayout.NORTH);

        gbc.insets = new Insets(0, 0, 20, 0);

        loginButton = new JButton("Login");
        setButtonDesign(loginButton);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        mainPanel.add(loginButton, gbc);

        registerButton = new JButton("Register");
        setButtonDesign(registerButton);
        gbc.gridy = 4;
        mainPanel.add(registerButton, gbc);

        toNextDayButton = new JButton("Next Day");
        setButtonDesign(toNextDayButton);
        gbc.gridy = 6;
        gbc.insets = new Insets(0, 0, 0, 0);
        mainPanel.add(toNextDayButton, gbc);

        dateLabel = new JLabel("Hari ini: " + NotaManager.fmt.format(NotaManager.cal.getTime()));
        dateLabel.setFont(new Font("Gang of Three", Font.PLAIN, 20));
        dateLabel.setForeground(new Color(219, 22, 14));
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.insets = new Insets(50, 0, 0, 0);
        mainPanel.add(dateLabel, gbc);

        // Buat objek image icon dan label dari gambar yang ingin ditampilkan, kemudian tambahkan ke panel untuk gambar (pictPanel)
        String dir = System.getProperty("user.dir")+"/assignment4/src/main/java/assignments/assignment4/gui/designs/fuji.jpg";
        ImageIcon fuji =new ImageIcon(dir);
        JLabel fujiLabel =new JLabel(fuji);
        gbc.gridx = 0;
        gbc.gridy = 0;
        pictPanel.add(fujiLabel, gbc);

        // action listener button login yang akan memanggil method handleToLogin() jika button ditekan
        loginButton.addActionListener(e -> {
            handleToLogin();
        });

        // action listener button register yang akan memanggil method handleToRegister() jika button ditekan
        registerButton.addActionListener(e -> {
            handleToRegister();
        });

        // action listener button next day yang akan memanggil method handleNextDay() jika button ditekan
        toNextDayButton.addActionListener(e -> {
            handleNextDay();
        });
    }

    /**
     * Method untuk mengatur tampilan button.
     * */
    private void setButtonDesign(JButton button) {
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setFont(new Font("Shikamaru", Font.PLAIN, 15));
        button.setPreferredSize(new Dimension(100, 25));
        button.setBackground(new Color(23, 36, 110));
        button.setForeground(Color.WHITE);
        gbc.gridx = 0;

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
     * Method untuk pergi ke halaman register.
     * Akan dipanggil jika pengguna menekan "registerButton"
     * */
    private static void handleToRegister() {
        MainFrame.getInstance().navigateTo(RegisterGUI.KEY);
    }

    /**
     * Method untuk pergi ke halaman login.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    private static void handleToLogin() {
        MainFrame.getInstance().navigateTo(LoginGUI.KEY);
    }

    /**
     * Method untuk skip hari.
     * Akan dipanggil jika pengguna menekan "toNextDayButton"
     * */
    private void handleNextDay() {
        // menambahkan gif pada pop up message yang akan ditampilkan
        String dir = System.getProperty("user.dir")+"/assignment4/src/main/java/assignments/assignment4/gui/designs/sleep2.gif";
        ImageIcon sleeping =new ImageIcon(dir);

        // panggil method toNextDay() dari NotaManager yang sudah diimplementasikan pada TP3
        NotaManager.toNextDay();
        dateLabel.setText("Hari ini: " + NotaManager.fmt.format(NotaManager.cal.getTime())); // update label sesuai dengan tanggal pada sistem
        JOptionPane.showMessageDialog(this, "Kamu tidur hari ini ...zzz...", "Nehan Shoja no Jutsu!",
                JOptionPane.INFORMATION_MESSAGE, sleeping); // tampilkan message


    }
}