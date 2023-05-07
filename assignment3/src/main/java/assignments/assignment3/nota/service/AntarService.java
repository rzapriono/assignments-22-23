package assignments.assignment3.nota.service;

public class AntarService implements LaundryService{
    private boolean isDone;

    @Override
    public String doWork() {
        // TODO
        isDone = true;
        return "Sedang mengantar...";
    }

    @Override
    public boolean isDone() {
        // TODO
        if (isDone){ // jika pernah dikerjakan, maka service antar sudah selesai
            return true;
        } else {
            return false;
        }
    }

    @Override
    public long getHarga(int berat) {
        // TODO
        if (berat >= 4){ // kalikan berat cucian dengan harga service antar (500 per kg) jika berat >= 4 kg
            return berat * 500;
        } else { // jika berat < 2 kg maka harga service antar menjadi 2000
            return 2_000;
        }
    }

    @Override
    public String getServiceName() {
        return "Antar";
    }
}
