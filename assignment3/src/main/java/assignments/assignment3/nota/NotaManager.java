package assignments.assignment3.nota;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NotaManager {
    public static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    public static Calendar cal = Calendar.getInstance();
    static public Nota[] notaList = new Nota[0];

    /**
     * Skips ke hari berikutnya dan update semua entri nota yang sesuai.
     */
    public static void toNextDay() {
        // TODO: implement skip hari
        cal.add(Calendar.DATE, 1);
        for (Nota nota : notaList){
            nota.toNextDay(); // kurangi sisa hari pengerjaan nota
        }
    }

    /**
     * Menambahkan nota baru ke NotaList.
     *
     * @param nota Nota object untuk ditambahkan.
     */
    public static void addNota(Nota nota) {
        // TODO: implement add nota
        Nota[] enlargedNotaList = new Nota[notaList.length + 1];
        for (int i = 0; i < notaList.length; i++) {
            enlargedNotaList[i] = notaList[i];
        }
        notaList = enlargedNotaList;
        notaList[notaList.length - 1] = nota;
    }
}
