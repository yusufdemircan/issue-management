import {NgModule} from "@angular/core";
import {BsModalRef, ModalModule} from "ngx-bootstrap/modal";
import { ReactiveFormsModule} from "@angular/forms";
import {ConfirmationComponent} from "./confirmation/confirmation.component";
import {CommonModule} from '@angular/common';
import {FormsModule} from "@angular/forms";
import {NgxDatatableModule} from "@swimlane/ngx-datatable";
import { NotfoundComponent } from './notfound/notfound.component';
@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    NgxDatatableModule,
    ModalModule.forRoot()
  ],
  providers: [BsModalRef],
  declarations: [
    ConfirmationComponent,
    NotfoundComponent
  ],
  entryComponents: [
    ConfirmationComponent
  ],
  exports: [
    ModalModule,
    ReactiveFormsModule,
    ConfirmationComponent
  ]
})
export class SharedModule { }
