import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators,
  AbstractControl,
  ValidationErrors,
} from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search-review',
  templateUrl: './search-review.component.html',
  styleUrls: ['./search-review.component.css'],
})
export class SearchReviewComponent implements OnInit {
  searchForm!: FormGroup;
  constructor(private fb: FormBuilder, private router: Router) {}
  ngOnInit(): void {
    this.searchForm = this.fb.group({
      query: this.fb.control('', [
        Validators.required,
        Validators.minLength(2),
        this.nonWhiteSpace,
      ]),
    });
  }

  onFormSubmit() {
    const query = this.searchForm.get('query')?.value;
    this.router.navigate(['/search'], { queryParams: { query: query } });
  }

  readonly nonWhiteSpace = (ctrl: AbstractControl) => {
    if (ctrl.value.trim().length >= 2) {
      return null;
    } else {
      return { nonWhiteSpace: true } as ValidationErrors;
    }
  };
}
