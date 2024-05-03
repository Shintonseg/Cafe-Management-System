import { Component } from '@angular/core';
import { AdminService } from '../../services/admin.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDatepickerControl, MatDatepickerPanel } from '@angular/material/datepicker';

@Component({
  selector: 'app-post-task',
  templateUrl: './post-task.component.html',
  styleUrls: ['./post-task.component.scss']
})
export class PostTaskComponent {
  
  taskForm!: FormGroup;
  listOfEmployees: any = [];
  listOfPriorities: any = ["LOW", "MEDIUM", "HIGH"];

  constructor(private adminService: AdminService,
    private formBuilder: FormBuilder
  ){
    this.getUsers();
    this.taskForm = this.formBuilder.group({
      employeeId: [null, Validators.required] ,
      title: [null, Validators.required],
      description: [null, Validators.required],
      dueDate: [null, Validators.required],
      priority: [null, Validators.required]
    })
  }

  getUsers(){
    this.adminService.getUsers().subscribe(res=>{
      console.log(res);
      this.listOfEmployees=res;
    })
  }

  postTask(){
    console.log(this.taskForm.value);
  }
}
