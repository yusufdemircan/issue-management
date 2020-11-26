import {Injectable} from "@angular/core";
import {ApiService} from "../api.service";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";



@Injectable()
export class ProjectService{

    private PROJECT_PATH="/project";

    constructor(private apiService: ApiService) {
    }

    getAll(page):Observable<any>{
        return this.apiService.get(this.PROJECT_PATH+'/pagination',page).pipe(map(

            res => {
                if (res) {
                    return res;
                } else {

                    return null;
                }
            }

        ));

    }


  getAll2():Observable<any>{
    return this.apiService.get(this.PROJECT_PATH).pipe(map(

      res => {
        if (res) {
          return res;
        } else {

          return null;
        }
      }

    ));

  }




    getById(id):Observable<any>{
        return this.apiService.get(this.PROJECT_PATH+'/'+id).pipe(map(

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


    createProject(project):Observable<any>{
        return this.apiService.post(this.PROJECT_PATH,project).pipe(map(

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


    deleteProject(id):Observable<any>{
        return this.apiService.delete(this.PROJECT_PATH+'/'+id).pipe(map(

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
