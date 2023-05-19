package assignments.assignment4.gui.member;

import assignments.assignment3.user.Member;
import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class AbstractMemberGUI extends JPanel implements Loginable{
    private JLabel welcomeLabel;
    private JLabel loggedInAsLabel;
    protected Member loggedInMember;
    private final SystemCLI systemCLI;
    private JPanel mainPanel;
    private JPanel pictPanel;
    private GridBagConstraints gbc = new GridBagConstraints();
    private static final int DELAY = 10; // delay untuk running text
    private static final int SPEED = 1; // kecepatan pergeseran running text

    public AbstractMemberGUI(SystemCLI systemCLI) {
        super(new BorderLayout());
        this.systemCLI = systemCLI;
        this.setBackground(new Color(23, 36, 110));

        // buat panel yang akan menampung button dan info id
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(198, 224, 225));
        mainPanel.setPreferredSize(new Dimension(300,450));

        // buat panel yang akan menampung gambar
        pictPanel = new JPanel(new BorderLayout());
        pictPanel.setBackground(new Color(198, 224, 225));

        // Set up welcome label
        welcomeLabel = new JLabel("", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Gang of Three", Font.PLAIN, 30));
        welcomeLabel.setForeground(new Color(229, 174, 78));
        add(welcomeLabel, BorderLayout.NORTH);

        // membuat running text untuk welcomeLabel yang menampilkan nama user
        Timer timer = new Timer(DELAY, new ActionListener() {
            int x = mainPanel.getWidth(); // mulai dari posisi paling kanan
            @Override
            public void actionPerformed(ActionEvent e) {
                x -= SPEED; // menggeser posisi teks ke kiri
                if (x < -welcomeLabel.getWidth()) {
                    x = mainPanel.getWidth(); // mengatur ulang posisi ke paling kanan setelah teks mencapai batas kiri
                }
                welcomeLabel.setLocation(x, welcomeLabel.getY());
            }
        });
        timer.start();

        // Set up footer
        loggedInAsLabel = new JLabel("", SwingConstants.CENTER);
        loggedInAsLabel.setFont(new Font("Gang of Three", Font.PLAIN, 18));
        loggedInAsLabel.setForeground(new Color(219, 22, 14));
        gbc.gridy = 2;
        gbc.insets = new Insets(30,0,0,0);
        mainPanel.add(loggedInAsLabel, gbc);

        // Initialize buttons
        JPanel buttonsPanel = initializeButtons();
        gbc.gridy = 1;
        gbc.insets = new Insets(0,0,0,0);
        mainPanel.add(buttonsPanel, gbc);

        // buat object image icon dan label dari gambar yang ingin ditampilkan, kemudian tambahkan ke panel untuk gambar (pictPanel)
        String dir = System.getProperty("user.dir")+"/assignment4/src/main/java/assignments/assignment4/gui/designs/mai3.png";
        ImageIcon mai =new ImageIcon(dir);
        JLabel maiLabel =new JLabel(mai);
        pictPanel.add(maiLabel, BorderLayout.SOUTH);

        // tambahkan mainPanel dan pictPanel ke frame dan atur letaknya di frame
        add(mainPanel, BorderLayout.WEST);
        add(pictPanel, BorderLayout.CENTER);

        // atur background panel untuk button
        buttonsPanel.setBackground(new Color(198, 224, 225));
    }

    /**
     * Membuat panel button yang akan ditampilkan pada Panel ini.
     * Buttons dan ActionListeners akan disupply oleh method createButtons() & createActionListeners() respectively.
     * <p> Feel free to make any changes. Be creative and have fun!
     *
     * @return JPanel yang di dalamnya berisi Buttons.
     * */
    protected JPanel initializeButtons() {
        JButton[] buttons = createButtons();
        ActionListener[] listeners = createActionListeners();

        if (buttons.length != listeners.length) {
             throw new IllegalStateException("Number of buttons and listeners must be equal.");
        }

        // buat panel untuk button
        JPanel buttonsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // atur letak dan tampilan button
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 1.0;
        gbc.weighty = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridwidth = 1;

        for (int i = 0; i < buttons.length; i++) {
            JButton button = buttons[i];
            button.addActionListener(listeners[i]); // action listener untuk button yang telah dibuat
            setButtonDesign(button); // atur tampilan button yang telah dibuat
            buttonsPanel.add(button, gbc); // tambahkan button ke buttonsPanel
        }

        // buat button untuk login dan atur tampilannya
        JButton logoutButton = new JButton("Logout");
        setButtonDesign(logoutButton);

        // action listener button logout
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.getInstance().logout();
            }
        });
        buttonsPanel.add(logoutButton, gbc); // tambahkan button logout ke buttonsPanel
        return buttonsPanel;
    }

    /**
     * Method untuk mengatur tampilan button.
     * */
    private void setButtonDesign(JButton button) {
        button.setFont(new Font("Shikamaru", Font.BOLD, 15));
        button.setPreferredSize(new Dimension(250, 30));
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
                button.setForeground(Color.WHITE);
                ;
                button.setCursor(Cursor.getDefaultCursor());
            }
        });
        button.setFocusPainted(false);
    }

    /**
     * Method untuk login pada panel.
     * <p>
     * Method ini akan melakukan pengecekan apakah ID dan passowrd yang telah diberikan dapat login
     * pada panel ini. <p>
     * Jika bisa, member akan login pada panel ini, method akan:<p>
     *  - mengupdate Welcome & LoggedInAs label.<p>
     *  - mengupdate LoggedInMember sesuai dengan instance pemilik ID dan password.
     *
     * @param id -> ID anggota yang akan diautentikasi.
     * @param password -> password anggota untuk mengautentikasi.
     * @return true jika ID dan password sesuai dengan instance member, false jika tidak.
     * */
    public boolean login(String id, String password) {
        // TODO
        loggedInMember = systemCLI.authUser(id, password); // autentikasi user berdasarkan id dan password yang diinput

        if (loggedInMember != null) { // jika user valid / telah terautentikasi
            welcomeLabel.setText("----- Welcome, " + loggedInMember.getNama() + "! -----");
            loggedInAsLabel.setText("Logged in as:" + loggedInMember.getId());
            return true;
        } else { // jika user tidak terautentikasi
            return false;
        }
    }

    /**
     * Method untuk logout pada panel ini.
     * Akan mengubah loggedInMemberMenjadi null.
     * */
    public void logout() {
        loggedInMember = null;
    }

    /**
     * Method ini mensupply buttons apa saja yang akan dimuat oleh panel ini.
     * Button yang disediakan method ini BELUM memiliki ActionListener.
     * Harus diimplementasikan sesuai class yang menginherit class ini.
     *
     * @return Array of JButton, berisi button yang sudah stylize namun belum ada ActionListener.
     * */
    protected abstract JButton[] createButtons();

    /**
     * Method ini mensupply ActionListener korespondensi dengan button yang dibuat createButtons().
     * Harus diimplementasikan sesuai class yang menginherit class ini.
     *
     * @return Array of ActionListener.
     * */
    protected abstract ActionListener[] createActionListeners();
}
