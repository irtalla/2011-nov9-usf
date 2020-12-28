import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { UrlService } from '../url.service';
import { Person } from '../models/person';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private loggedUser: Person;
  private usersUrl: string;
  private formHeaders = new HttpHeaders('Content-Type': 'application/x-www-form-urlencoded'});
  private regHeaders = new HttpHeaders('Content-Type':'application/json'})

  constructor(private http: HttpClient, private urlService: UrlService, private cookieService: CookieService) {
    this.usersUrl = this.urlService.getUrl() + 'users';
  }

  loginUser(username: string, password: string): Observable<Person> {
    if (username && password) {
      const queryParams = `?user=${username}&pass=${password}`;
      return this.http.put(this.usersUrl + queryParams,
        {headers: this.formHeaders, withCredentials:true}).pipe(
          map(resp => resp as Person)
      );
    } else {
      return this.http.get(this.usersUrl,
        {withCredentials:true}).pipe(
          map(resp => resp as Person)
        );
    }
  }

  logoutUser(): Observable<object> {
    return this.http.delete(this.usersUrl, {headers:this.regHeaders, withCredentials:true}).pipe();
  }

  updateUser(updatedUser: Person): Observable<object> {
    this.loggedUser = updatedUser;
    return this.http.put(this.usersUrl + this.loggedUser.id, updatedUser, 
      {headers:this.regHeaders, withCredentials:true}).pipe();
  }

  getLoggedUser(): Person {
    return this.loggedUser;
  }
}
