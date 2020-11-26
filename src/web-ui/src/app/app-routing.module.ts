import { NgModule } from '@angular/core';
import { CommonModule, } from '@angular/common';
import { BrowserModule  } from '@angular/platform-browser';
import { Routes, RouterModule } from '@angular/router';

import {DashboardComponent} from "./pages/dashboard/dashboard.component";
import {ProjectComponent} from "./pages/project/project.component";
import {IssueComponent} from "./pages/issue/issue.component";
import {NotfoundComponent} from "./shared/notfound/notfound.component";
import {AppLayoutComponent} from "./layouts/app-layout/app-layout.component";
import {IssueDetailComponent} from "./pages/issue/issue-detail/issue-detail.component";
import {IssueRoutingModule} from "./pages/issue/issue.routing.module";
import {AuthGuard} from "./security/auth.guard";
import {LoginComponent} from "./login/login.component";

const routes: Routes =[
  {path:'dashboard', component:DashboardComponent,canActivate:[AuthGuard]},
  {path:'project', component:ProjectComponent,canActivate:[AuthGuard]},
  {path:'issue', component:IssueComponent,canActivate:[AuthGuard]},
  {path:'**',component:NotfoundComponent},
  {path:'login',component:LoginComponent}
  /*{
    path:'', component:AppLayoutComponent,
    children:[
      {path: '', pathMatch: 'full', redirectTo: 'dashboard'},
      {path:'dashboard', component:DashboardComponent},
      {path:'project', component:ProjectComponent},
      {path:'issue', component:IssueComponent}
      ],
  },
  {
    path: '**', component: NotfoundComponent
  }
  //{path:'**',component:NotfoundComponent}*/
];

@NgModule({
  imports: [
    IssueRoutingModule,
    RouterModule.forRoot(routes)],
  exports: [RouterModule
  ],
})
export class AppRoutingModule { }
