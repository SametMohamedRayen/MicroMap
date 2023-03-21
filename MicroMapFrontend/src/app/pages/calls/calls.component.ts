import { Call } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { CallsService } from 'app/calls.service';



@Component({
    selector: 'table-cmp',
    moduleId: module.id,
    templateUrl: 'calls.component.html',
    styleUrls: ['./calls.component.css']
})

export class CallsComponent implements OnInit{
   calls : Call[]=[];
   deleteId:string="";
   constructor(private callsService:CallsService){}
    ngOnInit(){
        this.callsService.getAll().subscribe((response:Call[])=>{
            this.calls=response;
        });
    }
    public deleteCall():void{
      
        this.callsService.delete(this.deleteId).subscribe();
        document.getElementById('id01').style.display='none';
        window.location.reload();
    }
    public deleteModel(id:string):void{
        document.getElementById('id01').style.display='block';
        this.deleteId=id;
    }
    
}
