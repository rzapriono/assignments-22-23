package assignments.assignment3.nota;

import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.LaundryService;
import assignments.assignment3.nota.service.SetrikaService;
import assignments.assignment3.user.Member;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Nota {
    private Member member;
    private String paket;
    private LaundryService[] services;
    private long baseHarga; // harga sesuai berat cucian (tanpa services)
    private long totalHarga; // harga include services + kompensasi (jika ada)
    private int hargaPaket;
    private boolean telat;
    private int sisaHariPengerjaan;
    private int berat;
    private int idNota;
    private String tanggalMasuk;
    private LocalDate tanggalSelesai;
    private boolean isDone;
    static public int totalNota;

    public Nota(Member member, int berat, String paket, String tanggal) {
        // TODO
        this.member = member;
        this.berat = berat;
        this.paket = paket;
        this.tanggalMasuk = tanggal;
        this.idNota = totalNota++;
        this.services = new LaundryService[0];
    }

    public void addService(LaundryService service) {
        // TODO
        LaundryService[] enlargedServices = new LaundryService[this.services.length + 1];
        for (int i = 0; i < this.services.length; i++) {
            enlargedServices[i] = services[i];
        }
        this.services = enlargedServices;
        this.services[this.services.length - 1] = service;
    }

    public String kerjakan() {
        // TODO
        String statusNyuciTime = "";
        for (LaundryService service : services){
            if (!service.isDone()){
                statusNyuciTime = "Nota " + idNota +  " : " + service.doWork();
                break;
            } else {
                continue;
            }
        }
        return statusNyuciTime;
    }

    public void toNextDay() {
        // TODO
        sisaHariPengerjaan -= 1;
    }

    public long calculateHarga() {
        // TODO
        for (LaundryService service : services){
            if (service instanceof SetrikaService){
                totalHarga = baseHarga + service.getHarga(berat);  
            } else if (service instanceof AntarService){
                totalHarga = baseHarga + service.getHarga(berat);
            }
        }

        if (sisaHariPengerjaan < 0){
            telat = true;
            totalHarga += 2000 * Math.abs(sisaHariPengerjaan);
        }
        return totalHarga;
    }

    public String getNotaStatus() {
        // TODO
        if (this.isDone()){
            return String.format("Nota %d : Sudah selesai.", idNota);
        } else {
            return String.format("Nota %d : Belum selesai.", idNota);
        }
    }

    public void generateNota(){
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // set format tanggal menjadi dd/MM/yyyy
        LocalDate tanggal = LocalDate.parse(this.tanggalMasuk, dateFormat); // parse input tanggal ke dalam format yang telah di set

        if (paket.equalsIgnoreCase("express")){
            hargaPaket = 12_000;
            sisaHariPengerjaan = 1;
            tanggalSelesai = tanggal.plusDays(1); // tambahkan 1 hari dari tanggal terima
        } else if (paket.equalsIgnoreCase("fast")){
            hargaPaket = 10_000;
            sisaHariPengerjaan = 2;
            tanggalSelesai = tanggal.plusDays(2); // tambahkan 2 hari dari tanggal terima
        } else {
            hargaPaket = 7_000;
            sisaHariPengerjaan = 3;
            tanggalSelesai = tanggal.plusDays(3); // tambahkan 3 hari dari tanggal terima
        }
        baseHarga = berat * hargaPaket;
    }

    @Override
    public String toString() {
        // TODO
        String serviceList = "--- SERVICE LIST ---\n";
        for(LaundryService service : services){
            serviceList += "-" + service.getServiceName() + "@ Rp." + service.getHarga(berat);
        }
        if (telat){
            serviceList += "Harga Akhir: " + calculateHarga() + "Ada kompensasi keterlambatan" + Math.abs(sisaHariPengerjaan) + "*2000 hari\n";
        } else {
            serviceList += "Harga Akhir: " + calculateHarga() + "\n";
        }

        if (telat){
            return "";
        } else{
            return String.format(
            "[ID Nota = %d]\nID    : %s\nPaket : %s\nHarga :\n%s kg x %s = %s\n,Tanggal Terima  : %s\nTanggal Selesai : %s\n", 
            idNota, member.getId(), paket, berat, hargaPaket, baseHarga, tanggalMasuk, tanggalSelesai) + serviceList;
        }
    }
    
    // Dibawah ini adalah getter

    public String getPaket() {
        return paket;
    }

    public int getBerat() {
        return berat;
    }

    public String getTanggal() {
        return tanggalMasuk;
    }

    public int getSisaHariPengerjaan() {
        return sisaHariPengerjaan;
    }

    public boolean isDone() {
        for (LaundryService service : services){
            if (service.isDone()){
                continue;
            } else {
                isDone = false;
                return isDone;
            }
        }
        isDone = true;
        return isDone;
    }

    public LaundryService[] getServices() {
        return services;
    }

    public int getIdNota(){
        return idNota;
    }
}
