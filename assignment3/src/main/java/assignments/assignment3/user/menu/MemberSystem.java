package assignments.assignment3.user.menu;

import assignments.assignment1.NotaGenerator;
import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.SetrikaService;
import assignments.assignment3.user.Member;

import static assignments.assignment3.nota.NotaManager.cal;
import static assignments.assignment3.nota.NotaManager.fmt;

public class MemberSystem extends SystemCLI {
    /**
     * Memproses pilihan dari Member yang masuk ke sistem ini sesuai dengan menu
     * specific.
     *
     * @param choice -> pilihan pengguna.
     * @return true jika user log.
     */
    @Override
    protected boolean processChoice(int choice) {
        boolean logout = false;
        // TODO
        switch (choice) { // proses sesuai dengan pilihan user
            case 1 -> processLaundry();
            case 2 -> processLihatNota();
            case 3 -> logout = true;
        }
        return logout;
    }

    /**
     * Displays specific menu untuk Member biasa.
     */
    @Override
    protected void displaySpecificMenu() {
        System.out.println("1. Saya ingin laundry");
        System.out.println("2. Lihat detail nota saya");
        System.out.println("3. Logout");
    }

    /**
     * Menambahkan Member baru ke sistem.
     *
     * @param member -> Member baru yang akan ditambahkan.
     */
    public void addMember(Member member) {
        // TODO
        Member[] enlargedMemberList = new Member[memberList.length + 1]; // buat temp array yang lebih panjang 1 elemen
        for (int i = 0; i < memberList.length; i++) {
            enlargedMemberList[i] = memberList[i]; // copy elemen array ke temp array
        }
        memberList = enlargedMemberList; // samakan ukuran array dan elemennya
        memberList[memberList.length - 1] = member; // masukkan paramater ke array asli
    }

    /**
     * Proses buat nota jika member yang sedang login ingin laundry
     */
    public void processLaundry() {
        // minta input yang diperlukan
        String paket = validasiPaket();
        int berat = validasiBerat();

        System.out.print("Apakah kamu ingin cucianmu disetrika oleh staff professional kami?\nHanya tambah 1000 / kg :0\n[Ketik x untuk tidak mau]: ");
        String inputSetrika = in.nextLine();
        System.out.print("Mau diantar oleh kurir kami? Dijamin aman dan cepat sampai tujuan!\nCuma 2000 / 4kg, kemudian 500 / kg\n[Ketik x untuk tidak mau]: ");
        String inputAntar = in.nextLine();

        Nota newNota = new Nota(loginMember, berat, paket, fmt.format(cal.getTime())); // buat object nota baru
        loginMember.addNota(newNota); // tambahkan nota ke notaList member yang sedang login
        NotaManager.addNota(newNota); // tambahkan nota ke notaList yang berisikan nota dari semua member
        
        if (!inputSetrika.equalsIgnoreCase("x")){
            SetrikaService setrika = new SetrikaService(); 
            newNota.addService(setrika); // add service setrika ke nota jika user ingin cucian disetrika
        }
        if (!inputAntar.equalsIgnoreCase("x")){
            AntarService antar = new AntarService();
            newNota.addService(antar); // add service antar ke nota jika user ingin cucian diantar
        }
        System.out.println("Nota berhasil dibuat!");
    }

    /**
     * Proses menampilkan seluruh nota dari member yang sedang login
     */
    public void processLihatNota(){
        Nota[] notaListLoginMember = loginMember.getNotaList(); // nota pesanan dari member yang sedang login
        for (Nota nota : notaListLoginMember){
            System.out.println(nota); // tampilkan informasi nota sesuai dengan ketentuan soal
        }
    }

    /**
     * Validasi agar paket yang dipesan sesuai dengan yang tersedia
     * @return string nama paket
     */
    public String validasiPaket() {
        while (true) { // validasi input paket laundry
            System.out.println("Masukkan paket laundry: ");
            NotaGenerator.showPaket();
            String inputPaket = in.nextLine();
            if (inputPaket.equalsIgnoreCase("reguler") || inputPaket.equalsIgnoreCase("express") 
            ||inputPaket.equalsIgnoreCase("fast")) {
                return inputPaket;
            } else if (inputPaket.equalsIgnoreCase("?")) {
                NotaGenerator.showPaket();
            } else {
                System.out.printf("Paket %s tidak diketahui\n", inputPaket);
                System.out.println("[ketik ? untuk mencari tahu jenis paket]");
            }
        }
    }

    /**
     * Validasi agar berat yang diinput sesuai dengan ketentuan soal
     * @return int berat
     */
    public int validasiBerat() {
        String inputBeratCucian;
        System.out.println("Masukkan berat cucian Anda [Kg]: ");
        inputBeratCucian = in.nextLine();
        while (inputBeratCucian.matches("[0-9]+") != true || Integer.parseInt(inputBeratCucian) == 0
        || inputBeratCucian.contains(" ")) { // validasi input berat cucian agar hanya mengandung angka positif dalam bentuk string
            System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
            inputBeratCucian = in.nextLine();
        }
        int inputBeratCucianInt = Integer.parseInt(inputBeratCucian); // convert berat cucian ke int untuk perhitungan
        if (inputBeratCucianInt < 2) { // jika berat < 2 kg, maka jadikan 2 kg
            inputBeratCucianInt = 2;
            System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
        }
        return inputBeratCucianInt;
    }
}