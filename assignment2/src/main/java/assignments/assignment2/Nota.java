package assignments.assignment2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Nota {
    // TODO: tambahkan attributes yang diperlukan untuk class ini
    private int idNota;
    private String paket;
    private Member member;
    private int berat;
    private String tanggalMasuk;
    private int sisaHariPengerjaan;
    private boolean isReady;

    public Nota(Member member, String paket, int berat, String tanggalMasuk) {
        // TODO: buat constructor untuk class ini
        this.member = member;
        this.paket = paket;
        this.berat = berat;
        this.tanggalMasuk = tanggalMasuk;
        member.addBonusCounter(); // increment counter untuk diskon member tiap objek nota dibuat
    }

    // TODO: tambahkan methods yang diperlukan untuk class ini
    public void nextDay(){
        sisaHariPengerjaan -= 1; 
    }

    public void generateNota(){
        // TODO: Implement generate nota sesuai soal.
        if (berat < 2){ // jika berat < 2 kg, maka jadikan 2 kg 
            berat = 2;
            System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
        }
        System.out.println("Berhasil menambahkan nota!");
        System.out.printf("[ID Nota = %d]\n", this.idNota);
        
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // set format tanggal menjadi dd/MM/yyyy
        LocalDate tanggal = LocalDate.parse(this.tanggalMasuk, dateFormat); // parse input tanggal ke dalam format yang telah di set

        long harga;
        long totalHarga;
        LocalDate tanggalSelesai;
        // hitung total harga dan tanggal selesai sesuai dengan paket laundry
        if (paket.equalsIgnoreCase("express")){
            harga = 12000;
            totalHarga = berat * harga;
            sisaHariPengerjaan = 1;
            tanggalSelesai = tanggal.plusDays(1); // tambahkan 1 hari dari tanggal terima
        } else if (paket.equalsIgnoreCase("fast")){
            harga = 10000;
            totalHarga = berat * harga;
            sisaHariPengerjaan = 2;
            tanggalSelesai = tanggal.plusDays(2); // tambahkan 2 hari dari tanggal terima
        } else {
            harga = 7000;
            totalHarga = berat * harga;
            sisaHariPengerjaan = 3;
            tanggalSelesai = tanggal.plusDays(3); // tambahkan 3 hari dari tanggal terima
        }
        if (member.getBonusCounter() == 3){ // jika member mendapat diskon (nota ke 3)
            member.resetBonusCounter(); // reset counter diskon
            System.out.println(String.format("ID    : %s\nPaket : %s\nHarga :\n%s kg x %s = %s = %s (Discount member 50%%!!!)\nTanggal Terima  : %s\nTanggal Selesai : %s\nStatus      	: %s"
            , this.member.getID(), paket, berat, harga, totalHarga, (totalHarga/2), 
            tanggalMasuk, tanggalSelesai.format(dateFormat), this.getStatus()));  
        } else {
            System.out.println(String.format("ID    : %s\nPaket : %s\nHarga :\n%s kg x %s = %s\nTanggal Terima  : %s\nTanggal Selesai : %s\nStatus      	: %s"
            , this.member.getID(), paket, berat, harga, totalHarga, 
            tanggalMasuk, tanggalSelesai.format(dateFormat), this.getStatus()));
        }
        
    }
    public int getIdNota(){
        return idNota;
    }
    public String getPaket(){
        return paket;
    }
    public int getBerat(){
        return berat;
    }
    public String getTanggalMasuk(){
        return tanggalMasuk;
    }
    public int getSisaHariPengerjaan(){
        return sisaHariPengerjaan;
    }
    public String getStatus(){
        if(isReady == true){
            return "Sudah dapat diambil!";
        } else{
            return "Belum bisa diambil :(";
        }
    }
    public void setStatus(){
        if (sisaHariPengerjaan == 0){
            isReady = true;
        }
    }
    public void setIdNota(int counter) {
        this.idNota = counter;
    }
}
