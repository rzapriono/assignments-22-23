package assignments.assignment3.user;

public class Employee extends Member {
    public static int employeeCount;

    public Employee(String nama, String password) {
        super(nama, generateId(nama), password); // panggil constructor superclassnya (class Member)
        employeeCount++; // counter untuk id employee agar nomornya sesuai dengan urutan pembuatan object employee nya
    }

    /**
     * Membuat ID employee dari nama employee dengan format
     * NAMA_DEPAN-[jumlah employee, mulai dari 0]
     * Contoh: Dek Depe adalah employee pertama yang dibuat, sehingga IDnya adalah
     * DEK-0
     *
     * @param nama -> Nama lengkap dari employee
     */
    private static String generateId(String nama) {
        // TODO
        nama = nama.split(" ")[0]; // ambil nama depan
        String id = nama.toUpperCase() + "-" + employeeCount; // buat id sesuai ketentuan pada soal
        return id;
    }
    
}
