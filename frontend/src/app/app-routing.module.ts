import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutusComponent } from './aboutus/aboutus.component';
import { HomeComponent } from './home/home.component';
import { GeminichatComponent } from './geminichat/geminichat.component';
import { LoginComponent } from './login/login.component';
import { FileuploadComponent } from './fileupload/fileupload.component';
import { AuthGuard } from './auth.guard';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full'},
  { path: 'home', component: HomeComponent},
  { path: 'about-us', component: AboutusComponent, canActivate: [AuthGuard]},
  { path: 'file', component: FileuploadComponent, canActivate: [AuthGuard]},
  { path: 'gemini', component: GeminichatComponent},
  { path: 'login', component: LoginComponent},
  {
    path: '**',
    redirectTo: '/home',
    pathMatch:'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
