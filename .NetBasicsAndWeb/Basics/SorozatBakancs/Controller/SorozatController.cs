using SorozatBakancs.DAO;
using System;
using System.Collections.Generic;
using System.Text;
using SorozatBakancs.Model;

namespace SorozatBakancs.Controller
{
    public class SorozatController
    {
        private readonly ISorozatDAO dao;

        public SorozatController(ISorozatDAO isor)
        {
            dao = isor;
        }
        public bool AddSorozat(Sorozat s)
        {
            return dao.AddSorozat(s);
        }

        public bool ModifyHero(Sorozat s)
        {
            return dao.ModifySorozat(s);
        }

        public IEnumerable<Sorozat> GetSorozatok()
        {
            return dao.GetSorozatok();
        }

        public Sorozat GetSorozat(int SorozatId)
        {
            return dao.GetSorozat(SorozatId);
        }
    }
}
