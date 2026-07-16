import { Component } from '@angular/core';
import { Authentification } from '../../core/services/authentification';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  imports: [FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {

  constructor(private auth:Authentification){}
  email : string = '';
  password : string = '';
  
  login() :  void {
    this.auth.login(this.email, this.password).subscribe({
      next : response => console.log(response)
    });
  }

}
