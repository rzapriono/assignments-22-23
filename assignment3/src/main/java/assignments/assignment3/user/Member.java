package assignments.assignment3.user;

import assignments.assignment3.nota.Nota;

public class Member {
    protected String id;
    protected String password;
    protected String nama;
    protected Nota[] notaList = new Nota[0];

    public Member(String nama, String id, String password) {
        this.nama = nama;
        this.id = id;
        this.password = password;
    }

    /**
     * Method otentikasi member dengan ID dan password yang diberikan.
     *
     * @param id       -> ID anggota yang akan diautentikasi.
     * @param password -> password anggota untuk mengautentikasi.
     * @return true jika ID dan password sesuai dengan instance member, false jika
     *         tidak.
     */
    public boolean login(String id, String password) {
        return id.equals(this.id) && authenticate(password);
    }

    /**
     * Menambahkan nota baru ke NotaList instance member.
     *
     * @param nota Nota object untuk ditambahkan.
     */
    public void addNota(Nota nota) {
        // TODO
        Nota[] enlargedNotaList = new Nota[notaList.length + 1]; // buat temp array yang lebih panjang 1 elemen
        for (int i = 0; i < notaList.length; i++) {
            enlargedNotaList[i] = notaList[i]; // copy elemen array ke temp array
        }
        notaList = enlargedNotaList; // samakan ukuran array dan elemennya
        notaList[notaList.length - 1] = nota; // masukkan paramater ke array asli
    }

    /**
     * Method otentikasi member dengan password yang diberikan.
     *
     * @param password -> sandi password anggota untuk mengautentikasi.
     * @return true jika ID dan password sesuai dengan instance member, false jika
     *         tidak.
     */
    protected boolean authenticate(String password) {
        // TODO
        if (this.password.equals(password)) { // cek kebenaran password yang diinput oleh user
            return true;
        } else {
            return false;
        }
    }

    // Dibawah ini adalah getter

    public String getNama() {
        return nama;
    }

    public String getId() {
        return id;
    }

    public Nota[] getNotaList() {
        return notaList;
    }
}