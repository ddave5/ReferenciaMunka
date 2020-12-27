import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Movie } from './movies/movie'

@Injectable({
  providedIn: 'root'
})
export class AppService {

  datas = [];
  
  constructor(private httpClient: HttpClient) { }


  getMovies(): Promise<Movie[]>{
    return this.httpClient.get<Movie[]>('/assets/movies.json').toPromise();
  }
}
