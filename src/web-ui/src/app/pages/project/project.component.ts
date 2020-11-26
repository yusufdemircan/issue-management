import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {ProjectService} from "../../services/sharedService/project.service";
import {Page} from "../../common/page";
import {Project} from "../../common/project.model";
import {BsModalRef, BsModalService} from "ngx-bootstrap/modal";
import {isNgTemplate} from "@angular/compiler";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ConfirmationComponent} from "../../shared/confirmation/confirmation.component";
import {UserService} from "../../services/sharedService/user.service";

@Component({
    selector: 'app-project',
    templateUrl: './project.component.html',
    styleUrls: ['./project.component.css']
})
export class ProjectComponent implements OnInit {
    modalRef: BsModalRef;
    projectForm : FormGroup;

    @ViewChild('tplProjectDeleteCell') tplProjectDeleteCell: TemplateRef<any>;
    page = new Page();
    cols=[];
    rows = [];
    managerOption = [];
    constructor(private projectService: ProjectService,
                private modalService: BsModalService,
                private formBuilder: FormBuilder,
                private userService : UserService) {

    }


    ngOnInit(): void {

        this.projectForm = this.formBuilder.group({
          'projectName': [null,[Validators.required,Validators.minLength(4)]],
          'projectCode': [null,[Validators.required,Validators.minLength(2),Validators.maxLength(10)]],
          'managerId' : [null,[Validators.required]]
        });

      this.setPage({offset: 0});

      this.userService.getAll().subscribe(
        res => {
          this.managerOption = res;

        }

      );
    }

  ngAfterViewInit() {
      this.cols = [
        {prop:'id' ,name:'No'},
        {prop:'projectName', name: 'Project Name',sortable:false},
        {prop:'projectCode', name: 'Project Code',sortable:false},
        {prop:'manager.nameSurname', name: 'Manager',sortable:false},
        {prop:'id', name: 'Action',cellTemplate:this.tplProjectDeleteCell,flexGrow:2,sortable:false},

      ];

  }
    setPage(pageInfo) {
        this.page.page=pageInfo.offset;
        this.projectService.getAll(this.page).subscribe(pagedData =>{
            this.page.size = pagedData.size;
            this.page.page = pagedData.page;
            this.page.totalElements = pagedData.totalElements;
            this.rows = pagedData.content;

        })
    }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }


  get f(){
      return this.projectForm.controls;
  }

  closeAndResetModal(){
      this.projectForm.reset();
      this.modalRef.hide();
  }

  saveProject(){
      if(!this.projectForm.valid){
        return;
      }
      this.projectService.createProject(this.projectForm.value).subscribe(
        response => {
          this.setPage({ offset: 0 });
          this.closeAndResetModal();
        })}


  showDeleteConfirmation(value):void{
      const modal = this.modalService.show(ConfirmationComponent);
    (<ConfirmationComponent>modal.content).showConfirmation(
      'Delete Confirmation',
      'Are you sure delete project'
    );

    (<ConfirmationComponent>modal.content).onClose.subscribe(
      result =>{
        if(result === true){
          this.projectService.deleteProject(value).subscribe(
            response =>{
              if(response === true){
                this.setPage({offset:0});
              }
            }
          );
        }
        else if(result === false ){
          console.log("NO");
        }
      }
    );
  }
}
