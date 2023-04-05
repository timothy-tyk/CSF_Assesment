import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Review } from '../models';
import { MovieService } from '../services/movie.service';

@Component({
  selector: 'app-movie-reviews-list',
  templateUrl: './movie-reviews-list.component.html',
  styleUrls: ['./movie-reviews-list.component.css'],
})
export class MovieReviewsListComponent implements OnInit {
  query!: string;
  movies!: Review[];
  hasItLoaded: boolean = false;
  constructor(
    private movieSvc: MovieService,
    private activatedRoute: ActivatedRoute,
    private location: Location
  ) {}
  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(
      (p: any) => (this.query = p['query'])
    );
    this.movieSvc
      .searchMovie(this.query)
      .then((res) => {
        this.hasItLoaded = true;
        this.movies = res as Review[];
      })
      .catch((err) => console.log(err));
  }

  goBack() {
    this.location.back();
  }
}
