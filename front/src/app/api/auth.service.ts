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

  loginGoogle(credentials : any): Observable<any> {
    return this.http.post(AUTH_API + 'api/loginGoogle', {
      email: credentials.email,
      token: credentials.token
    }, httpOptions)
  }
  register(credentials : any): Observable<any> {
    return this.http.post(AUTH_API + 'api/register', {
      login: credentials.login,
      email: credentials.email,
      password: credentials.password,
    }, httpOptions)
  }
  registerGoogle(credentials : any): Observable<any> {
    return this.http.post(AUTH_API + 'api/registerGoogle', {
      email: credentials.email,
      oAuth: credentials.Oauth
    }, httpOptions)
  }
  login(credentials : any): Observable<any> {
    return this.http.post(AUTH_API + 'api/login', {
      email: credentials.email,
      password: credentials.password
    }, httpOptions)
  }

  handleError(error :any) {
    return "Salut";
  }
}
