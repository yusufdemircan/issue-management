import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {catchError} from "rxjs/operators";
import {environment} from "../../environments/environment";
@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private httpOption = {
    headers: new HttpHeaders(

        {
          'Content-Type':'application/json'
        })
  };

  constructor(private http : HttpClient) { }



  get(path:string,params:HttpParams = new HttpParams()):Observable<any>{
    return this.http.get(environment.API_BASE_PATH+path,{params}).pipe(catchError(this.formatError));
  }

  post(path:string,params:HttpParams = new HttpParams()):Observable<any>{
    return this.http.post(environment.API_BASE_PATH + path,JSON.stringify(params),this.httpOption).pipe(catchError(this.formatError));
  }

  put(path:string,params:HttpParams = new HttpParams()):Observable<any>{
    return this.http.put(environment.API_BASE_PATH+path,JSON.stringify(params),this.httpOption).pipe(catchError(this.formatError));
  }

  delete(path:string,params:HttpParams = new HttpParams()):Observable<any>{
    return this.http.delete(environment.API_BASE_PATH+path,{params}).pipe(catchError(this.formatError));
  }
  formatError(error: any){

    return of(error.error);
  }
}
