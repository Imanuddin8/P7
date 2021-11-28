/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemparkir1;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;


public class SistemParkir2 {
    Scanner in = new Scanner(System.in);
    
    Random random = new Random();
    
    ArrayList<String> JenisKendaraan, PlatNomor;
    ArrayList<Integer> KodeParkir;
    ArrayList<LocalTime> DataJamMasuk, DataJamKeluar;
    ArrayList<Duration> LamaParkir;
    
    DateTimeFormatter FormatJam = DateTimeFormatter.ofPattern("HH:mm:ss");
    
    int indexjeniskendaraan, indexplatnomor, indexkodeparkir, indexdatajammasuk, indexdatajamkeluar, indexlamaparkir, angkakodeparkir, cariindekskodeparkir, totalbiaya;
    Duration durasiparkir;
    LocalTime jamparkirmasuk, jamparkirkeluar;
    
    public SistemParkir2() {
        this.JenisKendaraan = new ArrayList<>();
        this.PlatNomor = new ArrayList<>();
    this.KodeParkir = new ArrayList<>();
    this.DataJamMasuk = new ArrayList<>();
    this.DataJamKeluar = new ArrayList<>();
    this.LamaParkir = new ArrayList<>();
    System.out.println("");
    System.out.println("Selamat Datang di Sistem Parkir");
    ShowMenu();
    }
    
    void ShowMenu(){
        while(true) {
            System.out.println("======= MENU SISTEM PARKIR ========");
            System.out.println("1. Parkir Masuk");
            System.out.println("2. Parkir Keluar");
            System.out.println("3. Daftar Parkir");
            System.out.println("4. Keluar Sistem");
            System.out.println("===================================");
            System.out.println("Masukkan Pilihan Menu Anda?(1-4)");
            String pilihmenu = in.nextLine().toLowerCase();
            
            if (pilihmenu.equals("1")) {
                InputParkirMasuk();
            }else if (pilihmenu.equals("2")) {
                InputParkirKeluar();
            }else if (pilihmenu.equals("3")) {
                DaftarParkir();
            }else if (pilihmenu.equals("4")) {
                KeluarSistem();
            }else{
                System.out.println("Pilihan Menu yang anda masukkan tidak ditemukan!");
            }
        }
    }
    void InputParkirMasuk(){
        System.out.println("");
        System.out.println("Harap Isi Jenis Kendaraan dengan Ketentuan Jenis Kendaraan dibawah ini");
        System.out.println("=> SEPEDA MOTOR");
        System.out.println("=> MOBIL");
        System.out.println("=> BUS");
        System.out.println("Jenis Kendaraan?(SEPEDA MOTOR/MOBIL/BUS)");
        String pilihjeniskendaraan = in.nextLine().toUpperCase();
        
        if(pilihjeniskendaraan.isEmpty()){
            System.out.println("Jenis Kendaraan tidak boleh kosong!");
            System.out.println("");
        }else
        if(!pilihjeniskendaraan.equals("SEPEDA MOTOR") && 
                !pilihjeniskendaraan.equals("MOBIL") && 
                !pilihjeniskendaraan.equals("BUS")){
            System.out.println("Jenis Kendaraan yang dimasukkan tidak ditemukan!");
            System.out.println("");
        }else{
            if (JenisKendaraan.size() == 0) {
                indexjeniskendaraan = 0;
            }else{
                indexjeniskendaraan =+ 1;
            }
            this.JenisKendaraan.add(indexjeniskendaraan, pilihjeniskendaraan);
            
            if (DataJamMasuk.size() == 0) {
                indexdatajammasuk = 0;
            }else{
                indexdatajammasuk =+ 1;
            }
            jamparkirmasuk = LocalTime.now();
            this.DataJamMasuk.add(indexdatajammasuk, jamparkirmasuk);
            
            if (KodeParkir.size() == 0) {
                indexkodeparkir = 0;
            }else{
                indexkodeparkir =+ 1;
            }
            this.KodeParkir.add(indexkodeparkir, random.nextInt(99999));
            InputPlatNomor();
        }
    }
    
