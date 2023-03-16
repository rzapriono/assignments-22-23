package assignments.assignment2;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import assignments.assignment1.NotaGenerator;


public class MainMenu {
    private static final Scanner input = new Scanner(System.in);
    private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    private static Calendar cal = Calendar.getInstance();
    private static ArrayList<Nota> notaList = new ArrayList<Nota>();
    private static ArrayList<Member> memberList = new ArrayList<Member>();
    private static int counterIdNota;
    private static Nota tempRemoveNota;

    public static void main(String[] args) {
        boolean isRunning = true;
        while (isRunning) {
            printMenu();
            System.out.print("Pilihan : ");
            String command = input.nextLine();
            System.out.println("================================");
            switch (command){
                case "1" -> handleGenerateUser();
                case "2" -> handleGenerateNota();
                case "3" -> handleListNota();
                case "4" -> handleListUser();
                case "5" -> handleAmbilCucian();
                case "6" -> handleNextDay();
                case "0" -> isRunning = false;
                default -> System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
            }
        }
        System.out.println("Terima kasih telah menggunakan NotaGenerator!");
    }

    private static void handleGenerateUser() {
        // TODO: handle generate user
        System.out.println("Masukkan nama Anda: ");
        String nama = input.nextLine();

        System.out.println("Masukkan nomor handphone Anda: "); 
        String nomorHP = input.nextLine();
        while (nomorHP.matches("[0-9]+") != true){ // cek apakah nomor hp hanya mengandung digit
            System.out.println("Field nomor hp hanya menerima digit");
            nomorHP = input.nextLine();
        }

        if (memberList.size() == 0){ // jika memberList masih kosong
            Member newMember = new Member(nama, nomorHP); // inisiasi objek member baru
            memberList.add(newMember);
            System.out.printf("Berhasil membuat member dengan ID %s!\n", newMember.getID());
        } else{  
            boolean found = false;
            Member newMember = new Member(nama, nomorHP); // inisiasi objek member baru
            for (Member member : memberList){
                if (member.getID().equals(newMember.getID())){ // cek apakah objek member dengan id yang sama sudah ada di memberList
                    found = true;
                    System.out.printf("Member dengan nama %s dan nomor hp %s sudah ada!\n", newMember.getNama(), newMember.getNoHp());
                    break;  
                }
            } 
            if (found != true){
                memberList.add(newMember); // add objek member ke memberList jika objek dengan id tersebut blm ada di memberList
                System.out.printf("Berhasil membuat member dengan ID %s!\n", newMember.getID());      
            }
        }
    }

    private static void handleGenerateNota() {
        // TODO: handle ambil cucian
        System.out.println("Masukkan ID member:");
        String inputID = input.nextLine();
        if (memberList.size() == 0){ // jika tidak ada sama sekali member terdaftar
            System.out.printf("Member dengan ID %s tidak ditemukan!\n", inputID);
        } else {
            boolean found = false;
            for (Member member : memberList){
                if (member.getID().equals(inputID)){ // jika ada objek dengan id yang sama dengan input id
                    found = true;
                    String paket = validasiPaket();
                    int berat = validasiBerat();
                    Nota newNota = new Nota(member, paket, berat, fmt.format(cal.getTime())); // inisiasi objek nota baru 
                    newNota.setIdNota(counterIdNota); // set id nota dengan menggunakan counter
                    notaList.add(newNota); // add objek ke notaList
                    newNota.generateNota(); // generate nota untuk objek yang telah dibuat
                    counterIdNota++; // increment counter untuk id nota
                    break;
                }
            }
            if (found != true){ // apabila member dengan id yang diinput tidak ada
                System.out.printf("Member dengan ID %s tidak ditemukan!\n", inputID);
            }
        }
    }

    private static void handleListNota() {
        // TODO: handle list semua nota pada sistem
        System.out.printf("Terdaftar %d nota dalam sistem.\n", notaList.size());
        for (Nota nota : notaList){ // iterasi semua objek nota pada notaList
            System.out.printf("- [%d] Status      	: %s\n", nota.getIdNota(), nota.getStatus()); // tampilkan id nota dan status dari tiap objek nota
        }
    }

