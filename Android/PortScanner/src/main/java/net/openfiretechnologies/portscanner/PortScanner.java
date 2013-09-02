package net.openfiretechnologies.portscanner;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by 0x337 on 02.09.13.
 */
public class PortScanner extends AsyncTask<String, Integer, String> {

    //Variables
    Context context;
    boolean scanAll = false, done = false;
    ProgressDialog pd;
    int startPort, endPort;
    StringBuilder scanResult = new StringBuilder();

    @SuppressWarnings("deprecation")
    public PortScanner(Context c, boolean scanAll, int startPort, int endPort) {
        this.context = c;
        this.scanAll = scanAll;
        pd = new ProgressDialog(this.context);
        pd.setTitle("Scan in Progress");
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                pd.dismiss();
                PortScanner.this.cancel(true);
            }
        });

        pd.setCancelable(false);

        if (scanAll) {
            this.startPort = 1;
            this.endPort = 65535;
        } else {
            if (startPort > endPort) {
                this.startPort = endPort;
                this.endPort = startPort;
            } else {
                this.startPort = startPort;
                this.endPort = endPort;
            }
        }

        pd.setMax(this.endPort);
    }

    @Override
    protected void onCancelled() {
        scanResult.append("\n[!!!] INTERRUPTED");
        ((MainActivity) context).updateResult(scanResult.toString());
    }

    @Override
    protected String doInBackground(String... strings) {


        String host = strings[0];
        InetAddress inetAddress = null;
        try {
            if (host.split("\\.").length != 4)
                inetAddress = InetAddress.getByName(host.replace("http://", "").replace("/", "").trim());
            else {
                String[] ip = host.trim().split("\\.");
                int[] ips = new int[4];
                ips[0] = Integer.parseInt(ip[0]);
                ips[1] = Integer.parseInt(ip[1]);
                ips[2] = Integer.parseInt(ip[2]);
                ips[3] = Integer.parseInt(ip[3]);
                inetAddress = InetAddress.getByAddress(new byte[]{(byte) ips[0], (byte) ips[1], (byte) ips[2], (byte) ips[3]});
            }
        } catch (Exception e) {
            Log.e("PORTSCANNER", "Error: " + e);
        }

        Log.e("PORTSCANNER", "inetAddress: " + inetAddress);

        //Set connection timeout here in milliseconds
        int timeout = 1000;
        Socket s1;
        SocketAddress sockaddr;
        scanResult.append("Results for: ").append(inetAddress.toString()).append("\n\n");
        while ((startPort <= endPort) && (!isCancelled())) {

            try {
                s1 = new Socket();
                sockaddr = new InetSocketAddress(inetAddress, startPort);
                s1.connect(sockaddr, timeout);
                scanResult.append("[+] OPEN : " + startPort + "\n");
                s1.close();
            } catch (IOException ex) {
                scanResult.append("[-] CLOSED : " + startPort + "\n");
            } catch (Exception e) {
                Log.e("PORTSCANNER", "Error: " + e.getMessage());
            }

            startPort++;
            publishProgress(startPort);
        }

        scanResult.append("\n\n[!] Scan has been Completed\n");

        return scanResult.toString();
    }

    @Override
    protected void onPreExecute() {
        pd.show();
    }

    @Override
    protected void onPostExecute(String s) {
        if (pd.isShowing()) {
            pd.dismiss();
        }
        ((MainActivity) context).updateResult(s);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        pd.setProgress(values[0]);
    }
}
