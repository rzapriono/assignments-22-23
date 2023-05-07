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
        cal.add(Calendar.DATE, 1); // tambahkan 1 hari ke sistem
        for (Nota nota : notaList){
            nota.toNextDay(); // kurangi sisa hari pengerjaan nota dan cek apakah nota terlambat
        }
    }

    /**
     * Menambahkan nota baru ke NotaList.
     *
     * @param nota Nota object untuk ditambahkan.
     */
    public static void addNota(Nota nota) {
        // TODO: implement add nota
        Nota[] enlargedNotaList = new Nota[notaList.length + 1]; // buat temp array yang lebih panjang 1 elemen
        for (int i = 0; i < notaList.length; i++) {
            enlargedNotaList[i] = notaList[i]; // copy elemen array ke temp array
        }
        notaList = enlargedNotaList; // samakan ukuran array dan elemennya
        notaList[notaList.length - 1] = nota; // masukkan paramater ke array asli
    }
}
