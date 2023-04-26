package assignments.assignment3.nota.service;

import assignments.assignment3.nota.Nota;

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
        if (isDone){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public long getHarga(int berat) {
        // TODO
        if (berat >= 4){
            return berat * 500;
        } else {
            return 2_000;
        }
    }

    @Override
    public String getServiceName() {
        return "Antar";
    }
}
