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

  auth(credentials : any): Observable<any> {
    return this.http.post(AUTH_API + 'api/authentification', {
      token: credentials.token,
      method: credentials.method
    }, httpOptions)
  }
  addWidget(credentials : any): Observable<any> {
    const httpOptions1 = {
      headers: new HttpHeaders({'Content-Type':'application/json; charset=utf-8', 'Access-Control-Allow-Origin': '*', 'x-auth-token': localStorage.getItem('token') || "", "method": localStorage.getItem('type') || ""})
    };
    return this.http.post(AUTH_API + 'api/service/addWidget', credentials, httpOptions1)
  }

  getWidget(): Observable<any> {
    const httpOptions1 = {
      headers: new HttpHeaders({'Content-Type':'application/json; charset=utf-8', 'Access-Control-Allow-Origin': '*', 'x-auth-token': localStorage.getItem('token') || "", "method": localStorage.getItem('type') || ""})
    };
    return this.http.get(AUTH_API + 'api/service/getWidgets', httpOptions1);
  }

  getWidgetSpecific(id: any): Observable<any> {
    const httpOptions1 = {
      headers: new HttpHeaders({'Content-Type':'application/json; charset=utf-8', 'Access-Control-Allow-Origin': '*', 'x-auth-token': localStorage.getItem('token') || "", "method": localStorage.getItem('type') || ""})
    };
    return this.http.get(AUTH_API + 'api/service/getWidget?id=' + id, httpOptions1);
  }

  removeWidget(credentials : any): Observable<any> {
    const httpOptions1 = {
      headers: new HttpHeaders({'Content-Type':'application/json;', 'Access-Control-Allow-Origin': '*', 'x-auth-token': localStorage.getItem('token') || "", "method": localStorage.getItem('type') || ""})
    };
    return this.http.post(AUTH_API + 'api/service/removeWidget', {
      id : credentials.id
    }, httpOptions1)
  }

  handleError(error :any) {
    return "Salut";
  }
}
