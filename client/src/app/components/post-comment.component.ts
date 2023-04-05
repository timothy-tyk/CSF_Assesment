import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Comment } from '../models';
import { MovieService } from '../services/movie.service';

@Component({
  selector: 'app-post-comment',
  templateUrl: './post-comment.component.html',
  styleUrls: ['./post-comment.component.css'],
})
export class PostCommentComponent implements OnInit {
  title!: string;
  commentForm!: FormGroup;
  constructor(
    private activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    private movieSvc: MovieService,
    private location: Location
  ) {}
  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(
      (p: any) => (this.title = p['params']['title'])
    );
    this.commentForm = this.fb.group({
      user: this.fb.control('', [Validators.required, Validators.minLength(3)]),
      rating: this.fb.control('', [Validators.required]),
      comment: this.fb.control('', [Validators.required]),
    });
  }

  onCommentSubmit() {
    const comment: Comment = {
      user: this.commentForm.get('user')?.value,
      title: this.title,
      rating: this.commentForm.get('rating')?.value,
      comment: this.commentForm.get('comment')?.value,
    };
    this.movieSvc
      .postMovieComment(comment)
      .then((res) => console.log(res))
      .then(() => this.location.back());
  }

  goBack() {
    this.location.back();
  }
}
