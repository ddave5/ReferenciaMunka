using SorozatBakancs.Controller;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using SorozatBakancs;
using System.Windows.Forms;
using SorozatBakancs.DAO;
using SorozatBakancs.Model;

namespace SorozatBakancs.View
{
    public partial class SeriesAddModify : Form
    {
        private readonly SorozatController controller;
        private readonly int sorozatID;
        private readonly bool IsModification = false;
        public SeriesAddModify(SorozatController controller)
        {
       
            InitializeComponent();
            this.controller = controller;
            
        }
        public SeriesAddModify(SorozatController controller, Sorozat sorozat) : this(controller)
        {
            IsModification = true;
            sorozatID = sorozat.id;

            titleTextBox.Text = sorozat.title;
            categoryTextBox.Text = sorozat.category;
            seasonTextBox.Text = Convert.ToString(sorozat.season);
            episodesTextBox.Text = Convert.ToString(sorozat.episodes);
            priorityTextBox.Text = Convert.ToString(sorozat.priority);
            //InitializeComponent();
        }


        private void saveButton_Click(object sender, EventArgs e)
        {
            string name = titleTextBox.Text;
            string category = categoryTextBox.Text;
            int season = Int32.Parse(seasonTextBox.Text);
            int episodes = Int32.Parse(episodesTextBox.Text);
            int priority = Int32.Parse(priorityTextBox.Text);

            if (name == string.Empty || category == string.Empty)
            {
                MessageBox.Show("Kötelező megadni a címet és a kategóriát!", "Hiba!", MessageBoxButtons.OK, MessageBoxIcon.Error);
                DialogResult = DialogResult.Abort;
                return;
            }

            if (season < 1)
            {
                MessageBox.Show("Nem lehet az évad kisebb, mint 1!", "Hiba!", MessageBoxButtons.OK, MessageBoxIcon.Error);
                DialogResult = DialogResult.Abort;
                return;
            }
            if (episodes < 0)
            {
                MessageBox.Show("Nem lehet az részek száma kisebb, mint 0!", "Hiba!", MessageBoxButtons.OK, MessageBoxIcon.Error);
                DialogResult = DialogResult.Abort;
                return;
            }
            if (priority < 1)
            {
                MessageBox.Show("Nem lehet a prioritás kisebb, mint 1!", "Hiba!", MessageBoxButtons.OK, MessageBoxIcon.Error);
                DialogResult = DialogResult.Abort;
                return;
            }

            Sorozat sori = new Sorozat
            {
                title = name,
                category = category,
                season = season,
                episodes = episodes,
                priority = priority
            };

            bool result = false;
            if (IsModification)
            {
                sori.id = sorozatID;
                result = controller.ModifyHero(sori);
            }
            else
            {
                result = controller.AddSorozat(sori);
            }

            if (!result)
            {
                MessageBox.Show("Probléma merült fel a művelet elvégzése közben!", "Hiba!", MessageBoxButtons.OK, MessageBoxIcon.Error);
                DialogResult = DialogResult.Abort;
            }
            else
            {
                MessageBox.Show("Sikeres mentés!", "Siker!", MessageBoxButtons.OK, MessageBoxIcon.Information);
                DialogResult = DialogResult.OK;
            }
        }

        private void cancelButton_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}
