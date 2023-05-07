package assignments.assignment3.nota.service;

public class SetrikaService implements LaundryService{
    private boolean isDone;

    @Override
    public String doWork() {
        // TODO
        isDone = true;
        return "Sedang menyetrika...";
    }

    @Override
    public boolean isDone() {
        // TODO
        if (isDone){ // jika pernah dikerjakan, maka service setrika sudah selesai
            return true;
        } else {
            return false;
        }
    }

    @Override
    public long getHarga(int berat) {
        // TODO
        return berat * 1_000; // kalikan berat cucian dengan harga service setrika (1000 per kg)
    }

    @Override
    public String getServiceName() {
        return "Setrika";
    }
}
