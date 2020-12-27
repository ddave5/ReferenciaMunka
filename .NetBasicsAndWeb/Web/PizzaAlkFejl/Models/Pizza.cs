using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations;

namespace PizzaAlkFejl.Models
{
    public class Pizza
    {
        [Key]
        public int ID { get; set; }
        [Required]
        public String Name { get; set; }
        [Required]
        public String FoodName { get; set; }
        [Required]
        public int Price { get; set; }
        [Required]
        public int ExpectedShipping { get; set; }
        [Required]
        public int Points { get; set; }
    }
}
