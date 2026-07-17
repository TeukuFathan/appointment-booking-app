import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment'
import { RegisterRequest } from '../models/register-request';
import { LoginRequest } from '../models/login-request';

@Injectable({
  providedIn: 'root',
})
export class Authentification {

  constructor(private http:HttpClient) {}

  login(loginRequest: LoginRequest){
    const requestBody  = loginRequest;
    return this.http.post(`${environment.API_URL}/auth/login` ,requestBody);
  }

  register(request: RegisterRequest){
      return this.http.post(`${environment.API_URL}/auth/register`, request);
  }

  


}
