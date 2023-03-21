import { Call } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { CallsService } from 'app/calls.service';



@Component({
    selector: 'table-cmp',
    moduleId: module.id,
    templateUrl: 'calls.component.html'
})

export class CallsComponent implements OnInit{
   calls : Call[]=[];
   constructor(private callsService:CallsService){}
    ngOnInit(){
        this.callsService.getAll().subscribe((response:Call[])=>{
            this.calls=response;
        });
    }
    
}
