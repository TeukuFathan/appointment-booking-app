import { Component } from '@angular/core';
import { RegisterRequest } from '../../core/models/register-request';
import { Authentification } from '../../core/services/authentification';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-register',
  imports: [FormsModule],
  templateUrl: './register.html',
  styleUrl: './register.css',
})
export class Register {

  constructor(private auth: Authentification, private router: Router){}

  form : RegisterRequest = 
    {
    name: '',
    email: '',
    password: '',
    businessName: '',
    businessSlug: '',
  };

  register(): void{
    console.log(`Registering : ${this.form}`);
    // An observable 
    this.auth.register(this.form).subscribe({
      // 'next' in subscriber are called if the return code is 2xx
      next : () => this.router.navigate(['/login'])
    });
  }

  
}
