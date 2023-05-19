package assignments.assignment4.gui.member.member;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.SetrikaService;
import assignments.assignment3.user.Member;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CreateNotaGUI extends JPanel {
    public static final String KEY = "CREATE_NOTA";
    private JLabel paketLabel;
    private JComboBox<String> paketComboBox;
    private JButton showPaketButton;
    private JLabel beratLabel;
    private JTextField beratTextField;
    private JCheckBox setrikaCheckBox;
    private JCheckBox antarCheckBox;
    private JButton createNotaButton;
    private JButton backButton;
    private final SimpleDateFormat fmt;
    private final Calendar cal;
    private final MemberSystemGUI memberSystemGUI;

    public CreateNotaGUI(MemberSystemGUI memberSystemGUI) {
        this.memberSystemGUI = memberSystemGUI;
        this.fmt = NotaManager.fmt;
        this.cal = NotaManager.cal;

        // Set up main panel, Feel free to make any changes
        initGUI();
    }

    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */
    private void initGUI() {
        // TODO
        // atur letak dan tampilan dari frame dan components
        this.setBorder(BorderFactory.createEmptyBorder(15, 20, 15,20));
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(198, 224, 225));

        GridBagConstraints gbc = new GridBagConstraints();
        JPanel labelAndOptionPanel = new JPanel();
        JPanel buttonPanel = new JPanel();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        // buat label untuk menanyakan paket dan tambahkan ke frame
        paketLabel = new JLabel("Paket Laundry");
        paketLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(paketLabel, gbc);

        // buat combobox untuk memilih paket dan tambahkan ke frame
        String[] paketArray = {"Express", "Fast", "Reguler"}; // pilihan paket yang bisa dipilih pada combobox
        paketComboBox = new JComboBox<>(paketArray);
        paketComboBox.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
        gbc.gridx = 1;
        add(paketComboBox, gbc);

        // buat button untuk show paket dan tambahkan ke frame
        showPaketButton = new JButton("Show Paket");
        setButtonDesign(showPaketButton);
        gbc.gridx = 2;
        gbc.insets = new Insets(0,15,0,0);
        gbc.gridwidth = 1;
        add(showPaketButton, gbc);

        // buat label untuk menanyakan berat dan tambahkan ke frame
        beratLabel = new JLabel("Berat Cucian (Kg)");
        beratLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(10,0,0,0);
        add(beratLabel, gbc);

        // buat text field untuk input berat dan tambahkan ke frame
        beratTextField = new JTextField();
        beratTextField.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
        gbc.gridx = 1;
        add(beratTextField, gbc);

        // buat checkbox untuk service setrika dan tambahkan ke frame
        setrikaCheckBox = new JCheckBox("Tambah Setrika Service (1000 / kg)");
        setrikaCheckBox.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
        setrikaCheckBox.setBackground(new Color(198, 224, 225));
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(setrikaCheckBox, gbc);

        // buat checkbox untuk service antar dan tambahkan ke frame
        antarCheckBox = new JCheckBox("Tambah Antar Service (2000 / 4kg pertama, kemudian 500 / kg");
        antarCheckBox.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
        antarCheckBox.setBackground(new Color(198, 224, 225));
        gbc.gridy = 3;
        add(antarCheckBox, gbc);

        // buat button untuk create nota dan tambahkan ke frame
        gbc.gridwidth = 3;
        createNotaButton = new JButton("Buat Nota");
        setButtonDesign(createNotaButton);
        gbc.insets = new Insets(30,0,0,0);
        gbc.gridy = 4;
        add(createNotaButton, gbc);

        // buat button untuk back dan tambahkan ke frame
        backButton = new JButton("Kembali");
        setButtonDesign(backButton);
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridy = 5;
        add(backButton, gbc);

        // action listener button show paket yang akan memanggil method showPaket() jika button ditekan
        showPaketButton.addActionListener(e -> {
            showPaket();
        });

        // action listener button create nota yang akan memanggil method createNota() jika button ditekan
        createNotaButton.addActionListener(e -> {
            createNota();
        });

        // action listener button back yang akan memanggil method handleBack() jika button ditekan
        backButton.addActionListener(e -> {
            handleBack();
        });
    }

    /**
     * Method untuk mengatur tampilan button.
     * */
    private void setButtonDesign(JButton button) {
        button.setFont(new Font("Shikamaru", Font.BOLD, 15));
        button.setPreferredSize(new Dimension(100, 25));
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
     * Menampilkan list paket pada user.
     * Akan dipanggil jika pengguna menekan "showPaketButton"
     * */
    private void showPaket() {
        String paketInfo = """
                        <html><pre>
                        +-------------Paket-------------+
                        | Express | 1 Hari | 12000 / Kg |
                        | Fast    | 2 Hari | 10000 / Kg |
                        | Reguler | 3 Hari |  7000 / Kg |
                        +-------------------------------+
                        </pre></html>
                        """;

        JLabel label = new JLabel(paketInfo);
        label.setFont(new Font("monospaced", Font.PLAIN, 12));
        JOptionPane.showMessageDialog(this, label, "Paket Information", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Method untuk melakukan pengecekan input user dan mendaftarkan nota yang sudah valid pada sistem.
     * Akan dipanggil jika pengguna menekan "createNotaButton"
     * */
    private void createNota() {
        // TODO
        String paket = (String) paketComboBox.getSelectedItem(); // get paket yang telah dipilih pada combobox
        String beratString = beratTextField.getText(); // get berat yang telah diinput pada text field berat
        if (!beratString.matches("[0-9]+") || Integer.parseInt(beratString) == 0
                || beratString.contains(" ")) { // validasi input berat cucian agar hanya mengandung angka positif dalam bentuk string
            JOptionPane.showMessageDialog(this, "Berat cucian harus berisi angka!",
                    "Berat tidak valid", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int beratInt = Integer.parseInt(beratString); // convert berat cucian ke int untuk perhitungan
        if (beratInt < 2) { // jika berat < 2 kg, maka jadikan 2 kg
            beratInt = 2;
            JOptionPane.showMessageDialog(this, "Cucian < 2 kg, maka akan dianggap sebagai 2 kg",
                    "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }

        Member loggedInMember = memberSystemGUI.getLoggedInMember(); // member yang sedang login saat ini
        Nota newNota = new Nota(loggedInMember, beratInt, paket, fmt.format(cal.getTime())); // buat object nota
        loggedInMember.addNota(newNota); // tambahkan object nota ke list nota member yang sedang login
        NotaManager.addNota(newNota); // tambahkan object nota ke list nota keseluruhan

        boolean isSetrika = setrikaCheckBox.isSelected(); // get value checkbox service setrika apakah diceklis / dipilih
        if(isSetrika){ // jika diceklis / dipilih
            newNota.addService(new SetrikaService()); // masukkan service setrika ke daftar service yang dipesan

        }
        boolean isAntar = antarCheckBox.isSelected(); // get value checkbox service antar apakah diceklis / dipilih
        if (isAntar){
            newNota.addService(new AntarService());  // masukkan service antar ke daftar service yang dipesan
        }

        // reset seluruh pilihan agar tampilan kembali seperti awal
        paketComboBox.setSelectedItem("Express");
        beratTextField.setText("");
        setrikaCheckBox.setSelected(false);
        antarCheckBox.setSelected(false);

        JOptionPane.showMessageDialog(this, "Nota berhasil dibuat",
                "Success", JOptionPane.INFORMATION_MESSAGE); // tampilkan pesan bahwa nota berhasil dibuat
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        // TODO
        // reset seluruh pilihan agar tampilan kembali seperti awal
        paketComboBox.setSelectedItem("Express");
        beratTextField.setText("");
        setrikaCheckBox.setSelected(false);
        antarCheckBox.setSelected(false);

        MainFrame.getInstance().navigateTo(MemberSystemGUI.KEY); // tampilkan / pindah ke panel MemberSystemGUI
    }
}
