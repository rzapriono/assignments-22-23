package assignments.assignment1;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NotaGenerator {
    private static final Scanner input = new Scanner(System.in);

    /**
     * Method main, program utama kalian berjalan disini.
     */
    public static void main(String[] args) {
        // TODO: Implement interface menu utama
        while (true) {
            int pilihan; 
            // validasi input pilihan (jika input bukan int)
            while (true){
                try{
                    printMenu();
                    System.out.print("Pilihan : ");
                    pilihan = input.nextInt();
                    break;
                } catch (InputMismatchException a){ 
                    System.out.println("================================");
                    System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
                    input.nextLine(); // mencegah infinite loop
                }
            }
            
            if (pilihan == 1){
                // input nama dan no HP
                input.nextLine();
                System.out.println("Masukkan nama Anda: ");
                String nama = input.nextLine();

                System.out.println("Masukkan nomor handphone Anda: "); 
                String nomorHP = input.nextLine();
                while (nomorHP.matches("[0-9]+") != true){ // cek apakah nomor hp hanya mengandung digit
                    System.out.println("Nomor hp hanya menerima digit");
                    System.out.println("Masukkan nomor handphone Anda: ");
                    nomorHP = input.nextLine();
                }

                // generate ID dan ditampilkan
                String ID = generateId(nama, nomorHP);
                System.out.printf("ID Anda : %s \n",ID);
                
            } else if (pilihan == 2){
                System.out.println("================================");
                System.out.println("Masukkan nama Anda: ");
                input.nextLine();
                String nama = input.nextLine();
                
                System.out.println("Masukkan nomor handphone Anda: ");
                String nomorHP = input.nextLine();
                while (nomorHP.matches("[0-9]+") != true){ // cek apakah nomor hp hanya mengandung digit
                    System.out.println("Nomor hp hanya menerima digit");
                    System.out.println("Masukkan nomor handphone Anda: ");
                    nomorHP = input.nextLine();
                }
                String ID = generateId(nama, nomorHP);

                System.out.println("Masukkan tanggal terima: ");
                String tanggalTerima = input.next();          

                String paket;
                while (true) { // validasi input paket laundry
                    System.out.println("Masukkan paket laundry: "); 
                    paket = input.next();  
                    if (paket.equalsIgnoreCase("reguler") || paket.equalsIgnoreCase("express") || 
                    paket.equalsIgnoreCase("fast")){
                        break;
                    } else if (paket.equalsIgnoreCase("?")){
                        showPaket();
                    } else {
                        System.out.printf("Paket %s tidak diketahui\n", paket);
                        System.out.println("[ketik ? untuk mencari tahu jenis paket]");
                        input.nextLine();
                    }
                }

                int beratCucian;
                System.out.println("Masukkan berat cucian Anda: ");
                while (true){ // validasi input berat cucian
                    try{ 
                        beratCucian = input.nextInt();
                        while (beratCucian <= 0){
                            System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                            beratCucian = input.nextInt();
                        } break;
                    } catch (InputMismatchException a){ // handle jika input bukan int
                        System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                        input.nextLine(); // mencegah infinite loop
                    }
                }

                if (beratCucian < 2){
                    beratCucian = 2;
                    System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
                }
                System.out.println("Nota Laundry");
                String notaLaundry = generateNota(ID, paket, beratCucian, tanggalTerima);
                System.out.println(notaLaundry);

            } else if (pilihan == 0){
                System.out.println("================================");
                System.out.println("Terima kasih telah menggunakan NotaGenerator!");
                break;
            } else {
                System.out.println("================================");
                System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
                             
            }
        }
    }

    /**
     * Method untuk menampilkan menu di NotaGenerator.
     */
    private static void printMenu() {
        System.out.println("Selamat datang di NotaGenerator!");
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate ID");
        System.out.println("[2] Generate Nota");
        System.out.println("[0] Exit");
    }

    /**
     * Method untuk menampilkan paket.
     */
    private static void showPaket() {
        System.out.println("+-------------Paket-------------+");
        System.out.println("| Express | 1 Hari | 12000 / Kg |");
        System.out.println("| Fast    | 2 Hari | 10000 / Kg |");
        System.out.println("| Reguler | 3 Hari |  7000 / Kg |");
        System.out.println("+-------------------------------+");
    }

    /**
     * Method untuk membuat ID dari nama dan nomor handphone.
     * Parameter dan return type dari method ini tidak boleh diganti agar tidak mengganggu testing
     *
     * @return String ID anggota dengan format [NAMADEPAN]-[nomorHP]-[2digitChecksum]
     */
    public static String generateId(String nama, String nomorHP){
        // TODO: Implement generate ID sesuai soal.
        nama = nama.split(" ")[0]; // ambil kata pertama dari input nama
        int checkSum = 0;
        String gabungan = nama.toUpperCase() + "-" + nomorHP;
        for (int i = 0; i < gabungan.length(); i++){ // iterate setiap char dari string gabungan
            char character = gabungan.charAt(i);
            if (String.valueOf(character).matches("[0-9]+")){ // untuk char angka
                checkSum += ((int)(character)) - '0'; // ASCII code dari char
            } else if (String.valueOf(character).matches("[a-zA-Z]")) { // untuk char alphabet
                checkSum +=  ((int)(character)) - 'A' + 1;
            } else { // char selain huruf dan angka
                checkSum += 7;
            }
        }
        // generate 2 digit ID terakhir
        String twoDigitCode;
        if (checkSum < 10){ 
            twoDigitCode = "0" + Integer.toString(checkSum); // tambahkan "0" di awal jika kode hanya 1 digit
        } else if (checkSum >= 100){
            String temp = Integer.toString(checkSum);
            twoDigitCode = temp.substring(temp.length()-2); // ambil 2 digit terakhir jika kode > 2 digit
        } else {
            twoDigitCode = Integer.toString(checkSum);
        }
        String ID = gabungan + "-" + twoDigitCode;
        return ID;
    }

    /**
     *
     * Method untuk membuat Nota.
     * Parameter dan return type dari method ini tidak boleh diganti agar tidak mengganggu testing.
     *
     * @return string nota dengan format di bawah:
     *         <p>ID    : [id]
     *         <p>Paket : [paket]
     *         <p>Harga :
     *         <p>[berat] kg x [hargaPaketPerKg] = [totalHarga]
     *         <p>Tanggal Terima  : [tanggalTerima]
     *         <p>Tanggal Selesai : [tanggalTerima + LamaHariPaket]
     */

    public static String generateNota(String id, String paket, int berat, String tanggalTerima){
        // TODO: Implement generate nota sesuai soal.
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // set format tanggal menjadi dd/MM/yyyy
        LocalDate tanggal = LocalDate.parse(tanggalTerima, dateFormat); // parse input tanggal ke dalam format yang telah di set

        int harga;
        int totalHarga;
        LocalDate tanggalSelesai;
        // hitung total harga dan tanggal selesai sesuai dengan paket laundry
        if (paket.equalsIgnoreCase("express")){
            harga = 12000;
            totalHarga = berat * harga;
            tanggalSelesai = tanggal.plusDays(1); // tambahkan 1 hari dari tanggal terima
        } else if (paket.equalsIgnoreCase("fast")){
            harga = 10000;
            totalHarga = berat * harga;
            tanggalSelesai = tanggal.plusDays(2); // tambahkan 2 hari dari tanggal terima
        } else {
            harga = 7000;
            totalHarga = berat * harga;
            tanggalSelesai = tanggal.plusDays(3); // tambahkan 3 hari dari tanggal terima
        }
        return String.format("ID    : %s\nPaket : %s\nHarga :\n%s kg x %s = %s\nTanggal Terima  : %s\nTanggal Selesai : %s"
        , id, paket, berat, harga, totalHarga, tanggalTerima, tanggalSelesai.format(dateFormat));
        
    }
}