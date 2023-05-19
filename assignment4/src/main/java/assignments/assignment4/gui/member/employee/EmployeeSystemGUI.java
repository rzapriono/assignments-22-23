package assignments.assignment4.gui.member.employee;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;

import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment4.gui.member.AbstractMemberGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class EmployeeSystemGUI extends AbstractMemberGUI {
    public static final String KEY = "EMPLOYEE";

    public EmployeeSystemGUI(SystemCLI systemCLI) {
        super(systemCLI);
    }


    @Override
    public String getPageName(){
        return KEY;
    }

    /**
     * Method ini mensupply buttons yang sesuai dengan requirements Employee.
     * Button yang disediakan method ini BELUM memiliki ActionListener.
     *
     * @return Array of JButton, berisi button yang sudah stylize namun belum ada ActionListener.
     * */
    @Override
    protected JButton[] createButtons() {
        // TODO
        JButton nyuciButton = new JButton("It's nyuci time");
        JButton displayListNotaButton = new JButton("Display List Nota");

        return new JButton[]{
                nyuciButton,
                displayListNotaButton
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
                e -> cuci(),
                e -> displayNota(),
        };
    }

    /**
     * Menampilkan semua Nota yang ada pada sistem.
     * Akan dipanggil jika pengguna menekan button pertama pada createButtons
     * */
    private void displayNota() {
        // TODO
        String displayNota = "";
        for (Nota nota : NotaManager.getAllNota()){
            displayNota += nota.getNotaStatus() + "\n";
        }

        if (NotaManager.getAllNota().length == 0){
            String dir = System.getProperty("user.dir")+"/assignment4/src/main/java/assignments/assignment4/gui/designs/cry2.gif";
            ImageIcon sleeping =new ImageIcon(dir);

            JOptionPane.showMessageDialog(this, "Belum pernah ada yang laundry di CuciCuci! hiks:(", "List Nota",
                    JOptionPane.INFORMATION_MESSAGE, sleeping);
            return;
        }

        JOptionPane.showMessageDialog(this, displayNota, "List Nota",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Menampilkan dan melakukan action mencuci.
     * Akan dipanggil jika pengguna menekan button kedua pada createButtons
     * */
    private void cuci() {
        // TODO
        String kerjakanString = "";
        for (Nota nota : NotaManager.getAllNota()){
            kerjakanString += nota.kerjakan() + "\n";
        }

        if (NotaManager.getAllNota().length == 0){
            String dir = System.getProperty("user.dir")+"/assignment4/src/main/java/assignments/assignment4/gui/designs/cry2.gif";
            ImageIcon sleeping =new ImageIcon(dir);

            JOptionPane.showMessageDialog(this, "Belum pernah ada yang laundry di CuciCuci! hiks:(", "List Nota",
                    JOptionPane.INFORMATION_MESSAGE, sleeping);
            return;
        }

        JOptionPane.showMessageDialog(this,
                "Stand back! " + loggedInMember.getNama() + " beginning to nyuci!", "List Nota",
                JOptionPane.INFORMATION_MESSAGE);

        JOptionPane.showMessageDialog(this, kerjakanString, "List Nota",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
