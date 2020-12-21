import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UrlService } from '../url.service';
import { Observable } from 'rxjs';
import { Cat } from '../models/cat';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CatService {
  private catsUrl: string;

  constructor(private http: HttpClient, private urlService: UrlService) {
    this.catsUrl = this.urlService.getUrl() + 'cats';
  }

  getAvailableCats(): Observable<Cat[]> {
    return this.http.get(this.catsUrl, {withCredentials:true}).pipe(
      map(resp => resp as Cat[])
    );
  }

  adoptCat(id: number): Observable<object> {
    return this.http.put(this.catsUrl + 'adopt/' + id, {withCredentials:true}).pipe();
  }

  addCat(cat: Cat): Observable<object> {
    return this.http.post(this.catsUrl, cat, {withCredentials:true}).pipe();
  }

  updateCat(cat: Cat): Observable<object> {
    return this.http.put(this.catsUrl + cat.id, cat, {withCredentials:true}).pipe();
  }
}
