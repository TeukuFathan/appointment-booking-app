import { Component } from '@angular/core';
import { RegisterRequest } from '../../core/models/register-request';
import { Authentification } from '../../core/services/authentification';
import { Router } from '@angular/router';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-register',
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './register.html',
  styleUrl: './register.css',
})
export class Register {

  constructor(private auth: Authentification, private router: Router){}

  registerForm = new FormGroup({
    name:  new FormControl("",{
      nonNullable: true,
      validators: [Validators.required]
    }),
    email: new FormControl("",{
      nonNullable: true,
      validators: [Validators.required]
    }),
    password: new FormControl("",{
      nonNullable: true,
      validators: [Validators.required]
    }),
    businessName: new FormControl("",{
      nonNullable: true,
      validators: [Validators.required]
    }),
    businessSlug: new FormControl("",{
      nonNullable: true,
      validators: [Validators.required]
    })
  })


  register(): void{
    console.log(this.registerForm.getRawValue());
    // An observable 
    this.auth.register(this.registerForm.getRawValue()).subscribe({
      // 'next' in subscriber are called if the return code is 2xx
      next : () => this.router.navigate(['/login'])
    });
  }


  
}
