namespace SorozatBakancs.View
{
    partial class SeriesAddModify
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
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
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.splitContainer1 = new System.Windows.Forms.SplitContainer();
            this.priorityLabel = new System.Windows.Forms.Label();
            this.episodesLabel = new System.Windows.Forms.Label();
            this.seasonLabel = new System.Windows.Forms.Label();
            this.categoryLabel = new System.Windows.Forms.Label();
            this.cimLabel = new System.Windows.Forms.Label();
            this.saveButton = new System.Windows.Forms.Button();
            this.priorityTextBox = new System.Windows.Forms.TextBox();
            this.episodesTextBox = new System.Windows.Forms.TextBox();
            this.seasonTextBox = new System.Windows.Forms.TextBox();
            this.categoryTextBox = new System.Windows.Forms.TextBox();
            this.titleTextBox = new System.Windows.Forms.TextBox();
            this.cancelButton = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.splitContainer1)).BeginInit();
            this.splitContainer1.Panel1.SuspendLayout();
            this.splitContainer1.Panel2.SuspendLayout();
            this.splitContainer1.SuspendLayout();
            this.SuspendLayout();
            // 
            // splitContainer1
            // 
            this.splitContainer1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.splitContainer1.Location = new System.Drawing.Point(0, 0);
            this.splitContainer1.Name = "splitContainer1";
            // 
            // splitContainer1.Panel1
            // 
            this.splitContainer1.Panel1.Controls.Add(this.priorityLabel);
            this.splitContainer1.Panel1.Controls.Add(this.episodesLabel);
            this.splitContainer1.Panel1.Controls.Add(this.seasonLabel);
            this.splitContainer1.Panel1.Controls.Add(this.categoryLabel);
            this.splitContainer1.Panel1.Controls.Add(this.cimLabel);
            // 
            // splitContainer1.Panel2
            // 
            this.splitContainer1.Panel2.Controls.Add(this.saveButton);
            this.splitContainer1.Panel2.Controls.Add(this.priorityTextBox);
            this.splitContainer1.Panel2.Controls.Add(this.episodesTextBox);
            this.splitContainer1.Panel2.Controls.Add(this.seasonTextBox);
            this.splitContainer1.Panel2.Controls.Add(this.categoryTextBox);
            this.splitContainer1.Panel2.Controls.Add(this.titleTextBox);
            this.splitContainer1.Panel2.Controls.Add(this.cancelButton);
            this.splitContainer1.Size = new System.Drawing.Size(800, 450);
            this.splitContainer1.SplitterDistance = 266;
            this.splitContainer1.TabIndex = 0;
            this.splitContainer1.Text = "splitContainer1";
            // 
            // priorityLabel
            // 
            this.priorityLabel.AutoSize = true;
            this.priorityLabel.Location = new System.Drawing.Point(183, 274);
            this.priorityLabel.Name = "priorityLabel";
            this.priorityLabel.Size = new System.Drawing.Size(50, 15);
            this.priorityLabel.TabIndex = 4;
            this.priorityLabel.Text = "Prioritás";
            // 
            // episodesLabel
            // 
            this.episodesLabel.AutoSize = true;
            this.episodesLabel.Location = new System.Drawing.Point(183, 232);
            this.episodesLabel.Name = "episodesLabel";
            this.episodesLabel.Size = new System.Drawing.Size(42, 15);
            this.episodesLabel.TabIndex = 3;
            this.episodesLabel.Text = "Részek";
            // 
            // seasonLabel
            // 
            this.seasonLabel.AutoSize = true;
            this.seasonLabel.Location = new System.Drawing.Point(183, 196);
            this.seasonLabel.Name = "seasonLabel";
            this.seasonLabel.Size = new System.Drawing.Size(32, 15);
            this.seasonLabel.TabIndex = 2;
            this.seasonLabel.Text = "Évad";
            // 
            // categoryLabel
            // 
            this.categoryLabel.AutoSize = true;
            this.categoryLabel.Location = new System.Drawing.Point(183, 156);
            this.categoryLabel.Name = "categoryLabel";
            this.categoryLabel.Size = new System.Drawing.Size(57, 15);
            this.categoryLabel.TabIndex = 1;
            this.categoryLabel.Text = "Kategória";
            // 
            // cimLabel
            // 
            this.cimLabel.AutoSize = true;
            this.cimLabel.Location = new System.Drawing.Point(183, 120);
            this.cimLabel.Name = "cimLabel";
            this.cimLabel.Size = new System.Drawing.Size(29, 15);
            this.cimLabel.TabIndex = 0;
            this.cimLabel.Text = "Cím";
            // 
            // saveButton
            // 
            this.saveButton.Location = new System.Drawing.Point(33, 395);
            this.saveButton.Name = "saveButton";
            this.saveButton.Size = new System.Drawing.Size(75, 23);
            this.saveButton.TabIndex = 6;
            this.saveButton.Text = "Save";
            this.saveButton.UseVisualStyleBackColor = true;
            this.saveButton.Click += new System.EventHandler(this.saveButton_Click);
            // 
            // priorityTextBox
            // 
            this.priorityTextBox.Location = new System.Drawing.Point(33, 266);
            this.priorityTextBox.Name = "priorityTextBox";
            this.priorityTextBox.Size = new System.Drawing.Size(215, 23);
            this.priorityTextBox.TabIndex = 5;
            // 
            // episodesTextBox
            // 
            this.episodesTextBox.Location = new System.Drawing.Point(33, 224);
            this.episodesTextBox.Name = "episodesTextBox";
            this.episodesTextBox.Size = new System.Drawing.Size(215, 23);
            this.episodesTextBox.TabIndex = 4;
            // 
            // seasonTextBox
            // 
            this.seasonTextBox.Location = new System.Drawing.Point(33, 188);
            this.seasonTextBox.Name = "seasonTextBox";
            this.seasonTextBox.Size = new System.Drawing.Size(215, 23);
            this.seasonTextBox.TabIndex = 3;
            // 
            // categoryTextBox
            // 
            this.categoryTextBox.Location = new System.Drawing.Point(33, 148);
            this.categoryTextBox.Name = "categoryTextBox";
            this.categoryTextBox.Size = new System.Drawing.Size(215, 23);
            this.categoryTextBox.TabIndex = 2;
            // 
            // titleTextBox
            // 
            this.titleTextBox.Location = new System.Drawing.Point(33, 112);
            this.titleTextBox.Name = "titleTextBox";
            this.titleTextBox.Size = new System.Drawing.Size(215, 23);
            this.titleTextBox.TabIndex = 1;
            // 
            // cancelButton
            // 
            this.cancelButton.Location = new System.Drawing.Point(129, 395);
            this.cancelButton.Name = "cancelButton";
            this.cancelButton.Size = new System.Drawing.Size(75, 23);
            this.cancelButton.TabIndex = 0;
            this.cancelButton.Text = "Cancel";
            this.cancelButton.UseVisualStyleBackColor = true;
            this.cancelButton.Click += new System.EventHandler(this.cancelButton_Click);
            // 
            // SeriesAddModify
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.splitContainer1);
            this.Name = "SeriesAddModify";
            this.Text = "SeriesAddModify";
            this.splitContainer1.Panel1.ResumeLayout(false);
            this.splitContainer1.Panel1.PerformLayout();
            this.splitContainer1.Panel2.ResumeLayout(false);
            this.splitContainer1.Panel2.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.splitContainer1)).EndInit();
            this.splitContainer1.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.SplitContainer splitContainer1;
        private System.Windows.Forms.Button cancelButton;
        private System.Windows.Forms.Label priorityLabel;
        private System.Windows.Forms.Label episodesLabel;
        private System.Windows.Forms.Label seasonLabel;
        private System.Windows.Forms.Label categoryLabel;
        private System.Windows.Forms.Label cimLabel;
        private System.Windows.Forms.Button saveButton;
        private System.Windows.Forms.TextBox priorityTextBox;
        private System.Windows.Forms.TextBox episodesTextBox;
        private System.Windows.Forms.TextBox seasonTextBox;
        private System.Windows.Forms.TextBox categoryTextBox;
        private System.Windows.Forms.TextBox titleTextBox;
    }
}