import { Call } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
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
   type:string="sync";
   id:number;
   api:string;
   topic:string;
   eventProduced:string;
   description:string;

   constructor(private callsService:CallsService){}
    ngOnInit(){
        this.callsService.getAll().subscribe((response:Call[])=>{
            this.calls=response;
        });
    }
    public deleteCall():void{
      
        this.callsService.delete(this.deleteId).subscribe(()=>
        {
            this.callsService.getAll().subscribe((response:Call[])=>{
                this.calls=response;
            });
        });
        document.getElementById('id01').style.display='none';
        
    }
    public deleteModel(id:string):void{
        document.getElementById('id01').style.display='block';
        this.deleteId=id;
    }
    public updateModel(id:number,type:string,api:string,topic:string,eventProduced:string,description:string):void{
        document.getElementById('updateModel').style.display='block';
        this.id=id;
        this.type=type;
        this.api=api;
        this.topic=topic;
        this.eventProduced=eventProduced;
        this.description=description;
        

        
        
        
    }
    updateCall():void{
        const formData = new FormData();
        
        formData.append("type",this.type);
        if(this.type=='sync'){
            
            formData.append("api",this.api);
        }
        else if(this.type=='async'){
           
            formData.append("topic",this.topic);
            formData.append("eventProduced",this.eventProduced);

        }
        formData.append("description",this.description);
      
     
        this.callsService.updateCall(this.id,formData).subscribe(()=>{
           
           
            document.getElementById('updateModel').style.display='none';
            alert("Call Updated Successfully");
            this.callsService.getAll().subscribe((response:Call[])=>{
                this.calls=response;
            });
            
            
        }
        
            
        );
        
        
    }
}
