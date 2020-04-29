import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Movie } from './movie';
import MovieJSON from '../../assets/movies.json';


@Component({
  selector: 'web-movies',
  templateUrl: './movies.component.html',
  styleUrls: ['./movies.component.css']
})
export class MoviesComponent implements OnInit {
  @Input() hasAction = true;
  @Input() movie: Movie;
  @Output() getMovie = new EventEmitter<Movie | null>();

  constructor() { }

  ngOnInit(): void {}
    toggleStar() {
    this.movie.star = !this.movie.star;
    this.getMovie.emit(this.movie);
  }

}
