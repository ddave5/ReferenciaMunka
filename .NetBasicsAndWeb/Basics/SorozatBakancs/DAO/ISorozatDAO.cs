using System;
using System.Collections.Generic;
using System.Text;
using SorozatBakancs.Model;

namespace SorozatBakancs.DAO
{
    public interface ISorozatDAO
    {
        bool AddSorozat(Sorozat s);
        bool ModifySorozat(Sorozat s);
        IEnumerable<Sorozat> GetSorozatok();
        Sorozat GetSorozat(int s);
        int SorozatCount();
    }
}
