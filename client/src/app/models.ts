export interface Review {
  title: string;
  rating: number;
  summary: string;
  reviewUrl: string;
  commentCount: number;
  image: string;
  byline: string;
  headline: string;
}

export interface Comment {
  title: string;
  user: string;
  comment: string;
  rating: number;
}
