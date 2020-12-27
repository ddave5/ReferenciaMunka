using Microsoft.EntityFrameworkCore;
using PizzaAlkFejl.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace PizzaAlkFejl.Context
{
    public class EFContext: DbContext
    {
        public DbSet<Pizza> pizzas { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseSqlite("Data Source=.//..//DB//orders.db");
        }

    }
}
