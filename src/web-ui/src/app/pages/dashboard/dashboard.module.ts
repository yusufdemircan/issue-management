import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DashboardComponent } from './dashboard.component';
import {DashboardRoutingModule} from "./dashboard.routing.module";
import {ColorDirective} from "../../directives/color.directive";




@NgModule({
  declarations: [DashboardComponent,ColorDirective],
  imports: [
    CommonModule,
    DashboardRoutingModule


  ]
})
export class DashboardModule { }