    private static void handleListUser() {
        // TODO: handle list semua user pada sistem
        System.out.printf("Terdaftar %d member dalam sistem.\n", memberList.size());
        for (Member member : memberList){ // iterasi semua objek member pada memberList
            System.out.printf("- %s : %s\n", member.getID(), member.getNama()); // tampilkan id dan nama dari tiap objek member
        }
    }

    private static void handleAmbilCucian() {
        // TODO: handle ambil cucian
        System.out.println("Masukkan ID nota yang akan diambil: ");
        String inputIdNota = input.nextLine();
        while (inputIdNota.matches("[0-9]+") != true){ // cek apakah input id nota berbentuk angka
            System.out.println("ID nota berbentuk angka!");
            inputIdNota = input.nextLine();
        }
        boolean found = false;
        boolean found2 = false;
        for (Nota nota : notaList){
            if (nota.getIdNota() == (Integer.parseInt(inputIdNota))){ // jika terdapat nota yang sesuai dengan input id nota
                found2 = true;
                if (nota.getStatus().equals("Sudah dapat diambil!")){ // cek apakah nota sudah bisa diambil
                    found = true;
                    tempRemoveNota = nota;
                    System.out.printf("Nota dengan ID %s berhasil diambil!\n", inputIdNota);
                    break;
                }
                break;
            }
        } 

        if (found != true){
            if (found2 == true){  // jika nota belum bisa diambil 
                System.out.printf("Nota dengan ID %s gagal diambil!\n", inputIdNota);
            } else { // jika tidak terdapat nota yang sesuai dengan input id nota
                System.out.printf("Nota dengan ID %s tidak ditemukan!\n", inputIdNota);
            }
        } else {
            notaList.remove(tempRemoveNota); // remove nota yang berhasil diambil dari notaList
        }

    }

    private static void handleNextDay() {
        // TODO: handle ganti hari
        cal.add(Calendar.DATE, 1); // add 1 hari ke tanggal sistem
        for (Nota nota : notaList){
            nota.nextDay(); // kurangi sisa hari pengerjaan nota
            nota.setStatus(); // cek apakah hari pengerjaan sudah 0
        }
        System.out.println("Dek Depe tidur hari ini... zzz...");
        for (Nota nota : notaList){ // tampilkan nota yang sudah bisa diambil pada hari tersebut jika ada
            if (nota.getStatus().equals("Sudah dapat diambil!")){
                System.out.printf("Laundry dengan nota ID %d sudah dapat diambil!\n", nota.getIdNota());
            } 
        }
        System.out.println("Selamat pagi dunia!\nDek Depe: It's CuciCuci Time.");
    }

    private static void printMenu() {
        System.out.println("\nSelamat datang di CuciCuci!");
        System.out.printf("Sekarang Tanggal: %s\n", fmt.format(cal.getTime()));
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate User");
        System.out.println("[2] Generate Nota");
        System.out.println("[3] List Nota");
        System.out.println("[4] List User");
        System.out.println("[5] Ambil Cucian");
        System.out.println("[6] Next Day");
        System.out.println("[0] Exit");
    }

    private static String validasiPaket(){
        while (true) { // validasi input paket laundry
            System.out.println("Masukkan paket laundry: "); 
            String inputPaket = input.nextLine();  
            if (inputPaket.equalsIgnoreCase("reguler") || inputPaket.equalsIgnoreCase("express") || 
            inputPaket.equalsIgnoreCase("fast")){
                return inputPaket;
            } else if (inputPaket.equalsIgnoreCase("?")){
                NotaGenerator.showPaket();
            } else {
                System.out.printf("Paket %s tidak diketahui\n", inputPaket);
                System.out.println("[ketik ? untuk mencari tahu jenis paket]");
            }
        }
    }

    private static int validasiBerat(){
        String inputBeratCucian;
        System.out.println("Masukkan berat cucian Anda [Kg]: ");
        inputBeratCucian = input.nextLine();
        while (inputBeratCucian.matches("[0-9]+") != true || Integer.parseInt(inputBeratCucian) == 0 
        || inputBeratCucian.contains(" ")){ // validasi input berat cucian agar hanya mengandung angka positif dalam bentuk string
            System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
            inputBeratCucian = input.nextLine();
        } 
        int inputBeratCucianInt = Integer.parseInt(inputBeratCucian); // convert berat cucian ke int untuk perhitungan harga generateNota
        return inputBeratCucianInt;
    }   
}
