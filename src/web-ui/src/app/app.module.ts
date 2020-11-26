import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {RouterModule} from '@angular/router';
import {AppRoutingModule} from './app-routing.module';

import {AppComponent} from './app.component';
import {ApiService} from "./services/api.service";
import {ProjectService} from "./services/sharedService/project.service";
import {ProjectComponent} from "./pages/project/project.component";
import {IssueComponent} from "./pages/issue/issue.component";
import {IssueService} from "./services/sharedService/issue.service";
import {NgxDatatableModule} from "@swimlane/ngx-datatable";
import {DashboardComponent} from "./pages/dashboard/dashboard.component";
import {HeaderComponent} from "./layouts/header/header.component";
import {SidebarComponent} from "./layouts/sidebar/sidebar.component";
import {BrowserModule} from "@angular/platform-browser";
import {ModalModule} from "ngx-bootstrap/modal";
import {BsDropdownModule} from "ngx-bootstrap/dropdown";
import {CollapseModule} from "ngx-bootstrap/collapse";
import {PaginationModule} from "ngx-bootstrap/pagination";
import {BsDatepickerModule} from "ngx-bootstrap/datepicker";
import {ToastNoAnimation, ToastNoAnimationModule, ToastrModule} from "ngx-toastr";
import {CommonModule} from "@angular/common";
import {ConfirmationComponent} from "./shared/confirmation/confirmation.component";
import {UserService} from "./services/sharedService/user.service";
import {IssueHistoryService} from "./services/sharedService/issue.history.service";
import {IssueDetailComponent} from "./pages/issue/issue-detail/issue-detail.component";
import {AppLayoutComponent} from './layouts/app-layout/app-layout.component';

import {ColorDirective} from './directives/color.directive';
import {JwtInterceptor} from "./security/jwt.interceptor";
import {AuthenticationService} from "./security/authentication.service";
import {AuthGuard} from "./security/auth.guard";
import {ErrorInterceptor} from "./security/authentication.interceptor";
import { LoginComponent } from './login/login.component';


@NgModule({
  imports: [

    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    RouterModule,
    HttpClientModule,
    ReactiveFormsModule,
    ModalModule.forRoot(),
    NgxDatatableModule,
    BsDropdownModule.forRoot(),
    CollapseModule.forRoot(),
    PaginationModule.forRoot(),
    BsDatepickerModule.forRoot(),
    ToastNoAnimationModule,
    ToastrModule.forRoot({
      toastComponent: ToastNoAnimation,
      maxOpened: 1,
      autoDismiss: true
    }),
    AppRoutingModule,


  ],
  declarations: [
    AppComponent,
    HeaderComponent,
    SidebarComponent,
    ProjectComponent,
    DashboardComponent,
    ConfirmationComponent,
    IssueComponent,
    IssueDetailComponent,
    AppLayoutComponent,
    ColorDirective,
    LoginComponent

  ],

  providers: [
    ApiService,
    ProjectService,
    IssueService,
    UserService,
    IssueHistoryService,
    AuthenticationService,
    AuthGuard,
    {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
