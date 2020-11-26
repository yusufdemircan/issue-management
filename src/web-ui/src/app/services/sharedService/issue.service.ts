import {Injectable} from "@angular/core";
import {ApiService} from "../api.service";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";


@Injectable()
export class IssueService {

  private ISSUE_PATH = "/issue";
  private ISSUE_GET_BY_ID_DETAILS=this.ISSUE_PATH+"/detail/";
  private ISSUE_GET_STATUSES = this.ISSUE_PATH+"/statuses";

  constructor(private apiService: ApiService) {
  }

  getAllPageable(page): Observable<any> {
    return this.apiService.get(this.ISSUE_PATH + '/pagination', page).pipe(map(
      res => {
        if (res) {
          return res;
        } else {
          console.log(res);
          return null;
        }
      }
    ));

  }


  getAll(): Observable<any> {
    return this.apiService.get(this.ISSUE_PATH).pipe(map(
      res => {
        if (res) {
          return res;
        } else {
          console.log(res);
          return null;
        }
      }
    ));

  }


  getById(id): Observable<any> {
    return this.apiService.get(this.ISSUE_PATH, id).pipe(map(
      res => {
        if (res) {
          return res;
        } else {
          console.log(res);
          return null;
        }
      }
    ));

  }


  createIssue(issue): Observable<any> {
    return this.apiService.post(this.ISSUE_PATH, issue).pipe(map(
      res => {
        if (res) {
          return res;
        } else {
          console.log(res);
          return null;
        }
      }
    ));
  }

  updateIssue(issue) : Observable<any>{
    return this.apiService.put(this.ISSUE_PATH+ '/'+ issue.id ,issue).pipe(map(
      res =>{
        if(res){
          return res;
        }else{
          console.log(res);
          return {};
        }
      }
    ));
  }

  delete(id): Observable<any> {
    return this.apiService.get(this.ISSUE_PATH, id).pipe(map(
      res => {
        if (res) {
          return res;
        } else {
          console.log(res);
          return null;
        }
      }
    ));

  }

  getAllIssueStatus() {
    return this.apiService.get(this.ISSUE_GET_STATUSES).pipe(map(
      res => {
        if (res) {
          return res;
        } else {
          console.log(res);
          return null;
        }
      }
    ));
  }


  getByIdWithDetails(id: number) {
    return this.apiService.get(this.ISSUE_GET_BY_ID_DETAILS + id).pipe(map(
      res => {
        if (res) {
          return res;
        } else {
          console.log(res);
          return null;
        }
      }
    ));
  }
}
