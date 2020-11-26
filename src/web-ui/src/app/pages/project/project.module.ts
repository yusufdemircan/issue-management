import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProjectComponent } from './project.component';
import {ProjectRoutingModule} from './project.routing.module';
import {ProjectService} from "../../services/sharedService/project.service";
import {SharedModule} from '../../shared/shared.module';
import {NgxDatatableModule} from '@swimlane/ngx-datatable';
import {ReactiveFormsModule} from "@angular/forms";





@NgModule({
  declarations: [ProjectComponent],
  imports: [

    CommonModule,
    ProjectRoutingModule,
    SharedModule,
    NgxDatatableModule

  ],
  providers:[ProjectService]
})
export class ProjectModule { }
