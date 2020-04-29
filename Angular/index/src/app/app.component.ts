import { Component, OnInit } from '@angular/core';
import { AppService } from './app.service';
import { Movie } from './movies/Movie';
import MovieJSON from '../assets/movies.json';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'index';

  constructor(){

  }
    
  ngOnInit(){

  }
}
