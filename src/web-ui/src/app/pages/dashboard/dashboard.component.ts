import {Component, ElementRef, OnInit} from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  title : string = "Dashboard 2 way binding özelliği";
  redColor = 'red';
  greenColor = 'green';


  constructor() { }

  ngOnInit(): void {

  }

}
