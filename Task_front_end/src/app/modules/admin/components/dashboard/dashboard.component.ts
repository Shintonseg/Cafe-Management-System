import { Component } from '@angular/core';
import { AdminService } from '../../services/admin.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {

  listOfTasks: any = [];

  constructor(
    private adminService: AdminService,
    private snackBar : MatSnackBar
  ){
    this.getTasks();
  }

  getTasks(){
    this.adminService.getAllTasks().subscribe(res=>{
      this.listOfTasks = res;
    })
  }

  deleteTask(id: number){
    this.adminService.deleteTask(id).subscribe((res)=>{
      console.log("id is"+id);
      this.snackBar.open("Task deleted successfully","Close", {duration:5000});
      this.getTasks()
    })
  }
}
