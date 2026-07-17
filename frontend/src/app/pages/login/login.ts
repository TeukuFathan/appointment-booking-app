import { Component } from '@angular/core';
import { Authentification } from '../../core/services/authentification';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { required } from '@angular/forms/signals';
import { LoginRequest } from '../../core/models/login-request';
import { CommonModule } from '@angular/common';
import { UserService  } from '../../core/services/user';

@Component({
  selector: 'app-login',
  imports: [FormsModule, ReactiveFormsModule, CommonModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {

  constructor(private auth:Authentification, private user: UserService ){}

  loginForm = new FormGroup({
    email : new FormControl("",{
      nonNullable: true,
      validators: [Validators.required]
    }),
    password : new FormControl("",{
      nonNullable: true,
      validators: [Validators.required]
    })
  });
  
  login() :  void {  
    this.auth.login(this.loginForm.getRawValue()).subscribe({
      next : response => console.log(response)
    });
  }

  // return observable that receive/subscribe the data the request go from the service to here 
  getUserData() : void  {
    // send the data to the getUserByEmailFunction
    this.user.getUserByEmail(this.loginForm.controls['email'].value)
    // subscribe to it 
    // open a listener if it return 200> , <300 then receive the respond body through next
    .subscribe({
      next : (httpresp) => console.log(httpresp),
      error : (err) => console.error(err)
    })
  }


}
