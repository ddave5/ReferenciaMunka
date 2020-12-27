using System;
using System.Collections.Generic;
using System.Reflection;
using System.Runtime.CompilerServices;
using System.Text;

namespace SorozatBakancs.Model
{
    public class Sorozat
    {
        public int id { get; set; }
        public string title { get; set; }
        public string category { get; set; }
        public int season { get; set; }
        public int episodes { get; set; }
        public int priority { get; set; }
        
    }

    
}
