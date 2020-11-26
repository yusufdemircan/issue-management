  import {Component, OnInit, TemplateRef} from '@angular/core';
import {IssueService} from "../../services/sharedService/issue.service";
import {Page} from "../../common/page";
  import {FormBuilder, FormGroup, Validators} from "@angular/forms";
  import {ProjectService} from "../../services/sharedService/project.service";
  import {BsModalRef, BsModalService} from "ngx-bootstrap/modal";

@Component({
  selector: 'app-issue',
  templateUrl: './issue.component.html',
  styleUrls: ['./issue.component.css']
})
export class IssueComponent implements OnInit {
  page = new Page();
  rows = [];
  projectOption =[];
  modalRef : BsModalRef;
  issueForm : FormGroup;

  constructor(private issueService : IssueService,
              private formBuilder : FormBuilder,
              private projectService :ProjectService,
              private bsmodalService : BsModalService) { }

  ngOnInit(): void {
    this.setPage({offset: 0});
    this.issueForm=this.formBuilder.group({
      description: [null,[Validators.required]],
      project_id: [null,[Validators.required]]
    });

    this.LoadProject();


  }

  setPage(pageInfo) {
    this.page.page=pageInfo.offset;
    this.issueService.getAllPageable(this.page).subscribe(pagedData =>{
      this.page.size = pagedData.size;
      this.page.page = pagedData.page;
      this.page.totalElements = pagedData.totalElements;
      this.rows = pagedData.content;

    })
  }


  openModal(templateRef :TemplateRef<any>){
    this.modalRef=this.bsmodalService.show(templateRef);
  }


  private LoadProject() {
    this.projectService.getAll2().subscribe(response =>{
      this.projectOption = response;
    });
  }

  saveIssue(){
    this.issueService.createIssue(this.issueForm.value).subscribe(res =>{
      this.setPage({offset:0});
      this.closeAndResetModal();
    });
  }

  get f(){
    return this.issueForm.controls;
  }
  closeAndResetModal(){
    this.issueForm.reset();
    this.modalRef.hide();
  }
}
