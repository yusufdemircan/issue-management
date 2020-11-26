import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { IssueComponent } from "./issue.component";
import {IssueDetailComponent} from "./issue-detail/issue-detail.component";
import {DashboardComponent} from "../dashboard/dashboard.component";
import {NotfoundComponent} from "../../shared/notfound/notfound.component";
import {AppRoutingModule} from "../../app-routing.module";

const routes: Routes = [
  {
    path : '', component :IssueComponent
  },

  {
    path: 'issue-detail/:id',  component: IssueDetailComponent
  },

];


@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class IssueRoutingModule { }
