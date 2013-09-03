namespace PortScanner
{
    partial class Form1
    {
        /// <summary>
        /// Erforderliche Designervariable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Verwendete Ressourcen bereinigen.
        /// </summary>
        /// <param name="disposing">True, wenn verwaltete Ressourcen gelöscht werden sollen; andernfalls False.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Vom Windows Form-Designer generierter Code

        /// <summary>
        /// Erforderliche Methode für die Designerunterstützung.
        /// Der Inhalt der Methode darf nicht mit dem Code-Editor geändert werden.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            this.txtHost = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.cbScanAll = new System.Windows.Forms.CheckBox();
            this.bStart = new System.Windows.Forms.Button();
            this.txtResult = new System.Windows.Forms.TextBox();
            this.numStartPort = new System.Windows.Forms.NumericUpDown();
            this.numEndPort = new System.Windows.Forms.NumericUpDown();
            this.ep1 = new System.Windows.Forms.ErrorProvider(this.components);
            this.bw1 = new System.ComponentModel.BackgroundWorker();
            this.pb = new System.Windows.Forms.ProgressBar();
            ((System.ComponentModel.ISupportInitialize)(this.numStartPort)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.numEndPort)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.ep1)).BeginInit();
            this.SuspendLayout();
            // 
            // txtHost
            // 
            this.txtHost.Location = new System.Drawing.Point(72, 13);
            this.txtHost.Name = "txtHost";
            this.txtHost.Size = new System.Drawing.Size(181, 20);
            this.txtHost.TabIndex = 0;
            this.txtHost.TextChanged += new System.EventHandler(this.txtHost_TextChanged);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(11, 16);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(55, 13);
            this.label1.TabIndex = 1;
            this.label1.Text = "Hostname";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(10, 65);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(51, 13);
            this.label2.TabIndex = 3;
            this.label2.Text = "Start Port";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(10, 91);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(48, 13);
            this.label3.TabIndex = 5;
            this.label3.Text = "End Port";
            // 
            // cbScanAll
            // 
            this.cbScanAll.AutoSize = true;
            this.cbScanAll.Location = new System.Drawing.Point(72, 39);
            this.cbScanAll.Name = "cbScanAll";
            this.cbScanAll.Size = new System.Drawing.Size(92, 17);
            this.cbScanAll.TabIndex = 1;
            this.cbScanAll.Text = "Scan All Ports";
            this.cbScanAll.UseVisualStyleBackColor = true;
            this.cbScanAll.CheckedChanged += new System.EventHandler(this.cbScanAll_CheckedChanged);
            // 
            // bStart
            // 
            this.bStart.Enabled = false;
            this.bStart.Location = new System.Drawing.Point(177, 114);
            this.bStart.Name = "bStart";
            this.bStart.Size = new System.Drawing.Size(75, 23);
            this.bStart.TabIndex = 4;
            this.bStart.Text = "Start Scan";
            this.bStart.UseVisualStyleBackColor = true;
            this.bStart.Click += new System.EventHandler(this.bStart_Click);
            // 
            // txtResult
            // 
            this.txtResult.Location = new System.Drawing.Point(13, 142);
            this.txtResult.Multiline = true;
            this.txtResult.Name = "txtResult";
            this.txtResult.ReadOnly = true;
            this.txtResult.ScrollBars = System.Windows.Forms.ScrollBars.Vertical;
            this.txtResult.Size = new System.Drawing.Size(239, 108);
            this.txtResult.TabIndex = 8;
            this.txtResult.TabStop = false;
            // 
            // numStartPort
            // 
            this.numStartPort.Location = new System.Drawing.Point(71, 62);
            this.numStartPort.Maximum = new decimal(new int[] {
            65535,
            0,
            0,
            0});
            this.numStartPort.Minimum = new decimal(new int[] {
            1,
            0,
            0,
            0});
            this.numStartPort.Name = "numStartPort";
            this.numStartPort.Size = new System.Drawing.Size(181, 20);
            this.numStartPort.TabIndex = 2;
            this.numStartPort.Value = new decimal(new int[] {
            1,
            0,
            0,
            0});
            // 
            // numEndPort
            // 
            this.numEndPort.Location = new System.Drawing.Point(71, 88);
            this.numEndPort.Maximum = new decimal(new int[] {
            65535,
            0,
            0,
            0});
            this.numEndPort.Minimum = new decimal(new int[] {
            1,
            0,
            0,
            0});
            this.numEndPort.Name = "numEndPort";
            this.numEndPort.Size = new System.Drawing.Size(181, 20);
            this.numEndPort.TabIndex = 3;
            this.numEndPort.Value = new decimal(new int[] {
            1,
            0,
            0,
            0});
            // 
            // ep1
            // 
            this.ep1.ContainerControl = this;
            // 
            // bw1
            // 
            this.bw1.WorkerReportsProgress = true;
            this.bw1.DoWork += new System.ComponentModel.DoWorkEventHandler(this.bw1_DoWork);
            this.bw1.ProgressChanged += new System.ComponentModel.ProgressChangedEventHandler(this.bw1_ProgressChanged);
            this.bw1.RunWorkerCompleted += new System.ComponentModel.RunWorkerCompletedEventHandler(this.bw1_RunWorkerCompleted);
            // 
            // pb
            // 
            this.pb.Location = new System.Drawing.Point(13, 257);
            this.pb.Name = "pb";
            this.pb.Size = new System.Drawing.Size(239, 23);
            this.pb.TabIndex = 11;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(274, 285);
            this.Controls.Add(this.pb);
            this.Controls.Add(this.numEndPort);
            this.Controls.Add(this.numStartPort);
            this.Controls.Add(this.txtResult);
            this.Controls.Add(this.bStart);
            this.Controls.Add(this.cbScanAll);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.txtHost);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedToolWindow;
            this.Name = "Form1";
            this.Text = "PortScanner";
            ((System.ComponentModel.ISupportInitialize)(this.numStartPort)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.numEndPort)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.ep1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.TextBox txtHost;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.CheckBox cbScanAll;
        private System.Windows.Forms.Button bStart;
        private System.Windows.Forms.TextBox txtResult;
        private System.Windows.Forms.NumericUpDown numStartPort;
        private System.Windows.Forms.NumericUpDown numEndPort;
        private System.Windows.Forms.ErrorProvider ep1;
        private System.ComponentModel.BackgroundWorker bw1;
        private System.Windows.Forms.ProgressBar pb;
    }
}

