namespace SorozatBakancs
{
    partial class MainWindow
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(MainWindow));
            this.menuStrip1 = new System.Windows.Forms.MenuStrip();
            this.addSorozatItem = new System.Windows.Forms.ToolStripMenuItem();
            this.getSorozatokItem = new System.Windows.Forms.ToolStripMenuItem();
            this.sorozatDGW = new System.Windows.Forms.DataGridView();
            this.menuStrip1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.sorozatDGW)).BeginInit();
            this.SuspendLayout();
            // 
            // menuStrip1
            // 
            this.menuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.addSorozatItem,
            this.getSorozatokItem});
            this.menuStrip1.Location = new System.Drawing.Point(0, 0);
            this.menuStrip1.Name = "menuStrip1";
            this.menuStrip1.Size = new System.Drawing.Size(800, 24);
            this.menuStrip1.TabIndex = 0;
            this.menuStrip1.Text = "menuStrip1";
            this.menuStrip1.ItemClicked += new System.Windows.Forms.ToolStripItemClickedEventHandler(this.menuStrip1_ItemClicked);
            // 
            // addSorozatItem
            // 
            this.addSorozatItem.Name = "addSorozatItem";
            this.addSorozatItem.Size = new System.Drawing.Size(121, 20);
            this.addSorozatItem.Text = "Sorozat hozzáadása";
            // 
            // getSorozatokItem
            // 
            this.getSorozatokItem.Name = "getSorozatokItem";
            this.getSorozatokItem.Size = new System.Drawing.Size(116, 20);
            this.getSorozatokItem.Text = "Sorozatok lekérése";
            // 
            // sorozatDGW
            // 
            this.sorozatDGW.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.sorozatDGW.Location = new System.Drawing.Point(12, 27);
            this.sorozatDGW.Name = "sorozatDGW";
            this.sorozatDGW.ReadOnly = true;
            this.sorozatDGW.Size = new System.Drawing.Size(776, 411);
            this.sorozatDGW.TabIndex = 1;
            this.sorozatDGW.Text = "dataGridView1";
            this.sorozatDGW.CellContentClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.sorozatDGW_CellContentClick);
            this.sorozatDGW.CellMouseClick += new System.Windows.Forms.DataGridViewCellMouseEventHandler(this.sorozatDGW_CellMouseClick);
            this.sorozatDGW.CellMouseDoubleClick += new System.Windows.Forms.DataGridViewCellMouseEventHandler(this.sorozatDGW_CellMouseDoubleClick);
            // 
            // MainWindow
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.sorozatDGW);
            this.Controls.Add(this.menuStrip1);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedToolWindow;
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "MainWindow";
            this.Text = "AfterSemester";
            this.menuStrip1.ResumeLayout(false);
            this.menuStrip1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.sorozatDGW)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.MenuStrip menuStrip1;
        private System.Windows.Forms.ToolStripMenuItem addSorozatItem;
        private System.Windows.Forms.ToolStripMenuItem getSorozatokItem;
        private System.Windows.Forms.DataGridView sorozatDGW;
    }
}

