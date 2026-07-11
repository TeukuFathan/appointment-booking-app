import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class Auth {

  private http = inject(HttpClient);


  login(data: { email: string; password: string }) {
    return this.http
      .post<{ token: string }>(
        `${environment.apiUrl}/auth/login`,
        data
      )
      .pipe(
        tap(response => {
          localStorage.setItem('token', response.token);
        })
      );
  }

  register(data: {
    name: string;
    email: string;
    password: string;
    businessName: string;
    businessSlug: string;
  }) {
    return this.http.post(
      `${environment.apiUrl}/auth/register`,
      data
    );
  }

  getToken() {
    return localStorage.getItem('token');
  }

  logout() {
    localStorage.removeItem('token');
  }
}

