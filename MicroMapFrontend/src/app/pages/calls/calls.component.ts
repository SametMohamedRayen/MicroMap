import { Call } from 'app/call';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { CallsService } from 'app/calls.service';
import { error } from 'console';



@Component({
    selector: 'calls',
    moduleId: module.id,
    templateUrl: 'calls.component.html',
    styleUrls: ['./calls.component.css']
})

export class CallsComponent implements OnInit {
    calls: Call[] = [];
    deleteId: string = "";
    type: string = "sync";
    id: number;
    api: string;
    topic: string;
    eventProduced: string;
    description: string;
    showAlert = false;
    errorUpdateCall = false;
    showDeleteAlert = false;
    errorLoading =false;
    errorDelete = false;
    
    constructor(private callsService: CallsService) { }
    ngOnInit() {
        this.callsService.getAllCalls().subscribe((response: Call[]) => {
            this.calls = response;
        },
        (error)=>{
            this.errorLoading=true;
            

        }
        );
        
        
        
    }
  
    public updateModel(id: number, type: string, api: string, topic: string, eventProduced: string, description: string): void {
        document.getElementById('updateModel').style.display = 'block';
        this.id = id;
        this.type = type;
        this.api = api;
        this.topic = topic;
        this.eventProduced = eventProduced;
        this.description = description;
    }
    updateCall(): void {
        const formData = new FormData();
        formData.append("type", this.type);
        if (this.type == 'sync') {
            formData.append("api", this.api);
        }
        else if (this.type == 'async') {
            formData.append("topic", this.topic);
            formData.append("eventProduced", this.eventProduced);

        }
        formData.append("description", this.description);
        this.callsService.updateCall(this.id, formData).subscribe((res) => {
            document.getElementById('updateModel').style.display = 'none';
            if (res != null) {
                this.showAlert = true;
                setTimeout(() => {
                    this.showAlert = false;
                }, 1000);
            }
            else {
                this.errorUpdateCall = true;
                setTimeout(() => {
                    this.errorUpdateCall = false;
                }, 2000);
            }
            this.callsService.getAllCalls().subscribe((response: Call[]) => {
                this.calls = response;
            }
            ,
            (error)=>{
                this.errorLoading=true;
            });
            
            
        }
        ,(error)=>{
            this.errorUpdateCall = true;
            setTimeout(() => {
                this.errorUpdateCall = false;
            }, 2000);
        }
        );
    }
    public deleteModel(id: string): void {
        document.getElementById('id01').style.display = 'block';
        this.deleteId = id;
    }
    public deleteCall(): void {

        this.callsService.deleteCall(this.deleteId).subscribe(() => {
            this.showDeleteAlert = true;
            setTimeout(() => {
                this.showDeleteAlert = false;
            }, 1000);
            this.callsService.getAllCalls().subscribe((response: Call[]) => {
                this.calls = response;
            },
            (error)=>{
            this.errorLoading=true;}
            );
        }
        ,(error)=>{
            this.errorDelete=true;
        });
        document.getElementById('id01').style.display = 'none';

    }
    
}
