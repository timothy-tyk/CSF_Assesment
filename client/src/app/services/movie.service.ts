import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom } from 'rxjs';
import { Comment } from '../models';

const SERVER_URL = '/api';
const headers = new HttpHeaders().set('Content-Type', 'application/json');
@Injectable({
  providedIn: 'root',
})
export class MovieService {
  constructor(private httpClient: HttpClient) {}

  searchMovie(query: string) {
    const params = new HttpParams().set('query', query);
    return firstValueFrom(
      this.httpClient.get(`${SERVER_URL}/search`, { headers, params }).pipe()
    );
  }

  postMovieComment(comment: Comment) {
    return firstValueFrom(
      this.httpClient.post(`${SERVER_URL}/comment`, comment, { headers }).pipe()
    );
  }
}
