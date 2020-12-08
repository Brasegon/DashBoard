import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';

const AUTH_API = "http://localhost:8080/";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type':'application/json; charset=utf-8', 'Access-Control-Allow-Origin': '*'})
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  register(credentials : any): Observable<any> {
    return this.http.post(AUTH_API + 'register', {
      login: credentials.username,
      email: credentials.email,
      password: credentials.password,
    }, httpOptions)
  }
  login(credentials : any): Observable<any> {
    return this.http.post(AUTH_API + 'login', {
      email: credentials.email,
      password: credentials.password
    }, httpOptions)
  }

  handleError(error :any) {
    return "Salut";
  }
}
