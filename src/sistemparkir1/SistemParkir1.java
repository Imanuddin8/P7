/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemparkir1;

import java.io.File;
import java.util.Scanner;
import java.lang.Character;
/**
 *
 * @author ASUS
 */
public class SistemParkir1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner in = new Scanner(System.in);
        
        while(true) {
            System.out.println("Apakah anda akan Masuk ke Sistem Parkir?(y/n)");
            char masuk = Character.toLowerCase(in.nextLine().charAt(0));
            
            if (masuk == 'y') {
                new SistemParkir2();
            }else if (masuk == 'n') {
                break;
            }else{
                System.out.println("Perintah yang anda masukkan salah!");
            }
        }
    }
    
}
