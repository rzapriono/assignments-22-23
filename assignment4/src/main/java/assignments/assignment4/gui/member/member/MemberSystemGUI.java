package assignments.assignment4.gui.member.member;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.user.Member;
import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment4.MainFrame;
import assignments.assignment4.gui.member.AbstractMemberGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MemberSystemGUI extends AbstractMemberGUI {
    public static final String KEY = "MEMBER";

    public MemberSystemGUI(SystemCLI systemCLI) {
        super(systemCLI);
    }

    @Override
    public String getPageName(){
        return KEY;
    }

    public Member getLoggedInMember(){
        return loggedInMember;
    }

    /**
     * Method ini mensupply buttons yang sesuai dengan requirements MemberSystem.
     * Button yang disediakan method ini BELUM memiliki ActionListener.
     *
     * @return Array of JButton, berisi button yang sudah stylize namun belum ada ActionListener.
     * */
    @Override
    protected JButton[] createButtons() {
        // TODO
        // buat button untuk laundry / buat nota dan button untuk menampilkan detail nota
        JButton laundryButton = new JButton("Saya ingin Laundry");
        JButton detailNotaButton = new JButton("Lihat detail nota saya");

        return new JButton[]{
                laundryButton,
                detailNotaButton
        };
    }

    /**
     * Method ini mensupply ActionListener korespondensi dengan button yang dibuat createButtons()
     * sesuai dengan requirements MemberSystem.
     *
     * @return Array of ActionListener.
     * */
    @Override
    protected ActionListener[] createActionListeners() {
        return new ActionListener[]{
                e -> createNota(),
                e -> showDetailNota(),
        };
    }

    /**
     * Menampilkan detail Nota milik loggedInMember.
     * Akan dipanggil jika pengguna menekan button pertama pada createButtons
     * */
    private void showDetailNota() {
        // TODO
        // buat text area untuk menampilkan detail nota yang telah dibuat oleh member yang sedang login
        JTextArea notaTextArea = new JTextArea(15,40);
        notaTextArea.setEditable(false);

        // implementasikan scroll secara vertikal pada text area
        JScrollPane scrollPane = new JScrollPane(notaTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        String detailNota = "";
        Nota[] notaListLoggedInMember = loggedInMember.getNotaList();
        for (Nota nota : notaListLoggedInMember){
            nota.calculateHarga();
            detailNota += nota + "\n"; // tambahkan detail tiap nota dari nota yang telah dipesan oleh loggedInMember ke variabel detailnota
        }
        if (notaListLoggedInMember.length == 0){ // apabila loggedInMember pernah laundry
            detailNota = "Belum pernah laundry di CuciCuci? hiks:(";
        }
        notaTextArea.setText(detailNota); // masukkan string detailNota ke text area yang telah dibuat
        notaTextArea.setCaretPosition(0); // posisi default dari atas

        JOptionPane.showMessageDialog(this, scrollPane, "List Nota",
                JOptionPane.INFORMATION_MESSAGE); // tampilkan informasi detail nota

    }

    /**
     * Pergi ke halaman CreateNotaGUI.
     * Akan dipanggil jika pengguna menekan button kedua pada createButtons
     * */
    private void createNota() {
        // TODO
        MainFrame.getInstance().navigateTo(CreateNotaGUI.KEY);
    }

}
