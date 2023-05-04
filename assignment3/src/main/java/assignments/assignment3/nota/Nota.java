package assignments.assignment3.nota;

import assignments.assignment3.nota.service.CuciService;
import assignments.assignment3.nota.service.LaundryService;
import assignments.assignment3.user.Member;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Nota {
    private Member member;
    private String paket;
    private LaundryService[] services;
    private long baseHarga; // harga paket
    private long cuciHarga; // harga paket * berat cucian
    private long totalHarga; // harga include services + kompensasi (jika ada)
    private boolean isLate;
    private int sisaHariPengerjaan;
    private int berat;
    private int idNota;
    private String tanggalMasuk;
    private LocalDate tanggalSelesai;
    private boolean isDone;
    static public int totalNota;
    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // set format tanggal menjadi dd/MM/yyyy

    public Nota(Member member, int berat, String paket, String tanggal) {
        // TODO
        this.member = member;
        this.berat = berat;
        this.paket = paket;
        this.tanggalMasuk = tanggal;
        this.idNota = totalNota++;
        this.services = new LaundryService[0];
        this.addService(new CuciService());

        if (paket.equalsIgnoreCase("express")){
            baseHarga = 12000;
            sisaHariPengerjaan = 1;
        } else if (paket.equalsIgnoreCase("fast")){
            baseHarga = 10000;
            sisaHariPengerjaan = 2;
        } else {
            baseHarga = 7000;
            sisaHariPengerjaan = 3;
        }
        cuciHarga = berat * baseHarga;

        this.isDone();
        this.calculateHarga();
    }

    /**
     * Menambahkan service baru ke array services instance LaundryService.
     * @param service -> Service object yang dipesan untuk ditambahkan.
     */
    public void addService(LaundryService service) { 
        // TODO
        LaundryService[] enlargedServices = new LaundryService[this.services.length + 1]; 
        for (int i = 0; i < this.services.length; i++) {
            enlargedServices[i] = services[i];
        }
        this.services = enlargedServices;
        this.services[this.services.length - 1] = service;
    }

    /**
     * Mengerjakan tiap service pada array LaundryService
     * @return string progress pengerjaan service pada nota
     */
    public String kerjakan() {
        // TODO
        String statusNyuciTime = "";
        if (!isDone()){
            for (LaundryService service : services){
                if (!service.isDone()){
                    statusNyuciTime = "Nota " + idNota +  " : " + service.doWork();
                    break;
                } else {
                    continue;
                }
            }
        } else {
            statusNyuciTime = "Nota " + idNota +  " : Sudah selesai.";
        }
        return statusNyuciTime;
        
    }

    /**
     * Mengurangi sisaHariPengerjaan dan cek apakah nota terlambat
     */
    public void toNextDay() {
        // TODO
        sisaHariPengerjaan -= 1;
        if (!isDone() && sisaHariPengerjaan < 0){
            isLate = true;
        }
    }

    /**
     * Menghitung harga sesuai service yang dipesan
     * @return total harga nota dengan service dan kompensasi keterlambatan (jika ada)
     */
    public long calculateHarga() {
        // TODO
        totalHarga = cuciHarga;
        for (LaundryService service : services){
            totalHarga += service.getHarga(berat);  
        }
        
        if (isLate){
            totalHarga -= (2000 * Math.abs(sisaHariPengerjaan));
        }

        if (totalHarga < 0){
            totalHarga = 0;
        }

        return Math.abs(totalHarga);
    }

    /**
     * @return string keterangan apakah nota sudah selesai atau belum
     */
    public String getNotaStatus() {
        // TODO
        if (this.isDone()){
            return String.format("Nota %d : Sudah selesai.", idNota);
        } else {
            return String.format("Nota %d : Belum selesai.", idNota);
        }
    }

    @Override
    public String toString() {
        // TODO
        this.calculateHarga();
        LocalDate parsedTanggal = LocalDate.parse(this.tanggalMasuk, dateFormat); // parse input tanggal ke dalam format yang telah di set
        if (paket.equalsIgnoreCase("express")){
            tanggalSelesai = parsedTanggal.plusDays(1); // tambahkan 1 hari dari tanggal terima
        } else if (paket.equalsIgnoreCase("fast")){
            tanggalSelesai = parsedTanggal.plusDays(2); // tambahkan 2 hari dari tanggal terima
        } else {
            tanggalSelesai = parsedTanggal.plusDays(3); // tambahkan 3 hari dari tanggal terima
        }

        String serviceList = "--- SERVICE LIST ---\n";
        for (LaundryService service : services){
            serviceList += "-" + service.getServiceName() + " @ Rp." + service.getHarga(berat) + "\n";
        }
        if (isLate){
            serviceList += "Harga Akhir: " + totalHarga + " Ada kompensasi keterlambatan " + Math.abs(sisaHariPengerjaan) + " * 2000 hari\n";
        } else {
            serviceList += "Harga Akhir: " + totalHarga + "\n";
        }
        
        return String.format(
        "[ID Nota = %d]\nID    : %s\nPaket : %s\nHarga :\n%s kg x %s = %s\ntanggal terima  : %s\ntanggal selesai : %s\n", 
        idNota, member.getId(), paket, berat, baseHarga, cuciHarga, tanggalMasuk, tanggalSelesai.format(dateFormat)) + serviceList;
        
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

    /**
     * Cek apakah seluruh service sudah dikerjakan dan apakah nota dapat dinyatakan selesai
     * @return true jika seluruh service dan nota sudah selesai dikerjakan
     */
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
