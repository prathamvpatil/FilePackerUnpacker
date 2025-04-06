import java.io.*;

class Packer {
    public static void pack(String directory, String outputFile) {
        try (FileOutputStream fos = new FileOutputStream(outputFile);
             DataOutputStream dos = new DataOutputStream(fos)) {

            dos.writeUTF("MARVELLOUS"); // Magic identifier

            File folder = new File(directory);
            for (File file : folder.listFiles()) {
                if (file.isFile()) {
                    byte[] data = Utilities.readFile(file);
                    byte[] encrypted = Utilities.simpleEncrypt(data);
                    String checksum = Utilities.checksum(encrypted);

                    dos.writeUTF(file.getName());
                    dos.writeInt(encrypted.length);
                    dos.writeUTF(checksum);
                    dos.write(encrypted);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}