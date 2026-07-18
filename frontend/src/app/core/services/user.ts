import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class UserService  {

constructor(private httpClient: HttpClient){}


  getUserResponseByEmail(email : string){
    // Creates and returns an HTTP Observable.
    // The request starts when something subscribes to it.
    const paramsSend = { params : {email} }
    return this.httpClient.get(`${environment.API_URL}/user`, paramsSend);
  }
}