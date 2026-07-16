import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment'
import { RegisterRequest } from '../models/register-request';

@Injectable({
  providedIn: 'root',
})
export class Authentification {

  constructor(private http:HttpClient) {}

  getUserEmail(email: string){
    return this.http.get(`${environment.API_URL}/${email}`);
  }

  login(email: string, password: string){
    const requestBody  = {
      email : email,
      password : password
    }
    return this.http.post(`${environment.API_URL}/auth/login` ,requestBody);
  }

  register(request: RegisterRequest){
      return this.http.post(`${environment.API_URL}/auth/register`, request);
  }

  


}
