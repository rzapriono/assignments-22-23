package assignments.assignment3.nota.service;

public class CuciService implements LaundryService{
    private boolean isDone;

    @Override
    public String doWork() {
        // TODO
        isDone = true;
        return "Sedang mencuci...";
    }

    @Override
    public boolean isDone() {
        // TODO
        if (isDone){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public long getHarga(int berat) {
        // TODO
        return 0; // harga cuci sudah termasuk saat nota dibuat
    }

    @Override
    public String getServiceName() {
        return "Cuci";
    }
}
