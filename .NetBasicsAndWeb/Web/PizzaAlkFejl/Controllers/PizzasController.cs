using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using PizzaAlkFejl.Context;
using PizzaAlkFejl.Models;

namespace PizzaAlkFejl.Controllers
{
    public class PizzasController : Controller
    {
        private readonly EFContext _context;

        public PizzasController(EFContext context)
        {
            _context = context;
        }

        // GET: Pizzas
        public async Task<IActionResult> Index()
        {
            return View(await _context.pizzas.ToListAsync());
        }

        // GET: Pizzas/Details/5
        public async Task<IActionResult> Details(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var pizza = await _context.pizzas
                .FirstOrDefaultAsync(m => m.ID == id);
            if (pizza == null)
            {
                return NotFound();
            }

            return View(pizza);
        }

        // GET: Pizzas/Create
        public IActionResult Create()
        {
            return View();
        }

        // POST: Pizzas/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create([Bind("ID,Name,FoodName,Price,ExpectedShipping,Points")] Pizza pizza)
        {
            if (ModelState.IsValid)
            {
                _context.Add(pizza);
                await _context.SaveChangesAsync();
                return RedirectToAction(nameof(Index));
            }
            return View(pizza);
        }

        // GET: Pizzas/Edit/5
        public async Task<IActionResult> Edit(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var pizza = await _context.pizzas.FindAsync(id);
            if (pizza == null)
            {
                return NotFound();
            }
            return View(pizza);
        }

        // POST: Pizzas/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Edit(int id, [Bind("ID,Name,FoodName,Price,ExpectedShipping,Points")] Pizza pizza)
        {
            if (id != pizza.ID)
            {
                return NotFound();
            }

            if (ModelState.IsValid)
            {
                try
                {
                    _context.Update(pizza);
                    await _context.SaveChangesAsync();
                }
                catch (DbUpdateConcurrencyException)
                {
                    if (!PizzaExists(pizza.ID))
                    {
                        return NotFound();
                    }
                    else
                    {
                        throw;
                    }
                }
                return RedirectToAction(nameof(Index));
            }
            return View(pizza);
        }

        // GET: Pizzas/Delete/5
        public async Task<IActionResult> Delete(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var pizza = await _context.pizzas
                .FirstOrDefaultAsync(m => m.ID == id);
            if (pizza == null)
            {
                return NotFound();
            }

            return View(pizza);
        }

        // POST: Pizzas/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(int id)
        {
            var pizza = await _context.pizzas.FindAsync(id);
            _context.pizzas.Remove(pizza);
            await _context.SaveChangesAsync();
            return RedirectToAction(nameof(Index));
        }

        private bool PizzaExists(int id)
        {
            return _context.pizzas.Any(e => e.ID == id);
        }
    }
}
