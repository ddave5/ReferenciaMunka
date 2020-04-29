import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { Movie } from '../movies/movie';
import { AppService } from '../app.service';
import { Subscription } from 'rxjs';
import { CATEGORIES} from '../category/categories';


@Component({
  selector: 'web-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{


  movies: Movie[] = [];
  vigjatekok:Array<Movie> =[];
  thrillerek:Array<Movie> =[];
  horrorok:Array<Movie> =[]; 
  akciok:Array<Movie> =[];
  romantikusok:Array<Movie> =[]; 
  dramak:Array<Movie> =[]; 
  category = '';
  page='';
  favorites= [];

  isAuth = false;

  categories = CATEGORIES;

  constructor(private appService: AppService) {}
  async ngOnInit(){
    this.movies = (await this.appService.getMovies().then());
    this.vigjatekok= this.movies.filter(film => (film.category.includes("Vigjatek")));
    this.thrillerek = this.movies.filter(film => (film.category.includes("Thriller")));
    this.horrorok = this.movies.filter(film => (film.category.includes("Horror")));
    this.akciok = this.movies.filter(film => (film.category.includes("Akcio")));
    this.romantikusok = this.movies.filter(film => (film.category.includes("Romantikus")));
    this.dramak = this.movies.filter(film => (film.category.includes("Drama")));
  }

  onSelect(event: string){
    this.category = event;
  }

  onFavorite(event: Movie) {
    if (event && event.star) {
      this.favorites.push(event);
    }
    this.favorites = this.favorites.filter(favorite => favorite.star);
  }

}
