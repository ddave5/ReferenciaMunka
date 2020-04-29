import { Component, OnInit } from '@angular/core';
import * as CanvasJS from '../canvasjs.min';
import { Movie } from '../movies/movie';
import { AppService } from '../app.service';
import { ConditionalExpr } from '@angular/compiler';

@Component({
  selector: 'web-statisztika',
  templateUrl: './statisztika.component.html',
  styleUrls: ['./statisztika.component.css']
})
export class StatisztikaComponent implements OnInit {

  movies: Movie[] = [];
  vigjatekok:Array<Movie> =[];
  thrillerek:Array<Movie> =[];
  horrorok:Array<Movie> =[]; 
  akciok:Array<Movie> =[];
  romantikusok:Array<Movie> =[]; 
  dramak:Array<Movie> =[]; 


  constructor(private appService: AppService) { }

  async ngOnInit(){
    this.movies = (await this.appService.getMovies().then());
    this.vigjatekok= this.movies.filter(film => (film.category.includes("Vigjatek")));
    this.thrillerek = this.movies.filter(film => (film.category.includes("Thriller")));
    this.horrorok = this.movies.filter(film => (film.category.includes("Horror")));
    this.akciok = this.movies.filter(film => (film.category.includes("Akcio")));
    this.romantikusok = this.movies.filter(film => (film.category.includes("Romantikus")));
    this.dramak = this.movies.filter(film => (film.category.includes("Drama")));
    
    this.atlaghossz();
    this.leghosszabb();
    this.legrovidebb();
    this.atlagrating();
    this.legjobb();
    this.legrosszabb();
  }

  atlaghossz(){
    let atlagok: number[] = [];
    let counter = 0;
    this.romantikusok.forEach(movie => counter+=movie.length);
    console.log(counter/this.romantikusok.length);
    atlagok.push(counter/this.romantikusok.length);
    counter=0;
    this.akciok.forEach(movie => counter+=movie.length);
    atlagok.push(counter/this.akciok.length);
    counter=0;
    this.vigjatekok.forEach(movie => counter+=movie.length);
    atlagok.push(counter/this.vigjatekok.length);
    counter=0;
    this.horrorok.forEach(movie => counter+=movie.length);
    atlagok.push(counter/this.horrorok.length);
    counter=0;
    this.thrillerek.forEach(movie => counter+=movie.length);
    atlagok.push(counter/this.thrillerek.length);
    counter=0;
    this.dramak.forEach(movie => counter+=movie.length);
    atlagok.push(counter/this.dramak.length);

    var atlagchart = new CanvasJS.Chart("atlaghossz", {
      animationEnabled: true,
      exportEnabled: true,
      title: {
        text: "Átlagos filmhossz kategóriák szerint"
      },
      data: [{
        type: "column",
        dataPoints: [
          { y: atlagok[0], label: "Romantikus" },
          { y: atlagok[1], label: "Akció" },
          { y: atlagok[2], label: "Vígjáték" },
          { y: atlagok[3], label: "Horror" },
          { y: atlagok[4], label: "Thriller" },
          { y: atlagok[5], label: "Dráma" },
        ]
      }]
    });
      
    atlagchart.render();
  }
  
