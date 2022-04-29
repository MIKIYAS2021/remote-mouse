package java.project.mouse;

import android.util.Log;

import java.io.IOException;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SenderClient {
    DatagramSocket client;
    DatagramPacket send;
    String serverip;
    String message;

    public SenderClient(String serverip)
    {
        this.serverip = serverip;
    }

    public void PRINT(String params) {
        message = params;
        try {
            client = new DatagramSocket();
          byte[] by = message.getBytes();
           send = new DatagramPacket(by, by.length,
                   InetAddress.getByName(this.serverip), 9000);
            client.send(send);
            client.close();

        } catch (IOException e) {
            Log.e("remotedroid", "Error while connecting", e);
        }
    }
}
