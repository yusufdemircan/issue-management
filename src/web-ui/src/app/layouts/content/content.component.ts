import {Component, OnInit} from "@angular/core";
import {NavigationEnd, Router} from "@angular/router";

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.css']
})
export class ContentComponent implements OnInit {
 

  constructor(public router: Router) {
   
  }

  ngOnInit() {
  }

 
  
}
