import java.rmi.RemoteException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.*;

public class Prog {
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);
        System.out.println("count");
        DataOutputStream dout = null;
        DataInputStream din = null;
        DataOutputStream dout2 = null;
        DataInputStream din2 = null;

        try{
            int count = sc.nextInt();
            File f1 = new File("src.txt");
            f1.createNewFile();
            dout = new DataOutputStream(new FileOutputStream(f1));
            for (int i = 0; i < count; i++){
                int a = sc.nextInt();
                dout.writeInt(a);
            }
            din = new DataInputStream(new FileInputStream(f1));
            File f2 = new File("result.txt");
            f2.createNewFile();
            dout2 = new DataOutputStream(new FileOutputStream(f2));
            int n = 0;
            for (int i = 0; i < count; i++){
                int number = din.readInt();
                if (number%2==0){
                    dout2.writeInt(number);
                    n++;
                }
            }
            din2 = new DataInputStream(new FileInputStream(f2));
            if (n!=0){
                for (int i = 0; i < n;i++){
                    System.out.println(din2.readInt());
                }
            }
            else {
                throw new RemoteException("В исходном файле нет четных чисел");
            }
        }
        catch (IOException io){
            io.printStackTrace();
        }
        catch (RuntimeException re){
            System.out.println(re.getMessage());
        }
        finally{
            dout.flush();
            dout.close();
        }
    }
}
