import { Node } from '@angular/compiler';
import { Call } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { CallsService } from 'app/calls.service';

@Component({
    selector: 'user-cmp',
    moduleId: module.id,
    templateUrl: 'add-call.component.html'
})

export class AddCallComponent implements OnInit{
    constructor(private callService:CallsService){

    }
    options:string[];
    type:string="sync";
    showAlert=false;
    errorAddCall=false;
    addCall(form:NgForm):void{
        const formData = new FormData();
        formData.append("startNode",form.value.startNode);
        formData.append("endNode",form.value.endNode);
        formData.append("type",form.value.type);
        if(this.type=='sync'){
            
            formData.append("api",form.value.api);
        }
        else if(this.type=='async'){
           
            formData.append("topic",form.value.topic);
            formData.append("eventProduced",form.value.eventProduced);

        }
        formData.append("description",form.value.description);
        this.callService.addCall(formData).subscribe((res:Call)=>{
            if(res!=null){
            this.showAlert = true;
            setTimeout(() => {
                this.showAlert = false;
            }, 1000);
            form.reset();
            this.type='sync'}
            else{
                form.reset();
                this.type='sync'
                this.errorAddCall=true;
                setTimeout(() => {
                    this.errorAddCall = false;
                }, 2000);
            }
            
        }
            
        );
        
        
    }
    ngOnInit(){
        this.callService.getAllNodesNames().subscribe((res)=>{
            this.options=res;
        });
    }
}
