import java.io.*;
import javax.swing.JOptionPane;

class Unpacker {
    public static void unpack(String packedFile) {
        try (FileInputStream fis = new FileInputStream(packedFile);
             DataInputStream dis = new DataInputStream(fis)) {

            String magic = dis.readUTF();
            if (!magic.equals("MARVELLOUS")) {
                JOptionPane.showMessageDialog(null, "Invalid Packed File");
                return;
            }

            while (dis.available() > 0) {
                String filename = dis.readUTF();
                int size = dis.readInt();
                String checksum = dis.readUTF();

                byte[] encrypted = new byte[size];
                dis.readFully(encrypted);

                if (!checksum.equals(Utilities.checksum(encrypted))) {
                    JOptionPane.showMessageDialog(null, "Checksum mismatch for " + filename);
                    continue;
                }

                byte[] decrypted = Utilities.simpleEncrypt(encrypted); // Reversible
                try (FileOutputStream fos = new FileOutputStream(filename)) {
                    fos.write(decrypted);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
