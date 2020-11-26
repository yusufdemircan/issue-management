import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {IssueService} from "../../../services/sharedService/issue.service";
import {ProjectService} from "../../../services/sharedService/project.service";
import {UserService} from "../../../services/sharedService/user.service";
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-issue-detail',
  templateUrl: './issue-detail.component.html',
  styleUrls: ['./issue-detail.component.css']
})
export class IssueDetailComponent implements OnInit {

  @ViewChild('tplDateCell') tplDateCell: TemplateRef<any>;


  issueDetailForm : FormGroup;
  //Root parameter option
  id: number;
  private sub: any;

  //History Table option
  datatable_rows = [];
  columns = [];

  //Dropdown values
  projectOptions = [];
  issueStatusOptions = [];
  assigneeOptions = [];

  issueDetail = {};

  constructor(private route: ActivatedRoute,
              private issueService : IssueService,
              private projectService : ProjectService,
              private userService : UserService,
              private formBuilder: FormBuilder,) {
  }

  ngOnInit(): void {
    this.sub=this.route.params.subscribe(params =>{
      this.id = +params['id'];
      this.loadIssueDetails();
    })

    this.columns = [
      {prop: 'id', name: 'No',maxWidth:30},
      {prop: 'description', name: 'Description'},
      {prop: 'date', name: 'Issue Date', cellTemplate:this.tplDateCell},
      {prop: 'issueStatus', name: 'Issue Status'},
      {prop: 'assignee.nameSurname', name: 'Assignee'},
      {prop: 'issue.project.projectName', name: 'Project Name'}
    ];

    //1-Project DD dolacak
    this.LoadProject();
    //2-Assignee DD dolacak
    this.LoadAssignee();
    //1-Issue Status DD dolacak
    this.LoadIssueStatus();

  }

  private LoadIssueStatus() {
    this.issueService.getAllIssueStatus().subscribe(response =>{
      this.issueStatusOptions = response;
    });
  }

  private LoadAssignee() {
    this.userService.getAll().subscribe(response =>{
      this.assigneeOptions = response;
    });
  }

  private LoadProject() {
    this.projectService.getAll2().subscribe(response =>{
      this.projectOptions = response;
    });
  }

  private loadIssueDetails() {
    this.issueService.getByIdWithDetails(this.id).subscribe(response =>{
      this.issueDetail = response;
      this.issueDetailForm = this.createIssueDetailFormGroup(response);
      this.datatable_rows = response['issueHistories'];
    });
  }


  createIssueDetailFormGroup(response) {
    return this.formBuilder.group({
      id: response['id'],
      description: response['description'],
      details: response['details'],
      date: this.fromJsonDate(response['date']),
      issueStatus: response['issueStatus'],
      assignee_id: response['assignee'] ? response['assignee']['id'] :'',
      project_id: response['project']? response['project']['id']:'',
      project_manager: response['project'] && response['project']['manager'] ? response['project']['manager']['nameSurname']: '',
    });
  }

  saveIssue(){
    this.issueService.updateIssue(this.issueDetailForm.value).subscribe(response=>{
      this.issueDetailForm = this.createIssueDetailFormGroup(response);
      this.datatable_rows = response['issueHistories'];
    });
  }

  fromJsonDate(jDate): string {
    const bDate: Date = new Date(jDate);
    return bDate.toISOString().substring(0, 10);
  }
}