    void InputParkirKeluar(){
        if(jamparkirmasuk == null){
            System.out.println("");
            System.out.println("Data Parkir Kosong!");
            System.out.println("");
        }else{
            jamparkirkeluar = jamparkirmasuk.plusHours(1);
            durasiparkir = Duration.between(jamparkirmasuk, jamparkirkeluar);
            
            if (DataJamKeluar.size() == 0) {
                indexdatajamkeluar = 0;
            }else{
                indexdatajamkeluar =+ 1;
            }
            this.DataJamKeluar.add(indexdatajamkeluar, jamparkirkeluar);
            
            if (LamaParkir.size() == 0) {
                 indexlamaparkir = 0;
            }else{
                indexlamaparkir =+ 1;
            }
            this.LamaParkir.add(indexlamaparkir, durasiparkir);
            
            System.out.println("");
            System.out.print("Masukkan Kode Parkir : ");
            String inputkodeparkir = in.nextLine();
            
            if(inputkodeparkir.matches("[0-9]+")){
                angkakodeparkir = Integer.parseInt(inputkodeparkir);
                if (!KodeParkir.contains(angkakodeparkir)) {
                    System.out.println("Tidak Menemukan Kode Parkir!");
                    System.out.println("\n");
            }else{
                System.out.println("Waktu Keluar : " + 
                this.DataJamKeluar.get(indexdatajamkeluar).format(FormatJam));
                
                durasiparkir = Duration.parse(LamaParkir.get(cariindekskodeparkir).toString());
                // System.out.println(p.toHours()+ " Jam\n"
                //                    + p.toMinutes()+ " Menit\n"
                //                    + p.toMillis()+ " Detik");
                System.out.println("Lama Parkir : " + durasiparkir.toHours() + " Jam");
                TotalBiaya();
            }
        }else{
            System.out.println("Kode Parkir yang ada masukkan salah!");
            System.out.println("");
        }
    }
}
    
void DaftarParkir(){
    String formatcetak = "| %-16s | %-16s | %-16s |%n";
    if(KodeParkir.size() == 0){
        System.out.println("");
        System.out.println("Daftar Kode Parkir Kosong!");
        System.out.println("");
    }else{
        System.out.println("");
        System.out.format("Daftar Kode Parkir :%n");
        System.out.format("+------------------+------------------+------------------+%n");
        System.out.format("+ Kode Karcis      + Jenis Kendaraan  + Plat Nomor       +%n");
        System.out.format("+------------------+------------------+------------------+%n");
        for(int i = 0; i < KodeParkir.size(); i++) {
            System.out.format(formatcetak, KodeParkir.get(i).toString(), 
            JenisKendaraan.get(i).toString(), PlatNomor.get(i).toString());
        }
        System.out.format("+------------------+------------------+------------------+%n");
        System.out.println("");
    }
}

void KeluarSistem(){
    System.exit(0); 
}

void InputPlatNomor(){
    System.out.println("Plat Nomor Kendaraan : ");
    if (PlatNomor.size() == 0) {
        indexplatnomor = 0;
    }else{
        indexplatnomor =+ 1;
    }
    this.PlatNomor.add(indexplatnomor, in.nextLine().toUpperCase());
    
    if(PlatNomor.get(indexplatnomor).isEmpty()){
        System.out.print("Masukkan Plat Nomor Kendaraan");
        InputPlatNomor();
    }else{
        System.out.println("Waktu Masuk : " + 
        this.DataJamMasuk.get(indexdatajammasuk).format(FormatJam));
        System.out.println("Kode Parkir : " + this.KodeParkir.get(indexkodeparkir));
        System.out.println("Sukses input data kendaraan!");
        System.out.println("");
        ShowMenu();
    }
}

void TotalBiaya() {
    cariindekskodeparkir = KodeParkir.indexOf(angkakodeparkir);
    if (this.JenisKendaraan.get(cariindekskodeparkir).equals("SEPEDA MOTOR")) {
        totalbiaya = (int) this.LamaParkir.get(cariindekskodeparkir).toHours() * 2000;
        System.out.println("Pembayaran Untuk Sepeda Motor : " + totalbiaya);
    }
    if (this.JenisKendaraan.get(cariindekskodeparkir).equals(("MOBIL"))) {
        totalbiaya = (int) this.LamaParkir.get(cariindekskodeparkir).toHours() * 3000;
        System.out.println("Pembayaran Untuk Mobil : " + totalbiaya);
    }
    if (this.JenisKendaraan.get(cariindekskodeparkir).equals(("BUS"))) {
        totalbiaya = (int) this.LamaParkir.get(cariindekskodeparkir).toHours() * 6000;
        System.out.println("Pembayaran Untuk Bus : Rp. " + totalbiaya);
    }
    
    System.out.println("Terima Kasih");
    System.out.println("");
    
    File theDir = new File("./File_Karcis_Parkir");
    if (!theDir.exists()){
        theDir.mkdirs();
    }
    
    File file = new File("./File_Karcis_Parkir/" + angkakodeparkir + ".txt");
    try {
        file.createNewFile();
    } catch (IOException e) {
        System.out.println("Terjadi kesalahan karena: " + e.getMessage());
    }
    
    String fileName = "./File_Karcis_Parkir/" + angkakodeparkir + ".txt";
    String formatcetak = "| %-45s |\r\n";
    String fileContent = "+-----------------------------------------------+\r\n";
    fileContent += "| Kartu Parkir |\r\n"; 
    fileContent += "+-----------------------------------------------+\r\n";
    fileContent += String.format(formatcetak, "Nomor Parkir : " + 
    this.KodeParkir.get(cariindekskodeparkir));
    fileContent += String.format(formatcetak, "Jenis Kendaraan : " + 
    this.JenisKendaraan.get(cariindekskodeparkir));
    fileContent += String.format(formatcetak, "Plat Nomor Kendaraan : " + 
    this.PlatNomor.get(cariindekskodeparkir));
    fileContent += String.format(formatcetak, "Waktu Masuk : " + 
    this.DataJamMasuk.get(cariindekskodeparkir).format(FormatJam));
    fileContent += String.format(formatcetak, "Waktu Keluar : " + 
    this.DataJamKeluar.get(cariindekskodeparkir).format(FormatJam));
    fileContent += String.format(formatcetak, "Lama Parkir : " + durasiparkir.toHours() + " Jam");
    fileContent += String.format(formatcetak, "Total Biaya Parkir : Rp. " + totalbiaya);
    fileContent += "+-----------------------------------------------+\r\n";
    try {
        FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.write(fileContent);
        fileWriter.close();
    } catch (IOException e) {
        System.out.println("Terjadi kesalahan karena: " + e.getMessage());
    }
    
    try {
        // membaca file
        File myFile = new File(fileName);
        Scanner fileReader = new Scanner(myFile);
        
        System.out.println("Membaca isi File = " + fileName);
        // cetak isi file
        while(fileReader.hasNextLine()){
            String data = fileReader.nextLine();
            System.out.println(data);
        }
        System.out.println("");
        JenisKendaraan.remove(cariindekskodeparkir);
        PlatNomor.remove(cariindekskodeparkir);
        KodeParkir.remove(cariindekskodeparkir);
        DataJamMasuk.remove(cariindekskodeparkir);
        DataJamKeluar.remove(cariindekskodeparkir);
        LamaParkir.remove(cariindekskodeparkir);
        
        } catch (FileNotFoundException e) {
            System.out.println("Terjadi Kesalahan: " + e.getMessage());
            e.printStackTrace();
        }
    }
}