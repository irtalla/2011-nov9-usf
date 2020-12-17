import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Associate } from './classes/associate';

@Injectable({
  providedIn: 'root'
})
export default class AssociateService {

  constructor(private http: HttpClient) {}

  getAssociates():Observable<Associate[]> {
    return this.http.get('http://localhost:8080/associates').pipe(
      map(resp => resp as Associate[])
    );
  }

  getAssociateById(id: number): Observable<Associate> {
    return this.http.get('http://localhost:8080/associates/' + id).pipe(
      map(resp => resp as Associate)
    );
  }
}
