using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using SorozatBakancs.Model;
using SorozatBakancs.Controller;
using SorozatBakancs.DAO;
using SorozatBakancs.View;

namespace SorozatBakancs
{
    public partial class MainWindow : Form
    {
        private readonly SorozatController controller;
        public MainWindow()
        {
            InitializeComponent();
            this.addSorozatItem.Click += AddSorozatMenuItem_Click;
            this.getSorozatokItem.Click += ListMenuItem_Click;
            this.sorozatDGW.CellContentClick += sorozatDGW_CellContentClick;

            controller = new SorozatController(new SorozatAddDAO());
        }

        private void ListMenuItem_Click(object sender, EventArgs e)
        {
            var sorik = controller.GetSorozatok();

            var viewModels = new List<SorozatViewModell>();
            foreach (var item in sorik)
                viewModels.Add(new SorozatViewModell
                {
                    title = item.title,
                    category = item.category,
                    season = item.season,
                    episodes = item.episodes
                }); 
            
            sorozatDGW.DataSource = null;
            sorozatDGW.DataSource = viewModels;
            sorozatDGW.Visible = true;
        }

        private void AddSorozatMenuItem_Click(object sender, EventArgs e)
        {
            using var addserie = new SeriesAddModify(controller);
            addserie.ShowDialog();
        }


        private void menuStrip1_ItemClicked(object sender, ToolStripItemClickedEventArgs e)
        {

        }

        private void sorozatDGW_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
            SorozatViewModell sorozatvm = (SorozatViewModell) sorozatDGW.CurrentRow.DataBoundItem;
            Sorozat sorozat = new Sorozat
            {
                title = sorozatvm.title,
                category = sorozatvm.category,
                season = sorozatvm.season,
                episodes = sorozatvm.episodes,
            };

            var sorilist = controller.GetSorozatok();

            foreach (var item in sorilist)
            {
                if (item.title.Equals(sorozat.title))
                {
                    sorozat.priority = item.priority;
                    break;
                }
            }
            using var window = new SeriesAddModify(controller, sorozat);
            window.ShowDialog();
        }

        private void sorozatDGW_CellMouseClick(object sender, DataGridViewCellMouseEventArgs e)
        {
           
        }

        private void sorozatDGW_CellMouseDoubleClick(object sender, DataGridViewCellMouseEventArgs e)
        {
            
        }
    }
}
