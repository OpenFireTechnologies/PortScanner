using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace PortScanner
{
    public partial class Form1 : Form
    {

        int startPort, endPort;
        IPAddress ip;
        bool DEBUG = false;
        bool running = false;
        bool connected = false;
        Socket sock;

        public Form1()
        {
            InitializeComponent();
        }

        private void cbScanAll_CheckedChanged(object sender, EventArgs e)
        {
            if (cbScanAll.Checked)
            {
                numStartPort.Enabled = numEndPort.Enabled = false;
            }
            else
            {
                numStartPort.Enabled = numEndPort.Enabled = true;
            }
        }

        private void txtHost_TextChanged(object sender, EventArgs e)
        {
            bStart.Enabled = !txtHost.Text.Equals("");
        }

        private void bStart_Click(object sender, EventArgs e)
        {
            if (!running)
            {
                running = true;
                validatePorts();

                ip = getIpFromHost(txtHost.Text);
                debugBox("Ip: " + ip);
                if (ip == null)
                {
                    ep1.SetError(txtHost, "Cannot get ip from hostadress!");
                    return;
                }

                pb.Maximum = endPort;

                txtResult.Text = "Results for: " + ip.ToString() + Environment.NewLine + Environment.NewLine;
                bStart.Text = "STOP!";


                bw1.RunWorkerAsync();
            }
            else
            {
                running = false;
                try
                {
                    bw1.CancelAsync();
                }
                catch (Exception) { }
            }

        }

        private IPAddress getIpFromHost(string host)
        {
            try
            {
                IPHostEntry hostInfo;
                hostInfo = Dns.GetHostEntry(host);

                foreach (IPAddress ipaddr in hostInfo.AddressList)
                {
                    debugBox(ipaddr.ToString() + " | " + ipaddr.AddressFamily.ToString());
                    if (ipaddr.AddressFamily.ToString().Equals("InterNetwork"))
                        return ipaddr;
                }
            }
            catch (Exception)
            {
                debugBox("Unable to resolve host: " + host);
            }

            return null;

        }

        private void debugBox(string message)
        {
            if (DEBUG)
            {
                MessageBox.Show(message, "DebugBox");
            }
        }

        private void validatePorts()
        {
            if (!cbScanAll.Checked)
            {
                if (numStartPort.Value > numEndPort.Value)
                {
                    startPort = (int)numEndPort.Value;
                    endPort = (int)numStartPort.Value;
                }
                else
                {
                    startPort = (int)numStartPort.Value;
                    endPort = (int)numEndPort.Value;
                }
            }
            else
            {
                startPort = 1;
                endPort = 65535;
            }
        }

        private void bw1_DoWork(object sender, DoWorkEventArgs e)
        {
            IPAddress ipAdress = ip;
            IPEndPoint ipEndPoint = new IPEndPoint(ip, startPort);

            sock = new Socket(ipEndPoint.AddressFamily,
             SocketType.Stream,
             ProtocolType.Tcp);
            sock.ReceiveTimeout = 1000;
            sock.SendTimeout = 1000;

            while ((startPort <= endPort) && running)
            {
                try
                {
                    ipEndPoint = new IPEndPoint(ipAdress, startPort);
                    sock.Connect(ipEndPoint);
                    if (sock.Connected)
                    {
                        connected = true;
                    }
                    else
                    {
                        connected = false;
                    }
                }
                catch (Exception)
                {
                    connected = false;
                }
                bw1.ReportProgress(startPort);
                startPort++;
                if (startPort > endPort) running = false;
            }
        }

        private void bw1_ProgressChanged(object sender, ProgressChangedEventArgs e)
        {
            pb.Value = e.ProgressPercentage;
            if (connected)
            {
                txtResult.AppendText("[+] OPEN: " + e.ProgressPercentage + Environment.NewLine);
            }
            else
            {
                txtResult.AppendText("[-] CLOSED: " + e.ProgressPercentage + Environment.NewLine);
            }
        }

        private void bw1_RunWorkerCompleted(object sender, RunWorkerCompletedEventArgs e)
        {
            running = false;
            txtResult.AppendText(Environment.NewLine + "[!] Scan Finished");
            bStart.Text = "Start Scan";
        }
    }
}
