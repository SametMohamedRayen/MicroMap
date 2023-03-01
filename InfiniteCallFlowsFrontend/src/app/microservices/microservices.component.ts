import { DOCUMENT } from '@angular/common';
import { Component, Inject, OnInit } from '@angular/core';
import { MicroserviceService } from '../microservice.service';

@Component({
  selector: 'app-microservices',
  templateUrl: './microservices.component.html',
  styleUrls: ['./microservices.component.css']
})
export class MicroservicesComponent implements OnInit {
deletedName: string="";

  reference:any;
  names:string[]=[];
  constructor(private microserviceService : MicroserviceService,@Inject(DOCUMENT) private document: Document) { }

  ngOnInit(): void {
    this.microserviceService.getNames().subscribe((response:string[])=>{
      this.names=response;
  });
  }
  deleteModel(name: string) {

    this.deletedName=name;
    this.reference =this.document.getElementById('id01')
    if(this.reference){
    this.reference.style.display='block'}
    }
  delete(name:string) {
    this.document.getElementById("noButton")?.click();
    if(name){
      this.microserviceService.delete(name).subscribe(() => { window.location.reload() });
    }
    }
  

}
