package assignments.assignment2;

import assignments.assignment1.NotaGenerator;

public class Member {
    // TODO: tambahkan attributes yang diperlukan untuk class ini
    private String nama;
    private String noHp;
    private String ID;
    private int bonusCounter = 0;

    public Member(String nama, String noHp) {
        // TODO: buat constructor untuk class ini
        this.nama = nama;
        this.noHp = noHp;
        this.ID = NotaGenerator.generateId(nama, noHp);
    }

    public String getNama(){
        return nama;
    }
    public String getNoHp(){
        return noHp;
    }
    public String getID(){
        return ID;
    }
    public int getBonusCounter(){
        return bonusCounter;
    }
    public void addBonusCounter(){
        bonusCounter += 1;
    }
    public void resetBonusCounter(){
        bonusCounter = 0;
    }
}
