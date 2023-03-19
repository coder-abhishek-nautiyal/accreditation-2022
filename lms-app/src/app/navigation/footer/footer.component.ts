import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

  pageFooter: string;
  
  constructor() { }

  ngOnInit(): void {
    this.pageFooter='©'+ new Date().getFullYear() +' Abhishek Nautiyal | All Rights Reserved';
  }



}
