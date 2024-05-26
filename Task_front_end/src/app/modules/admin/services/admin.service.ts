import { HttpClient, HttpHandler, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StorageService } from 'src/app/auth/services/storage/storage.service';

const BASE_URL = "http://task.us-east-1.elasticbeanstalk.com/";
@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) {

   }

   getUsers(): Observable<any>{
    return this.http.get(BASE_URL+"/api/admin/users", {
      headers: this.createAuthorizationHeader()
    });
   }

   postTask(taskDTO: any): Observable<any>{
    return this.http.post(BASE_URL+"/api/admin/task",taskDTO, {
      headers: this.createAuthorizationHeader()
    })
   }

   getAllTasks(): Observable<any>{
    return this.http.get(BASE_URL+"/api/admin/tasks", {
      headers: this.createAuthorizationHeader()
    });
   }

   deleteTask(id: number): Observable<any>{
    return this.http.delete(BASE_URL+"/api/admin/task/"+id, {
      headers: this.createAuthorizationHeader()
    })
   }

   getTaskById(id: number): Observable<any>{
    return this.http.get(BASE_URL+"/api/admin/task/"+id, {
      headers: this.createAuthorizationHeader()
    });
   }

   updateTask(id: number, taskDTO: any): Observable<any>{
    return this.http.put(BASE_URL+"/api/admin/task/update/"+id ,taskDTO, {
      headers: this.createAuthorizationHeader()
    });
   }

   private createAuthorizationHeader(): HttpHeaders{
    return new HttpHeaders().set('Authorization','Bearer '+ StorageService.getToken())
   }
}
