import { Routes } from '@angular/router';
import { Login } from './pages/login/login'; 
import { Register } from './pages/register/register'; 
import { Dashboard } from './pages/dashboard/dashboard'; 
import { BusinessServices } from './pages/business-services/business-services'; 



export const routes: Routes = [
    { path: 'login', component: Login },
    { path: 'register', component: Register },
    { path: 'dashboard', component: Dashboard },
    { path: 'business-services', component: BusinessServices },
    
    // This handles the root URL: http://localhost:4200/
    //  pathMatch: 'full' is to avoid ('' + 'dashboard') become valid
    { path: '', redirectTo: 'login', pathMatch: 'full' },
    //'**' is for any other url that doesnt exist 
    { path: '**', redirectTo : 'login'}

];