  leghosszabb(){
    let legnagyobbak: number[] =[];
    let max= 0;
    
    this.romantikusok.forEach( movie => {if (movie.length> max ){max = movie.length;}});
    legnagyobbak.push(max);
    max = 0;

    this.akciok.forEach( movie => {if (movie.length> max ){max = movie.length;}});
    legnagyobbak.push(max);
    max = 0;

    this.vigjatekok.forEach( movie => {if (movie.length> max ){max = movie.length;}});
    legnagyobbak.push(max);
    max = 0;

    this.horrorok.forEach( movie => {if (movie.length> max ){max = movie.length;}});
    legnagyobbak.push(max);
    max = 0;

    this.thrillerek.forEach( movie => {if (movie.length> max ){max = movie.length;}});
    legnagyobbak.push(max);
    max = 0;

    this.dramak.forEach( movie => {if (movie.length> max ){max = movie.length;}});
    legnagyobbak.push(max);
  
    var leghosszabbchart = new CanvasJS.Chart("leghosszabb", {
      animationEnabled: true,
      exportEnabled: true,
      title: {
        text: "Kategóriák szerinti leghosszabb film",
      },
      data: [{
        type: "column",
        dataPoints: [
          { y: legnagyobbak[0], label: "Romantikus" },
          { y: legnagyobbak[1], label: "Akció" },
          { y: legnagyobbak[2], label: "Vígjáték" },
          { y: legnagyobbak[3], label: "Horror" },
          { y: legnagyobbak[4], label: "Thriller" },
          { y: legnagyobbak[5], label: "Dráma" },
        ]
      }]
    });
      
    leghosszabbchart.render();
  }
  legrovidebb(){
    let legrovidebbek: number[] =[];
    let min= 999;
    
    this.romantikusok.forEach( movie => {if (movie.length < min ){min = movie.length;}});
    legrovidebbek.push(min);
    min = 999;

    this.akciok.forEach( movie => {if (movie.length< min ){min = movie.length;}});
    legrovidebbek.push(min);
    min = 999;

    this.vigjatekok.forEach( movie => {if (movie.length< min ){min = movie.length;}});
    legrovidebbek.push(min);
    min = 999;

    this.horrorok.forEach( movie => {if (movie.length< min ){min = movie.length;}});
    legrovidebbek.push(min);
    min = 999;

    this.thrillerek.forEach( movie => {if (movie.length< min ){min = movie.length;}});
    legrovidebbek.push(min);
    min = 999;

    this.dramak.forEach( movie => {if (movie.length< min ){min = movie.length;}});
    legrovidebbek.push(min);
    
    //this.kirajzol("legrovidebb","Kategóriák szerinti legrövidebb film", legrovidebbek);
    var legrovidebbchart = new CanvasJS.Chart("legrovidebb", {
      animationEnabled: true,
      exportEnabled: true,
      title: {
        text: "Kategóriák szerinti legrövidebb film",
      },
      data: [{
        type: "column",
        dataPoints: [
          { y: legrovidebbek[0], label: "Romantikus" },
          { y: legrovidebbek[1], label: "Akció" },
          { y: legrovidebbek[2], label: "Vígjáték" },
          { y: legrovidebbek[3], label: "Horror" },
          { y: legrovidebbek[4], label: "Thriller" },
          { y: legrovidebbek[5], label: "Dráma" },
        ]
      }]
    });
      
    legrovidebbchart.render();
  }
  atlagrating(){
    let atlagok: number[] =[];
    let counter = 0;
    this.romantikusok.forEach(movie => counter+=movie.imdb);
    atlagok.push(counter/this.romantikusok.length);
    counter=0;
    this.akciok.forEach(movie => counter+=movie.imdb);
    atlagok.push(counter/this.akciok.length);
    counter=0;
    this.vigjatekok.forEach(movie => counter+=movie.imdb);
    atlagok.push(counter/this.vigjatekok.length);
    counter=0;
    this.horrorok.forEach(movie => counter+=movie.imdb);
    atlagok.push(counter/this.horrorok.length);
    counter=0;
    this.thrillerek.forEach(movie => counter+=movie.imdb);
    atlagok.push(counter/this.thrillerek.length);
    counter=0;
    this.dramak.forEach(movie => counter+=movie.imdb);
    atlagok.push(counter/this.dramak.length);

    var atlagchart = new CanvasJS.Chart("atlagrating", {
      animationEnabled: true,
      exportEnabled: true,
      title: {
        text: "Átlagos filmértékelés kategóriák szerint"
      },
      data: [{
        type: "column",
        dataPoints: [
          { y: atlagok[0], label: "Romantikus" },
          { y: atlagok[1], label: "Akció" },
          { y: atlagok[2], label: "Vígjáték" },
          { y: atlagok[3], label: "Horror" },
          { y: atlagok[4], label: "Thriller" },
          { y: atlagok[5], label: "Dráma" },
        ]
      }]
    });
      
    atlagchart.render();
  }
  legrosszabb(){
    let legrosszabb: number[] =[];
    let min= 999;
    
    this.romantikusok.forEach( movie => {if (movie.imdb < min ){min = movie.imdb;}});
    legrosszabb.push(min);
    min = 999;

    this.akciok.forEach( movie => {if (movie.imdb< min ){min = movie.imdb;}});
    legrosszabb.push(min);
    min = 999;

    this.vigjatekok.forEach( movie => {if (movie.imdb< min ){min = movie.imdb;}});
    legrosszabb.push(min);
    min = 999;

    this.horrorok.forEach( movie => {if (movie.imdb< min ){min = movie.imdb;}});
    legrosszabb.push(min);
    min = 999;

    this.thrillerek.forEach( movie => {if (movie.imdb< min ){min = movie.imdb;}});
    legrosszabb.push(min);
    min = 999;

    this.dramak.forEach( movie => {if (movie.imdb< min ){min = movie.imdb;}});
    legrosszabb.push(min);
    
    //this.kirajzol("legrovidebb","Kategóriák szerinti legrövidebb film", legrovidebbek);
    var legrovidebbchart = new CanvasJS.Chart("legrovidebb", {
      animationEnabled: true,
      exportEnabled: true,
      title: {
        text: "Kategóriák szerinti legrövidebb film",
      },
      data: [{
        type: "column",
        dataPoints: [
          { y: legrosszabb[0], label: "Romantikus" },
          { y: legrosszabb[1], label: "Akció" },
          { y: legrosszabb[2], label: "Vígjáték" },
          { y: legrosszabb[3], label: "Horror" },
          { y: legrosszabb[4], label: "Thriller" },
          { y: legrosszabb[5], label: "Dráma" },
        ]
      }]
    });
      
    legrovidebbchart.render();
  }
  legjobb(){
    let legnagyobbak: number[] =[];
    let max= 0;
    
    this.romantikusok.forEach( movie => {if (movie.imdb> max ){max = movie.imdb;}});
    legnagyobbak.push(max);
    max = 0;

    this.akciok.forEach( movie => {if (movie.imdb> max ){max = movie.imdb;}});
    legnagyobbak.push(max);
    max = 0;

    this.vigjatekok.forEach( movie => {if (movie.imdb> max ){max = movie.imdb;}});
    legnagyobbak.push(max);
    max = 0;

    this.horrorok.forEach( movie => {if (movie.imdb> max ){max = movie.imdb;}});
    legnagyobbak.push(max);
    max = 0;

    this.thrillerek.forEach( movie => {if (movie.imdb> max ){max = movie.imdb;}});
    legnagyobbak.push(max);
    max = 0;

    this.dramak.forEach( movie => {if (movie.imdb> max ){max = movie.imdb;}});
    legnagyobbak.push(max);
  
    var leghosszabbchart = new CanvasJS.Chart("leghosszabb", {
      animationEnabled: true,
      exportEnabled: true,
      title: {
        text: "Kategóriák szerinti legjobb értékű film",
      },
      data: [{
        type: "column",
        dataPoints: [
          { y: legnagyobbak[0], label: "Romantikus" },
          { y: legnagyobbak[1], label: "Akció" },
          { y: legnagyobbak[2], label: "Vígjáték" },
          { y: legnagyobbak[3], label: "Horror" },
          { y: legnagyobbak[4], label: "Thriller" },
          { y: legnagyobbak[5], label: "Dráma" },
        ]
      }]
    });
      
    leghosszabbchart.render();
  }

  kirajzol(id: string, cim: string, adatok: number[]){
    var chart = new CanvasJS.Chart(id, {
      animationEnabled: true,
      exportEnabled: true,
      title: {
        text: cim
      },
      data: [{
        type: "column",
        dataPoints: [
          { y: adatok[0], label: "Romantikus" },
          { y: adatok[1], label: "Akció" },
          { y: adatok[2], label: "Vígjáték" },
          { y: adatok[3], label: "Horror" },
          { y: adatok[4], label: "Thriller" },
          { y: adatok[5], label: "Dráma" },
        ]
      }]
    });
      
    chart.render();
  }
}
